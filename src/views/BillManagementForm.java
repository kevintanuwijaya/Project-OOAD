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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableModel;
import static javax.swing.ScrollPaneConstants.*;

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
	private JPanel topPanel, centerPanel, bottomPanel, empNamePanel, patientNamePanel, createdDatePanel, paymentTypePanel, formPanel, searchPanel, idPanel, tablePanel, medicineIDPanel, quantityPanel;
    private JLabel title, empNameLabel, patientNameLabel, createdDateLabel, paymentTypeLabel, searchLabel, idLabel, medicineIDLabel, quantityLabel;
    private JTextField empName, patientName, createdDate, paymentType, searchBill, id,  medicineID, quantity;
    private JTable table, billDetailTable;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane, detailScrollPane, formScrollPanel;
    private JButton addButton, updateButton, searchButton, backButton, insertDetailButton, clearFieldButton, checkoutButton;
    private JComboBox<String> paymentCombo;
    private JComboBox<Employee> employeeCombo;
    private JComboBox<Patient> patientCombo;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Vector<String> columnName;
    private Vector<String> columnDetailName;
    private Vector<String> dataDummy;
    private Vector<String> row;
    private Vector<Employee> employeeList;
    private Vector<Patient> patientList;
    
    
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
        tablePanel = new JPanel();
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
        scrollPane.setPreferredSize(new Dimension(((int)screenSize.getWidth()-300)/2, 400));
        detailScrollPane = new JScrollPane(billDetailTable);
        detailScrollPane.setPreferredSize(new Dimension(((int)screenSize.getWidth()-300)/2, 400));

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
        medicineIDLabel = new JLabel("Medicine ID");
        medicineIDLabel.setFont(new Font("Status", Font.PLAIN, 20));
        medicineIDLabel.setPreferredSize(new Dimension(150, 25));
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(new Font("Status", Font.PLAIN, 20));
        quantityLabel.setPreferredSize(new Dimension(150, 25));
        
        id = new JTextField();
        id.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        id.setEditable(false);
        searchBill = new JTextField();
        searchBill.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        empName = new JTextField();
        empName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        patientName = new JTextField();
        patientName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
//        paymentType = new JTextField();
//        paymentType.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        
        Vector<String> paymentType = new Vector<>();
        paymentType.add("Cash");
        paymentType.add("Credit");
        paymentCombo = new JComboBox<>((Vector) paymentType);
        paymentCombo.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        paymentCombo.setSelectedIndex(-1);
        List<Employee> employeeList = new Vector<>();
        EmployeeController empControl = EmployeeController.getInstance();
        employeeList = empControl.GetAllEmployee();
        employeeCombo = new JComboBox<>((Vector) employeeList);
        employeeCombo.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        employeeCombo.setSelectedIndex(-1);
        List<Patient> patientList = new Vector<>();
        PatientController patControl = PatientController.getInstance();
        patientList = patControl.GetAllPatient();
        patientCombo = new JComboBox<>((Vector) patientList);
        patientCombo.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        patientCombo.setSelectedIndex(-1);
        medicineID = new JTextField();
        medicineID.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        medicineID.setEditable(false);
        quantity = new JTextField();
        quantity.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        quantity.setEditable(false);

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
        medicineIDPanel = new JPanel();
        medicineIDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medicineIDPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
        

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add Bill");
        updateButton = new JButton("Update Bill");
        searchButton = new JButton("Search Bill");
        insertDetailButton = new JButton("Insert Bill Detail");
        clearFieldButton = new JButton("Clear Field");
        checkoutButton = new JButton("Checkout");
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
                employeeCombo.setSelectedIndex(billControl.GetBill(Integer.parseInt(table.getValueAt(row, 0).toString())).getEmployeeID()-1);
                patientCombo.setSelectedIndex(billControl.GetBill(Integer.parseInt(table.getValueAt(row, 0).toString())).getPatientID()-1);
                if(table.getValueAt(row, 3).toString().equals("Cash")) {
                	paymentCombo.setSelectedIndex(0);
                }
                else {
                	paymentCombo.setSelectedIndex(1);
                }
