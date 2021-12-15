package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.BillController;
import models.Bill;

public class BillManagementForm extends JFrame {
	private JPanel topPanel, centerPanel, bottomPanel, empNamePanel, patientNamePanel, createdDatePanel, paymentTypePanel, statusPanel, formPanel;
    private JLabel title, empNameLabel, patientNameLabel, createdDateLabel, paymentTypeLabel, statusLabel;
    private JTextField empName, patientName, createdDate, paymentType, status;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane;
    private JButton addButton;

    private Vector<String> columnName;
    private Vector<String> dataDummy;
    private Vector<String> row;
	
    private void initItem() {
    	setLayout(new BorderLayout());

        /* For Top Panel */
        topPanel = new JPanel();
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title = new JLabel("Bill Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));

        /* For Center Panel */
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // TODO Buat inital table
        columnName = new Vector<>();
        columnName.add("Bill ID");
        columnName.add("Employee");
        columnName.add("Patient");
        columnName.add("Date Time Created");
        columnName.add("Payment Type");
        columnName.add("Status");

        System.out.println(columnName);

        // TODO Data dummy, nanti harus diganti pake data yang diambil dari data access
        dataDummy = new Vector<>();
        dataDummy.add("1");
        dataDummy.add("Steven Santoso");
        dataDummy.add("Marcellino");
        dataDummy.add("12-12-2012");
        dataDummy.add("Credit");
        dataDummy.add("Paid");

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

        empNameLabel = new JLabel("Employee");
        empNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        patientNameLabel = new JLabel("Patient");
        patientNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        createdDateLabel = new JLabel("Date Time Created");
        createdDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        paymentTypeLabel = new JLabel("Payment Type");
        paymentTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Status", Font.PLAIN, 20));

        empName = new JTextField();
        empName.setColumns(40);
        patientName = new JTextField();
        patientName.setColumns(40);
        createdDate = new JTextField();
        createdDate.setColumns(40);
        paymentType = new JTextField();
        paymentType.setColumns(40);
        status = new JTextField();
        status.setColumns(40);

        empNamePanel = new JPanel();
        empNamePanel.setLayout(new GridLayout(3, 2, 4, 4));
        patientNamePanel = new JPanel();
        patientNamePanel.setLayout(new GridLayout(3, 2, 4, 4));
        createdDatePanel = new JPanel();
        createdDatePanel.setLayout(new GridLayout(3, 2, 4, 4));
        paymentTypePanel = new JPanel();
        paymentTypePanel.setLayout(new GridLayout(3, 2, 4, 4));
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3, 2, 4, 4));

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add");
		
    }

    private void setItem() {
    	/* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        empNamePanel.add(empNameLabel);
        empNamePanel.add(empName);

        patientNamePanel.add(patientNameLabel);
        patientNamePanel.add(patientName);

        createdDatePanel.add(createdDateLabel);
        createdDatePanel.add(createdDate);
        
        paymentTypePanel.add(paymentTypeLabel);
        paymentTypePanel.add(paymentType);
        
        statusPanel.add(statusLabel);
        statusPanel.add(status);

        formPanel.add(empNamePanel);
        formPanel.add(patientNamePanel);
        formPanel.add(createdDatePanel);
        formPanel.add(paymentTypePanel);
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
    	Object[] header = { "Employee Name", "Patient Name", "Payment type", "Status" };
    	List<Bill> listBill = BillController.getInstance().GetAllBill();

        dtm = new DefaultTableModel(header, 0);
        
        for(Bill bill :listBill) {
        	row = new Vector<>();
        	row.add(Integer.toString(bill.getEmployeeID()));
        	row.add(Integer.toString(bill.getPatientID()));
        	row.add(bill.getPaymentType());
        	row.add(bill.getStatus());
        	dtm.addRow(row);
        }
    	

        table.setModel(dtm);
    }
	
	public BillManagementForm() {
		// TODO Auto-generated constructor stub
		initItem();
		setItem();
		setListener();
		loadData();
		
		setVisible(true); //nampilin
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Bill Management View");
	}

}
