package controllers;

import java.sql.Date;
import java.util.List;

import models.Patient;
import models.PatientDetail;

public class PatientController {

	public PatientController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Patient> GetAllPatient(){
		
		Patient patient = new Patient();
		
		List<Patient> allPatients = patient.GetAllPatient();
		
		return allPatients;
	}
	
	public PatientDetail AddPatientDetail(int patientID, int employeeID, String symptom) {
		
		//validation
		
		PatientDetail patientDetail = new PatientDetail();
		java.util.Date currentDate = new java.util.Date();
		
		Date date = new Date(currentDate.getTime());
		
		patientDetail.setPatientID(patientID);
		patientDetail.setEmployeeID(employeeID);
		patientDetail.setSymptom(symptom);
		patientDetail.setCheckDate(date);
		patientDetail.AddPatientDetail();
		
		return patientDetail;
	}
	
	public List<Patient> SearchPatient(String name){
		
		//validation
		
		Patient patient = new Patient();
		
		List<Patient> selectedPatients = patient.SearchPatient(name);
		
		return selectedPatients;
	}
	
	public Patient UpdatePatient(int patientID, String name, Date DOB) {
		
		//validation
		
		Patient patient = new Patient();
		
		patient.setPatientID(patientID);
		patient.setName(name);
		patient.setDOB(DOB);
		
		return patient;
	}
	
	public Patient GetPatient(int patientID) {
	
		//validation
		
		Patient patient = new Patient();
		
		return patient.GetPatient(patientID);
	}
	
	public Patient AddPatient(String name, Date DOB) {
		
		//validation
		
		Patient patient = new Patient();
		
		patient.setName(name);
		patient.setDOB(DOB);
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
