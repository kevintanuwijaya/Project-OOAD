package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewEmployeeForm extends JFrame{
	private JPanel topPanel, centerPanel, bottomPanel, usernamePanel, namePanel, passwordPanel, rolePanel, salaryPanel, statusPanel, formPanel;
    private JLabel title, usernameLabel, nameLabel, passwordLabel, roleLabel, salaryLabel, statusLabel;
    private JTextField empUsername, empName, empPassword, empRole, empSalary, empStatus;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane;
    private JButton addButton;

    private Vector<String> columnName;
    private Vector<String> dataDummy;
    
    private JPanel rolesPanel;

    private void initItem() {
        setLayout(new BorderLayout());

        /* For Top Panel */
        topPanel = new JPanel();
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title = new JLabel("Employee Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));

        /* For Center Panel */
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // TODO Buat inital table
        columnName = new Vector<>();
//        columnName.add("Medicine ID");
        columnName.add("Employee Name");
        columnName.add("Employee Salary");
        columnName.add("Employee Status");

        System.out.println(columnName);

        // TODO Data dummy, nanti harus diganti pake data yang diambil dari data access
        dataDummy = new Vector<>();
        dataDummy.add("Steven Santoso Suntialto");
        dataDummy.add("2MiliarJuta");
        dataDummy.add("Single?");

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);

        /* For Form Panel */
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordLabel = new JLabel("Password ");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        salaryLabel = new JLabel("Salary");
        salaryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        empUsername = new JTextField();
        empUsername.setColumns(40);
        empName = new JTextField();
        empName.setColumns(40);
        empPassword = new JTextField();
        empPassword.setColumns(40);
        empRole = new JTextField();
        empRole.setColumns(40);
        empSalary = new JTextField();
        empSalary.setColumns(40);
        empStatus = new JTextField();
        empStatus.setColumns(40);

        usernamePanel = new JPanel();
        usernamePanel.setLayout(new GridLayout(2, 2, 4, 4));
        namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(2, 2, 4, 4));
        passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(2, 2, 4, 4));    
        rolePanel = new JPanel();
        rolePanel.setLayout(new GridLayout(2, 2, 4, 4));
        salaryPanel = new JPanel();
        salaryPanel.setLayout(new GridLayout(2, 2, 4, 4));
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(2, 2, 4, 4));
        
        rolesPanel = new JPanel(new GridLayout(1,5));
		ButtonGroup roleRB = new ButtonGroup();
	
		JRadioButton adminRb = new JRadioButton("Administrative");
		rolesPanel.add(adminRb);
		JRadioButton pharmacistRb = new JRadioButton("Pharmacist");
		rolesPanel.add(pharmacistRb);
		JRadioButton doctorRb = new JRadioButton("Doctor");
		rolesPanel.add(doctorRb);
		JRadioButton nurseRb = new JRadioButton("Nurse");
		rolesPanel.add(nurseRb);
		JRadioButton humanResourceRb = new JRadioButton("Human Resource");
		rolesPanel.add(humanResourceRb);
		
		roleRB.add(adminRb);
		roleRB.add(pharmacistRb);
		roleRB.add(doctorRb);
		roleRB.add(nurseRb);
		roleRB.add(humanResourceRb);
        

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add");
    }

    private void setItem() {
        /* Top Panel */
    	topPanel.add(title);
    	
        /* Center Panel */
        usernamePanel.add(usernameLabel);
        usernamePanel.add(empUsername);

        namePanel.add(nameLabel);
        namePanel.add(empName);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(empPassword);
        
        rolePanel.add(roleLabel);
//        rolePanel.add(empRole);
        rolePanel.add(rolesPanel);

        salaryPanel.add(salaryLabel);
        salaryPanel.add(empSalary);

        statusPanel.add(statusLabel);
        statusPanel.add(empStatus);

        formPanel.add(usernamePanel);
        formPanel.add(namePanel);
        formPanel.add(passwordPanel);
        formPanel.add(rolePanel);
        formPanel.add(salaryPanel);
        formPanel.add(statusPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        centerPanel.add(scrollPane);
        centerPanel.add(formPanel);
        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        /* Bottom Panel */
        bottomPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {
    }

    private void loadData() {
        dtm = new DefaultTableModel(columnName, 0);
        dtm.addRow(dataDummy);

        table.setModel(dtm);
    }

	public ViewEmployeeForm() {
		// TODO Auto-generated constructor stub
		initItem();
        setItem();
        setListener();
        loadData();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Employee Management Form");
	}

}
