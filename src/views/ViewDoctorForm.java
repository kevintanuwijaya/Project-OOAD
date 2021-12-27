package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableModel;

import controllers.EmployeeController;
import models.Employee;

public class ViewDoctorForm extends JFrame{
	
	private JPanel northPanel,centerPanel,southPanel;
	private JLabel title;
	private JTable DoctorTable;
	private DefaultTableModel dtm;
	private JButton backBtn;
	private JScrollPane scrollTable;
	private Employee employee = MainMenu.currentEmployee;
	
	private void initItem() {

		setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
		
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		title = new JLabel("Doctor View");
		title.setFont(new Font("Segoe UI",Font.BOLD,40));
		
		
		DoctorTable = new JTable() {
			
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
		
	}
	
	
	private void setItem() {
		
		northPanel.add(title);
		scrollTable = new JScrollPane(DoctorTable);
		centerPanel.add(scrollTable);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		southPanel.add(backBtn);
		
		add(northPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
	}
	
	private void setListener() 
	{
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu(employee);
				dispose();
			}
			
		});
		
	}
	
	private void loadData() 
	{
		Object[] header = {"Employee ID","Doctor Name","Status"};
		dtm = new DefaultTableModel(header,0);
		
		List<Employee> doctorList = EmployeeController.getInstance().GetDoctorList();
		
		for (Employee employee : doctorList) {
			Vector<Object> row = new Vector<Object>();
			row.add(employee.getEmployeeID());
			row.add(employee.getName());
			row.add(employee.getStatus());
			dtm.addRow(row);
		}
		
		DoctorTable.setModel(dtm);	
	}
	
	
	public ViewDoctorForm() {
		initItem();
		setItem();
		setListener();
		loadData();
		
		
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(true); 
		setLocationRelativeTo(null); 
		setTitle("Doctor View");
	}

}
