package models;

import java.util.List;
import java.util.Vector;

import javax.crypto.AEADBadTagException;

import utils.DatabaseConnection;

public class Medicine {

	private int MedicineID;
	private String Name;
	private int Price;
	private int Stock;

	public Medicine() {
		// TODO Auto-generated constructor stub
	}

	public int getMedicineID() {
		return MedicineID;
	}

	public void setMedicineID(int medicineID) {
		MedicineID = medicineID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public List<Medicine> getAllMedicine() {

		List<Medicine> allMedicines = new Vector<Medicine>();
		allMedicines = DatabaseConnection.getInstance().getAllMedicines();

		// get all medicine from database

		return allMedicines;
	}

	public Medicine getMedicine(int medicineID) {

		// get medicine information by medicineID from database
		// set all information from database to this class
		Medicine medicine = new Medicine();
		List<Medicine> allMedicines = medicine.getAllMedicine();

		for (Medicine med : allMedicines) {
			if (med.getMedicineID() == medicineID) {
				return med;
			}
		}

		return medicine;
	}

	public List<Medicine> searchMedicine(String name) {

		List<Medicine> allMedicines = new Vector<Medicine>();

		// get all medicine by name from database

		return allMedicines;
	}

	public Medicine insertMedicine() {

		// add this medicine to database
		DatabaseConnection.getInstance().insertMedicine(this);

		return this;
	}

	public Medicine updateMedicine() {

		// update this medicine from database
		DatabaseConnection.getInstance().updateMedicine(this);

		return this;
	}

	public Medicine deleteMedicine() {

		// delete this medicine from database
		DatabaseConnection.getInstance().deleteMedicine(this);

		return this;
	}

}
