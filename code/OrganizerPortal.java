package cricCamp;  

import java.awt.BorderLayout;           
import java.awt.EventQueue;           
import javax.swing.JFrame;           
import javax.swing.JPanel;          
import javax.swing.border.EmptyBorder;           
import javax.swing.JLabel;           
import javax.swing.JButton;            
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;           
import javax.swing.JTextField;           
import java.awt.Font;  
import java.awt.Frame;  

import javax.swing.SwingConstants;           
import javax.swing.JPasswordField;           
import javax.swing.JTabbedPane;          
import javax.swing.JToolBar;           
import java.awt.Color;           
import javax.swing.JTextArea;          
import javax.swing.AbstractAction;          
import javax.swing.Action;          
import java.awt.List;          
import java.awt.Choice;          
import javax.swing.JRadioButton;          
import javax.swing.JComboBox;          
import javax.swing.JCheckBox;          
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Calendar;
import java.util.Collections;
import java.util.ArrayList;

import javax.swing.JList;  
import javax.swing.JOptionPane;  
import javax.swing.AbstractListModel;          
import javax.swing.ListSelectionModel;          
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Button;          
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Dialog.ModalExclusionType;      
import java.awt.Label;   
import javax.swing.JFormattedTextField;  
import javax.swing.JSeparator; 
import java.awt.SystemColor; 
import javax.swing.JTextPane; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel; 
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;         

public class OrganizerPortal extends JFrame {           
	/**            
	 * Launch the application.           
	 */           
	public static void main(String[] args) {           
		EventQueue.invokeLater(new Runnable() {           
			public void run() {           
				try {          
					OrganizerPortal frame = new OrganizerPortal();           
					frame.setVisible(true);           
				} catch (Exception e) {           
					e.printStackTrace();           
				}           
			}           
		});           
	}          

	//Initialize content
	private JPanel Panel1;          
	private JTextField txtPName;       
	private JTextField txtPId;         
	private JTextField txtPPswd;       
	private JTextField txtPPhone;   
	private JTextField txtPAge;     
	private JTextField txtPJersey;        
	private JTextField txtPDues;     
	private JTextField txtTeam1;   
	private JTextField txtTeam2;   
	private JTextField txtDate;   
	private JTextField txtTime;  
	private JTextField txtMId; 
	private JTable table; 
	static JList<String> PlayersList;
	static JList<String> MatchesList;
	private JTextPane txtpnPlayer;
	private static String currentMatchId;

