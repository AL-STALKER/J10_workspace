package app;

import java.sql.*;

public class addDB {

	public static void main(String[] args) {
		try{
			Connection con = DriverManager.getConnection("jdbc:derby:C:\\Users\\oper4\\J10_workspace\\CM");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name, charge FROM merchant");
			while (rs.next()){
				String nm = rs.getString("name");
				double p = rs.getDouble(2);
				System.out.println(nm + "   " + p);
			}
			con.close();

			}
			catch(SQLException ex){
				System.out.println("Error " + ex.getMessage());
			}
	}

}
