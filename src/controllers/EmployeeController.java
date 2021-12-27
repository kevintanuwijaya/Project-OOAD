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
	
	public Employee AddEmployee(String name, String username, int roleID, int salary, String password) {
		
		//validation

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Nama harus diisi");
			return null;
		} else if (username.equals("")) {
			JOptionPane.showMessageDialog(null, "Username harus diisi");
			return null;
		}
		
		Employee employee = new Employee();
		
		employee.setName(name);
		employee.setUsername(username);
		employee.setRoleID(roleID);
		employee.setSalary(salary);
		employee.setPassword(password);
		employee.setStatus("Active");
		employee.InsertEmployee();
		
		return employee;
	}
	
	public Employee UpdateEmployee(int employeeID, String name, String username, int role, String password, int salary) {
		
		//validation
		
		Employee employee = new Employee();
		
		employee = employee.GetEmployee(employeeID);

		employee.setName(name);
		employee.setUsername(username);
		employee.setRoleID(role);
		employee.setPassword(password);
		employee.setSalary(salary);
		
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
