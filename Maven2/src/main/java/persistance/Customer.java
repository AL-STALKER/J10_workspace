package persistance;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Customer {
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	
	private
		String ccType;
		String ccNo;
		String email;
		String address;
		String name;
		java.sql.Date maturity;
	
	public Customer() { }	
	public Customer(String newType, String newNo, String newEmail, String newAddress, String newName, java.sql.Date newMaturity) {
		ccType = newType;
		ccNo = newNo;
		email = newEmail;
		address = newAddress;
		name = newName;
		maturity = newMaturity;
	}
	public Date getMaturity() {
		return maturity;
	}
	public void setMaturity(java.sql.Date maturity) {
		this.maturity = maturity;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	public String getCcNo() {
		return ccNo;
	}
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getStringForPrint(){
		String txt = "id = " + ID + ";   name = '" + name + "';   ccType = ";
		txt += "" + ccType + ";   ccNo = " + ccNo + ";   email = " + email;
	    
		return txt;
	}
}


