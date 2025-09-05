package cricCamp;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Match {    
	private String id;  
	private String team1;  
	private String team2;  
	private String date;  
	private String time;   
	private ArrayList<String> allRegistrants;

	public Match(String id, String team1, String team2, String date, String time) {   
		this.id = id;  
		this.team1 = team1;  
		this.team2 = team2;  
		this.date = date;  
		this.time = time;  	  
		allRegistrants = new ArrayList<>();
	}  
	
	//Setter Methods
	public String setId(String i) {
		id = i;
		return id;
	}
	public String setTeam1(String t1) {
		team1 = t1;
		return team1;
	}
	public String setTeam2(String t2) {
		team2 = t2;
		return team2;
	}
	public String setDate(String d) {
		date = d;
		return date;
	}
	public String setTime(String t) {
		time = t;
		return time;
	}
	
	//Getter Methods
	public String getId() {
		return id;
	}
	public String getTeam1() {
		return team1;
	}
	public String getTeam2() {
		return team2;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	
	//auto-generate password upon creating match
	public static String generateId() {
		int i = FileIO.nextMatchIDNum();
		String id = "2024010" + (i);
		return id;
	}
	
	//add registrant to file
	public void addRegistrant(Boolean p, String playerId) {
		String priority = "NO";
		if (p==true){
			priority = "YES";
		}
		
		String filename = "match_" + id + ".txt";
		try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
			file.seek(file.length());
                file.writeBytes(priority + " " + playerId + "\n");
		} catch (IOException e) {
            System.out.println("Error saving registrants to file: " + e.getMessage());
        }
	}
}


