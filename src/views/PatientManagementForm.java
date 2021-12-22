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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.util.List;
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
import models.Patient;
import models.PatientDetail;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PatientManagementForm extends JFrame{

	private JPanel northPanel,centerPanel,southPanel;
	private JPanel formPanel, searchPanel, patientIDPanel,namePanel, DOBPanel, employeePanel, symptomPanel, checkDatePanel, tablePanel;
	private JLabel title, searchLabel, patientIDLabel, nameLabel, DOBLabel, employeeLabel, symptomLabel, checkDateLabel;
	private JTextField patientIDField, searchField, nameField,symptomField;
	private JTable PatientTable, PatientDetailTabel;
	private DefaultTableModel dtmPatient, dtmPatientDetail;
	private UtilDateModel DOBModel, checkDateModel;
	private JDatePanelImpl DOBDatePanel, checkDateDatePanel;
	private JDatePickerImpl DOBPicker, checkDatePicker;
	private JButton searchBtn,backBtn, insertBtn, updateBtn,clearBtn;
	private JScrollPane patientScrollPane, patientDetailScrollPane;
	private JComboBox<Employee> employeesCombo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Employee employee = new Employee();
	
	private void initItem() {
		//employee dummy
		employee.setRoleID(3);
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
		
		searchBtn = new JButton("Search");
		backBtn = new JButton("Back To Menu");
		clearBtn = new JButton("Clear Field");
		
		patientIDPanel = new JPanel();
		patientIDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		patientIDPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),25));
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
		
		updateBtn = new JButton("Update Patient");

		patientIDLabel = new JLabel("Patient ID");
		patientIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		patientIDLabel.setPreferredSize(new Dimension(150, 25));
		patientIDField = new JTextField();
		patientIDField.setPreferredSize(new Dimension((int)screenSize.getWidth()-300,25));
		patientIDField.setEditable(false);
		
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
		List<Employee> currentEmp = loadEmployees();
		employeesCombo = new JComboBox<>((Vector) currentEmp);
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
		PatientTable.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = PatientTable.getSelectedRow();
				patientIDField.setText(PatientTable.getValueAt(row, 0).toString());
				int patientID = Integer.parseInt(PatientTable.getValueAt(row, 0).toString());
				loadDataPatientDetail(patientID);
			}
			
		});
		
		
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
		PatientDetailTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = PatientTable.getSelectedRow();
				symptomField.setText(PatientDetailTabel.getValueAt(row, 3).toString());
				Date selectedDate = Date.valueOf((PatientDetailTabel.getValueAt(row, 4).toString()));
				String[] datas = selectedDate.toLocalDate().toString().split("-");
//				int year = Integer.parseInt(datas[0]);
//				int month = Integer.parseInt(datas[1]);
//				int day = Integer.parseInt(datas[2]);
//				checkDatePicker.getModel().setYear(year);
				
				
				
			}
		});
	}
	
	private void setItem() {
		
		northPanel.add(title);
		patientScrollPane = new JScrollPane(PatientTable);
		patientScrollPane.setPreferredSize(new Dimension(((int)screenSize.getWidth()-300)/2, 400));
		patientDetailScrollPane = new JScrollPane(PatientDetailTabel);
		patientDetailScrollPane.setPreferredSize(new Dimension(((int)screenSize.getWidth()-300)/2, 400));
		tablePanel.add(patientScrollPane);
		tablePanel.add(patientDetailScrollPane);
		centerPanel.add(tablePanel);
		
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		
		formPanel.add(searchPanel);
		formPanel.setBorder(new EmptyBorder(25,25,25,25));
		
		if(employee.getRoleID() == 1) {
			insertBtn = new JButton("Insert Patient");
			namePanel.add(nameLabel);
			namePanel.add(nameField);
			DOBPanel.add(DOBLabel);
			DOBPanel.add(DOBPicker);
			formPanel.add(namePanel);
			formPanel.add(DOBPanel);
			southPanel.add(searchBtn);
			southPanel.add(insertBtn);
			
		}else
			if(employee.getRoleID() == 3 || employee.getRoleID() == 4) {
				insertBtn = new JButton("Insert Patient Detail");
				patientIDPanel.add(patientIDLabel);
				patientIDPanel.add(patientIDField);
				employeePanel.add(employeeLabel);
				employeePanel.add(employeesCombo);
				symptomPanel.add(symptomLabel);
				symptomPanel.add(symptomField);
				checkDatePanel.add(checkDateLabel);
				checkDatePanel.add(checkDatePicker);
				southPanel.add(insertBtn);
				southPanel.add(updateBtn);
	
				formPanel.add(patientIDPanel);
				formPanel.add(employeePanel);
				formPanel.add(symptomPanel);
				formPanel.add(checkDatePanel);
			}
		
				
		centerPanel.add(formPanel);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		southPanel.add(clearBtn);
		southPanel.add(backBtn);

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
				DOBDatePanel.getModel().setValue(null);
				employeesCombo.setSelectedIndex(0);
				symptomField.setText("");
				checkDateDatePanel.getModel().setValue(null);
			}
		});
		
//		if(employee.getRoleID() == 3 || employee.getRoleID() == 4) {
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
//		}
		
	}
	
	private List<Employee> loadEmployees(){
		Employee model = new Employee();
		return model.GetDoctorList();
	}
	
	private void loadDataPatient() {
		
		Object[] header = {"Patient ID","Patient Name","Patient DOB"};
		dtmPatient = new DefaultTableModel(header,0);
		
		Patient modelPatient = new Patient();
		List<Patient> patients = modelPatient.GetAllPatient();
		
		for (Patient patient : patients) {
			Vector<Object> row = new Vector<Object>();
			row.add(patient.getPatientID());
			row.add(patient.getName());
			row.add(patient.getDOB());
			
			dtmPatient.addRow(row);
		}
		
		PatientTable.setModel(dtmPatient);
	}
	
	private void loadDataPatientDetail(int patientID) {
		
		Object[] header = {"Patient ID","PatientDetail ID","Employee ID","Symptom","CheckDate"};
		dtmPatientDetail = new DefaultTableModel(header,0);
		
		PatientDetail modelPatientDetail = new PatientDetail();
		List<PatientDetail> patientDetails = modelPatientDetail.GetAllPatientDetail(patientID);
		
		for (PatientDetail patientDetail : patientDetails) {
			Vector<Object> row = new Vector<Object>();
			row.add(patientDetail.getPatientID());
			row.add(patientDetail.getPatientDetailID());
			row.add(patientDetail.getEmployeeID());
			row.add(patientDetail.getSymptom());
			row.add(patientDetail.getCheckDate());
			
			dtmPatientDetail.addRow(row);
		}
		
		PatientDetailTabel.setModel(dtmPatientDetail);
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
