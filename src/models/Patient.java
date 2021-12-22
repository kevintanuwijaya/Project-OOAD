package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import utils.DatabaseConnection;

public class Patient {

	private int PatientID;
	private String Name;
	private Date DOB;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public int getPatientID() {
		return PatientID;
	}

	public void setPatientID(int patientID) {
		PatientID = patientID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	/*
	 * Add Patient into Database
	 */
	
	public Patient AddPatient() {
		
		Connection conn = DatabaseConnection.getInstance().getConnection();
		String sqlQuery = "INSERT INTO patient VALUES (?,?)";
    	
    	try {
	        PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
	        stat.setString(1, this.getName());
	        stat.setDate(2, this.getDOB());
	        
	        int add = stat.executeUpdate();
	        
	        if(add > 0) {
	        	return this;
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return null;
	}
	
	/*
	 * Get all Patient from database
	 */
	
	public List<Patient> GetAllPatient(){
			
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
		List<Patient> patients = new Vector<Patient>();
		
		String sqlQuery = "SELECT * FROM patient";
		
		try {
	        PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
	
	        ResultSet rs = stat.executeQuery();
	
	        if (rs.next()) {
	
	            Patient patient = new Patient();
	            patient.setPatientID(rs.getInt("PatientID"));
	            patient.setName(rs.getString("Name"));
	            patient.setDOB(rs.getDate("DOB"));
	            
	            patients.add(patient);
	        	
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return patients;
	}
	
	/*
	 * Search Patient based on its name
	 */
	public List<Patient> SearchPatient(String name){
		
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
		List<Patient> patients = new Vector<Patient>();
    	
    	String sqlQuery = "SELECT * FROM patient WHERE Name LIKE \'%?$\'";
    	
    	try {
	        PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
	        stat.setString(1, name);
	        
	        ResultSet rs = stat.executeQuery();
	
	        if (rs.next()) {
	
	            Patient patient = new Patient();
	            patient.setPatientID(rs.getInt("PatientID"));
	            patient.setName(rs.getString("Name"));
	            patient.setDOB(rs.getDate("DOB"));
	            
	            patients.add(patient);
	        	
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    	
    	return patients;
	}
	
	/*
	 * Update patient in database
	 */
	public Patient UpdatePatient() {
			
		Connection conn = DatabaseConnection.getInstance().getConnection();
		
		String sqlQuery = "UPDATE patient SET Name = ?, DOB = ? WHERE PatientID = ?";
    	
    	try {
	        PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
	        stat.setString(1, getName());
	        stat.setDate(2, getDOB());
	        stat.setInt(3, getPatientID());
	        
	        int add = stat.executeUpdate();
	        
	        if(add > 0) {
	        	return this;
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return null;
	}
	
	/*
	 * Get patient by ID
	 */
	public Patient GetPatient(int patientID) {
		
		Connection conn = DatabaseConnection.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM patient WHERE PatientID = ?";
    	
    	try {
	        PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
	        stat.setInt(1, getPatientID());
	        
	        ResultSet rs = stat.executeQuery();
	
	        if (rs.next()) {
	            setPatientID(rs.getInt("PatientID"));
	            setName(rs.getString("Name"));
	            setDOB(rs.getDate("DOB"));
	            
	            return this;
	        	
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return null;
	}

}
