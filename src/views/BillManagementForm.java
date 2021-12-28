package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JOptionPane;
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
    private JPanel topPanel, centerPanel, bottomPanel, empNamePanel, patientNamePanel, paymentTypePanel, formPanel,
            searchPanel, idPanel, tablePanel, medicineIDPanel, quantityPanel, moneyPanel;
    private JLabel title, empNameLabel, patientNameLabel, paymentTypeLabel, searchLabel, idLabel, medicineIDLabel,
            quantityLabel, moneyLabel;
    private JTextField empName, patientName, searchBill, id, quantity, money;
    private JTable table, billDetailTable;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane, detailScrollPane, formScrollPanel;
    private JButton addButton, searchButton, backButton, insertDetailButton, clearFieldButton, checkoutButton;
    private JComboBox<String> paymentCombo;
    private JComboBox<Employee> employeeCombo;
    private JComboBox<Patient> patientCombo;
    private JComboBox<Medicine> medicineCombo;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Vector<String> row;
    private List<Medicine> medicineList;

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
                return false;
            }

            @Override
            public void sorterChanged(RowSorterEvent e) {
                super.sorterChanged(e);
            }
        };

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        billDetailTable.getTableHeader().setResizingAllowed(false);
        billDetailTable.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(((int) screenSize.getWidth() - 300) / 2, 400));
        detailScrollPane = new JScrollPane(billDetailTable);
        detailScrollPane.setPreferredSize(new Dimension(((int) screenSize.getWidth() - 300) / 2, 400));

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
        moneyLabel = new JLabel("Money");
        moneyLabel.setFont(new Font("Status", Font.PLAIN, 20));
        moneyLabel.setPreferredSize(new Dimension(150, 25));

        id = new JTextField();
        id.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        id.setEditable(false);
        searchBill = new JTextField();
        searchBill.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        empName = new JTextField();
        empName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        patientName = new JTextField();
        patientName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

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
        medicineList = MedicineController.getInstance().getAllMedicine();
        medicineCombo = new JComboBox<>((Vector) medicineList);
        medicineCombo.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        medicineCombo.setSelectedIndex(-1);
        quantity = new JTextField();
        quantity.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
        quantity.setEditable(false);
        money = new JTextField();
        money.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

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
        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        moneyPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add Bill");
        searchButton = new JButton("Search Bill");
        insertDetailButton = new JButton("Insert Bill Detail");
        clearFieldButton = new JButton("Clear Field");
        checkoutButton = new JButton("Checkout");
        backButton = new JButton("Back");

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
        medicineIDPanel.add(medicineCombo);

        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);

        moneyPanel.add(moneyLabel);
        moneyPanel.add(money);

        formPanel.add(searchPanel);
        formPanel.add(idPanel);
        formPanel.add(empNamePanel);
        formPanel.add(patientNamePanel);
        formPanel.add(paymentTypePanel);
        formPanel.add(moneyPanel);
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

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                BillController billControl = BillController.getInstance();

                loadDetailData(table.getValueAt(row, 0).toString());
                id.setText(table.getValueAt(row, 0).toString());
                employeeCombo
                        .setSelectedIndex(billControl.GetBill(table.getValueAt(row, 0).toString()).getEmployeeID() - 1);
                patientCombo
                        .setSelectedIndex(billControl.GetBill(table.getValueAt(row, 0).toString()).getPatientID() - 1);
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
                int newEmpIndex = employeeCombo.getSelectedIndex();
                int newPatientIndex = patientCombo.getSelectedIndex();
                if (newEmpIndex < 0) {
                    JOptionPane.showMessageDialog(null, "Employee harus diisi");
                    return;
                }
                if (newPatientIndex < 0) {
                    JOptionPane.showMessageDialog(null, "Patient harus diisi");
                    return;
                }
                String newEmpID = "" + employeeList.get(employeeCombo.getSelectedIndex()).getEmployeeID();
                String newPatientID = "" + patientList.get(patientCombo.getSelectedIndex()).getPatientID();
                String newPaymentType = "";
                if (paymentCombo.getSelectedIndex() == 0) {
                    newPaymentType = "Cash";
                } else {
                    newPaymentType = "Credit";
                }
                BillController billController = BillController.getInstance();
                billController.CreateBill(newEmpID, newPatientID);
                loadData();
            }
        });

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String searchPateintID = searchBill.getText();

                BillController billController = BillController.getInstance();
                List<Bill> foundBill = billController.SearchBill(searchPateintID);
                loadData(foundBill);
            }
        });

        insertDetailButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String billID = id.getText();
                if (medicineCombo.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Medicine harus dipilih");
                    return;
                }
                String medID = "" + medicineList.get(medicineCombo.getSelectedIndex()).getMedicineID();
                String qty = quantity.getText();

                BillController billController = BillController.getInstance();
                List<BillDetail> listBillDetail = billController.AddBillDetail(billID, medID, qty);
                MedicineController.getInstance().decreaseStock(medID, qty);

                loadData();
                loadDetailData(billID);

            }
        });

        checkoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String newPaymentType = "";
                if (paymentCombo.getSelectedIndex() == 0) {
                    newPaymentType = "Cash";
                } else {
                    newPaymentType = "Credit";
                }
                String billID = id.getText();
                String moneyStr = money.getText();

                BillController billController = BillController.getInstance();
                billController.Checkout(billID, newPaymentType, moneyStr);
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
                medicineCombo.setSelectedIndex(-1);
                quantity.setText("");
                quantity.setEditable(false);
                money.setText("");
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

    private void loadData() {
        Object[] header = { "Bill ID", "Employee Name", "Patient Name", "Payment type", "Status" };
        List<Bill> listBill = BillController.getInstance().GetAllBill();

        dtm = new DefaultTableModel(header, 0);
        EmployeeController empControl = EmployeeController.getInstance();
        PatientController patControl = PatientController.getInstance();

        for (Bill bill : listBill) {
            row = new Vector<>();

            String employeeName = empControl.GetEmployee(bill.getEmployeeID()).getName();
            String patientName = patControl.GetPatient("" + bill.getPatientID()).getName();
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
            String patientName = patControl.GetPatient("" + bill.getPatientID()).getName();
            row.add(Integer.toString(bill.getBillID()));
            row.add(employeeName);
            row.add(patientName);
            row.add(bill.getPaymentType());
            row.add(bill.getStatus());
            dtm.addRow(row);
        }

        table.setModel(dtm);
    }

    private void loadDetailData(String billID) {
        Object[] header = { "Bill Detail ID", "Bill ID", "Medicine ID", "Quantity" };
        List<BillDetail> listBillDetail = BillController.getInstance().getBillDetail(billID);

        dtm = new DefaultTableModel(header, 0);

        for (BillDetail billDetail : listBillDetail) {
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
        initItem();
        setItem();
        setListener();
        loadData();

        setVisible(true); // nampilin
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Bill Management View");
    }

}