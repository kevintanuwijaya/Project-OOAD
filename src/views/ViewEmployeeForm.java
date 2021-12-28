package views;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// import org.w3c.dom.events.MouseEvent;

import controllers.EmployeeController;
import models.Employee;

public class ViewEmployeeForm extends JFrame {
    private JPanel idPanel, topPanel, centerPanel, bottomPanel, usernamePanel, namePanel, rolePanel, salaryPanel,
            formPanel, passwordPanel;
    private JLabel idLabel, title, usernameLabel, nameLabel, passwordLabel, roleLabel, salaryLabel, statusLabel;
    private JTextField empId, empUsername, empName, empPassword, empRole, empSalary, empStatus;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane;
    private JButton addButton, updateButton, fireButton, clearButton, backButton;
    private Employee employee = MainMenu.currentEmployee;

    ButtonGroup roleRB;
    private JRadioButton adminRb, pharmacistRb, doctorRb, nurseRb, humanResourceRb;

    /* For Search Form Panel */
    // private JPanel searchFormPanel; // Panel untuk 1 Search Form Panel
    // private JLabel searchLabel;
    // private JTextField searchTextField;
    // private JButton searchButton;

    private Vector<String> row;

    // private Vector<String> columnName;
    // private Vector<String> dataDummy;

    private JPanel rolesPanel;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        idLabel.setPreferredSize(new Dimension(50, 25));
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

        empId = new JTextField();
        empId.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        empId.setEditable(false);

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

        // searchPanel = new JPanel();
        // searchPanel.setLayout(new GridLayout(2, 2, 4, 4));

        // searchLabel = new JLabel("Search");
        // searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        // searchLabel.setPreferredSize(new Dimension(150, 25));

        // searchTextField = new JTextField();
        // searchTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() -
        // 300, 25));

        idPanel = new JPanel();
        idPanel.setLayout(new GridLayout(2, 2, 4, 4));

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
        // statusPanel = new JPanel();
        // statusPanel.setLayout(new GridLayout(2, 2, 4, 4));

        rolesPanel = new JPanel(new GridLayout(1, 5));
        roleRB = new ButtonGroup();

        adminRb = new JRadioButton("Administrative");
        rolesPanel.add(adminRb);
        pharmacistRb = new JRadioButton("Pharmacist");
        rolesPanel.add(pharmacistRb);
        doctorRb = new JRadioButton("Doctor");
        rolesPanel.add(doctorRb);
        nurseRb = new JRadioButton("Nurse");
        rolesPanel.add(nurseRb);
        humanResourceRb = new JRadioButton("Human Resource");
        rolesPanel.add(humanResourceRb);

