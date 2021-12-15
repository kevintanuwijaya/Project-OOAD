package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewLoginForm extends JFrame{
	private JPanel topPanel, formPanel, bottomPanel, usernamePanel, passwordPanel;
	private JLabel title, usernameLabel, passwordLabel;
	private JTextField empUsername, empPassword;
	private DefaultTableModel dtm;
	private JButton loginButton;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private void initItem() {
		setLayout(new BorderLayout());
		
		/* For Top Panel */
        topPanel = new JPanel();
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title = new JLabel("Login Form");
        title.setFont(new Font("Segoe U	I", Font.BOLD, 40));
        
        /* For Form Panel */
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        
        usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(150, 25));
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(150, 25));
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        
        empUsername = new JTextField();
        empUsername.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
        empPassword = new JTextField();
        empPassword.setPreferredSize(new Dimension((int)screenSize.getWidth()-400,25));
        
        usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));    
        
        /* For Bottom Panel */
        bottomPanel = new JPanel();

        loginButton = new JButton("Login");
	}
	
	private void setItem() {
		/* Top Panel */
    	topPanel.add(title);
    	
    	/* Form Panel */
        usernamePanel.add(usernameLabel);
        usernamePanel.add(empUsername);
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(empPassword);
        
        formPanel.add(usernamePanel);
        formPanel.add(passwordPanel);
        formPanel.setBorder(new EmptyBorder(25,25,25,25));
        
        /* Bottom Panel */
        bottomPanel.add(loginButton);

        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
	}
	
	private void setListener() {
		
	}
	
	private void loadData() {
		
	}

	public ViewLoginForm() {
		// TODO Auto-generated constructor stub
		initItem();
        setItem();
        setListener();
        loadData();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);f
        setSize(600, 300);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Login Form");
	}

}
