package controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import models.Bill;
import models.BillDetail;
import models.Bill;
import models.BillDetail;
import models.Bill;

public class BillController {

	private static BillController instance;

	private BillController() {

	}
	
	public static BillController getInstance() {

		if(BillController.instance == null) {
			BillController.instance = new BillController();
		}
		
		
		return BillController.instance;
	}

	public List<Bill> GetAllBill(){
		
		Bill bill = new Bill();
		
		List<Bill> allBills = bill.GetAllBill();
		
		return allBills;
	}
	
	
	public List<Bill> SearchBill(int patientID){
	
		//validation
	
		List<Bill> allBill = new Vector<>();
		Bill bill = new Bill();
		allBill = bill.searchBill(patientID);
		
		return allBill;
	}
	
	
	public Bill GetBill(int billID) {
	
		//validation
		
		Bill bill = new Bill();
		
		return bill.GetBill(billID);
	}
	
	public Bill AddBill(int employeeID, int patientID, String paymentType, String status) {
		
		//validation
		Date currentDate = new Date();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		
		Bill bill = new Bill();
		bill.setEmployeeID(employeeID);
		bill.setPatientID(patientID);
		bill.setDateTimeCreated(date);
		bill.setPaymentType(paymentType);
		bill.setStatus(status);
		bill.AddBill();
		
		return bill;
	}
	
	public Bill UpdateBill(int billID, int employeeID, int patientID, String paymentType, String status) {
		
		Bill bill = new Bill();
		bill.setBillID(billID);
		bill.setEmployeeID(employeeID);
		bill.setPatientID(patientID);
		bill.setPaymentType(paymentType);
		bill.setStatus(status);
		bill.UpdateBill();
		
		return bill;
	}
	
	
	public BillDetail AddBillDetail(int billID, int medicineID, int quantity) {
		
		//validation
		
		BillDetail billDetail = new BillDetail();
		
		billDetail.setBillID(billID);
		billDetail.setMedicineID(medicineID);
		billDetail.setQuantity(quantity);
		billDetail.AddBillDetail();
		
		return billDetail;
	}
	
	public List<BillDetail> getAllBillDetail(int billID){
		BillDetail billDetail = new BillDetail();
		List<BillDetail> listAllBillDetail = billDetail.GetAllBillDetail(billID);
		
		return listAllBillDetail;
	}
	

}
