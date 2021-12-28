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
import java.util.Date;
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

import controllers.EmployeeController;
import controllers.PatientController;
import models.Employee;
import models.Patient;
import models.PatientDetail;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PatientManagementForm extends JFrame {

	private JPanel northPanel, centerPanel, southPanel;
	private JPanel formPanel, searchPanel, patientIDPanel, namePanel, DOBPanel, employeePanel, symptomPanel,
			checkDatePanel, tablePanel;
	private JLabel title, searchLabel, patientIDLabel, nameLabel, DOBLabel, employeeLabel, symptomLabel;
	private JTextField patientIDField, searchField, nameField, symptomField;
	private JTable PatientTable, PatientDetailTabel;
	private DefaultTableModel dtmPatient, dtmPatientDetail;
	private UtilDateModel DOBModel;
	private JDatePanelImpl DOBDatePanel;
	private JDatePickerImpl DOBPicker;
	private JButton searchBtn, backBtn, insertBtn, updateBtn, clearBtn;
	private JScrollPane patientScrollPane, patientDetailScrollPane;
	private JComboBox<Employee> employeesCombo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Employee employee = MainMenu.currentEmployee;

	private void initItem() {

		setLayout(new BorderLayout());

		northPanel = new JPanel();
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		tablePanel = new JPanel();

		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

		searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		searchLabel.setPreferredSize(new Dimension(150, 25));
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

		searchBtn = new JButton("Search");
		backBtn = new JButton("Back To Menu");
		clearBtn = new JButton("Clear Field");

		patientIDPanel = new JPanel();
		patientIDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		patientIDPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
		DOBPanel = new JPanel();
		DOBPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		DOBPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
		employeePanel = new JPanel();
		employeePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		employeePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
		symptomPanel = new JPanel();
		symptomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		symptomPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));
		checkDatePanel = new JPanel();
		checkDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkDatePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

		updateBtn = new JButton("Update Patient");

		patientIDLabel = new JLabel("Patient ID");
		patientIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		patientIDLabel.setPreferredSize(new Dimension(150, 25));
		patientIDField = new JTextField();
		patientIDField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
		patientIDField.setEditable(false);

		nameLabel = new JLabel("Pantient Name");
		nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nameLabel.setPreferredSize(new Dimension(150, 25));
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

		DOBLabel = new JLabel("Patient DOB");
		DOBLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		DOBLabel.setPreferredSize(new Dimension(150, 25));

		DOBModel = new UtilDateModel();
		DOBDatePanel = new JDatePanelImpl(DOBModel);
		DOBPicker = new JDatePickerImpl(DOBDatePanel);

		employeeLabel = new JLabel("Employee");
		employeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		employeeLabel.setPreferredSize(new Dimension(150, 25));
		List<Employee> currentEmp = EmployeeController.getInstance().GetDoctorList();
		employeesCombo = new JComboBox<>((Vector) currentEmp);
		employeesCombo.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));
		employeesCombo.setSelectedIndex(-1);

		symptomLabel = new JLabel("Symptoms");
		symptomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		symptomLabel.setPreferredSize(new Dimension(150, 25));
		symptomField = new JTextField();
		symptomField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

		title = new JLabel("Patient Management View");
		title.setFont(new Font("Segoe UI", Font.BOLD, 40));

		PatientTable = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public void sorterChanged(RowSorterEvent e) {
				super.sorterChanged(e);
			}
		};
		PatientTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = PatientTable.getSelectedRow();
				patientIDField.setText(PatientTable.getValueAt(row, 0).toString());
				nameField.setText(PatientTable.getValueAt(row, 1).toString());
				java.sql.Date date = java.sql.Date.valueOf(PatientTable.getValueAt(row, 2).toString());
				Date selectedDate = new Date(date.getTime());
				DOBDatePanel.getModel().setDate(1900 + selectedDate.getYear(), selectedDate.getMonth(),
						selectedDate.getDay());
				DOBDatePanel.getModel().setSelected(true);
				String patientID = PatientTable.getValueAt(row, 0).toString();
				loadDataPatientDetail(patientID);
			}

		});

		PatientDetailTabel = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public void sorterChanged(RowSorterEvent e) {
				super.sorterChanged(e);
			}
		};
		PatientDetailTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = PatientDetailTabel.getSelectedRow();
				patientIDField.setText(PatientDetailTabel.getValueAt(row, 0).toString());
				symptomField.setText(PatientDetailTabel.getValueAt(row, 3).toString());
				java.sql.Date date = java.sql.Date.valueOf(PatientDetailTabel.getValueAt(row, 4).toString());
				Date selectedDate = new Date(date.getTime());
				int employeeID = Integer.parseInt(PatientDetailTabel.getValueAt(row, 2).toString());
				employeesCombo.setSelectedIndex(findDoctorInCombo(employeeID));

			}
		});
	}

	private void setItem() {

		northPanel.add(title);
		patientScrollPane = new JScrollPane(PatientTable);
		patientScrollPane.setPreferredSize(new Dimension(((int) screenSize.getWidth() - 300) / 2, 400));
		patientDetailScrollPane = new JScrollPane(PatientDetailTabel);
		patientDetailScrollPane.setPreferredSize(new Dimension(((int) screenSize.getWidth() - 300) / 2, 400));
		tablePanel.add(patientScrollPane);

		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		patientIDPanel.add(patientIDLabel);
		patientIDPanel.add(patientIDField);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		DOBPanel.add(DOBLabel);
		DOBPanel.add(DOBPicker);
		employeePanel.add(employeeLabel);
		employeePanel.add(employeesCombo);
		symptomPanel.add(symptomLabel);
		symptomPanel.add(symptomField);

		formPanel.add(searchPanel);
		formPanel.add(patientIDPanel);

		southPanel.add(searchBtn);

		if (employee.getRoleID() == 1) {
			insertBtn = new JButton("Insert Patient");

			formPanel.add(namePanel);
			formPanel.add(DOBPanel);

			southPanel.add(insertBtn);
			southPanel.add(updateBtn);
		} else if (employee.getRoleID() == 3 || employee.getRoleID() == 4) {
			tablePanel.add(patientDetailScrollPane);
			insertBtn = new JButton("Insert Patient Detail");

			formPanel.add(employeePanel);
			formPanel.add(symptomPanel);
			formPanel.add(checkDatePanel);

			southPanel.add(insertBtn);
		}

		formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		centerPanel.add(tablePanel);
		centerPanel.add(formPanel);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		southPanel.add(clearBtn);
		southPanel.add(backBtn);

		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

	}

	private void setListener() {

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu(employee);
				dispose();

			}
		});

		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
				patientIDField.setText("");
				nameField.setText("");
				DOBDatePanel.getModel().setValue(null);
				employeesCombo.setSelectedIndex(-1);
				symptomField.setText("");
				PatientDetailTabel.setModel(new DefaultTableModel());
				loadAllDataPatient();
			}
		});

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = searchField.getText();
				search(name);
			}
		});

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String patientID = patientIDField.getText();
				String name = nameField.getText();
				Date DOB = (Date) DOBPicker.getModel().getValue();
				PatientController.getInstance().UpdatePatient(patientID, name, DOB);
				loadAllDataPatient();

			}
		});

	}

	private void loadAllDataPatient() {

		Object[] header = { "Patient ID", "Patient Name", "Patient DOB" };
		dtmPatient = new DefaultTableModel(header, 0);

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

	private void loadSearchDataPatient(List<Patient> patients) {

		Object[] header = { "Patient ID", "Patient Name", "Patient DOB" };
		dtmPatient = new DefaultTableModel(header, 0);

		for (Patient patient : patients) {
			Vector<Object> row = new Vector<Object>();
			row.add(patient.getPatientID());
			row.add(patient.getName());
			row.add(patient.getDOB());

			dtmPatient.addRow(row);
		}

		PatientTable.setModel(dtmPatient);
	}

	private void loadDataPatientDetail(String patientID) {

		Object[] header = { "Patient ID", "PatientDetail ID", "Employee ID", "Symptom", "CheckDate" };
		dtmPatientDetail = new DefaultTableModel(header, 0);

		List<PatientDetail> patientDetails = PatientController.getInstance().GetAllPatientDetail(patientID);

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

	private void search(String name) {
		List<Patient> patients = PatientController.getInstance().SearchPatient(name);
		loadSearchDataPatient(patients);
	}

	private void insert() {
		if (employee.getRoleID() == 1) {
			String name = nameField.getText();
			Date DOB = (Date) DOBPicker.getModel().getValue();
			PatientController.getInstance().AddPatient(name, DOB);
			loadAllDataPatient();
		} else if (employee.getRoleID() == 3 || employee.getRoleID() == 4) {
			String patientID = patientIDField.getText();
			List<Employee> doctorList = EmployeeController.getInstance().GetDoctorList();
			int index = employeesCombo.getSelectedIndex();
			if (index < 0) {
				JOptionPane.showMessageDialog(null, "Kolom Employee Kosong");
				return;
			}
			int employeeID = doctorList.get(index).getEmployeeID();
			String symptom = symptomField.getText();
			PatientController.getInstance().AddPatientDetail(patientID, employeeID, symptom);
			loadDataPatientDetail(patientID);
		}
	}

	private int findDoctorInCombo(int employeeID) {
		List<Employee> doctorList = EmployeeController.getInstance().GetDoctorList();

		for (int i = 0; i < doctorList.size(); i++) {
			if (doctorList.get(i).getEmployeeID() == employeeID) {
				return i;
			}
		}
		return -1;
	}

	public PatientManagementForm() {
		initItem();
		setItem();
		setListener();
		loadAllDataPatient();

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Patient Management Form");
	}

}
