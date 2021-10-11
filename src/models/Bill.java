package models;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

public class Bill {

	private int BillID;
	private int EmployeeID;
	private int PatientID;
	private Date DateTimeCreated;
	private String PaymentType;
	private String Status;
	
	public Bill() {
		// TODO Auto-generated constructor stub
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
	
	public List<Bill> GetAllBill(){
		
		List<Bill> allBills = new Vector<Bill>();
		
		//get all bills from database
		
		return allBills;
	}
	
	public Bill SearchBill(int patientID){
		
		//get Bill data by patientID from database
		//set all attribute from database to this class
		
		return this;
	}
	
	public Bill AddBill() {
		
		//add this Bill to database
		
		return this;
	}
	
	public Bill UpdateBill() {
		
		//update this Bill in database
		
		return this;
	}
	
	

}
