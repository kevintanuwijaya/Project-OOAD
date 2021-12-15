package models;

import java.util.List;
import java.util.Vector;

import utils.DatabaseConnection;

public class Employee {
	
	private int EmployeeID; 
	private int RoleID;
	private String Name;
	private String Username;
	private String Password;
	private int Salary;
	private String Status;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public int getRoleID() {
		return RoleID;
	}
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Name;
	}
	
	public List<Employee> GetAllEmployee(){
		
		List<Employee> allEmployees = new Vector<Employee>();
		
		//get all employees from database
		
		return allEmployees;
	}
	
	public Employee GetEmployee(int employeeID) {
		
		//get employee data by employeeID
		//set all employee attributes from database to this class
		
		return this;
	}
	
	public Employee InsertEmployee() {
		
		//add this employee to database
		
		return this;
	}
	
	public Employee UpdateEmployee() {
		
		//update this employee from database
		
		return this;
	}
	
	public List<Employee> GetDoctorList(){
		
		List<Employee> allDoctors = new Vector<Employee>();
		
		//get all doctors from database
		
		return allDoctors;
	}
	
	public Employee getEmployee(String username, String password) {
		
		return DatabaseConnection.getInstance().getEmployee(username, password);
		
	}
	
	
	

}
