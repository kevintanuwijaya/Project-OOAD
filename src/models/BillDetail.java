package models;

import java.util.List;
import java.util.Vector;

import utils.DatabaseConnection;

public class BillDetail {

	private int BillDetailID;
	private int BillID;
	private int MedicineID;
	private int Quantity;

	public BillDetail() {
		// TODO Auto-generated constructor stub
	}

	public int getBillDetailID() {
		return BillDetailID;
	}

	public void setBillDetailID(int billDetailID) {
		BillDetailID = billDetailID;
	}

	public int getBillID() {
		return BillID;
	}

	public void setBillID(int billID) {
		BillID = billID;
	}

	public int getMedicineID() {
		return MedicineID;
	}

	public void setMedicineID(int medicineID) {
		MedicineID = medicineID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	
	public BillDetail AddBillDetail() {
		
		DatabaseConnection.getInstance().AddBillDetail(this);
		
		return this;
	}
	
	public List<BillDetail> GetAllBillDetail(int billID){
		
		List<BillDetail> allBillDetails = new Vector<BillDetail>();
		
		//get all bill details by BillID from database
		
		return allBillDetails; 
	}
	
}
