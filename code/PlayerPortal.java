package cricCamp;  

import java.awt.EventQueue;           
import javax.swing.JFrame;           
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;           
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;           
import javax.swing.JTextField;           
import java.awt.Font;  
import java.awt.Frame;  

import javax.swing.SwingConstants;           
import javax.swing.JTabbedPane;          
import javax.swing.JToolBar;           
import java.awt.Color;                    
import javax.swing.JList;  
import javax.swing.JOptionPane;  
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;          
import java.awt.Button;              
import java.awt.Label;   
import javax.swing.JFormattedTextField;  
import javax.swing.JTextPane; 

public class PlayerPortal extends JFrame {           
	/**            
	 * Launch the application.           
	 */           
	public static void main(String[] args) {           
		EventQueue.invokeLater(new Runnable() {           
			public void run() {           
				try {          
					PlayerPortal frame = new PlayerPortal(LoginPage.user);     
					System.out.println(LoginPage.user);
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
	private JTextField txtPAge;        
	private JTextField txtPPhone;        
	private JTextField txtPDues;      
	private JTextField txtPid;      
	private JTextField txtPswd;   
	private JTextField txtPJersey; 
	private JTextField txtPNewJersey; 
	private JTextField txtTeam1;   
	private JTextField txtTeam2;   
	private JTextField txtDate;   
	private JTextField txtTime; 
	private String playerId;
	private Match selectedMatch;
	
	/**           
	 * Create the frame.          
	 * @throws IOException 
	 */           
	public PlayerPortal(String playerId) throws IOException {
		this.playerId = playerId;
		initialize();
		generatePlayerInfo();
	}

	private void initialize() throws IOException {         
		setResizable(false);         
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
		setBounds(100, 100, 850, 600);          
		Panel1 = new JPanel();           
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));           
		Panel1.setLayout(null);           
		setContentPane(Panel1);          

		//Tool Bar       
		JToolBar toolBar = new JToolBar();           
		toolBar.setFloatable(false);          
		toolBar.setRollover(true);         
		toolBar.setBackground(new Color(100, 149, 237));           
		toolBar.setBounds(0, 0, 850, 25);           
		Panel1.add(toolBar);          
		//Header      
		JLabel lblCCHeader = new JLabel("3 STAR CRICKET CAMP");            
		toolBar.add(lblCCHeader);           
		lblCCHeader.setHorizontalAlignment(SwingConstants.CENTER);           
		lblCCHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));            

		//Main Tabs       
		JTabbedPane PlayerTabbedPane = new JTabbedPane(JTabbedPane.TOP);         
		PlayerTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);           
		PlayerTabbedPane.setToolTipText("");          
		PlayerTabbedPane.setBounds(11, 23, 824, 520);          
		Panel1.add(PlayerTabbedPane);          

		//*********************************************		       
		//*********************************************       
		//******Panel for Players Information**********       
		JPanel PersonalPanel = new JPanel();          
		PlayerTabbedPane.addTab("Personal Info", null, PersonalPanel, null);          
		PersonalPanel.setLayout(null);        
		//Personal Info Label       
		JLabel lblPersonalInfo = new JLabel("PERSONAL INFO");        
		lblPersonalInfo.setBounds(30, 11, 112, 14);        
		PersonalPanel.add(lblPersonalInfo);   
		//<<<<PLAYER INFO PANEL>>>>       
		JPanel PlayerInfoPanel = new JPanel(); 
		PlayerInfoPanel.setBounds(30, 62, 672, 322); 
		PersonalPanel.add(PlayerInfoPanel); 
		PlayerInfoPanel.setLayout(null);        
		//Name Label & Textbox
		JLabel lblPName = new JLabel("Name");        
		lblPName.setBounds(10, 14, 46, 14);        
		PlayerInfoPanel.add(lblPName);             
		txtPName = new JTextField();    
		txtPName.setBounds(106, 11, 86, 20);        
		PlayerInfoPanel.add(txtPName);        
		txtPName.setColumns(10);      
		//ID Label & Textbox
		JLabel lblPid = new JLabel("Player ID");     
		lblPid.setBounds(10, 39, 62, 14);     
		PlayerInfoPanel.add(lblPid);    
		txtPid = new JTextField();     
		txtPid.setEditable(false);     
		txtPid.setColumns(10);     
		txtPid.setBounds(106, 38, 86, 20);     
		PlayerInfoPanel.add(txtPid);  
		//Password Label & Textbox
		JLabel lblPwsd = new JLabel("Password");    
		lblPwsd.setBounds(10, 69, 62, 14);    
		PlayerInfoPanel.add(lblPwsd);  
		txtPswd = new JTextField();    
		txtPswd.setEditable(false);   
		txtPswd.setColumns(10);    
		txtPswd.setBounds(106, 66, 86, 20);    
		PlayerInfoPanel.add(txtPswd);   
		//Phone Num Label& Textbox  
		JLabel lblPPhone = new JLabel("Phone No.");        
		lblPPhone.setBounds(10, 96, 86, 14);        
		PlayerInfoPanel.add(lblPPhone);        
		txtPPhone = new JTextField();    
		txtPPhone.setBounds(106, 93, 86, 20);        
		PlayerInfoPanel.add(txtPPhone);        
		txtPPhone.setColumns(10);
		//Age Label & Textbox   
		JLabel lblPAge = new JLabel("Age");        
		lblPAge.setBounds(10, 121, 46, 14);        
		PlayerInfoPanel.add(lblPAge);             
		txtPAge = new JTextField();
		txtPAge.setBounds(106, 118, 86, 20);        
		PlayerInfoPanel.add(txtPAge);        
		txtPAge.setColumns(10);        
		//Jersey (both) Label, Textbox, Comment
		JLabel lblPJersey = new JLabel("Jersey No.");     
		lblPJersey.setBounds(10, 177, 62, 14);     
		PlayerInfoPanel.add(lblPJersey);  
		txtPJersey = new JTextField(); 
		txtPJersey.setEditable(false); 
		txtPJersey.setColumns(10); 
		txtPJersey.setBounds(106, 177, 86, 20); 
		PlayerInfoPanel.add(txtPJersey); 
		JTextPane txtpnEditJersey = new JTextPane(); 
		txtpnEditJersey.setText("Enter another number if you would like to change your jersey number. $10 will be added to your dues!"); 
		txtpnEditJersey.setBounds(287, 189, 292, 34); 
		PlayerInfoPanel.add(txtpnEditJersey); 
		txtPNewJersey = new JTextField(); 
		txtPNewJersey.setColumns(10); 
		txtPNewJersey.setBounds(106, 208, 86, 20); 
		PlayerInfoPanel.add(txtPNewJersey);  

		Main.allPlayers = FileIO.readPlayersFile();


		//Dues Label & Textbox
		Label lblPDues = new Label("Dues");      
		lblPDues.setBounds(10, 239, 62, 22);      
		PlayerInfoPanel.add(lblPDues);      
		txtPDues = new JTextField();      
		txtPDues.setEditable(false);  
		txtPDues.setBounds(106, 239, 86, 20);      
		PlayerInfoPanel.add(txtPDues);      
		txtPDues.setColumns(10);     

		//Update Button       
		Button btnUpdateInfo = new Button("Enter");          
		btnUpdateInfo.setActionCommand("Enter");  
		btnUpdateInfo.setBounds(106, 280, 86, 22);  
		PlayerInfoPanel.add(btnUpdateInfo);  
		btnUpdateInfo.addActionListener(new ActionListener() {     
			public void actionPerformed(ActionEvent arg0) {  
				if(!(txtPNewJersey.getText().equals(""))) {
					if(Main.checkJerseyNum(txtPNewJersey.getText())) {
						JOptionPane.showMessageDialog(null, "This number is already taken", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						txtPJersey.setText(txtPNewJersey.getText());
						txtPNewJersey.setText("");
						txtPDues.setText(Main.incrementDues(txtPDues.getText()));
						Main.editPlayerInArray(txtPid.getText(), txtPJersey.getText(), txtPDues.getText());
						FileIO.writeToPlayerFile(txtPid.getText(), txtPswd.getText(), txtPName.getText(), txtPAge.getText(), txtPPhone.getText(), txtPJersey.getText(), txtPDues.getText());
					} 
				}
				else {
					Main.editPlayerInArray(txtPid.getText(), txtPJersey.getText(), txtPDues.getText());
					FileIO.writeToPlayerFile(txtPid.getText(), txtPswd.getText(), txtPName.getText(), txtPAge.getText(), txtPPhone.getText(), txtPJersey.getText(), txtPDues.getText());
				}

			}     
		}); 

		//*************************************************       
		//*************************************************       
		//******Panel for Matches Information**********       
		JPanel MatchPanel = new JPanel();        
		PlayerTabbedPane.addTab("Matches", null, MatchPanel, null);       
		MatchPanel.setLayout(null);        
		//Matches Label       
		JLabel lblMatches = new JLabel("MATCHES");        
		lblMatches.setBounds(22, 29, 168, 14);        
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
						selectedMatch.getId();
					}
				}
			}
		});
		matchesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Main.allMatches = FileIO.readMatchesFile();
		updateMatchJList(MatchesList);

		//Match Info Panel
		JTabbedPane EditMatchTabbedPane = new JTabbedPane(JTabbedPane.TOP);    
		EditMatchTabbedPane.setBounds(404, 71, 318, 350);    
		MatchPanel.add(EditMatchTabbedPane);    
		//General Info Tab
		JPanel GenInfoPanel = new JPanel();    
		EditMatchTabbedPane.addTab("General Info", null, GenInfoPanel, null);    
		GenInfoPanel.setLayout(null);   
		//Match Name Label & Textboxes
		JLabel lblTName = new JLabel("Match Name");   
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
		txtTeam1.setEditable(false); 
		txtTeam1.setBounds(107, 28, 86, 20);   
		GenInfoPanel.add(txtTeam1);   
		txtTeam1.setColumns(10);   
		txtTeam2 = new JTextField();   
		txtTeam2.setEditable(false); 
		txtTeam2.setBounds(217, 28, 86, 20);   
		GenInfoPanel.add(txtTeam2);   
		txtTeam2.setColumns(10);   
		JLabel lblVs = new JLabel("vs.");   
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);   
		lblVs.setBounds(195, 31, 23, 14);   
		GenInfoPanel.add(lblVs);   
		//Date Label & Textbox
		JLabel lblTDate = new JLabel("Date");   
		lblTDate.setBounds(10, 79, 46, 14);   
		GenInfoPanel.add(lblTDate);  
		txtDate = new JTextField();   
		txtDate.setEditable(false); 
		txtDate.setBounds(107, 76, 175, 20);   
		GenInfoPanel.add(txtDate);
		//Time Label & Textbox
		JLabel lblTTime = new JLabel("Time");   
		lblTTime.setBounds(10, 120, 46, 14);   
		GenInfoPanel.add(lblTTime);   
		txtTime = new JTextField();   
		txtTime.setEditable(false); 
		txtTime.setBounds(107, 117, 175, 20);   
		GenInfoPanel.add(txtTime);
		
		//Priority Checkbox
		JCheckBox chckbxPriority = new JCheckBox("Priority");  
		chckbxPriority.setBounds(120, 160, 110, 25);  
		GenInfoPanel.add(chckbxPriority); 

		//Register Button
		Button btnRegister = new Button("Register");   
		btnRegister.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent arg0) { 
				System.out.println("The button is clicked!!");
				String selectedMatchName = MatchesList.getSelectedValue();
				for(Match match : Main.allMatches) {
					if ((match.getTeam1()+" vs "+match.getTeam2()).equals(selectedMatchName)) {
				        selectedMatch = match;
				        break;
				}
				}
				if (selectedMatch != null) {
					System.out.println("The selected Match is not NULL");
					String playerId = txtPid.getText();
					boolean selected = chckbxPriority.isSelected();
					selectedMatch.addRegistrant(selected, playerId);
				}
			}     
		});  
		btnRegister.setBounds(107, 206, 86, 22); 
		GenInfoPanel.add(btnRegister); 
		btnRegister.setForeground(Color.BLACK);  

		//Logout Button  
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
	} 

	private void generatePlayerInfo() {
		try (RandomAccessFile file = new RandomAccessFile("C:/Users/indvi/eclipse-workspace/Dossier12/players.txt", "r")) {
			file.readLine();
			String line;
			while ((line = file.readLine()) != null) {
				String[] parts = line.trim().split("\\s+");
				if (parts.length >= 7 && parts[0].equals(playerId)) {
					txtPid.setText(parts[0]);
					txtPswd.setText(parts[1]);
					txtPName.setText(parts[2]);
					txtPAge.setText(parts[3]);
					txtPPhone.setText(parts[4]);
					txtPJersey.setText(parts[5]);
					txtPDues.setText(parts[6]);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: File not found", "Login Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//UPDATE JLIST MATCH NAMES
	public static void updateMatchJList(JList<String> matchesList) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Match match : Main.allMatches) {
			model.addElement(match.getTeam1()+" vs "+match.getTeam2());
		}
		matchesList.setModel(model);
	}
} 
