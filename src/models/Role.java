package models;

import java.util.List;
import java.util.Vector;

public class Role {

	private int RoleID;
	private String Name;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public Role GetRole(int role) {
		
		//get role by id from database
		
		return this;
	}
	
	public List<Role> GetAllRoles(){
		
		List<Role> allRoles = new Vector<Role>();
		
		//get all roles from database
		
		return allRoles;
	}

}
