package net.leroisoftware.gpman;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class Player extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField bsaNumberTxtField;
	private JTextField nameTxtField;
	private JTextField surnameTxtField;
	private JTextField idNumTextField;
	private JTextField ageTxtField;
	private JTextField provinceTxtField;
	private JTextField clubTxtField;
	private JTextField roleTextField;
	private JTextField raceTxtField;
	private JComboBox<String> comboBoxGender;
	static Player dialog;
	int deletePlayer_Id;
	static ArrayList<String> playerSearch = new ArrayList<String>();
	static ArrayList<String> tableData = new ArrayList<String>();
	JButton btnNewButton_5;

	public void setPlayer_Id(String playerIdString) {
		deletePlayer_Id = Integer.valueOf(playerIdString);
	}

	public int getPlayer_Id() {
		return deletePlayer_Id;
	}

	public void clearFields() {
		bsaNumberTxtField.setText(null);
		nameTxtField.setText(null);
		surnameTxtField.setText(null);
		idNumTextField.setText(null);
		ageTxtField.setText(null);
		provinceTxtField.setText(null);
		clubTxtField.setText(null);
		roleTextField.setText(null);
		raceTxtField.setText(null);
		comboBoxGender.setSelectedIndex(0);
	}

	public static void loadTableData() throws ClassNotFoundException, SQLException, IOException {
		tableData = MakeDB.populateTable();
	}

	public static void loadPlayerData() throws ClassNotFoundException, SQLException, IOException {
		playerSearch = MakeDB.getPlayerData();
	}

	public void calculateAge() {
		if (idNumTextField.getText().length() == 13) {
			Date myDate = new Date();
			int year = myDate.getYear() + 1900;
			int nextYear = myDate.getYear() + 1901 - 2000;
			int month = myDate.getMonth() + 1;
			int ageNumber;
			String age;
			String idNum = idNumTextField.getText();
			String yearBornString = idNum.substring(0, 2);
			String monthBornString = idNum.substring(3, 4);
			idNum.substring(5, 6);
			String genderCalc = idNum.substring(6, 7);
			int genderCalcInt = Integer.valueOf(genderCalc);
			int yearBorn = Integer.valueOf(yearBornString);
			int monthBorn = (Integer.valueOf(monthBornString));

			if (yearBorn >= nextYear) {
				yearBorn = yearBorn + 1900;

				if (month < monthBorn) {
					ageNumber = year - yearBorn - 1;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				} else if (month == monthBorn) {
					ageNumber = year - yearBorn;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				} else {
					ageNumber = year - yearBorn;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				}
			} else {
				yearBorn = yearBorn + 2000;

				if (month < monthBorn) {
					ageNumber = year - yearBorn - 1;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				} else if (month == monthBorn) {
					ageNumber = year - yearBorn;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				} else {
					ageNumber = year - yearBorn;
					age = String.valueOf(ageNumber);
					ageTxtField.setText(age);
				}
			}

			if (genderCalcInt < 5) {
				comboBoxGender.setSelectedIndex(2);
			} else if (genderCalcInt >= 5) {
				comboBoxGender.setSelectedIndex(1);
			}

		} else
			JOptionPane.showMessageDialog(null, "Please enter a 13 digit id number", "Error", JOptionPane.OK_OPTION);

	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new Player();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Player() throws ClassNotFoundException, SQLException, IOException {
		try {
			loadPlayerData();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setType(Type.POPUP);
		setTitle("Player");
		setBounds(100, 100, 593, 759);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 46, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 33, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		JLabel lblNewLabel = new JLabel("BSA Number:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		{
			bsaNumberTxtField = new JTextField();
			GridBagConstraints gbc_bsaNumberTxtField = new GridBagConstraints();
			gbc_bsaNumberTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_bsaNumberTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_bsaNumberTxtField.gridx = 1;
			gbc_bsaNumberTxtField.gridy = 1;
			contentPanel.add(bsaNumberTxtField, gbc_bsaNumberTxtField);
			bsaNumberTxtField.setColumns(10);
		}
		{
			JButton btnNewButton_3 = new JButton("Issue BSA");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Playe Search" + playerSearch.get(1));
					// TODO CHECK HIGHEST BSA NUMBER ADD ONE AND ISSUE NEW Number
					int BSA;
					BSA = MakeDB.getHighestValue();
					System.out.println(BSA);
					try {
						MakeDB.getPlayerData();
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
			gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_3.gridx = 2;
			gbc_btnNewButton_3.gridy = 1;
			contentPanel.add(btnNewButton_3, gbc_btnNewButton_3);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Name:");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			nameTxtField = new JTextField();
			GridBagConstraints gbc_nameTxtField = new GridBagConstraints();
			gbc_nameTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTxtField.gridx = 1;
			gbc_nameTxtField.gridy = 2;
			contentPanel.add(nameTxtField, gbc_nameTxtField);
			nameTxtField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Surname:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 3;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			surnameTxtField = new JTextField();
			GridBagConstraints gbc_surnameTxtField = new GridBagConstraints();
			gbc_surnameTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_surnameTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_surnameTxtField.gridx = 1;
			gbc_surnameTxtField.gridy = 3;
			contentPanel.add(surnameTxtField, gbc_surnameTxtField);
			surnameTxtField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("ID Number:");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 4;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			idNumTextField = new JTextField();
			GridBagConstraints gbc_idNumTextField = new GridBagConstraints();
			gbc_idNumTextField.insets = new Insets(0, 0, 5, 5);
			gbc_idNumTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_idNumTextField.gridx = 1;
			gbc_idNumTextField.gridy = 4;
			contentPanel.add(idNumTextField, gbc_idNumTextField);
			idNumTextField.setColumns(10);
		}
		JComboBox<String> searchComboBox = new JComboBox<String>();
		GridBagConstraints gbc_searchComboBox = new GridBagConstraints();
		gbc_searchComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_searchComboBox.gridx = 2;
		gbc_searchComboBox.gridy = 4;
		contentPanel.add(searchComboBox, gbc_searchComboBox);
		searchComboBox.addItem("Search by:");
		searchComboBox.addItem("BSA Number");
		searchComboBox.addItem("Player Name");
		searchComboBox.addItem("Player Surname");
		searchComboBox.addItem("Player ID Number");
		{
			JLabel lblNewLabel_4 = new JLabel("Gender:");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 0;
			gbc_lblNewLabel_4.gridy = 5;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			comboBoxGender = new JComboBox<String>();
			comboBoxGender.addItem("Select Gender:");
			comboBoxGender.addItem("Male");
			comboBoxGender.addItem("Female");
			GridBagConstraints gbc_comboBoxGender = new GridBagConstraints();
			gbc_comboBoxGender.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxGender.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxGender.gridx = 1;
			gbc_comboBoxGender.gridy = 5;
			contentPanel.add(comboBoxGender, gbc_comboBoxGender);
		}
		{
			JButton btnNewButton_2 = new JButton("Search");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_5.setEnabled(true);

					if (searchComboBox.getSelectedIndex() == 0) {

					} else if (searchComboBox.getSelectedIndex() == 1) { // BSA Number selected
						String searchBSA = bsaNumberTxtField.getText();
						if (playerSearch.contains(searchBSA)) {

							int index = playerSearch.indexOf(searchBSA);
							int idToDelete = index - 1;
							setPlayer_Id(playerSearch.get(idToDelete));
							System.out.print("index = " + getPlayer_Id());
							nameTxtField.setText(playerSearch.get(index + 1));
							surnameTxtField.setText(playerSearch.get(index + 2));
							idNumTextField.setText(playerSearch.get(index + 3));
							calculateAge();

							// provinceTxtField.setText(playerSearch.get(index + 1));
							// clubTxtField.setText(playerSearch.get(index + 1));
							// roleTextField.setText(playerSearch.get(index + 1));
							// raceTxtField.setText(playerSearch.get(index + 1));

						}

					} else if (searchComboBox.getSelectedIndex() == 2) { // Player name selected
						String playerName = nameTxtField.getText();
						if (playerSearch.contains(playerName)) {

							int index = playerSearch.indexOf(playerName);

							System.out.println("Hello from " + index);

						}
						try {
							loadPlayerData();
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else if (searchComboBox.getSelectedIndex() == 3) { // Player surname selected
						surnameTxtField.getText();

					} else if (searchComboBox.getSelectedIndex() == 4) { // Player id number selected
						idNumTextField.getText();

					}

				}

			});

			GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
			gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_2.gridx = 2;
			gbc_btnNewButton_2.gridy = 5;
			contentPanel.add(btnNewButton_2, gbc_btnNewButton_2);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Age:");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 0;
			gbc_lblNewLabel_5.gridy = 6;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			ageTxtField = new JTextField();
			ageTxtField.setEditable(false);
			GridBagConstraints gbc_ageTxtField = new GridBagConstraints();
			gbc_ageTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_ageTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_ageTxtField.gridx = 1;
			gbc_ageTxtField.gridy = 6;
			contentPanel.add(ageTxtField, gbc_ageTxtField);
			ageTxtField.setColumns(10);
		}
		{
			JButton btnNewButton_1 = new JButton("Calculate Age");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calculateAge();
				}
			});
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 2;
			gbc_btnNewButton_1.gridy = 6;
			contentPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Race:");
			GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
			gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_9.gridx = 0;
			gbc_lblNewLabel_9.gridy = 7;
			contentPanel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		}
		{
			raceTxtField = new JTextField();
			GridBagConstraints gbc_raceTxtField = new GridBagConstraints();
			gbc_raceTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_raceTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_raceTxtField.gridx = 1;
			gbc_raceTxtField.gridy = 7;
			contentPanel.add(raceTxtField, gbc_raceTxtField);
			raceTxtField.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Province:");
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_6.gridx = 0;
			gbc_lblNewLabel_6.gridy = 8;
			contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		}
		{
			provinceTxtField = new JTextField();
			GridBagConstraints gbc_provinceTxtField = new GridBagConstraints();
			gbc_provinceTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_provinceTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_provinceTxtField.gridx = 1;
			gbc_provinceTxtField.gridy = 8;
			contentPanel.add(provinceTxtField, gbc_provinceTxtField);
			provinceTxtField.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Club:");
			GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
			gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_7.gridx = 0;
			gbc_lblNewLabel_7.gridy = 9;
			contentPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		}
		{
			clubTxtField = new JTextField();
			GridBagConstraints gbc_clubTxtField = new GridBagConstraints();
			gbc_clubTxtField.insets = new Insets(0, 0, 5, 5);
			gbc_clubTxtField.fill = GridBagConstraints.HORIZONTAL;
			gbc_clubTxtField.gridx = 1;
			gbc_clubTxtField.gridy = 9;
			contentPanel.add(clubTxtField, gbc_clubTxtField);
			clubTxtField.setColumns(10);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Role:");
			GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
			gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_8.gridx = 0;
			gbc_lblNewLabel_8.gridy = 10;
			contentPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		}
		{
			roleTextField = new JTextField();
			GridBagConstraints gbc_roleTextField = new GridBagConstraints();
			gbc_roleTextField.insets = new Insets(0, 0, 5, 5);
			gbc_roleTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_roleTextField.gridx = 1;
			gbc_roleTextField.gridy = 10;
			contentPanel.add(roleTextField, gbc_roleTextField);
			roleTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Sort by:");
			GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
			gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_10.gridx = 0;
			gbc_lblNewLabel_10.gridy = 11;
			contentPanel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 11;
			contentPanel.add(comboBox, gbc_comboBox);
			comboBox.addItem("BSA Number");
			comboBox.addItem("Name");
			comboBox.addItem("Surname");
		}
		{
			JCheckBox chckbxNewCheckBox = new JCheckBox("Active");
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxNewCheckBox.gridx = 2;
			gbc_chckbxNewCheckBox.gridy = 11;
			contentPanel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 2;
			gbc_scrollPane.gridwidth = 4;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 12;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{

				loadTableData();

				final DefaultTableModel model = new DefaultTableModel();

				Vector<String> rowData = new Vector<String>();

				tableData.size();

				for (int j = 0; j < tableData.size() / 4; j++) {
					rowData = new Vector<String>();

					for (int i = 0; i < tableData.size(); i++) {

						rowData.insertElementAt(tableData.get(i), i);

					}
					System.out.println(rowData);

				}
				model.addRow(rowData);

				model.addColumn("BSA Number");
				model.addColumn("Name");
				model.addColumn("Surname");
				model.addColumn("ID Number");

				JTable table = new JTable(model);

				System.out.print("ROW DATA" + rowData);

				/*
				 * table.setModel(new DefaultTableModel( new Object[][] { {bsaNumber, name,
				 * surname, idNumber},
				 * 
				 * }, new String[] { "BSA Number", "Name", "Surname", "ID Number" }
				 * 
				 * ));
				 */
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					JButton btnNewButton_4 = new JButton("Delete");
					btnNewButton_4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							MakeDB.deleteById(getPlayer_Id());
							try {
								loadPlayerData();
								loadTableData();
								clearFields();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					});
					btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
					buttonPane.add(btnNewButton_4);
					{
						btnNewButton_5 = new JButton("Update");
						btnNewButton_5.setEnabled(false);

						btnNewButton_5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						buttonPane.add(btnNewButton_5);
					}
				}
			}
			{
				{
					JButton btnNewButton = new JButton("Save");
					buttonPane.add(btnNewButton);
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							Date currentDate = new Date();
							int year = currentDate.getYear();
							System.out.print(year + 1900);

							System.out.print(rootPaneCheckingEnabled);
							String bsaNumber = bsaNumberTxtField.getText();
							String playerName = nameTxtField.getText();
							String playerSurname = surnameTxtField.getText();
							String playerIdNumber = idNumTextField.getText();
							String playerAge = ageTxtField.getText();
							String playerProvince = provinceTxtField.getText();
							String playerClub = clubTxtField.getText();
							String playerRole = roleTextField.getText();
							String playerRace = raceTxtField.getText();
							String playerGender;

							if (comboBoxGender.getSelectedIndex() == 0) {
								playerGender = "Not selected";
							} else
								playerGender = comboBoxGender.getItemAt(comboBoxGender.getSelectedIndex());

							try {
								MakeDB.insertData(bsaNumber, playerName, playerSurname, playerIdNumber, playerAge,
										playerProvince, playerClub, playerRole, playerRace, playerGender);
								clearFields();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {
								loadPlayerData();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					});
				}
			}
		}
	}

}
