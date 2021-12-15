package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PatientManagementForm extends JFrame{

	private JPanel northPanel,centerPanel,southPanel;
	private JPanel formPanel, searchPanel, namePanel, DOBPanel, employeePanel, symptomPanel, checkDatePanel, tablePanel;
	private JLabel title, searchLabel, nameLabel, DOBLabel, employeeLabel, symptomLabel, checkDateLabel;
	private JTextField searchField, nameField,symptomField;
	private JTable PatientTable, PatientDetailTabel;
	private DefaultTableModel dtmPatient, dtmPatientDetail;
	private UtilDateModel DOBModel, checkDateModel;
	private JDatePanelImpl DOBDatePanel, checkDateDatePanel;
	private JDatePickerImpl DOBPicker, checkDatePicker;
	private JButton backBtn, insertBtn, updateBtn, deleteBtn,clearBtn;
	private JScrollPane patientScrollPane, patientDetailScrollPane;
	private JComboBox<Employee> employeesCombo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Employee employee = new Employee();
	
	private void initItem() {
		//employee dummy
		employee.setRoleID(1);
		setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
		
		tablePanel = new JPanel();
		
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));
		formPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
		
		searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		searchLabel.setPreferredSize(new Dimension(150, 25));
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension((int)screenSize.getWidth()-300,25));
		
		backBtn = new JButton("Back To Menu");
		clearBtn = new JButton("Clear Field");
		
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
		
		insertBtn = new JButton("Insert Patient");
		updateBtn = new JButton("Update Patient");
		deleteBtn = new JButton("Delete Patient");

		nameLabel = new JLabel("Pantient Name");
		nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nameLabel.setPreferredSize(new Dimension(150, 25));
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension((int)screenSize.getWidth()-300,25));
		
		DOBLabel = new JLabel("Patient DOB");
		DOBLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		DOBLabel.setPreferredSize(new Dimension(150, 25));
		
		DOBModel = new UtilDateModel();
		DOBDatePanel = new JDatePanelImpl(DOBModel);
		DOBPicker = new JDatePickerImpl(DOBDatePanel);
		
		employeeLabel = new JLabel("Employee");
		employeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		employeeLabel.setPreferredSize(new Dimension(150, 25));
		Vector<Employee> currentEmp = loadEmployees();
		employeesCombo = new JComboBox<>(currentEmp);
		employeesCombo.setPreferredSize(new Dimension((int)screenSize.getWidth()-300,25));
		
		symptomLabel = new JLabel("Symptoms");
		symptomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		symptomLabel.setPreferredSize(new Dimension(150, 25));
		symptomField = new JTextField();
		symptomField.setPreferredSize(new Dimension((int)screenSize.getWidth()-300,25));
		
		checkDateLabel = new JLabel("Check Date");
		checkDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		checkDateLabel.setPreferredSize(new Dimension(150, 25));
		
		checkDateModel = new UtilDateModel();
		checkDateDatePanel = new JDatePanelImpl(checkDateModel);
		checkDatePicker = new JDatePickerImpl(checkDateDatePanel);
		
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
		
		PatientDetailTabel = new JTable() {
			
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
	}
	
	private void setItem() {
		
		northPanel.add(title);
		patientScrollPane = new JScrollPane(PatientTable);
		patientDetailScrollPane = new JScrollPane(PatientDetailTabel);
		tablePanel.add(patientScrollPane);
		tablePanel.add(patientDetailScrollPane);
		centerPanel.add(tablePanel);
		
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		
		formPanel.add(searchPanel);
		formPanel.setBorder(new EmptyBorder(25,25,25,25));
		
		if(employee.getRoleID() == 3 || employee.getRoleID() == 4) {
			namePanel.add(nameLabel);
			namePanel.add(nameField);
			DOBPanel.add(DOBLabel);
			DOBPanel.add(DOBPicker);
			employeePanel.add(employeeLabel);
			employeePanel.add(employeesCombo);
			symptomPanel.add(symptomLabel);
			symptomPanel.add(symptomField);
			checkDatePanel.add(checkDateLabel);
			checkDatePanel.add(checkDatePicker);
			southPanel.add(insertBtn);
			southPanel.add(updateBtn);
			southPanel.add(deleteBtn);

			
			formPanel.add(namePanel);
			formPanel.add(DOBPanel);
			formPanel.add(employeePanel);
			formPanel.add(symptomPanel);
			formPanel.add(checkDatePanel);
		}
		
				
		centerPanel.add(formPanel);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		southPanel.add(backBtn);
		southPanel.add(clearBtn);

		add(northPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
		
	}
	
	private void setListener() {
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchField.setText("");
				nameField.setText("");
				employeesCombo.setSelectedIndex(0);
				symptomField.setText("");
			}
		});
		
		if(employee.getRoleID() == 3 || employee.getRoleID() == 4) {
			insertBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			updateBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
	}
	
	private Vector<Employee> loadEmployees(){
		
		Vector<Employee> employees = new Vector<Employee>();
		
		return employees;
	}
	
	private void loadDataPatient() {
		
		Object[] header = {"Patient ID","Patient Name","Patient DOB"};
		dtmPatient = new DefaultTableModel(header,0);
		
		PatientTable.setModel(dtmPatient);
		
	}
	
	private void loadDataPatientDetail() {
		
		Object[] header = {"Patient ID","PatientDetail ID","Employee ID","Symptom","CheckDate"};
		dtmPatientDetail = new DefaultTableModel(header,0);
		
		
		
		
		PatientDetailTabel.setModel(dtmPatientDetail);
		
	}
	
	private void insert() {
		
	}
	
	private void delete() {
		
	}
	
	private void update() {
		
	}
	
	public PatientManagementForm() {
		initItem();
		setItem();
		setListener();
		loadDataPatient();
		loadDataPatientDetail();
		
		
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(true); 
		setLocationRelativeTo(null); 
		setTitle("Patient Management Form");
	}
	
	
	
}
