package cricCamp;  

import java.awt.EventQueue;  
import cricCamp.OrganizerPortal;  
import cricCamp.PlayerPortal;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JTextField;  
import javax.swing.JButton;  
import javax.swing.JSeparator;  
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;  
import javax.swing.SwingConstants;  
import javax.swing.JTextArea;  
import java.awt.Font;  
import javax.swing.JToolBar;  
import java.awt.Color;  
import javax.swing.JPasswordField;  

public class LoginPage {  

	//Initialize content
	private JFrame frame;  
	private JTextField txtUser;  
	private JPasswordField pswdField;  
	public static String user;
	public static String pswd;

	//Launch application  
	public static void main(String[] args) {  
		EventQueue.invokeLater(new Runnable() {  
			public void run() {  
				try {  
					LoginPage window = new LoginPage();  
					window.frame.setVisible(true);  
				} catch (Exception e) {  
					e.printStackTrace();  
				}  
			}  
		});  
	}  

	// Create application  
	public LoginPage() {  

		frame = new JFrame();  
		frame.setBounds(200, 200, 550, 350);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.getContentPane().setLayout(null);  

		//TOOLBAR HEADING  
		JToolBar toolBar = new JToolBar();  
		toolBar.setFloatable(false);  
		toolBar.setRollover(true);  
		toolBar.setBackground(new Color(100, 149, 237));  
		toolBar.setBounds(0, 0, 534, 23);  
		frame.getContentPane().add(toolBar);  

		JLabel lblCCHeader = new JLabel("3 STAR CRICKET CAMP");  
		lblCCHeader.setForeground(new Color(240, 248, 255));  
		lblCCHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));  
		lblCCHeader.setHorizontalAlignment(SwingConstants.CENTER);  
		toolBar.add(lblCCHeader);  

		//LOGIN LABEL & DETAILS  
		JLabel lblLogin = new JLabel("LOGIN HERE");  
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));  
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);  
		lblLogin.setBounds(211, 45, 89, 14);  
		frame.getContentPane().add(lblLogin);  

		JTextArea txtLoginInfo = new JTextArea();  
		txtLoginInfo.setFont(new Font("Monospaced", Font.PLAIN, 11));  
		txtLoginInfo.setWrapStyleWord(true);  
		txtLoginInfo.setLineWrap(true);  
		txtLoginInfo.setColumns(6);  
		txtLoginInfo.setEditable(false);  
		txtLoginInfo.setTabSize(4);  
		txtLoginInfo.setText("Enter the login information provided by the organizer upon registration. Contact the organizer if you have forgotten your credentials.");  
		txtLoginInfo.setBounds(70, 70, 379, 57);  
		frame.getContentPane().add(txtLoginInfo);  

		//USERNAME & PASSWORD LABELS & FIELDS  
		JLabel lbluser = new JLabel("Username");  
		lbluser.setBounds(145, 151, 79, 20);  
		frame.getContentPane().add(lbluser);  

		JLabel lblPswd = new JLabel("Password");  
		lblPswd.setBounds(145, 197, 79, 20);  
		frame.getContentPane().add(lblPswd);  

		txtUser = new JTextField();  
		txtUser.setBounds(234, 151, 173, 20);  
		frame.getContentPane().add(txtUser);  
		txtUser.setColumns(10);  

		pswdField = new JPasswordField();  
		pswdField.setBounds(234, 197, 173, 20);  
		frame.getContentPane().add(pswdField);  

		//SEPARATOR - (VISUAL)  
		JSeparator separator = new JSeparator();  
		separator.setBounds(70, 242, 379, 8);  
		frame.getContentPane().add(separator);  

		//LOGIN BUTTON & VALIDATION  
		JButton btnLogin = new JButton("Login");  
		btnLogin.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {  
				user = txtUser.getText();  
				pswd = pswdField.getText();
				//Check for organizer's credentials  
				if(user.contains("username") && pswd.contains("password")) {  
					JOptionPane.showMessageDialog(null, "Valid Login for Organizer", "Login Successful", JOptionPane.DEFAULT_OPTION);  
					try {
						OrganizerPortal info = new OrganizerPortal();
					} catch (IOException e) {
						e.printStackTrace();
					}  
					OrganizerPortal.main(null);  
				}  
				//check for player's credentials 
				else {
					boolean found = false;
					try(RandomAccessFile file = new RandomAccessFile("C:/Users/indvi/eclipse-workspace/Dossier12/players.txt", "r")){
						file.readLine();
						String line;
						while ((line = file.readLine()) != null) {
							String[] parts = new String(line.getBytes("ISO-8859-1"), "UTF-8").split("\\s+");
							if (parts.length >= 2 && parts[0].equals(user) && parts[1].equals(pswd)) {
								found = true;
								break;
							}
						}
						if (found) {
							JOptionPane.showMessageDialog(null, "Valid Login for Player", "Login Successful", JOptionPane.DEFAULT_OPTION);  
							PlayerPortal info = new PlayerPortal(user);  
							PlayerPortal.main(null);
						} else {
							JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);  
							txtUser.setText(null);  
							pswdField.setText(null);
						}
					} catch(FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Error: File not found", "Login Error", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}  
		});  
		btnLogin.setBounds(211, 257, 89, 23);  
		frame.getContentPane().add(btnLogin);  
	}  
}     
