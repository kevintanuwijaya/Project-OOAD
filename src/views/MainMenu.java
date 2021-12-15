package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {
	
	private JPanel topPanel, centerPanel, mainPanel;
	private JLabel title, welcome;
	private JButton BtnEmpManagement, btnBillManagement, btnMedManagement, btnPatientManagement;
	
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
        centerPanel = new JPanel(new GridLayout(2, 2, 50, 50));
    	
        btnBillManagement = new JButton("Bill Management");
        btnBillManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        BtnEmpManagement = new JButton("Employee Management");
        BtnEmpManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnMedManagement = new JButton("Medicine Management");
        btnMedManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnPatientManagement = new JButton("Patient Management");
        btnPatientManagement.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	}
	
	private void setItem() {
		topPanel.add(title);
		topPanel.add(welcome);
		
		centerPanel.add(btnBillManagement);
		centerPanel.add(BtnEmpManagement);
		centerPanel.add(btnMedManagement);
		centerPanel.add(btnPatientManagement);
		centerPanel.setBorder(new EmptyBorder(200, 200, 200, 200));
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		
		this.add(mainPanel);
		
	}
	
	private void setListener() {
		
	}
	
	private void loadData() {
		
	}

	public MainMenu() {
		initItem();
		setItem();
		setListener();
		loadData();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Main Menu");
	}
 
}
