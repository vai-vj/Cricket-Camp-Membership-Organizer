package cricCamp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.RandomAccessFile;

public class Player implements Comparable<Player>{  
	private String name;  
	private String id;  
	private String password;  
	private String phoneNum;  
	private String age;  
	private String jerseyNum;  
	private String dues;  

	public Player(String name, String id, String password, String phoneNum, String age, String jerseyNum, String dues) {  
		this.name = name;  
		this.id = id;  
		this.password = password;  
		this.phoneNum = phoneNum;  
		this.age = age;  
		this.jerseyNum = jerseyNum;  
		this.dues = dues;		  
	}  

	//Setter Methods
	public String setName(String n) {
		name = n;
		return name;
	}

	public String setId(String i) {
		id = i;
		return id;
	}

	public String setPassword(String pswd) {
		password = pswd;
		return password;
	}

	public String setPhoneNum(String phone) {
		phoneNum = phone;
		return phoneNum;
	}

	public String setAge(String a) {
		age = a;
		return age;
	}

	public String setJerseyNum(String jersey) {
		jerseyNum = jersey;
		return jerseyNum;
	}

	public String setDues(String d) {
		dues = d;
		return dues;
	}


	//Getter Methods
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getAge() {
		return age;
	}

	public String getJerseyNum() {
		return jerseyNum;
	}

	public String getDues() {
		return dues;
	}

	//auto-generate Player ID
	public static String generateId() {
		int i = FileIO.nextIDNum();
		String id = "2024000" + (i);
		return id;
	}

	//auto-generate Player password
	public static String generatePassword() {
		StringBuilder password = new StringBuilder();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 6; i++) {
			int index = (int) (Math.random() * characters.length());
			password.append(characters.charAt(index));
		}
		return password.toString();
	}


	@Override
	public int compareTo(Player otherPlayer) {
		String matchId = OrganizerPortal.getCurrentMatchId();
		String thisPriorityStatus = getPriorityStatus(this.id, matchId);
		String otherPriorityStatus = getPriorityStatus(otherPlayer.id, matchId);

		if (thisPriorityStatus.equals("YES") && !otherPriorityStatus.equals("YES")) {
			return -1; // This player has higher priority
		} else if (!thisPriorityStatus.equals("YES") && otherPriorityStatus.equals("YES")) {
			return 1; // This player has lower priority
		} else {
			// If both have the same priority status, first come first
			int thisIdIndex =-1;
			try {
				thisIdIndex = findIdIndex(this.id, matchId);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int otherIdIndex=-1;
			try {
				otherIdIndex = findIdIndex(otherPlayer.id, matchId);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (thisIdIndex < otherIdIndex)
				return -1;
			else if (thisIdIndex > otherIdIndex)
				return 1;
			else
				return 0;
		}
	}
	
	private int findIdIndex(String playerId, String matchId) throws IOException {
		String filename = "match_" + matchId + ".txt";
		try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
			file.seek(0);
			String line;
			int position =0;
			while ((line = file.readLine())!= null) {
				String[] parts = line.trim().split("\\s+");
				if(parts[1].equals(playerId)) {
					return position;
				}
				position = (int) file.getFilePointer();
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return -1;
	}

	private String getPriorityStatus(String playerId, String matchId) {
		String priorityStatus = "NO";
		String filename = "match_" + matchId + ".txt";
		try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
			file.seek(0);
			String line;
			while ((line = file.readLine())!= null) {
				if (line.endsWith(playerId)) {
					priorityStatus = line.substring(0,2);
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return priorityStatus;
	}
}



