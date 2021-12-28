package controllers;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import models.Bill;
import models.BillDetail;
import models.Medicine;

public class BillController {

	private static BillController instance;

	private BillController() {

	}

	public static BillController getInstance() {

		if (BillController.instance == null) {
			BillController.instance = new BillController();
		}

		return BillController.instance;
	}

	/**
	 * 
	 * @param employeeID
	 * @param patientID
	 * @param paymentType
	 * @param status
	 * @return create a new bill for a patient
	 */
	public Bill CreateBill(String employeeID, String patientID) {

		if (employeeID.equals("")) {
			JOptionPane.showMessageDialog(null, "EmployeeID harus diisi");
			return null;
		} else if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "PatientID harus diisi");
			return null;
		}

		int employeeIDInt = Integer.parseInt(employeeID);
		int patientIDInt = Integer.parseInt(patientID);
		Date currentDate = new Date();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		Bill bill = new Bill();
		bill.setEmployeeID(employeeIDInt);
		bill.setPatientID(patientIDInt);
		bill.setDateTimeCreated(date);
		bill.setStatus("Unpaid");
		bill.AddBill();

		return bill;
	}

	/**
	 * 
	 * @param billID
	 * @return get bill based on its ID
	 */
	public Bill GetBill(String billID) {

		if (billID.equals("")) {
			JOptionPane.showMessageDialog(null, "PatientID harus diisi");
			return null;
		}

		int billIDInt = Integer.parseInt(billID);

		Bill bill = new Bill();

		return bill.GetBill(billIDInt);
	}

	/**
	 * 
	 * @param billID
	 * @return Get bill detail from a bill head
	 */
	public List<BillDetail> getBillDetail(String billID) {

		if (billID.equals("")) {
			JOptionPane.showMessageDialog(null, "BillID harus diisi");
			return new Vector<>();
		}

		int billIDInt = Integer.parseInt(billID);

		BillDetail billDetail = new BillDetail();

		return billDetail.GetAllBillDetail(billIDInt);
	}

	/**
	 * 
	 * @return Get all Bill from database
	 */
	public List<Bill> GetAllBill() {

		Bill bill = new Bill();

		List<Bill> allBills = bill.GetAllBill();

		return allBills;
	}

	/**
	 * 
	 * @param billID
	 * @param paymentType
	 * @param money
	 * @return Finalize a Transaction
	 */

	public Bill Checkout(String billID, String paymentType, String money) {

		if (billID.equals("")) {
			JOptionPane.showMessageDialog(null, "BillID harus diisi");
			return null;
		} else if (paymentType.equals("")) {
			JOptionPane.showMessageDialog(null, "PaymentType harus diisi");
			return null;
		} else if (money.equals("")) {
			JOptionPane.showMessageDialog(null, "Uang harus diisi");
			return null;
		}

		int billIDInt = Integer.parseInt(billID);
		int moneyInt = Integer.parseInt(money);

		Bill bill = GetBill(billID);
		BillDetail modelDetail = new BillDetail();

		int grandTotal = 0;
		List<BillDetail> detailLists = modelDetail.GetAllBillDetail(billIDInt);

		for (BillDetail billDetail : detailLists) {
			Medicine modelMed = new Medicine();
			Medicine med = modelMed.getMedicine(billDetail.getMedicineID());
			grandTotal += med.getPrice();
		}

		if (moneyInt < grandTotal) {
			JOptionPane.showMessageDialog(null, "Uang tidak cukup");
			return null;
		}

		JOptionPane.showMessageDialog(null, "Berhasil membuat Bill Baru");
		bill.setPaymentType(paymentType);
		bill.setStatus("Paid");
		bill.UpdateBill();
		return bill;
	}

	/**
	 * 
	 * @param billID
	 * @param medicineID
	 * @param quantity
	 * @return Add a BillDetail to Database
	 */
	public List<BillDetail> AddBillDetail(String billID, String medicineID, String quantity) {

		if (billID.equals("")) {
			JOptionPane.showMessageDialog(null, "BillID harus diisi");
			return null;
		} else if (medicineID.equals("")) {
			JOptionPane.showMessageDialog(null, "MedicineID harus diisi");
			return null;
		} else if (quantity.equals("")) {
			JOptionPane.showMessageDialog(null, "Quantity harus diisi");
			return null;
		}

		int billIDInt = Integer.parseInt(billID);
		int medicineIDInt = Integer.parseInt(medicineID);
		int quantityInt = Integer.parseInt(quantity);

		BillDetail billDetail = new BillDetail();

		billDetail.setBillID(billIDInt);
		billDetail.setMedicineID(medicineIDInt);
		billDetail.setQuantity(quantityInt);
		billDetail.AddBillDetail();

		return getBillDetail(billID);
	}

	/**
	 * 
	 * @param patientID
	 * @return search bill of a specific patient
	 */
	public List<Bill> SearchBill(String patientID) {

		if (patientID.equals("")) {
			JOptionPane.showMessageDialog(null, "PatientID harus diisi");
			return GetAllBill();
		}

		int patientIDInt = Integer.parseInt(patientID);

		List<Bill> allBill = new Vector<>();
		Bill bill = new Bill();
		allBill = bill.searchBill(patientIDInt);

		return allBill;
	}

	// public Bill UpdateBill(int billID, int employeeID, int patientID, String
	// paymentType, String status) {
	//
	// Bill bill = new Bill();
	// bill.setBillID(billID);
	// bill.setEmployeeID(employeeID);
	// bill.setPatientID(patientID);
	// bill.setPaymentType(paymentType);
	// bill.setStatus(status);
	// bill.UpdateBill();
	//
	// return bill;
	// }

}