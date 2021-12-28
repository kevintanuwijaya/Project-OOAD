package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import utils.DatabaseConnection;

public class Medicine {

	private int MedicineID;
	private String Name;
	private int Price;
	private int Stock;

	public Medicine() {
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

	@Override
	public String toString() {
		return Name;
	}

	/*
	 * Get all Medicine from database
	 */
	public List<Medicine> getAllMedicine() {

		List<Medicine> meds = new Vector<>();
		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "SELECT * FROM medicine";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);

			ResultSet result = stat.executeQuery(sqlQuery);

			while (result.next()) {
				Medicine med = new Medicine();
				med.setMedicineID(result.getInt("MedicineId"));
				med.setName(result.getString("Name"));
				med.setPrice(result.getInt("Price"));
				med.setStock(result.getInt("Stock"));

				meds.add(med);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return meds;
	}

	/*
	 * get medicine information by medicineID from database
	 */

	public Medicine getMedicine(int medicineID) {

		setMedicineID(medicineID);
		Connection conn = DatabaseConnection.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM medicine WHERE MedicineId = ?";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getMedicineID());

			ResultSet result = stat.executeQuery();

			if (result.next()) {
				setMedicineID(result.getInt("MedicineId"));
				setName(result.getString("Name"));
				setPrice(result.getInt("Price"));
				setStock(result.getInt("Stock"));

				return this;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * get all medicine by name from database
	 */
	public List<Medicine> searchMedicine(String name) {

		setName(name);
		List<Medicine> meds = new Vector<Medicine>();
		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "SELECT * FROM medicine WHERE Name LIKE ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, "%" + name + "%");

			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Medicine med = new Medicine();
				med.setMedicineID(result.getInt("MedicineId"));
				med.setName(result.getString("Name"));
				med.setPrice(result.getInt("Price"));
				med.setStock(result.getInt("Stock"));

				meds.add(med);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return meds;
	}

	/*
	 * add this medicine to database
	 */
	public Medicine insertMedicine() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "INSERT INTO medicine(Name, Price, Stock) VALUES(?, ?, ?);";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, getName());
			stat.setInt(2, getPrice());
			stat.setInt(3, getStock());

			int result = stat.executeUpdate();

			if (result != -1)
				return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * update this medicine from database
	 */
	public Medicine updateMedicine() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "UPDATE medicine SET Name = ?, Price = ?, Stock = ? WHERE MedicineId = ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setString(1, getName());
			stat.setInt(2, getPrice());
			stat.setInt(3, getStock());
			stat.setInt(4, getMedicineID());

			int result = stat.executeUpdate();

			if (result != -1)
				return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * delete this medicine from database
	 */
	public Medicine deleteMedicine() {

		Connection conn = DatabaseConnection.getInstance().getConnection();

		String sqlQuery = "DELETE FROM medicine WHERE MedicineId = ?;";

		try {
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sqlQuery);
			stat.setInt(1, getMedicineID());

			int result = stat.executeUpdate();

			if (result != -1)
				return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}