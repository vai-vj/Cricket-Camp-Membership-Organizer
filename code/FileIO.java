package cricCamp; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileIO { 
	private RandomAccessFile file;
	public FileIO(String filename) {
		try {
			file = new RandomAccessFile("C:/Users/indvi/eclipse-workspace/Dossier12/" + filename, "rw");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//PLAYERS
	//transferring previous players to arraylist
	public static ArrayList<Player> readPlayersFile() throws IOException{
		ArrayList<Player> players = new ArrayList<>();
		try(RandomAccessFile file = new RandomAccessFile("players.txt", "r")){
			file.readLine();
			file.readLine();

			String line;
			while ((line = file.readLine()) != null) {
				String[] values = line.trim().split("\\s+");

				String id = values[0];
				String pswd = values[1];
				String name = values[2];
				String age = values[3];
				String phone = values[4];
				String jersey = values[5];
				String dues = values[6];
				
				//Testing
				for (String s: values) {
					System.out.print(s + " ");
				}
				System.out.println();

				Player player = new Player(name, id, pswd, phone, age, jersey, dues);
				players.add(player);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return players;
	}

	//Write id& password to file when CREATING new player
	public static void writeToPlayerFile(String playerId, String password) {
		try (RandomAccessFile file = new RandomAccessFile("players.txt", "rw")){
			long fileLength = file.length();
			file.seek(fileLength);
			file.writeBytes("\n" + playerId + "  " + password);
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	//Write to file& edit arraylist& player object when UPDATING
	public static void writeToPlayerFile(String playerId, String password, String name, String age, String phone, String jersey, String dues) {
		try (RandomAccessFile file = new RandomAccessFile("players.txt", "rw")){
			String line;
			long currentPosition = 0;
			while ((line = file.readLine()) != null) {
				String[] values = line.trim().split("\\s+");
				String id = values[0];

				if(playerId.equals(id)) {
					file.seek(currentPosition);
					file.writeBytes(playerId + "  " + password + "  " + name + "  " + age + "  " + phone + "  " + jersey + "  " + dues);
					break;
				}
				currentPosition = file.getFilePointer();
				System.out.println();
			}
			file.close();
			Main.editPlayerInArray(playerId, password, name, age, phone, jersey, dues);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	//Delete player from file
	public static void deletePlayer(String deletePlayerID) {
		try (RandomAccessFile inputFile = new RandomAccessFile("players.txt", "rw");
				RandomAccessFile tempFile = new RandomAccessFile("temp_players.txt", "rw")) {

			String line;
			while ((line = inputFile.readLine()) != null) {
				String[] values = line.trim().split("\\s+");
				String id = values[0];

				if (deletePlayerID.equals(id)) {
					continue;
				}
				tempFile.writeBytes(line);

				// Check if there are more lines to be processed
				if (inputFile.getFilePointer() < inputFile.length()) {
					tempFile.writeBytes(System.lineSeparator());
				}
			}

			// Close both files
			inputFile.close();
			tempFile.close();

			//Delete original file& replace with temporary file
			File originalFile = new File("players.txt");
			File tempFileRenamed = new File("temp_players.txt");

			if (!originalFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			if (!tempFileRenamed.renameTo(originalFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Determine ID number for next player
	public static int nextIDNum() {
		int lastIDNum = -1;

		try (RandomAccessFile file = new RandomAccessFile("players.txt", "r")) {
			String lastLine;
			long length = file.length();

			//Start from the end of file
			for (long pointer = length - 1; pointer >= 0; pointer--) {
				file.seek(pointer);
				char c = (char) file.readByte();

				if (c == '\n') {
					lastLine = file.readLine();
					String[] parts = lastLine.split("\\s+");
					String playerId = parts[0];

					if (playerId.startsWith("2024000") && playerId.length() > 7) {
						String digitsAfterPrefix = playerId.substring(7);
						lastIDNum = Integer.parseInt(digitsAfterPrefix);
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastIDNum+1;
	}

	//MATCHES
	//transferring previous matches to arraylist
	public static ArrayList<Match> readMatchesFile() throws IOException{
		ArrayList<Match> matches = new ArrayList<>();
		try(RandomAccessFile file = new RandomAccessFile("matches.txt", "r")){
			file.readLine();
			file.readLine();

			String line;
			while ((line = file.readLine()) != null) {
				String[] values = line.trim().split("\\s+");

				String id = values[0];
				String team1 = values[1];
				String team2 = values[2];
				String date = values[3];
				String time = values[4];
				
				//Testing
				for (String s: values) {
					System.out.print(s + " ");
				}
				System.out.println();

				Match match = new Match(id, team1, team2, date, time);
				matches.add(match);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return matches;
	}
	
	//Write match details to file
	public static void writeToMatchFile(String id) {
		try (RandomAccessFile file = new RandomAccessFile("matches.txt", "rw")){
			long fileLength = file.length();
			file.seek(fileLength);
			file.writeBytes("\n" + id);
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	//Write to file& edit arraylist& match object when UPDATING
	public static void writeToMatchFile(String matchId, String team1, String team2, String date, String time) {
		try (RandomAccessFile file = new RandomAccessFile("matches.txt", "rw")){
			String line;
			long currentPosition = 0;
			while ((line = file.readLine()) != null) {
				String[] values = line.trim().split("\\s+");
				String id = values[0];

				if(matchId.equals(id)) {
					file.seek(currentPosition);
					file.writeBytes(id + "  " + team1 + "  " + team2 +"  " + date +"  " + time);
					break;
				}
				currentPosition = file.getFilePointer();
			}
			file.close();
			Main.editMatchInArray(matchId, team1, team2, date, time);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	//Determine ID number for next match
	public static int nextMatchIDNum() {
		int lastIDNum = -1;
		try (RandomAccessFile file = new RandomAccessFile("matches.txt", "r")) {
			String lastLine;
			long length = file.length();

			//Start from the end of file
			for (long pointer = length - 1; pointer >= 0; pointer--) {
				file.seek(pointer);
				char c = (char) file.readByte();

				if (c == '\n') {
					lastLine = file.readLine();
					String[] parts = lastLine.split("\\s+");
					String matchId = parts[0];

					if (matchId.startsWith("2024010") && matchId.length() > 7) {
						String digitsAfterPrefix = matchId.substring(7);
						lastIDNum = Integer.parseInt(digitsAfterPrefix);
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastIDNum+1;
	}

	public void close() {
		try {
			file.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
} 


