package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableModel;

import controllers.BillController;
import controllers.EmployeeController;
import controllers.MedicineController;
import controllers.PatientController;
import models.Bill;
import models.BillDetail;
import models.Employee;
import models.Medicine;
import models.Patient;

public class BillManagementForm extends JFrame {
	private JPanel topPanel, centerPanel, bottomPanel, empNamePanel, patientNamePanel, createdDatePanel, paymentTypePanel, statusPanel, formPanel, searchPanel, idPanel;
    private JLabel title, empNameLabel, patientNameLabel, createdDateLabel, paymentTypeLabel, statusLabel, searchLabel, idLabel;
    private JTextField empName, patientName, createdDate, paymentType, status, searchBill, id;
    private JTable table, billDetailTable;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane, detailScrollPane;
    private JButton addButton, updateButton, searchButton, backButton;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Vector<String> columnName;
    private Vector<String> columnDetailName;
    private Vector<String> dataDummy;
    private Vector<String> row;
    
    private Employee currentEmployee = MainMenu.currentEmployee;
	
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
        
        columnDetailName = new Vector<>();
        columnDetailName.add("Bill Detail ID");
        columnDetailName.add("Bill ID");
        columnDetailName.add("Medicine ID");
        columnDetailName.add("Quantity");

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
        
