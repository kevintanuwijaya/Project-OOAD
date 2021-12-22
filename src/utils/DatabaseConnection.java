package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import models.Bill;
import models.BillDetail;
import models.Employee;
import models.Medicine;
import models.Patient;
import models.PatientDetail;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private Connection conn;

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_lab_ooad", "root", "");
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failed");
        }
    }
    
    public Connection getConnection() {
    	return this.conn;
    }

    public Vector<Bill> getAllBill() {
        Vector<Bill> bill = new Vector<>();

        String sqlQuery = "SELECT * FROM bill";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

            ResultSet result = stat.executeQuery(sqlQuery);

            while (result.next()) {
                Bill bil = new Bill();
                bil.setBillID(result.getInt("BillID"));
                bil.setEmployeeID(result.getInt("EmployeeID"));
                bil.setPatientID(result.getInt("PatientID"));
                bil.setDateTimeCreated(result.getDate("DatetimeCreated"));
                bil.setPaymentType(result.getString("PaymentType"));
                bil.setStatus(result.getString("Status"));

                bill.add(bil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bill;
    }

    public Boolean insertBill(Bill bill) {
        int employeeID = bill.getEmployeeID();
        int patientID = bill.getPatientID();
        Date dateTimeCreated = bill.getDateTimeCreated();
        String paymentType = bill.getPaymentType();
        String sqlQuery = "INSERT INTO bill(EmployeeID, PatientID, DateTimeCreated, PaymentType) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, employeeID);
            stat.setInt(2, patientID);
            stat.setDate(3, dateTimeCreated);
            stat.setString(4, paymentType);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean updateBill(Bill bill) {
        int employeeID = bill.getEmployeeID();
        int patientID = bill.getPatientID();
        Date dateTimeCreated = bill.getDateTimeCreated();
        String paymentType = bill.getPaymentType();

        String sqlQuery = "UPDATE bill SET EmployeeID = ?, PatientID = ?, DateTimeCreated = ?, PaymentType = ? WHERE BillID = ?;";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, employeeID);
            stat.setInt(2, patientID);
            stat.setDate(3, dateTimeCreated);
            stat.setString(4, paymentType);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean deleteBill(Bill bill) {
        int id = bill.getBillID();

        String sqlQuery = "DELETE FROM bill WHERE BillID = ?;";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, id);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public Boolean AddBillDetail(BillDetail billDetail) {
        int billDetailID = billDetail.getBillDetailID();
        int billID = billDetail.getBillID();
        int medicineID = billDetail.getMedicineID();
        int quantity = billDetail.getQuantity();
        String sqlQuery = "INSERT INTO billdetail(BillDetailID, BillID, MedicineID, Quantity) VALUES(?, ?, ?, ?);";

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1,  billDetailID);
            stat.setInt(2, billID);
            stat.setInt(3, medicineID);
            stat.setInt(4, quantity);

            // stat.execute() -> return false kalo gagal di execute & true kalo berhasil
            // stat.executeQuery() -> return table dalam bentuk ResultSet

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    

    // public Boolean insertBillDetail(BillDetail billDetail) {
    // int billDetailID = billDetail.getBillDetailID();
    // int patientID = billDetail.getPatientID();
    // Date dateTimeCreated = billDetail.getDateTimeCreated();
    // String paymentType = billDetail.getPaymentType();
    // String sqlQuery = "INSERT INTO bill(EmployeeID, PatientID, DateTimeCreated,
    // PaymentType) VALUES(?, ?, ?);";
    //
    // // Statement -> Object yang dipake buat execute
    // // static SQL statement
    // try {
    // PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
    // stat.setInt(1, employeeID);
    // stat.setInt(2, patientID);
    // stat.setDate(3, dateTimeCreated);
    // stat.setString(4, paymentType);
    //
    // // stat.execute() -> return false kalo gagal di execute & true kalo berhasil
    // // stat.executeQuery() -> return table dalam bentuk ResultSet
    //
    // int result = stat.executeUpdate();
    //
    // if (result != -1)
    // return true;
    //
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    //
    // return false;
    // }
    
    
    /*
     * Employee
     */
    public List<Employee> getAllEmployee(){
		
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

	public List<Employee> getDoctorList(){
		
		List<Employee> employees = new Vector<Employee>();
		
		String sqlQuery = "SELECT * FROM employee E JOIN role R ON R.RoleID = E.RoleID WHERE R.Name = ?";
		
		try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, "Doctor");

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {

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

    public Employee getEmployee(String username, String password) {

        String sqlQuery = "SELECT * FROM employee where Username = ? AND Password = ?";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, username);
            stat.setString(2, password);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {

                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setRoleID(rs.getInt("RoleID"));
                employee.setName(rs.getString("Name"));
                employee.setUsername(rs.getString("Username"));
                employee.setPassword(rs.getString("Password"));
                employee.setSalary(rs.getInt("Salary"));
                employee.setStatus(rs.getNString("Status"));

                return employee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean insertEmployee(Employee employee) {
        String name = employee.getName();
        String userName = employee.getUsername();
        int roleID = employee.getRoleID();
        int salary = employee.getSalary();
        String password = employee.getPassword();
        String status = "Active";
        String sqlQuery = "INSERT INTO employee(RoleID, Name, Username, Password, Salary, Status) VALUES(?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, roleID);
            stat.setString(2, name);
            stat.setString(3, userName);
            stat.setString(4, password);
            stat.setInt(5, salary);
            stat.setString(6, status);

            int result = stat.executeUpdate();

            if (result != -1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateEmployee(Employee employee){
        int id = employee.getEmployeeID();

        String name = employee.getName();
        String userName = employee.getUsername();
        // int roleID = employee.getRoleID();
        int salary = employee.getSalary();
        String password = employee.getPassword();
        // String status = employee.getStatus();

        String sqlQuery = "UPDATE employee SET Name = ?, Username = ?, Password = ?, Salary = ? WHERE EmployeeID = ?;";
        
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            // stat.setInt(1, roleID);
            stat.setString(1, name);
            stat.setString(2, userName);
            stat.setString(3, password);
            stat.setInt(4, salary);
            // stat.setString(6, status);
            stat.setInt(5, id);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return false;
    }

    public Boolean fireEmployee(Employee employee){
        int id = employee.getEmployeeID();
        String status = "Inactive";

        String sqlQuery = "UPDATE employee SET Status = ? WHERE EmployeeID = ?;";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, status);
            stat.setInt(2, id);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
    
}
