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
	
	
	
	// public Employee GetEmployee(int employeeID) {
		
	// 	//get employee data by employeeID
	// 	//set all employee attributes from database to this class
	// 	Employee employee = 
		
	// 	return this;
	// }
	public Employee GetEmployee(int employeeID) {

        // get employee data by employeeID
        // set all employee attributes from database to this class
        Employee employee = new Employee();
        List<Employee> allEmployee = employee.GetAllEmployee();

        for (Employee emp : allEmployee) {
            if (emp.getEmployeeID() == employeeID) {
                return emp;
            }
        }

        return employee;
    }
	
	public Employee InsertEmployee() {
		
		DatabaseConnection.getInstance().insertEmployee(this);
		
		return this;
	}
	
	public Employee UpdateEmployee() {
		
		DatabaseConnection.getInstance().updateEmployee(this);
		
		return this;
	}

	public Employee FireEmployee() {
		
		DatabaseConnection.getInstance().fireEmployee(this);
		
		return this;
	}
	
	
	
	public Employee getEmployee(String username, String password) {
		
		return DatabaseConnection.getInstance().getEmployee(username, password);
		
	}
	
	public List<Employee> GetAllEmployee(){

        return DatabaseConnection.getInstance().getAllEmployee();

    }
	public List<Employee> GetDoctorList(){

        return DatabaseConnection.getInstance().getDoctorList();

    }

}
