package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import utils.DatabaseConnection;

public class PatientDetail {

	private int PatientID;
	private int PatientDetailID;
	private int EmployeeID;
	private String Symptom;
	private Date CheckDate;

	public PatientDetail() {
	}

	public int getPatientID() {
		return PatientID;
	}

	public void setPatientID(int patientID) {
		PatientID = patientID;
	}

	public int getPatientDetailID() {
		return PatientDetailID;
	}

	public void setPatientDetailID(int patientDetailID) {
		PatientDetailID = patientDetailID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public String getSymptom() {
		return Symptom;
	}

	public void setSymptom(String symptom) {
		Symptom = symptom;
	}

	public Date getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(Date checkDate) {
		CheckDate = checkDate;
	}

	/**
	 * @param patientID
	 * @return Get all patient detail by PatientID
	 */
	public List<PatientDetail> GetAllPatientDetail(int patientID) {

		setPatientID(patientID);
		Connection conn = DatabaseConnection.getInstance().getConnection();
		List<PatientDetail> patientDetails = new Vector<PatientDetail>();

		String sqlQuery = "SELECT * FROM patientdetail WHERE PatientID = ?";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getPatientID());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				PatientDetail patientDetail = new PatientDetail();
				patientDetail.setPatientID(rs.getInt("PatientID"));
				patientDetail.setPatientDetailID(rs.getInt("PatientDetailID"));
				patientDetail.setEmployeeID(rs.getInt("EmployeeID"));
				patientDetail.setSymptom(rs.getString("Symptom"));
				patientDetail.setCheckDate(rs.getDate("CheckDate"));

				patientDetails.add(patientDetail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patientDetails;
	}

	/**
	 * 
	 * @return Insert new patientdetail to database
	 */
	public PatientDetail AddPatientDetail() {

		Connection conn = DatabaseConnection.getInstance().getConnection();
		String sqlQuery = "INSERT INTO patientdetail (PatientID, EmployeeID, Symptom, CheckDate) VALUES (?,?,?,?)";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getPatientID());
			stat.setInt(2, getEmployeeID());
			stat.setString(3, getSymptom());
			stat.setDate(4, getCheckDate());

			int add = stat.executeUpdate();

			if (add > 0) {
				return this;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
