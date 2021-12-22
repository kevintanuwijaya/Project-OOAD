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
        
}