        roleRB.add(adminRb);
        roleRB.add(pharmacistRb);
        roleRB.add(doctorRb);
        roleRB.add(nurseRb);
        roleRB.add(humanResourceRb);

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear Field");
        fireButton = new JButton("Fire");
        backButton = new JButton("Back To Menu");
        // searchButton = new JButton("Search");
    }

    private void setItem() {
        /* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        // searchPanel.add(searchLabel);
        // searchPanel.add(searchTextField);

        idPanel.add(idLabel);
        idPanel.add(empId);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(empUsername);

        namePanel.add(nameLabel);
        namePanel.add(empName);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(empPassword);

        rolePanel.add(roleLabel);
        // rolePanel.add(empRole);
        rolePanel.add(rolesPanel);

        salaryPanel.add(salaryLabel);
        salaryPanel.add(empSalary);

        // statusPanel.add(statusLabel);
        // statusPanel.add(empStatus);

        // formPanel.add(searchPanel);
        formPanel.add(idPanel);
        formPanel.add(usernamePanel);
        formPanel.add(namePanel);
        formPanel.add(passwordPanel);
        formPanel.add(rolePanel);
        formPanel.add(salaryPanel);
        // formPanel.add(statusPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        centerPanel.add(scrollPane);
        centerPanel.add(formPanel);
        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        /* Bottom Panel */
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(fireButton);
        bottomPanel.add(backButton);
        // bottomPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                empId.setText(table.getValueAt(row, 0).toString());
                empUsername.setText(table.getValueAt(row, 1).toString());
                empName.setText(table.getValueAt(row, 2).toString());
                empSalary.setText(table.getValueAt(row, 3).toString());
                Employee employee = EmployeeController.getInstance().GetEmployee(Integer.parseInt(empId.getText()));

                if (employee.getRoleID() == 1) {
                    adminRb.setSelected(true);
                } else if (employee.getRoleID() == 2) {
                    pharmacistRb.setSelected(true);
                } else if (employee.getRoleID() == 3) {
                    doctorRb.setSelected(true);
                } else if (employee.getRoleID() == 4) {
                    nurseRb.setSelected(true);
                } else if (employee.getRoleID() == 5) {
                    humanResourceRb.setSelected(true);
                }

            }

        });
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (empSalary.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Salary tidak boleh kosong");
                }

                String username = empUsername.getText();
                String name = empName.getText();
                int roleID = 0;
                String salary = empSalary.getText();
                String password = empPassword.getText();

                if (adminRb.isSelected()) {
                    roleID = 1;
                } else if (pharmacistRb.isSelected()) {
                    roleID = 2;
                } else if (doctorRb.isSelected()) {
                    roleID = 3;
                } else if (nurseRb.isSelected()) {
                    roleID = 4;
                } else if (humanResourceRb.isSelected()) {
                    roleID = 5;
                } else {
                    JOptionPane.showMessageDialog(null, "Harus Pilih Role");
                }

                EmployeeController empController = EmployeeController.getInstance();
                empController.AddEmployee(name, username, roleID, salary, password);
                loadData();
            }

        });

        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeID = empId.getText();
                String username = empUsername.getText();
                String name = empName.getText();
                String password = empPassword.getText();
                int roleID = 0;
                String salary = empSalary.getText();

                if (adminRb.isSelected()) {
                    roleID = 1;
                } else if (pharmacistRb.isSelected()) {
                    roleID = 2;
                } else if (doctorRb.isSelected()) {
                    roleID = 3;
                } else if (nurseRb.isSelected()) {
                    roleID = 4;
                } else if (humanResourceRb.isSelected()) {
                    roleID = 5;
                } else {
                    JOptionPane.showMessageDialog(null, "Harus Pilih Role");
                }

                System.out.println(username + " " + name + " " + roleID + " " + salary + "\n");
                EmployeeController empController = EmployeeController.getInstance();
                empController.UpdateEmployee(employeeID, name, username, roleID, password, salary);
                loadData();
            }

        });

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                empId.setText("");
                empUsername.setText("");
                empName.setText("");
                empSalary.setText("");

                roleRB.clearSelection();

                loadData();
            }
        });

        fireButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int employeeID = Integer.parseInt(empId.getText());

                EmployeeController empController = EmployeeController.getInstance();
                empController.FireEmployee(employeeID);
                loadData();
            }

        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(employee);
                dispose();

            }
        });
    }

    private void loadData() {
        Object[] header = { "Employee Id", "Employee Username", "Employee Name", "Employee Salary", "Employee Role",
                "Employee Status" };

        dtm = new DefaultTableModel(header, 0);
        List<Employee> emps = EmployeeController.getInstance().GetAllEmployee();

        for (Employee employee : emps) {
            row = new Vector<>();
            row.add(Integer.toString(employee.getEmployeeID()));
            row.add(employee.getUsername());
            row.add(employee.getName());
            row.add(Integer.toString(employee.getSalary()));
            row.add(Integer.toString(employee.getRoleID()));
            row.add(employee.getStatus());
            dtm.addRow(row);

        }

        table.setModel(dtm);
    }

    public ViewEmployeeForm() {
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
