package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import utils.DatabaseConnection;

public class Bill {

	private int BillID;
	private int EmployeeID;
	private int PatientID;
	private Date DateTimeCreated;
	private String PaymentType;
	private String Status;

	public Bill() {
	}

	public int getBillID() {
		return BillID;
	}

	public void setBillID(int billID) {
		BillID = billID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public int getPatientID() {
		return PatientID;
	}

	public void setPatientID(int patientID) {
		PatientID = patientID;
	}

	public Date getDateTimeCreated() {
		return DateTimeCreated;
	}

	public void setDateTimeCreated(Date dateTimeCreated) {
		DateTimeCreated = dateTimeCreated;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	/*
	 * Get all bill from database
	 */
	public List<Bill> GetAllBill() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		List<Bill> bill = new Vector<>();

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

	public List<Bill> searchBill(int patientID) {
		List<Bill> foundBill = new Vector<>();
		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "SELECT * FROM bill WHERE PatientID = ?";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, patientID);

			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Bill bil = new Bill();
				bil.setBillID(result.getInt("BillID"));
				bil.setEmployeeID(result.getInt("EmployeeID"));
				bil.setPatientID(result.getInt("PatientID"));
				bil.setDateTimeCreated(result.getDate("DatetimeCreated"));
				bil.setPaymentType(result.getString("PaymentType"));
				bil.setStatus(result.getString("Status"));

				foundBill.add(bil);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundBill;
	}

	public Bill GetBill(int billID) {
		Bill bill = new Bill();
		List<Bill> allBill = bill.GetAllBill();

		for (Bill bil : allBill) {
			if (bil.getBillID() == billID) {
				return bil;
			}
		}

		return bill;
	}

	public Bill AddBill() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "INSERT INTO bill(EmployeeID, PatientID, DateTimeCreated, Status) VALUES(?, ?, ?, ?);";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getEmployeeID());
			stat.setInt(2, getPatientID());
			stat.setDate(3, getDateTimeCreated());
			stat.setString(4, getStatus());

			int result = stat.executeUpdate();

			if (result != -1)
				return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Bill UpdateBill() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "UPDATE bill SET EmployeeID = ?, PatientID = ?, PaymentType = ?, Status = ? WHERE BillID = ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getEmployeeID());
			stat.setInt(2, getPatientID());
			stat.setString(3, getPaymentType());
			stat.setString(4, getStatus());
			stat.setInt(5, getBillID());

			System.out.println("masuk");

			int result = stat.executeUpdate();

			if (result != -1)
				return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}