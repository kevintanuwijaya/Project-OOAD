package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Employee;

public class MainMenu extends JFrame {
	
	private JPanel topPanel, centerPanel, mainPanel;
	private JLabel title, welcome;
	private JButton BtnEmpManagement, btnBillManagement, btnMedManagement, btnPatientManagement, btnDoctorView;
	
	public static Employee currentEmployee;
	
	private void initItem() {
    	setLayout(new BorderLayout());
    	
    	/* Main Panel */
    	mainPanel = new JPanel(new BorderLayout());
    	
    	
    	 /* For Top Panel */
        topPanel = new JPanel(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        
        title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        welcome = new JLabel("Welcome to Mo Ware Hospital!");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        
        /* For Center Panel */
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnBillManagement = new JButton("Bill Management");
        btnBillManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnBillManagement.setPreferredSize(new Dimension(300,50));
        BtnEmpManagement = new JButton("Employee Management");
        BtnEmpManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        BtnEmpManagement.setPreferredSize(new Dimension(300,50));
        btnMedManagement = new JButton("Medicine Management");
        btnMedManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnMedManagement.setPreferredSize(new Dimension(300,50));
        btnPatientManagement = new JButton("Patient Management");
        btnPatientManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnPatientManagement.setPreferredSize(new Dimension(300,50));
        btnDoctorView = new JButton("View Doctor");
        btnDoctorView.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnDoctorView.setPreferredSize(new Dimension(300,50));
        
	}
	
	private void setItem() {
		topPanel.add(title);
		topPanel.add(welcome);
		
		if(currentEmployee.getRoleID() == 1) {
			centerPanel.add(btnBillManagement);
			centerPanel.add(btnPatientManagement);
			centerPanel.add(btnDoctorView);
		}else
			if(currentEmployee.getRoleID() == 2) {
				centerPanel.add(btnMedManagement);
			}else
				if(currentEmployee.getRoleID() == 3) {
					centerPanel.add(btnMedManagement);
					centerPanel.add(btnPatientManagement);
				}else
					if(currentEmployee.getRoleID() == 4) {
						centerPanel.add(btnPatientManagement);
					}else 
						if(currentEmployee.getRoleID() == 5){
							centerPanel.add(BtnEmpManagement);
						}
		
		centerPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		
		this.add(mainPanel);
		
	}
	
	private void setListener() {
		
		btnBillManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnMedManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MedicineManagementForm();
				dispose();
			}
		});
		
		btnPatientManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PatientManagementForm();
				dispose();
				
			}
		});
		
		BtnEmpManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewEmployeeForm();
				dispose();
			}
		});
		
		btnDoctorView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewDoctorForm();
				dispose();
			}
		});
		
	}
	
	private void loadData() {
		
	}

	public MainMenu(Employee employee) {
		this.currentEmployee = employee;
		initItem();
		setItem();
		setListener();
		loadData();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 500);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Main Menu");
	}
 
}
