package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableModel;

import models.Employee;

public class PatientManagementForm extends JFrame{

	private JPanel northPanel,centerPanel,southPanel;
	private JPanel formPanel, searchPanel, namePanel, DOBPanel, employeePanel, symptomPanel, checkDatePanel;
	private JLabel title, searchLabel, nameLabel, DOBLabel, employeeLabel, symptomLabel, checkDateLabel;
	private JTextField searchField, nameField,symptomField;
	private JTable PatientTable;
	private DefaultTableModel dtm;
	private JButton backBtn;
	private JScrollPane scrollTable;
	private JComboBox<Employee> employeesCombo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private void initItem() {
		setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
		
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));
		formPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		DOBPanel = new JPanel();
		DOBPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		DOBPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		employeePanel = new JPanel();
		employeePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		employeePanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		symptomPanel= new JPanel();
		symptomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		symptomPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		checkDatePanel = new JPanel();
		checkDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkDatePanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		
		
		title = new JLabel("Patient Management View");
		title.setFont(new Font("Segoe UI",Font.BOLD,40));
		
		
		PatientTable = new JTable() {
			
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

		
		backBtn = new JButton("Back To Menu");
		
		searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		searchLabel.setPreferredSize(new Dimension(150, 25));
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
		
		nameLabel = new JLabel("Pantient Name");
		nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nameLabel.setPreferredSize(new Dimension(150, 25));
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
		
		DOBLabel = new JLabel("Patient DOB");
		DOBLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		DOBLabel.setPreferredSize(new Dimension(150, 25));
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

//		DOBField.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
		
		employeeLabel = new JLabel("Employee");
		employeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		employeeLabel.setPreferredSize(new Dimension(150, 25));
		Vector<Employee> currentEmp = loadEmployees();
		employeesCombo = new JComboBox<>(currentEmp);
		employeesCombo.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
		
		symptomLabel = new JLabel("Symptoms");
		symptomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		symptomLabel.setPreferredSize(new Dimension(150, 25));
		symptomField = new JTextField();
		symptomField.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
		
		checkDateLabel = new JLabel("Check Date");
		checkDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		checkDateLabel.setPreferredSize(new Dimension(150, 25));
		
	}
	
	private void setItem() {
		
		northPanel.add(title);
		scrollTable = new JScrollPane(PatientTable);
		centerPanel.add(scrollTable);
		
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		DOBPanel.add(DOBLabel);
//		DOBPanel.add(DOBField);
		employeePanel.add(employeeLabel);
		employeePanel.add(employeesCombo);
		symptomPanel.add(symptomLabel);
		symptomPanel.add(symptomField);
		checkDatePanel.add(checkDateLabel);
		
		formPanel.add(searchPanel);
		formPanel.add(namePanel);
		formPanel.add(DOBPanel);
		formPanel.add(employeePanel);
		formPanel.add(symptomPanel);
		formPanel.add(checkDatePanel);
		formPanel.setBorder(new EmptyBorder(25,25,25,25));
		
		centerPanel.add(formPanel);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		southPanel.add(backBtn);
		
		add(northPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
		
	}
	
	private void setListener() {
		
	}
	
	private Vector<Employee> loadEmployees(){
		
		Vector<Employee> employees = new Vector<Employee>();
		
		Employee employee1 = new Employee();
		employee1.setName("Kevin");
		Employee employee2 = new Employee();
		employee2.setName("Marcell");
		employees.add(employee1);
		employees.add(employee2);
		
		return employees;
	}
	
	private void loadData() {
		
		Object[] header = {"Patient ID","Patient Name","Patient DOB"};
		dtm = new DefaultTableModel(header,0);
		
//		Vector<Employee> employees = 
		
//		for (Item item : items) {
//			Vector<Object> row = new Vector<Object>();
//			row.add(item.getName());
//			row.add(item.getRak());
//			row.add(item.getStockRemain());
//			row.add(item.getQuantityType());
//			
//			dtm.addRow(row);
//		}
		
//		Vector<String> data = new Vector<String>();
//		data.add("Kevin");
//		
//		
//		dtm.addRow(data);
		
		PatientTable.setModel(dtm);
		
	}
	
	public PatientManagementForm() {
		initItem();
		setItem();
		setListener();
		loadData();
		
		
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(true); 
		setLocationRelativeTo(null); 
		setTitle("Patient Management Form");
	}
	
	
	
}
