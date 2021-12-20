package models;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

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
	
	public Patient AddPatient() {
		
		Patient patient = DatabaseConnection.getInstance().addPatient(this);
		
		if(patient == null) {
			
		}
		
		return patient;
	}
	
	public List<Patient> GetAllPatient(){
		
		return DatabaseConnection.getInstance().getAllPatient();
	}
	
	public List<Patient> SearchPatient(String name){
		
		return DatabaseConnection.getInstance().searchPatient(name);
	}
	
	public Patient UpdatePatient() {
		
		Patient patient = DatabaseConnection.getInstance().UpdatePatient(this);
		
		if(patient == null) {
			
		}
		
		return this;
	}
	
	public Patient GetPatient(int patientID) {
		
		Patient patient = DatabaseConnection.getInstance().GetPatient(patientID);
		
		if(patient == null) {
			
		}
		
		return this;
	}

}
