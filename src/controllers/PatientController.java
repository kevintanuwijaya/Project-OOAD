package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import models.Patient;
import models.PatientDetail;

public class PatientController {

	public static PatientController instance = null;

	public static PatientController getInstance() {
		if (instance == null) {
			instance = new PatientController();
		}
		return instance;
	}

	private PatientController() {
	}

	/**
	 * 
	 * @return Get all patient from database
	 */
	public List<Patient> GetAllPatient() {

		Patient patient = new Patient();

		List<Patient> allPatients = patient.GetAllPatient();

		return allPatients;
	}

	/**
	 * 
	 * @param patientID
	 * @param employeeID
	 * @param symptom
	 * @return Insert new patient detail into database
	 */
	public PatientDetail AddPatientDetail(String patientID, int employeeID, String symptom) {

		if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom PatientID Kosong");
			return null;
		} else if (employeeID < 0) {
			JOptionPane.showMessageDialog(null, "Kolom Employee Kosong");
			return null;
		} else if (symptom.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom Symptom Kosong");
			return null;
		}

		int patientIDInt = Integer.parseInt(patientID);
		PatientDetail patientDetail = new PatientDetail();
		Date currentDate = new Date();

		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		patientDetail.setPatientID(patientIDInt);
		patientDetail.setEmployeeID(employeeID);
		patientDetail.setSymptom(symptom);
		patientDetail.setCheckDate(date);
		patientDetail.AddPatientDetail();

		return patientDetail;
	}

	/**
	 * @param name
	 * @return Search patient by Name
	 */
	public List<Patient> SearchPatient(String name) {

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom Nama Kosong");
			return GetAllPatient();
		}

		Patient modelPatient = new Patient();

		List<Patient> selectedPatients = modelPatient.SearchPatient(name);

		return selectedPatients;
	}

	/**
	 * 
	 * @param patientID
	 * @param name
	 * @param DOB
	 * @return
	 */
	public Patient UpdatePatient(String patientID, String name, Date DOB) {

		if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom PatientID Kosong");
			return null;
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom Nama Kosong");
			return null;
		} else if (DOB == null) {
			JOptionPane.showMessageDialog(null, "Kolom DOB Kosong");
			return null;
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

	/**
	 * 
	 * @param patientID
	 * @return Get patient base on ID
	 */
	public Patient GetPatient(String patientID) {

		if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom PatientID Kosong");
			return null;
		}

		int patientIDInt = Integer.parseInt(patientID);

		Patient patient = new Patient();

		return patient.GetPatient(patientIDInt);
	}

	/**
	 * 
	 * @param name
	 * @param DOB
	 * @return Insert new Patient
	 */
	public Patient AddPatient(String name, Date DOB) {

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom Nama Kosong");
			return null;
		}
		if (DOB == null) {
			JOptionPane.showMessageDialog(null, "Kolom DOB Kosong");
			return null;
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

	/**
	 * @param patientID
	 * @return Get all patientdetail from database
	 */
	public List<PatientDetail> GetAllPatientDetail(String patientID) {

		if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "Kolom PatientID Kosong");
			return null;
		}

		int patientIDInt = Integer.parseInt(patientID);

		PatientDetail patientDetail = new PatientDetail();

		List<PatientDetail> patientDetails = patientDetail.GetAllPatientDetail(patientIDInt);

		return patientDetails;
	}

}
