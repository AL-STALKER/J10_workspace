package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Merchant {
	int id;
	String name;
	String bankName;
	String swift;
	String account;
	int charge;
	short period;
	int minSum;
	int total;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public short getPeriod() {
		return period;
	}
	public void setPeriod(short period) {
		this.period = period;
	}
	public int getMinSum() {
		return minSum;
	}
	public void setMinSum(int minSum) {
		this.minSum = minSum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	Merchant(int newID, String newName, String newBankName, String newSwift, String newAccount, int newCharge, short newPeriod, int newMinSum, int newTotal) {
		id = newID;
		name = newName;
		bankName = newBankName;
		swift = newSwift;
		account = newAccount;
		charge = newCharge;
		period = newPeriod;
		minSum = newMinSum;
		total = newTotal;
	}
	String getStringForPrint() {
		String toPrint;
		
		toPrint = "ID = " + id + "\tName = " + name + "\tmin sum = " + minSum + "\tTotal = " + total; 
		return toPrint;
	}
	public static ArrayList<Merchant> getMerchant() {
		ArrayList<Merchant> listMerchant = new ArrayList<Merchant>();
		try{
			Connection con = addDB.getConnection();
			String sql = "SELECT id, name, bankname, swift, account, charge, period, minsum, total FROM merchant"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				
				String nm = rs.getString("name");
				int id = rs.getInt("id");
				String bnm = rs.getString("bankname");
				String sw = rs.getString("swift");
				String ac = rs.getString("account");
				int ch = rs.getInt("charge");
				short pr = rs.getShort("period");
				int ms = rs.getInt("minsum");
				int tt = rs.getInt("total");
				Merchant mer = new Merchant(id, nm, bnm, sw, ac, ch, pr, ms, tt);
//				Merchant mer = new Merchant();
//				mer.setId(id);
//				mer.setName(nm);
//				mer.setBankName(bnm);
//				mer.setSwift(sw);
//				mer.setAccount(ac);
//				mer.setCharge(ch);
//				mer.setPeriod(pr);
//				mer.setMinSum(ms);
//				mer.setTotal(tt);
				listMerchant.add(mer);
			}

			con.close();

			}
			catch(SQLException | IOException ex){
				System.out.println("Error " + ex.getMessage());
			}
		return listMerchant;
	}
}
