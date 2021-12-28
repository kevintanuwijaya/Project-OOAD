package controllers;

import java.util.List;

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

	/**
	 * Add new medicine
	 * 
	 * @param String name
	 * @param String price
	 * @param String stock
	 * @return Medicine
	 */
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

	/**
	 * Update existing medicine
	 * 
	 * @param String id
	 * @param String name
	 * @param String price
	 * @param String stock
	 * @return Medicine
	 */
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

	/**
	 * Delete existing medicine
	 * 
	 * @param String medicineId
	 * @return Medicine
	 */
	public Medicine deleteMedicine(String medicineID) {
		if (medicineID.equals("")) {
			JOptionPane.showMessageDialog(null, "Medicine ID harus dipilih");
			return null;
		}

		Medicine medicine = new Medicine();

		medicine = medicine.getMedicine(Integer.parseInt(medicineID));
		medicine.deleteMedicine();

		return medicine;
	}

	/**
	 * Search medicine by name
	 * 
	 * @param name
	 * @return List<Medicine>
	 */
	public List<Medicine> searchMedicine(String name) {

		Medicine medicine = new Medicine();

		List<Medicine> allMedicines = medicine.searchMedicine(name);

		return allMedicines;
	}

	/**
	 * Get medicine by id
	 * 
	 * @param int medicineID
	 * @return Medicine
	 */
	public Medicine getMedicine(int medicineID) {

		Medicine medicine = new Medicine();

		medicine.getMedicine(medicineID);

		return medicine;
	}

	/**
	 * Decrease medicine stock
	 * 
	 * @param int medicineID
	 * @param int stock
	 * @return Medicine
	 */
	public Medicine decreaseStock(String medicineID, String stock) {

		if (medicineID.equals("")) {
			JOptionPane.showMessageDialog(null, "MedicineID harus diisi");
			return null;
		} else if (stock.equals("")) {
			JOptionPane.showMessageDialog(null, "Stock harus diisi");
			return null;
		}

		int medicineIDInt = Integer.parseInt(medicineID);
		int stockInt = Integer.parseInt(stock);

		Medicine medicine = new Medicine();

		medicine.getMedicine(medicineIDInt);
		medicine.setStock(medicine.getStock() - stockInt);
		medicine.updateMedicine();

		return medicine;
	}

}