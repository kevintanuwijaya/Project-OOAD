package models;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

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
		
		//add new patient to database
		
		return this;
	}
	
	public List<Patient> GetAllPatient(){
		
		List<Patient> allPatients = new Vector<Patient>();
		
		//get all patient from database
		
		return allPatients;
	}
	
	public List<Patient> SearchPatient(String name){
		
		List<Patient> selectedPatients = new Vector<Patient>();
		
		//get some patient by name from database
		
		return selectedPatients;
	}
	
	public Patient UpdatePatient() {
		
		//update this patient data
		
		return this;
	}
	
	public Patient GetPatient(int patientID) {
		
		//get patient data by name in database
		//set all attribute from database to this class
		
		return this;
	}

}
