package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import models.Bill;
import models.BillDetail;
import models.Employee;
import models.Medicine;

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

    public Vector<Medicine> getAllMedicines() {
        Vector<Medicine> meds = new Vector<>();

        String sqlQuery = "SELECT * FROM medicine";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

            ResultSet result = stat.executeQuery(sqlQuery);

            while (result.next()) {
                Medicine med = new Medicine();
                med.setMedicineID(result.getInt("MedicineId"));
                med.setName(result.getString("Name"));
                med.setPrice(result.getInt("Price"));
                med.setStock(result.getInt("Stock"));

                meds.add(med);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meds;
    }

    public Boolean insertMedicine(Medicine medicine) {
        String name = medicine.getName();
        int price = medicine.getPrice();
        int stock = medicine.getStock();
        String sqlQuery = "INSERT INTO medicine(Name, Price, Stock) VALUES(?, ?, ?);";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, name);
            stat.setInt(2, price);
            stat.setInt(3, stock);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean updateMedicine(Medicine medicine) {
        int id = medicine.getMedicineID();
        String name = medicine.getName();
        int price = medicine.getPrice();
        int stock = medicine.getStock();

        String sqlQuery = "UPDATE medicine SET Name = ?, Price = ?, Stock = ? WHERE MedicineId = ?;";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, name);
            stat.setInt(2, price);
            stat.setInt(3, stock);
            stat.setInt(4, id);

            int result = stat.executeUpdate();

            if (result != -1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean deleteMedicine(Medicine medicine) {
        int id = medicine.getMedicineID();

        String sqlQuery = "DELETE FROM medicine WHERE MedicineId = ?;";

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

    public Vector<Medicine> searchMedicine(String name) {
        Vector<Medicine> foundMedicine = new Vector<>();

        String sqlQuery = "SELECT * FROM medicine WHERE Name LIKE '%" + name + "%';";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

            ResultSet result = stat.executeQuery(sqlQuery);

            while (result.next()) {
                Medicine med = new Medicine();
                med.setMedicineID(result.getInt("MedicineId"));
                med.setName(result.getString("Name"));
                med.setPrice(result.getInt("Price"));
                med.setStock(result.getInt("Stock"));

                foundMedicine.add(med);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundMedicine;
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
        String sqlQuery = "INSERT INTO bill(EmployeeID, PatientID, DateTimeCreated, PaymentType) VALUES(?, ?, ?);";

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
}
