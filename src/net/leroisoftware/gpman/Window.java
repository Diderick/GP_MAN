package net.leroisoftware.gpman;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JMenuBar;

public class Window {

	private JFrame frmGpMan;
	Player playerGUI = new Player();
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    CreateNewTable.createNewTable();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmGpMan.setVisible(true);
					//Connects to database or create if it does not exist

				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}
	
	public JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 534, 22);
		frmGpMan.getContentPane().add(menuBar);
		
		
				JMenu mnuFile = new JMenu("File", true);
				mnuFile.setBounds(335, 80, 31, 20);
				menuBar.add(mnuFile);
				
				JMenuItem mnuFileNew = new JMenuItem("Player");
				mnuFile.add(mnuFileNew);
				
				JMenuItem mnuFileExit = new JMenuItem("Exit");
				mnuFile.add(mnuFileExit);
				
				JMenu mnuReports = new JMenu("Reports", true);
				menuBar.add(mnuReports);
				
				JMenu mnNewMenu = new JMenu("Tools");
				menuBar.add(mnNewMenu);
				
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Export Databse");
				mnNewMenu.add(mntmNewMenuItem_1);
				
				JMenuItem mntmNewMenuItem = new JMenuItem("Year End Process");
				mnNewMenu.add(mntmNewMenuItem);
				
				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Import Database");
				mnNewMenu.add(mntmNewMenuItem_2);
				
				JMenu mnuHelp = new JMenu("Help", true);
				menuBar.add(mnuHelp);
				
				JMenu mnuAbout = new JMenu("About", true);
				menuBar.add(mnuAbout);
		
		return menuBar;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGpMan = new JFrame();
		frmGpMan.setTitle("GP Man");
		frmGpMan.setBounds(200, 200, 550, 400);
		frmGpMan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGpMan.getContentPane().setLayout(null);
		
		createMenuBar();
		
		
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerGUI.setVisible(true);
			}
		});
		btnNewButton.setBounds(435, 327, 89, 23);
		frmGpMan.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(336, 327, 89, 23);
		frmGpMan.getContentPane().add(btnNewButton_1);
		
		

     
     
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 111, 461, 200);
		frmGpMan.getContentPane().add(scrollPane);
		
				
				table = new JTable();
				table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Kundan Kumar Jha", "4031", "CSE", null},
						{"Anand Jha", "6014", "IT", null},
					},
					new String[] {
						"BSA Number", "Name", "Surname", "ID Number"
					}
					
				));
				table.setBounds(131, 148, 393, 163);
				scrollPane.setViewportView(table);
				
				
				
		
	
		
		
	}
}
