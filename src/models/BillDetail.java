package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import utils.DatabaseConnection;

public class BillDetail {

	private int BillDetailID;
	private int BillID;
	private int MedicineID;
	private int Quantity;

	public BillDetail() {
		// TODO Auto-generated constructor stub
	}

	public int getBillDetailID() {
		return BillDetailID;
	}

	public void setBillDetailID(int billDetailID) {
		BillDetailID = billDetailID;
	}

	public int getBillID() {
		return BillID;
	}

	public void setBillID(int billID) {
		BillID = billID;
	}

	public int getMedicineID() {
		return MedicineID;
	}

	public void setMedicineID(int medicineID) {
		MedicineID = medicineID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	/*
	 * Add new bill detail
	 */
	public BillDetail AddBillDetail() {
		
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
        String sqlQuery = "INSERT INTO billdetail (BillID, MedicineID, Quantity) VALUES (?, ?, ?);";

        // Statement -> Object yang dipake buat execute
        // static SQL statement
        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            stat.setInt(1, getBillID());
            stat.setInt(2, getMedicineID());
            stat.setInt(3, getQuantity());

            // stat.execute() -> return false kalo gagal di execute & true kalo berhasil
            // stat.executeQuery() -> return table dalam bentuk ResultSet

            int result = stat.executeUpdate();

            if (result != -1) return this;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}
	
	public List<BillDetail> GetAllBillDetail(int billID){
		
Connection conn = DatabaseConnection.getInstance().getConnection();
		
		List<BillDetail> billDetailList = new Vector<>();

        String sqlQuery = "SELECT * FROM billdetail WHERE BillID = ?";

        try {
            PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
            
            stat.setInt(1, billID);

            ResultSet result = stat.executeQuery();

            while (result.next()) {
                BillDetail bilDetail = new BillDetail();
                bilDetail.setBillID(result.getInt("BillID"));
                bilDetail.setBillDetailID((result.getInt("BillDetailID")));
                bilDetail.setMedicineID(result.getInt("MedicineID"));
                bilDetail.setQuantity(result.getInt("Quantity"));

                billDetailList.add(bilDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billDetailList;
	}
	
}
