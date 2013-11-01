package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

public class addDB {
	
	public static Connection getConnection() throws IOException, SQLException {
		Connection conn = null;
	   	Properties props = new Properties();
		InputStreamReader in = new InputStreamReader(new FileInputStream("appProperties.txt"), "UTF-8");
	   
		props.load(in); 
	    
		in.close();  
		
		// uses pair: key = value There is DBConnectionString = jdbc:derby:C:\\Users\\oper4\\J10_workspace\\CM in a file
	    String connString = props.getProperty("DBConnectionString");
	    conn = DriverManager.getConnection(connString);
	    
	    return conn;
	}
	
	public static void addCustomer(String name, String address, String email, String ccNo, String ccType, java.sql.Date newDt) throws SQLException, IOException{
		Connection con = getConnection();
		String sql = "INSERT INTO customer (name, address, "; 
		sql += " email, ccNo, ccType, maturity) values(?,?,?,?,?,?) "; //ѕередаваемые пар-мы описаны знаками вопросов.
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, email);
		stmt.setString(4, ccNo);
		stmt.setString(5, ccType);
		stmt.setDate(6, newDt);
		stmt.executeUpdate();
		con.close();
	}
	
	public static void showTotal(int id){
		try{
		Connection con = getConnection();
		String sql = "SELECT total FROM merchant WHERE id = ? "; 
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()){
			//String nm = rs.getString("total");
			double p = rs.getDouble("total");
			System.out.println(p);
		}

		con.close();
		}
		catch(SQLException | IOException ex){
			System.out.println("Error " + ex.getMessage());
		}
	}
	
	public static void addPayment(java.sql.Date newDt, int merchID, int custID, String newGoods, int newTotal, Connection newConn) {
		PreparedStatement stmt;
		PreparedStatement stmtM;
		PreparedStatement stmtP;
		int newCharge;
		try {
			String sqlMerch = "UPDATE merchant m SET total = total + ";
			sqlMerch += " (SELECT (total - charge) ";
			sqlMerch += " FROM payment p WHERE p.merchantId = ?)";
			
			
			String sqlPay = "UPDATE payment p ";
			sqlPay += " SET charge = total * ";
			sqlPay += " (SELECT charge FROM merchant m ";
			sqlPay += " WHERE m.id = ?) / 100.0";

			
			String sql = "INSERT INTO payment (dt, "; 
			sql += " merchantid, customerid, goods, total) ";
			sql += " values(?,?,?,?,?) ";
			
			stmt = newConn.prepareStatement(sql);
			stmt.setDate(1, newDt);
			stmt.setInt(2, merchID);
			stmt.setInt(3, custID);
			stmt.setString(4, newGoods);
			stmt.setInt(5, newTotal);
			//stmt.executeUpdate();
			
			stmtP = newConn.prepareStatement(sqlPay);
			stmtP.setInt(1, merchID);
			stmtP.executeUpdate();
			
			stmtM = newConn.prepareStatement(sqlMerch);
			stmtM.setInt(1, merchID);
			stmtM.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try{
			GregorianCalendar c = new GregorianCalendar(2012, 03, 31);
//			java.util.Date dt = c.getTime();
//			java.sql.Date dt1 = new java.sql.Date(dt.getTime());
			java.sql.Date dt1 = new java.sql.Date(c.getTimeInMillis());
			ArrayList<Merchant> merchList = Merchant.getMerchant();
			//Connection con = DriverManager.getConnection("jdbc:derby:C:\\Users\\oper4\\J10_workspace\\CM");
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name, charge, minsum FROM merchant WHERE minsum > 100");
			while (rs.next()){
				String nm = rs.getString("name");
				double p = rs.getDouble(2);
				double minsum = rs.getDouble(3);
				System.out.println(nm + "   " + p + "\t" + minsum);
			}
			
			String sql = "INSERT INTO customer (name, address, "; 
			sql += " email, ccNo, ccType, maturity) values(";
			sql += " 'Clar Nelis', 'Vosselaar st. 19, Trnaut, Belgium', "; 
			sql += " 'Clar@adw.com', 	'11345694671231', ";
			sql += " 'MasterCard', '2014-07-31') ";
			Statement stmtIns = con.createStatement();
			stmtIns.executeUpdate(sql);

			addCustomer("James Smith", "CroosStreet ave. 15, New-York, USA", "jsth@gmail.com", "11345694671232", "Visa", dt1);
			showTotal(1);
			addPayment(dt1, 2, 1, "RedHat Linux", 50, con);
			con.close();
			
			for(Merchant merch: merchList) {
				String printStr = merch.getStringForPrint();
				System.out.println(printStr);
			}

			}
			catch(SQLException | IOException ex){
				System.out.println("Error " + ex.getMessage());
			}
	}

}
