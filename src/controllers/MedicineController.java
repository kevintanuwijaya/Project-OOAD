package controllers;

import java.util.List;
import java.util.Vector;

import models.Medicine;

public class MedicineController {

	private static MedicineController instance;

	private MedicineController() {

	}

	public static MedicineController getInstance() {
		if (MedicineController.instance == null) {
			if (MedicineController.instance == null) {
				MedicineController.instance = new MedicineController();
			}
		}

		return MedicineController.instance;
	}

	public List<Medicine> getAllMedicine() {

		Medicine medicine = new Medicine();

		List<Medicine> allMedicines = medicine.getAllMedicine();

		return allMedicines;
	}

	public Medicine addMedicine(String name, int price, int stock) {

		// validation

		Medicine medicine = new Medicine();

		medicine.setName(name);
		medicine.setPrice(price);
		medicine.setStock(stock);
		medicine.insertMedicine();

		return medicine;
	}

	public Medicine updateMedicine(int id, String name, int price, int stock) {

		// validation
		Medicine medicine = new Medicine();
		medicine = medicine.getMedicine(id);

		medicine.setName(name);
		medicine.setPrice(price);
		medicine.setStock(stock);
		medicine.updateMedicine();

		return medicine;
	}

	public Medicine deleteMedicine(int medicineID) {

		// validation

		Medicine medicine = new Medicine();

		medicine = medicine.getMedicine(medicineID);
		medicine.deleteMedicine();

		return medicine;
	}

	public List<Medicine> searchMedicine(String name) {

		// validation

		Medicine medicine = new Medicine();

		List<Medicine> allMedicines = medicine.searchMedicine(name);

		return allMedicines;
	}

	public Medicine getMedicine(int medicineID) {

		// validation

		Medicine medicine = new Medicine();

		medicine.getMedicine(medicineID);

		return medicine;
	}

	public Medicine decreaseStock(int medicineID, int stock) {

		// validation

		Medicine medicine = new Medicine();

		medicine.getMedicine(medicineID);
		medicine.setStock(stock);
		medicine.updateMedicine();

		return medicine;
	}

}
