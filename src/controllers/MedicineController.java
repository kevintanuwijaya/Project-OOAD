package controllers;

import java.util.List;
import java.util.Vector;

import models.Medicine;

public class MedicineController {

	public MedicineController() {
		// TODO Auto-generated constructor stub
	}

	public List<Medicine> GetAllMedicine(){
		
		Medicine medicine = new Medicine();
		
		List<Medicine> allMedicines = medicine.GetAllMedicine();
		
		return allMedicines;
	}
	
	public Medicine AddMedicine(String name, int price, int stock) {
		
		//validation
		
		Medicine medicine = new Medicine();
		
		medicine.setName(name);
		medicine.setPrice(price);
		medicine.setStock(stock);
		medicine.InsertMedicine();
		
		return medicine;
	}
	
	public Medicine UpdateMedicine(String name, int price, int stock) {
		
		//validation
		
		Medicine medicine = new Medicine();
		
		medicine.setName(name);
		medicine.setPrice(price);
		medicine.setStock(stock);
		medicine.InsertMedicine();
		
		return medicine;
	}
	
	public Medicine DeleteMedicine(int medicineID) {
		
		//validation
		
		Medicine medicine = new Medicine();
		
		medicine.GetMedicine(medicineID);
		medicine.DeleteMedicine();
		
		return medicine;
	}
	
	public List<Medicine> SearchMedicine(String name){
		
		//validation
		
		Medicine medicine = new Medicine();
		
		List<Medicine> allMedicines = medicine.SearchMedicine(name);
		
		return allMedicines;
	}
	
	public Medicine GetMedicine(int medicineID) {
		
		//validation
		
		Medicine medicine = new Medicine();
		
		medicine.GetMedicine(medicineID);
		
		return medicine;
	}
	
	public Medicine DecreaseStock(int medicineID, int stock) {
		
		//validation
		
		Medicine medicine = new Medicine();
		
		medicine.GetMedicine(medicineID);
		medicine.setStock(stock);
		medicine.updateMedicine();
		
		return medicine;
	}
	

	

	


	
}
