package controllers;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

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

	public Medicine addMedicine(String name, String price, String stock) {

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Nama harus diisi");
			return null;
		} else if (price.equals("")) {
			JOptionPane.showMessageDialog(null, "Harga harus diisi");
			return null;
		} else if (stock.equals("")) {
			JOptionPane.showMessageDialog(null, "Stock harus diisi");
			return null;
		}

		Medicine medicine = new Medicine();

		medicine.setName(name);
		medicine.setPrice(Integer.parseInt(price));
		medicine.setStock(Integer.parseInt(stock));
		medicine.insertMedicine();

		return medicine;
	}

	public Medicine updateMedicine(String id, String name, String price, String stock) {

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Nama harus diisi");
			return null;
		} else if (price.equals("")) {
			JOptionPane.showMessageDialog(null, "Harga harus diisi");
			return null;
		} else if (stock.equals("")) {
			JOptionPane.showMessageDialog(null, "Stock harus diisi");
			return null;
		}
		Medicine medicine = new Medicine();
		medicine = medicine.getMedicine(Integer.parseInt(id));

		medicine.setName(name);
		medicine.setPrice(Integer.parseInt(price));
		medicine.setStock(Integer.parseInt(stock));
		medicine.updateMedicine();

		return medicine;
	}

	public Medicine deleteMedicine(String medicineID) {

		Medicine medicine = new Medicine();

		medicine = medicine.getMedicine(Integer.parseInt(medicineID));
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
