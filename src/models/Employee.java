package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

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
		return Name;
	}

	/*
	 * Print employee ID
	 */
	public Employee GetEmployee(int employeeID) {
		setEmployeeID(employeeID);
		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "SELECT * FROM employee where EmployeeID = ?";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getEmployeeID());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				setEmployeeID(rs.getInt("EmployeeID"));
				setRoleID(rs.getInt("RoleID"));
				setName(rs.getString("Name"));
				setSalary(rs.getInt("Salary"));
				setStatus(rs.getNString("Status"));
				setPassword(rs.getNString("Password"));

				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * Insert employee in database
	 */
	public Employee InsertEmployee() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "INSERT INTO employee(RoleID, Name, Username, Password, Salary, Status) VALUES(?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getRoleID());
			stat.setString(2, getName());
			stat.setString(3, getUsername());
			stat.setString(4, getPassword());
			stat.setInt(5, getSalary());
			stat.setString(6, getStatus());

			int result = stat.executeUpdate();

			if (result != -1) {
				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Update employee in database
	 */
	public Employee UpdateEmployee() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "UPDATE employee SET Name = ?, Username = ?, Password = ?, Salary = ?, RoleID = ?, Status = ? WHERE EmployeeID = ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, getName());
			stat.setString(2, getUsername());
			stat.setString(3, getPassword());
			stat.setInt(4, getSalary());
			stat.setInt(5, getRoleID());
			stat.setString(6, getStatus());
			stat.setInt(7, getEmployeeID());

			int result = stat.executeUpdate();

			if (result > 0) {
				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Fire employee
	 */
	public Employee FireEmployee() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "UPDATE employee SET Status = ? WHERE EmployeeID = ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, getStatus());
			stat.setInt(2, getEmployeeID());

			int result = stat.executeUpdate();

			if (result > 0) {
				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Get all available doctor
	 */
	public Employee login() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "SELECT * FROM employee where Username = ? AND Password = ?";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, getUsername());
			stat.setString(2, getPassword());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				setEmployeeID(rs.getInt("EmployeeID"));
				setRoleID(rs.getInt("RoleID"));
				setName(rs.getString("Name"));
				setSalary(rs.getInt("Salary"));
				setStatus(rs.getNString("Status"));

				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Get all employee
	 */
	public List<Employee> GetAllEmployee() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		List<Employee> employees = new Vector<Employee>();

		String sqlQuery = "SELECT * FROM employee";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				Employee employee = new Employee();
				employee.setEmployeeID(rs.getInt("EmployeeID"));
				employee.setRoleID(rs.getInt("RoleID"));
				employee.setName(rs.getString("Name"));
				employee.setUsername(rs.getString("Username"));
				employee.setPassword(rs.getString("Password"));
				employee.setSalary(rs.getInt("Salary"));
				employee.setStatus(rs.getNString("Status"));

				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;

	}

	/*
	 * Get all available doctor
	 */
	public List<Employee> GetDoctorList() {

		Connection conn = DatabaseConnection.getInstance().getConnection();
		String role = "Doctor";
		List<Employee> employees = new Vector<Employee>();

		String sqlQuery = "SELECT * FROM employee E JOIN role R ON R.RoleID = E.RoleID WHERE R.Name = ? AND E.Status LIKE 'Active'";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, role);

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				Employee employee = new Employee();
				employee.setEmployeeID(rs.getInt("EmployeeID"));
				employee.setRoleID(rs.getInt("RoleID"));
				employee.setName(rs.getString("Name"));
				employee.setUsername(rs.getString("Username"));
				employee.setPassword(rs.getString("Password"));
				employee.setSalary(rs.getInt("Salary"));
				employee.setStatus(rs.getNString("Status"));

				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;

	}

}