//                paymentType.setText(table.getValueAt(row, 3).toString());
                medicineID.setEditable(true);
                quantity.setEditable(true);
            }

        });
        
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	EmployeeController empControl = EmployeeController.getInstance();
                final List<Employee> employeeList = empControl.GetAllEmployee();
                PatientController patControl = PatientController.getInstance();
                final List<Patient> patientList = patControl.GetAllPatient();
                int newEmpID = employeeList.get(employeeCombo.getSelectedIndex()).getEmployeeID();
                int newPatientID = patientList.get(patientCombo.getSelectedIndex()).getPatientID();
                String newPaymentType = "";
                if(paymentCombo.getSelectedIndex() == 1) {
                	newPaymentType = "Cash";
                }
                else {
                	newPaymentType = "Credit";
                }
                BillController billController = BillController.getInstance();
                billController.AddBill(newEmpID, newPatientID, newPaymentType);
                loadData();
            }
        });

        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	int newEmpID = Integer.parseInt(empName.getText());
                int newPatientID = Integer.parseInt(patientName.getText()) ;
                String newPaymentType = "";
                int billID = Integer.parseInt(id.getText());
                if(paymentCombo.getSelectedIndex() == 1) {
                	newPaymentType = "Cash";
                }
                else {
                	newPaymentType = "Credit";
                }

                BillController billController = BillController.getInstance();
                billController.UpdateBill(billID, newEmpID, newPatientID, newPaymentType);
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
        
        insertDetailButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int billID = Integer.parseInt(id.getText());
				int medID = Integer.parseInt(medicineID.getText());
				int qty = Integer.parseInt(quantity.getText());
				
				BillController billController = BillController.getInstance();
				System.out.println(billID);
				System.out.println(medID);
				System.out.println(qty);
				billController.AddBillDetail(billID, medID, qty);
				loadData();
				loadDetailData(billID);
				
				
			}
		});
        
        checkoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int newEmpID = Integer.parseInt(empName.getText());
                int newPatientID = Integer.parseInt(patientName.getText()) ;
               
                int billID = Integer.parseInt(id.getText());

                BillController billController = BillController.getInstance();
//                billController.UpdateBill(billID, newEmpID, newPatientID, newPaymentType);
                loadData();
				
			}
		});
        
        clearFieldButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				searchBill.setText("");
				employeeCombo.setSelectedIndex(-1);
				patientCombo.setSelectedIndex(-1);
				paymentCombo.setSelectedIndex(-1);
				medicineID.setText("");
				medicineID.setEditable(false);
				quantity.setText("");
				quantity.setEditable(false);
				billDetailTable.setModel(new DefaultTableModel());
				loadData();
				
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
        empNamePanel.add(employeeCombo);

        patientNamePanel.add(patientNameLabel);
        patientNamePanel.add(patientCombo);
        
        paymentTypePanel.add(paymentTypeLabel);
        paymentTypePanel.add(paymentCombo);
        
        medicineIDPanel.add(medicineIDLabel);
        medicineIDPanel.add(medicineID);
        
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);
        

        formPanel.add(idPanel);
        formPanel.add(searchPanel);
        formPanel.add(empNamePanel);
        formPanel.add(patientNamePanel);
        formPanel.add(paymentTypePanel);
        formPanel.add(medicineIDPanel);
        formPanel.add(quantityPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        formScrollPanel = new JScrollPane(formPanel);
        formScrollPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        tablePanel.add(scrollPane);
        tablePanel.add(detailScrollPane);
        centerPanel.add(tablePanel);
        centerPanel.add(formScrollPanel);
        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        
        /* Bottom Panel */
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(insertDetailButton);
        bottomPanel.add(checkoutButton);
        bottomPanel.add(clearFieldButton);
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
    
    private void clearDetailData() {
    	Object[] header = { "Bill Detail ID", "Bill ID", "Medicine ID", "Quantity" };
        dtm = new DefaultTableModel(header, 0);	

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
