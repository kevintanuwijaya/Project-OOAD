package controllers;

import java.util.List;

import javax.swing.JOptionPane;

import models.Employee;

public class EmployeeController {
	
	private static EmployeeController instance = null;

	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
	
	private EmployeeController() {}
	
	public List<Employee> GetDoctorList(){
		
		Employee employee = new Employee();
		
		List<Employee> allDoctors = employee.GetDoctorList();
		
		return allDoctors;
	}
	
	public List<Employee> GetAllEmployee(){
		
		Employee employee = new Employee();
		
		List<Employee> allEmployees = employee.GetAllEmployee();
		
		return allEmployees;
	}
	
	public Employee AddEmployee(String name, String username, int roleID, String salary, String password) {
		
		//validation
		int salaryInt = Integer.parseInt(salary);

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Nama harus diisi");
			return null;
		} else if (username.equals("")) {
			JOptionPane.showMessageDialog(null, "Username harus diisi");
			return null;
		} else if (salary.equals("")) {
			JOptionPane.showMessageDialog(null, "Salary harus diisi");
			return null;
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "Password harus diisi");
			return null;
		}
		
		Employee employee = new Employee();
		
		employee.setName(name);
		employee.setUsername(username);
		employee.setRoleID(roleID);
		employee.setSalary(salaryInt);
		employee.setPassword(password);
		employee.setStatus("Active");
		employee.InsertEmployee();
		
		return employee;
	}
	
	public Employee UpdateEmployee(String employeeID, String name, String username, int role, String password, String salary) {
		
		//validation
		int employeeIDInt = Integer.parseInt(employeeID);
		int salaryInt = Integer.parseInt(salary);
		
		if (employeeID.equals("")) {
			JOptionPane.showMessageDialog(null, "Pilih List pada Table untuk dapatkan ID");
			return null;
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Nama harus diisi");
			return null;
		} else if (username.equals("")) {
			JOptionPane.showMessageDialog(null, "Username harus diisi");
			return null;
		} else if (username.equals("")) {
			JOptionPane.showMessageDialog(null, "Salary harus diisi");
			return null;
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "Password harus diisi");
			return null;
		}
		
		Employee employee = new Employee();
		
		employee = employee.GetEmployee(employeeIDInt);

		employee.setName(name);
		employee.setUsername(username);
		employee.setRoleID(role);
		employee.setPassword(password);
		employee.setSalary(salaryInt);
		
		employee.UpdateEmployee();
		
		return employee;
	}
	
	public Employee FireEmployee(int employeeID) {
		
		//validation
		
		Employee employee = new Employee();
		
		employee = employee.GetEmployee(employeeID);
		employee.setStatus("Inactive");
		employee.FireEmployee();
		
		return employee;
	}
	
	public Employee loginEmployee(String username, String password) {
		
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		
		return employee.login();
	}
	
	public Employee GetEmployee(int employeeID) {
		
		Employee employee = new Employee();
		
		return employee.GetEmployee(employeeID);
		
	}
	

	

	
	
}
