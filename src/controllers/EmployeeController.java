package controllers;

import java.util.List;
import java.util.Vector;

import models.Employee;

public class EmployeeController {

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
	
	public Employee AddEmployee(String name, String username, int roleID, int salary) {
		
		//validation
		
		Employee employee = new Employee();
		
		employee.setName(name);
		employee.setUsername(username);
		employee.setRoleID(roleID);
		employee.setSalary(salary);
		employee.InsertEmployee();
		
		return employee;
	}
	
	public Employee UpdateEmployee(int employeeID, String username, String password, int salary) {
		
		//validation
		
		Employee employee = new Employee();
		
		employee.GetEmployee(employeeID);
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setSalary(salary);
		
		employee.UpdateEmployee();
		
		return employee;
	}
	
	public Employee FireEmployee(int employeeID) {
		
		//validation
		
		Employee employee = new Employee();
		
		employee.GetEmployee(employeeID);
		employee.setStatus("Retired");
		employee.UpdateEmployee();
		
		return employee;
	}
	

	

	

	
	
}
