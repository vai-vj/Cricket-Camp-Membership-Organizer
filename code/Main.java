package cricCamp;
import java.util.ArrayList;

public class Main {
	
	public static ArrayList<Player> allPlayers = new ArrayList<>();
	public static ArrayList<Match> allMatches = new ArrayList<>();

	public static void main(String[] args) {
		FileIO fileIO = new FileIO("players.txt");
		FileIO fileIO2 = new FileIO("matches.txt");
        fileIO.close();
	}
	
	/*FOR TESTING PURPOSES ONLY *********************************************************************
	public static void printFileInfo() {
		for (Player obj: allPlayers) {
			System.out.println(obj.getId() + " " + obj.getPassword() + " " + obj.getName());
		}
		System.out.println();
	}
	*/
	
	//Edit Player Object and ArrayList
	public static void editPlayerInArray(String playerId, String password, String name, String age, String phone, String jersey, String dues) {
		for (Player player : allPlayers) {
			if(player.getId().equals(playerId)) {
				player.setId(playerId);
				player.setPassword(password);
                player.setName(name);
                player.setAge(age);
                player.setPhoneNum(phone);
                player.setJerseyNum(jersey);
                player.setDues(dues);
                break;
			}
		}
	}
	
	public static void editPlayerInArray(String playerId, String jersey, String dues) {
		for (Player player : allPlayers) {
			if(player.getId().equals(playerId)) {
				player.setId(playerId);
                player.setJerseyNum(jersey);
                player.setDues(dues);
                break;
			}
		}
	}
	
	//Delete player object from list using id
	public static void deletePlayerFromArray(String playerId) {
	    Player playerToRemove = null;
	    for (Player player : allPlayers) {
	        if (player.getId().equals(playerId)) {
	            playerToRemove = player;
	            break;
	        }
	    }
	    if (playerToRemove != null)
	        allPlayers.remove(playerToRemove);
	}
	
	//Check if jersey number is taken
	public static boolean checkJerseyNum(String newJerseyNum) {
		boolean found = false;
		for (Player player : allPlayers) {
			if(player.getJerseyNum().equals(newJerseyNum)) {
				found = true;
			}
		}
		return found;
	}
	
	//Add $10 to dues
	public static String incrementDues(String previousDues) {
		String updatedDues = String.valueOf(Integer.parseInt(previousDues)+10);
		return updatedDues;
	}
	
	//Edit Match Object and ArrayList
	public static void editMatchInArray(String matchId, String team1, String team2, String date, String time) {
		for (Match match : allMatches) {
			if(match.getId().equals(matchId)) {
				match.setId(matchId);
				match.setTeam1(team1);
				match.setTeam2(team2);
				match.setDate(date);
				match.setTime(time);
                break;
			}
		}
	}
}
