package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Patient;
import models.PatientDetail;

public class PatientController {

	public static PatientController instance = null;
	
	public static PatientController getInstance() {
		if(instance == null) {
			instance = new PatientController();
		}
		return instance;
	}
	
	private PatientController() {}
	
	public List<Patient> GetAllPatient(){
		
		Patient patient = new Patient();
		
		List<Patient> allPatients = patient.GetAllPatient();
		
		return allPatients;
	}
	
	public PatientDetail AddPatientDetail(int patientID, int employeeID, String symptom) {
		
		//validation
		
		PatientDetail patientDetail = new PatientDetail();
		Date currentDate = new Date();
		
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		
		patientDetail.setPatientID(patientID);
		patientDetail.setEmployeeID(employeeID);
		patientDetail.setSymptom(symptom);
		patientDetail.setCheckDate(date);
		patientDetail.AddPatientDetail();
		
		return patientDetail;
	}
	
	/*
	 * Search patient by Name
	 */
	public List<Patient> SearchPatient(String name){
		
		//validation
		
		Patient modelPatient = new Patient();
		
		List<Patient> selectedPatients = modelPatient.SearchPatient(name);
		
		return selectedPatients;
	}
	
	public Patient UpdatePatient(String patientID, String name, Date DOB) {
		
		//validation
		if(patientID.equals("")) {
			
		} else if(name.equals("")) {
			
		} else if(DOB == null) {
			
		}
		int ID = Integer.parseInt(patientID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(DOB);
		java.sql.Date currDate = java.sql.Date.valueOf(dateStr);
		
		Patient patient = new Patient();
		
		patient.setPatientID(ID);
		patient.setName(name);
		patient.setDOB(currDate);
		patient.UpdatePatient();
		
		return patient;
	}
	
	public Patient GetPatient(int patientID) {
	
		//validation
		
		Patient patient = new Patient();
		
		return patient.GetPatient(patientID);
	}
	
	public Patient AddPatient(String name, Date DOB) {
		
		//validation
		if(name.equals("")) {
			
		}if(DOB == null) {
			
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(DOB);
		java.sql.Date currDate = java.sql.Date.valueOf(dateStr);
		
		Patient patient = new Patient();
		
		patient.setName(name);
		patient.setDOB(currDate);
		patient.AddPatient();
		
		return patient;
	}
	
	public List<PatientDetail> GetAllPatientDetail(int patientID){
		
		//validation
		
		PatientDetail patientDetail = new PatientDetail();
		
		List<PatientDetail> patientDetails = patientDetail.GetAllPatientDetail(patientID);
		
		return patientDetails;
	}
	


	

	


}