        billDetailTable = new JTable() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void sorterChanged(RowSorterEvent e) {
				// TODO Auto-generated method stub
				super.sorterChanged(e);
			}
		};

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        
        billDetailTable.getTableHeader().setResizingAllowed(false);
        billDetailTable.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        detailScrollPane = new JScrollPane(billDetailTable);
        

        /* For Form Panel */
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        idLabel.setPreferredSize(new Dimension(150, 25));
        searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        searchLabel.setPreferredSize(new Dimension(150, 25));
        empNameLabel = new JLabel("Employee");
        empNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        empNameLabel.setPreferredSize(new Dimension(150, 25));
        patientNameLabel = new JLabel("Patient");
        patientNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        patientNameLabel.setPreferredSize(new Dimension(150, 25));
        paymentTypeLabel = new JLabel("Payment Type");
        paymentTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        paymentTypeLabel.setPreferredSize(new Dimension(150, 25));
        statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Status", Font.PLAIN, 20));
        statusLabel.setPreferredSize(new Dimension(150, 25));
        
        id = new JTextField();
        id.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        id.setEditable(false);
        searchBill = new JTextField();
        searchBill.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        empName = new JTextField();
        empName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        patientName = new JTextField();
        patientName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        paymentType = new JTextField();
        paymentType.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        status = new JTextField();
        status.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        idPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        empNamePanel = new JPanel();
        empNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        empNamePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        patientNamePanel = new JPanel();
        patientNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        patientNamePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        paymentTypePanel = new JPanel();
        paymentTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        paymentTypePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        searchButton = new JButton("Search");
        backButton = new JButton("Back");
        
        
        
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                EmployeeController empControl = EmployeeController.getInstance();
                PatientController patControl = PatientController.getInstance();
                BillController billControl = BillController.getInstance();
                
                loadDetailData(Integer.parseInt(table.getValueAt(row, 0).toString()));
                id.setText(table.getValueAt(row, 0).toString());
                int selectedBillID = Integer.parseInt(table.getValueAt(row, 0).toString());
                empName.setText(Integer.toString(billControl.GetBill(selectedBillID).getEmployeeID()));
                patientName.setText(Integer.toString(billControl.GetBill(selectedBillID).getPatientID()));
                paymentType.setText(table.getValueAt(row, 3).toString());
                status.setText(table.getValueAt(row, 4).toString());
            }

        });
        
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int newEmpID = Integer.parseInt(empName.getText());
                int newPatientID = Integer.parseInt(patientName.getText()) ;
                String newPaymentType = paymentType.getText();
                String newStatus = status.getText();

                BillController billController = BillController.getInstance();
                billController.AddBill(newEmpID, newPatientID, newPaymentType, newStatus);
                loadData();
            }
        });

        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	int newEmpID = Integer.parseInt(empName.getText());
                int newPatientID = Integer.parseInt(patientName.getText()) ;
                String newPaymentType = paymentType.getText();
                String newStatus = status.getText();
                int billID = Integer.parseInt(id.getText());

                BillController billController = BillController.getInstance();
                billController.UpdateBill(billID, newEmpID, newPatientID, newPaymentType, newStatus);
                loadData();
            }
        });
        
        
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int searchPateintID = Integer.parseInt(searchBill.getText());

                BillController billController = BillController.getInstance();
                List<Bill> foundBill = billController.SearchBill(searchPateintID);
                loadData(foundBill);
            }
        });
        
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu(currentEmployee);
				dispose();
			}
		});
		
    }

    private void setItem() {
    	/* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        idPanel.add(idLabel);
        idPanel.add(id);
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchBill);
        
        empNamePanel.add(empNameLabel);
        empNamePanel.add(empName);

        patientNamePanel.add(patientNameLabel);
        patientNamePanel.add(patientName);
        
        paymentTypePanel.add(paymentTypeLabel);
        paymentTypePanel.add(paymentType);
        
        statusPanel.add(statusLabel);
        statusPanel.add(status);

        formPanel.add(idPanel);
        formPanel.add(searchPanel);
        formPanel.add(empNamePanel);
        formPanel.add(patientNamePanel);
        formPanel.add(paymentTypePanel);
        formPanel.add(statusPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        centerPanel.add(scrollPane);
        centerPanel.add(detailScrollPane);
        centerPanel.add(formPanel);
        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        /* Bottom Panel */
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {
    }

    private void loadData() {
    	Object[] header = { "Bill ID", "Employee Name", "Patient Name", "Payment type", "Status" };
    	List<Bill> listBill = BillController.getInstance().GetAllBill();

        dtm = new DefaultTableModel(header, 0);
        EmployeeController empControl = EmployeeController.getInstance();
        PatientController patControl = PatientController.getInstance();
        
        for(Bill bill :listBill) {
        	row = new Vector<>();
        	
        	String employeeName = empControl.GetEmployee(bill.getEmployeeID()).getName();
            String patientName = patControl.GetPatient(bill.getPatientID()).getName();
            row.add(Integer.toString(bill.getBillID()));
            row.add(employeeName);
        	row.add(patientName);
        	row.add(bill.getPaymentType());
        	row.add(bill.getStatus());
        	dtm.addRow(row);
        }
    	

        table.setModel(dtm);
    }
    
    private void loadData(List<Bill> foundBill) {
    	Object[] header = { "Bill ID", "Employee Name", "Patient Name", "Payment type", "Status" };

        dtm = new DefaultTableModel(header, 0);
        EmployeeController empControl = EmployeeController.getInstance();
        PatientController patControl = PatientController.getInstance();

        for (Bill bill : foundBill) {
            row = new Vector<>();
            
            String employeeName = empControl.GetEmployee(bill.getEmployeeID()).getName();
            String patientName = patControl.GetPatient(bill.getPatientID()).getName();
            row.add(Integer.toString(bill.getBillID()));
            row.add(employeeName);
        	row.add(patientName);
        	row.add(bill.getPaymentType());
        	row.add(bill.getStatus());
        	dtm.addRow(row);
        }

        table.setModel(dtm);
    }
    
    private void loadDetailData(int billID) {
    	Object[] header = { "Bill Detail ID", "Bill ID", "Medicine ID", "Quantity" };
    	List<BillDetail> listBillDetail = BillController.getInstance().getAllBillDetail(billID);

        dtm = new DefaultTableModel(header, 0);
        EmployeeController empControl = EmployeeController.getInstance();
        PatientController patControl = PatientController.getInstance();
        
        for(BillDetail billDetail : listBillDetail) {
        	row = new Vector<>();
        	
            row.add(Integer.toString(billDetail.getBillDetailID()));
        	row.add(Integer.toString(billDetail.getBillID()));
        	row.add(Integer.toString(billDetail.getMedicineID()));
        	row.add(Integer.toString(billDetail.getQuantity()));
        	dtm.addRow(row);
        }
    	

        billDetailTable.setModel(dtm);
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
