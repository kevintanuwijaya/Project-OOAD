package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;
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

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

            // stat.execute() -> return false kalo gagal di execute & true kalo berhasil
            // stat.executeQuery() -> return table dalam bentuk ResultSet

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

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, name);
            stat.setInt(2, price);
            stat.setInt(3, stock);

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

    public Boolean updateMedicine(Medicine medicine) {
        int id = medicine.getMedicineID();
        String name = medicine.getName();
        int price = medicine.getPrice();
        int stock = medicine.getStock();

        String sqlQuery = "UPDATE medicine SET Name = ?, Price = ?, Stock = ? WHERE MedicineId = ?;";

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setString(1, name);
            stat.setInt(2, price);
            stat.setInt(3, stock);
            stat.setInt(4, id);

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

    public Boolean deleteMedicine(Medicine medicine) {
        int id = medicine.getMedicineID();

        String sqlQuery = "DELETE FROM medicine WHERE MedicineId = ?;";

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, id);

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
}
