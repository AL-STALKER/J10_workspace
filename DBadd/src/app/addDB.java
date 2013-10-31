package app;

import java.sql.*;

public class addDB {

	public static void main(String[] args) {
		try{
			Connection con = DriverManager.getConnection("jdbc:derby:C:\\Users\\oper4\\J10_workspace\\CM");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name, charge, minsum FROM merchant WHERE minsum > 100");
			while (rs.next()){
				String nm = rs.getString("name");
				double p = rs.getDouble(2);
				double minsum = rs.getDouble(3);
				System.out.println(nm + "   " + p + "\t" + minsum);
			}
			con.close();

			}
			catch(SQLException ex){
				System.out.println("Error " + ex.getMessage());
			}
	}

}
