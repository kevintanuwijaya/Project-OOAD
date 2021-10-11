package models;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

public class PatientDetail {

	private int PatientID;
	private int PatientDetailID;
	private int EmployeeID;
	private String Symptom;
	private Date CheckDate;
	
	public PatientDetail() {
		// TODO Auto-generated constructor stub
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
	
	public List<PatientDetail> GetAllPatientDetail(int patientID){
		List<PatientDetail> patientDetails = new Vector<PatientDetail>();
		
		//get all patient details from database
		
		return patientDetails;
	}
	
	public PatientDetail AddPatientDetail() {
		
		//add this patient detail to database
		
		return this;
	}
	
	

}