	/**           
	 * Create the frame.          
	 * @throws IOException 
	 */           
	public OrganizerPortal() throws IOException {         
		setResizable(false);         
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
		setBounds(100, 100, 850, 600);          
		Panel1 = new JPanel();           
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));           
		Panel1.setLayout(null);           
		setContentPane(Panel1);          
		//Tool Bar       
		JToolBar toolBar = new JToolBar();  
		toolBar.setRollover(true);  
		toolBar.setBackground(new Color(100, 149, 237));           
		toolBar.setBounds(0, 0, 850, 25);           
		Panel1.add(toolBar);          
		//Header      
		JLabel lblCCHeader = new JLabel("3 STAR CRICKET CAMP");            
		toolBar.add(lblCCHeader);           
		lblCCHeader.setHorizontalAlignment(SwingConstants.CENTER);           
		lblCCHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));  
		//MAIN TABS       
		JTabbedPane OrganizerTabbedPane = new JTabbedPane(JTabbedPane.TOP);         
		OrganizerTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);           
		OrganizerTabbedPane.setToolTipText("");          
		OrganizerTabbedPane.setBounds(10, 23, 825, 520);          
		Panel1.add(OrganizerTabbedPane);          
		//*********************************************		       
		//*********************************************      
		//******Panel for Players Information**********       
		JPanel PlayersPanel = new JPanel();          
		OrganizerTabbedPane.addTab("PlayersTab", null, PlayersPanel, null);          
		PlayersPanel.setLayout(null);        
		//Players Label       
		JLabel lblPlayers = new JLabel("PLAYERS");        
		lblPlayers.setBounds(31, 11, 93, 14);        
		PlayersPanel.add(lblPlayers);  

		//Players List with ScrollBar
		JScrollPane playersScrollPane = new JScrollPane();
		playersScrollPane.setBounds(30, 62, 275, 346);
		PlayersPanel.add(playersScrollPane);

		JList<String> PlayersList = new JList<>();
		playersScrollPane.setViewportView(PlayersList);
		PlayersList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PlayersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PlayersList.setForeground(Color.BLACK);
		PlayersList.setBackground(Color.LIGHT_GRAY);

		//Selecting player in List
		PlayersList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedPlayerName = PlayersList.getSelectedValue();
					Player selectedPlayer = null;
					for (Player player : Main.allPlayers) {
						if (player.getName().equals(selectedPlayerName)) {
							selectedPlayer = player;
							break;
						}
					}
					// Display selected player's info in textfields
					if (selectedPlayer != null) {
						txtPName.setText(selectedPlayer.getName());
						txtPAge.setText(selectedPlayer.getAge());
						txtPPhone.setText(selectedPlayer.getPhoneNum());
						txtPId.setText(selectedPlayer.getId());
						txtPJersey.setText(selectedPlayer.getJerseyNum());
						txtPDues.setText(selectedPlayer.getDues());
						txtPPswd.setText(selectedPlayer.getPassword());
					}
				}
			}
		});
		playersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Main.allPlayers = FileIO.readPlayersFile();
		updatePlayerJList(PlayersList);

		//<<<<PLAYER INFO PANEL>>>>       
		JPanel PlayerInfoPanel = new JPanel(); 
		PlayerInfoPanel.setBackground(SystemColor.activeCaptionBorder); 
		PlayerInfoPanel.setBounds(466, 62, 301, 322); 
		PlayersPanel.add(PlayerInfoPanel); 
		PlayerInfoPanel.setLayout(null);        
		//Name Label & textbox
		JLabel lblPName = new JLabel("Name");        
		lblPName.setBounds(10, 14, 46, 14);        
		PlayerInfoPanel.add(lblPName);     
		txtPName = new JTextField();    
		txtPName.setBounds(106, 12, 151, 20);        
		PlayerInfoPanel.add(txtPName);        
		txtPName.setColumns(10);       
		//ID Label & Textbox  
		JLabel lblPId = new JLabel("Member ID");     
		lblPId.setBounds(10, 39, 62, 14);     
		PlayerInfoPanel.add(lblPId);    
		txtPId = new JTextField();     
		txtPId.setEditable(false);     
		txtPId.setColumns(10);     
		txtPId.setBounds(106, 38, 151, 20);     
		PlayerInfoPanel.add(txtPId);  
		//Password Label & Textbox
		JLabel lblPwsd = new JLabel("Password");    
		lblPwsd.setBounds(10, 69, 62, 14);    
		PlayerInfoPanel.add(lblPwsd);    
		txtPPswd = new JTextField();    
		txtPPswd.setEditable(false);   
		txtPPswd.setColumns(10);    
		txtPPswd.setBounds(106, 66, 151, 20);    
		PlayerInfoPanel.add(txtPPswd); 
		//Phone Num Label & Textbox       
		JLabel lblPPhone = new JLabel("Phone No.");        
		lblPPhone.setBounds(10, 96, 86, 14);        
		PlayerInfoPanel.add(lblPPhone);               
		txtPPhone = new JTextField();   
		txtPPhone.setBounds(106, 93, 151, 20);        
		PlayerInfoPanel.add(txtPPhone);        
		txtPPhone.setColumns(10);  
		//Age Label & Textbox    
		JLabel lblPAge = new JLabel("Age");        
		lblPAge.setBounds(10, 121, 86, 14);        
		PlayerInfoPanel.add(lblPAge);    
		txtPAge = new JTextField();   
		txtPAge.setBounds(106, 118, 151, 20);        
		PlayerInfoPanel.add(txtPAge);        
		txtPAge.setColumns(10);        
		//Jersey Num Label & Textbox  
		JLabel lblPJersey = new JLabel("Jersey No.");     
		lblPJersey.setBounds(10, 177, 86, 14);     
		PlayerInfoPanel.add(lblPJersey);     
		txtPJersey = new JTextField();     
		txtPJersey.setEditable(false);     
		txtPJersey.setColumns(10);     
		txtPJersey.setBounds(106, 177, 151, 20);     
		PlayerInfoPanel.add(txtPJersey);  
		//Dues Label & Textbox
		JLabel lblPDues = new JLabel("Dues"); 
		lblPDues.setBounds(10, 211, 86, 14); 
		PlayerInfoPanel.add(lblPDues);
		txtPDues = new JTextField();      
		txtPDues.setEditable(false);
		txtPDues.setBounds(106, 208, 151, 20);      
		PlayerInfoPanel.add(txtPDues);    
		txtPDues.setColumns(10);  

		//*************************************************       
		//*************************************************       
		//******Panel for Match Information**********       
		JPanel MatchPanel = new JPanel();        
		OrganizerTabbedPane.addTab("MatchTab", null, MatchPanel, null);       
		MatchPanel.setLayout(null);        
		//Match Label       
		JLabel lblMatches = new JLabel("MATCHES");        
		lblMatches.setBounds(22, 11, 168, 14);        
		MatchPanel.add(lblMatches);    

		//Matches List with ScrollBar
		JScrollPane matchesScrollPane = new JScrollPane();
		matchesScrollPane.setBounds(30, 62, 275, 346);
		MatchPanel.add(matchesScrollPane);

		JList<String> MatchesList = new JList<>();
		matchesScrollPane.setViewportView(MatchesList);
		MatchesList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MatchesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MatchesList.setForeground(Color.BLACK);
		MatchesList.setBackground(Color.LIGHT_GRAY);

		//Selecting match in List
		MatchesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedMatchName = MatchesList.getSelectedValue();
					Match selectedMatch = null;
					for (Match match : Main.allMatches) {
						if ((match.getTeam1()+" vs "+match.getTeam2()).equals(selectedMatchName)) {
							selectedMatch = match;
							break;
						}
					}
					// Display selected player's info in textfields
					if (selectedMatch != null) {
						txtTeam1.setText(selectedMatch.getTeam1());
						txtTeam2.setText(selectedMatch.getTeam2());
						txtDate.setText(selectedMatch.getDate());
						txtTime.setText(selectedMatch.getTime());
						txtMId.setText(selectedMatch.getId());

						setCurrentMatchId(selectedMatch.getId());

						//Update registrant display
						String filename = "match_" + selectedMatch.getId() + ".txt";
						try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
							StringBuilder registrants = new StringBuilder();
							String line;
							while ((line = file.readLine()) != null) {
								String[] parts = line.split(" ");
								String playerId = parts[1];
								for (Player player : Main.allPlayers) {
									if (player.getId().equals(playerId)) {
										registrants.append(player.getName()).append("     ");
										break;
									}
								}
							}
							txtpnPlayer.setText(registrants.toString());
						} catch (IOException ex) {
							txtpnPlayer.setText("");
							System.out.println("Error reading registrants from file: " + ex.getMessage());
						}
					}
				}
			}
		});
		matchesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Main.allMatches = FileIO.readMatchesFile();
		updateMatchJList(MatchesList);    

		//Matches Tab
		JTabbedPane EditMatchTabbedPane = new JTabbedPane(JTabbedPane.TOP);    
		EditMatchTabbedPane.setBounds(404, 71, 318, 350);    
		MatchPanel.add(EditMatchTabbedPane);    
		//<<<General Info Tab>>>
		JPanel GenInfoPanel = new JPanel();    
		EditMatchTabbedPane.addTab("General Info", null, GenInfoPanel, null);    
		GenInfoPanel.setLayout(null);   
		//Team Name Labels & textboxes
		JLabel lblTName = new JLabel("Team Names");   
		lblTName.setBounds(10, 31, 95, 14);   
		GenInfoPanel.add(lblTName);   
		JLabel lblTeam1 = new JLabel("Team 1");   
		lblTeam1.setBounds(130, 11, 46, 14);   
		GenInfoPanel.add(lblTeam1);   
		JLabel lblTeam2 = new JLabel("Team 2");   
		lblTeam2.setHorizontalAlignment(SwingConstants.CENTER);   
		lblTeam2.setBounds(236, 11, 46, 14);   
		GenInfoPanel.add(lblTeam2);   
		txtTeam1 = new JTextField();   
		txtTeam1.setBounds(107, 28, 86, 20);   
		GenInfoPanel.add(txtTeam1);   
		txtTeam1.setColumns(10);   
		txtTeam2 = new JTextField();   
		txtTeam2.setBounds(217, 28, 86, 20);   
		GenInfoPanel.add(txtTeam2);   
		txtTeam2.setColumns(10);   
		JLabel lblVs = new JLabel("vs.");   
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);   
		lblVs.setBounds(195, 31, 23, 14);   
		GenInfoPanel.add(lblVs);   
		//Date label & textbox
		JLabel lblTDate = new JLabel("Date");   
		lblTDate.setBounds(10, 79, 46, 14);   
		GenInfoPanel.add(lblTDate);   
		txtDate = new JTextField();   
		txtDate.setBounds(107, 76, 175, 20);   
		GenInfoPanel.add(txtDate);   
		txtDate.setColumns(10); 
		//Time label & textbox
		JLabel lblTTime = new JLabel("Time");   
		lblTTime.setBounds(10, 120, 46, 14);   
		GenInfoPanel.add(lblTTime);   
		txtTime = new JTextField();   
		txtTime.setBounds(107, 117, 175, 20);   
		GenInfoPanel.add(txtTime);   
		txtTime.setColumns(10);  
		//ID label & textbox  
		JLabel lblMId = new JLabel("Match ID");     
		lblMId.setBounds(10, 161, 50, 14);     
		GenInfoPanel.add(lblMId);    
		txtMId = new JTextField();     
		txtMId.setEditable(false);     
		txtMId.setColumns(10);     
		txtMId.setBounds(106, 158, 175, 20);     
		GenInfoPanel.add(txtMId);  
		//<<<Registrant Tab>>>
		JPanel RegistrantPanel = new JPanel();   
		EditMatchTabbedPane.addTab("Registrants", null, RegistrantPanel, null);   
		RegistrantPanel.setLayout(null); 
		//Player List TextPane
		txtpnPlayer = new JTextPane(); 
		txtpnPlayer.setText("");     
		txtpnPlayer.setEditable(false);
		txtpnPlayer.setBounds(24, 49, 260, 197); 
		RegistrantPanel.add(txtpnPlayer); 
		//<<<Teams Tab>>>
		JPanel TeamPanel = new JPanel();   
		EditMatchTabbedPane.addTab("Teams", null, TeamPanel, null);   
		TeamPanel.setLayout(null); 
		//Teams with Players Table
		table = new JTable(); 
		table.setBorder(null); 
		table.setToolTipText(""); 
		table.setShowHorizontalLines(false); 
		table.setRowSelectionAllowed(false); 
		table.setBounds(50, 57, 216, 240); 
		TeamPanel.add(table); 
		table.setModel(new DefaultTableModel( 
				new Object[][] { 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
					{null, null}, 
				}, 
				new String[] { 
						"Team 1", "Team 2" 
				} 
				)); 
		table.getColumnModel().getColumn(0).setPreferredWidth(120); 
		table.getColumnModel().getColumn(1).setPreferredWidth(120);  

		//LOGOUT BUTTON 
		JButton btnLogOut = new JButton("Log Out");  
		btnLogOut.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {  
				Frame frmOrganizer = new Frame("Exit");  
				if (JOptionPane.showConfirmDialog(frmOrganizer, "Confirm if you want to log out", "Login Systems", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){    
					System.exit(0);    
				}  
			}  
		});  
		btnLogOut.setBounds(746, 548, 85, 20);  
		Panel1.add(btnLogOut);  

		//ADD NEW PLAYER BUTTON
		Button btnAddP = new Button("Add New Player");     
		btnAddP.setForeground(new Color(34, 139, 34));     
		btnAddP.addActionListener(new ActionListener() {     
			public void actionPerformed(ActionEvent arg0) {   
				//Clear all fields, generate new id& pswd
				txtPName.setText("");
				txtPId.setText(Player.generateId());
				txtPPswd.setText(Player.generatePassword());
				txtPPhone.setText("");
				txtPAge.setText("");
				txtPJersey.setText("--");
				txtPDues.setText("0");

				//Retrieve player info (all empty except id & password)
				String name = txtPName.getText();
				String age = txtPAge.getText();
				String phone = txtPPhone.getText();
				String id = txtPId.getText();
				String password = txtPPswd.getText();
				String jersey = txtPJersey.getText();
				String dues = txtPDues.getText();

				//Create a new player object
				Player player = new Player(name, id, password, phone, age, jersey, dues);

				//Add player to the list
				Main.allPlayers.add(player);
				updatePlayerJList(PlayersList);

				//Update file
				FileIO.writeToPlayerFile(id, password);
			}     
		});     
		btnAddP.setBounds(110, 428, 93, 22);     
		PlayersPanel.add(btnAddP);

		//UPDATE PLAYER INFO BUTTON       
		Button btnUpdateP = new Button("Update");          
		btnUpdateP.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent arg0) {     
				// Retrieve player
				String name = txtPName.getText();
				String age = txtPAge.getText();
				String phone = txtPPhone.getText();
				String id = txtPId.getText();
				String password = txtPPswd.getText();
				String jersey = txtPJersey.getText();
				String dues = txtPDues.getText();

				//Update player object, file, arrayList
				Main.editPlayerInArray(id, password, name, age, phone, jersey, dues);
				FileIO.writeToPlayerFile(id, password, name, age, phone, jersey, dues);
				updatePlayerJList(PlayersList);
			}     
		});     
		btnUpdateP.setBounds(547, 428, 70, 22);          
		PlayersPanel.add(btnUpdateP);     

		//DELETE PLAYER BUTTON
		Button btnDeleteP = new Button("Delete");     
		btnDeleteP.setForeground(Color.RED);    
		btnDeleteP.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent arg0) {     
				// Retrieve player info
				String id = txtPId.getText();

				//Update file & arrayList
				FileIO.deletePlayer(id);
				Main.deletePlayerFromArray(id);
				updatePlayerJList(PlayersList);

				//Clear all fields
				txtPName.setText("");
				txtPId.setText("");
				txtPPswd.setText("");
				txtPPhone.setText("");
				txtPAge.setText("");
				txtPJersey.setText("");
				txtPDues.setText("");
			}     
		});  
		btnDeleteP.setBounds(637, 428, 70, 22);     
		PlayersPanel.add(btnDeleteP);    

		//CLEAR PLAYER DUES BUTTON
		JButton btnClearDues = new JButton("Clear Dues");   
		btnClearDues.addActionListener(new ActionListener() {     
			public void actionPerformed(ActionEvent arg0) {     
				Frame frmOrganizer = new Frame("Exit");  
				if (JOptionPane.showConfirmDialog(frmOrganizer, "Confirm if you want to clear dues", "Confirmation Message", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){    
					txtPDues.setText("0   ");
				} 
			}     
		}); 
		btnClearDues.setBounds(126, 239, 111, 22);     
		PlayerInfoPanel.add(btnClearDues); 

		//ADD NEW MATCH BUTTON
		Button btnAddMatch = new Button("Add New Match");   
		btnAddMatch.setForeground(new Color(34, 139, 34));   
		btnAddMatch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {   
				//Clear all fields
				txtTeam1.setText("");
				txtTeam2.setText("");
				txtDate.setText("");
				txtTime.setText("");
				txtMId.setText(Match.generateId());

				//Retrieve match info, generate id
				String team1 = txtTeam1.getText();
				String team2 = txtTeam2.getText();
				String date = txtDate.getText();
				String time = txtTime.getText();
				String id = txtMId.getText();

				//Create a new match object
				Match match = new Match(id, team1, team2, date, time);

				//Add match to the list
				Main.allMatches.add(match);
				updateMatchJList(MatchesList);

				//Update file
				FileIO.writeToMatchFile(id);
			}  
		}); 
		btnAddMatch.setBounds(99, 427, 131, 22);   
		MatchPanel.add(btnAddMatch); 

		//UPDATE MATCH INFO BUTTON       
		Button btnUpdateM = new Button("Update");          
		btnUpdateM.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent arg0) {     
				//Retrieve match info
				String id = txtMId.getText();
				String team1 = txtTeam1.getText();
				String team2 = txtTeam2.getText();
				String date = txtDate.getText();
				String time = txtTime.getText();

				//Update player object, file, arrayList
				Main.editMatchInArray(id, team1, team2, date, time);
				FileIO.writeToMatchFile(id, team1, team2, date, time);
				updateMatchJList(MatchesList);
			}     
		});     
		btnUpdateM.setBounds(547, 428, 70, 22);          
		MatchPanel.add(btnUpdateM);     

		//FORM TEAMS BUTTON
		JButton btnFormTeams = new JButton("FORM TEAMS"); 
		btnFormTeams.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) { 
				String selectedMatchId = txtMId.getText();
				ArrayList<String> registeredPlayerIds = readRegisteredPlayerIds(selectedMatchId);
				PlayerPriorityQueue playerQueue = new PlayerPriorityQueue();

				//add priority players first
				for (Player player : Main.allPlayers) {
					if (registeredPlayerIds.contains(player.getId())) {
						playerQueue.enqueue(player);
					}
				}

				//Get the top 22 players from the queue
				java.util.List<Player> selectedPlayers = playerQueue.getTopPlayers(22);
				Collections.shuffle(selectedPlayers);

				// Divide players into two teams
				ArrayList<String> team1 = new ArrayList<>();
				ArrayList<String> team2 = new ArrayList<>();

				if(selectedPlayers.size() < 22) {
					JOptionPane.showMessageDialog(null, "There are not enough registered players for this match.");
					return;
				}
				else {
					for (int i = 0; i < 22; i++) {
						if(i<11)
							team1.add(selectedPlayers.get(i).getName());
						else
							team2.add(selectedPlayers.get(i).getName());
					}
				}

				// Display teams in the table
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				for (int i = 0; i < 11; i++) {
					tableModel.addRow(new Object[]{team1.get(i), team2.get(i)});
				}
			} 
		}); 
		btnFormTeams.setBounds(91, 11, 137, 23); 
		TeamPanel.add(btnFormTeams); 
	}           

	public static void setCurrentMatchId(String matchId) {
		currentMatchId = matchId;
	}

	public static String getCurrentMatchId() {
		return currentMatchId;
	}

	//UPDATE JLIST PLAYER NAMES
	public static void updatePlayerJList(JList<String> playersList) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Player player : Main.allPlayers) {
			model.addElement(player.getName());
		}
		playersList.setModel(model);
	}

	//UPDATE JLIST MATCH NAMES
	public static void updateMatchJList(JList<String> matchesList) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Match match : Main.allMatches) {
			model.addElement(match.getTeam1()+" vs "+match.getTeam2());
		}
		matchesList.setModel(model);
	}

	// Method to read registered player IDs from a file
	private ArrayList<String> readRegisteredPlayerIds(String matchId) {
		ArrayList<String> registeredPlayerIds = new ArrayList<>();
		String filename = "match_" + matchId + ".txt";
		try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
			String line;
			while ((line = file.readLine()) != null) {
				String[] parts = line.trim().split("\\s+");
				registeredPlayerIds.add(parts[1].trim());
			}
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return registeredPlayerIds;
	}
} 
