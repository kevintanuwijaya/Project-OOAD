package controllers;

import java.sql.Date;
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
	
	
	public Bill SearchBill(int patientID){
		
		//validation
		
		Bill bill = new Bill();
		
		Bill selectedBills = bill.SearchBill(patientID);
		
		return selectedBills;
	}
	
	
	public Bill GetBill(int billID) {
	
		//validation
		
		Bill bill = new Bill();
		
		return bill.GetBill(billID);
	}
	
	public Bill AddBill(int billID, int employeeID, int patientID, Date date, String paymentType, String status) {
		
		//validation
		
		Bill bill = new Bill();
		
		bill.setBillID(billID);
		bill.setEmployeeID(employeeID);
		bill.setPatientID(patientID);
		bill.setDateTimeCreated(date);
		bill.setPaymentType(paymentType);
		bill.setStatus(status);
		
		return bill;
	}
	
	
	public BillDetail AddBillDetail(int billID, int medicineID, int quantity) {
		
		//validation
		
		BillDetail billDetail = new BillDetail();
		java.util.Date currentDate = new java.util.Date();
		
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
