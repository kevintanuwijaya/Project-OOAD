package models;

import java.util.List;
import java.util.Vector;

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
	
	public List<Medicine> GetAllMedicine(){
		
		List<Medicine> allMedicines = new Vector<Medicine>();
		
		//get all medicine from database
		
		return allMedicines;
	}
	
	public Medicine GetMedicine(int medicineID) {
		
		//get medicine information by medicineID from database
		//set all information from database to this class
		
		return this;
	}
	
	public List<Medicine> SearchMedicine(String name){
		
		List<Medicine> allMedicines = new Vector<Medicine>();
		
		//get all medicine by name from database
		
		return allMedicines;
	}
	
	public Medicine InsertMedicine() {
		
		//add this medicine to database
		
		return this;
	}
	
	public Medicine updateMedicine() {
		
		//update this medicine from database
		
		return this;
	}
	
	public Medicine DeleteMedicine() {
		
		//delete this medicine from database
		
		return this;
	}

}
