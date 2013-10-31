package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
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

	public static void main(String[] args) {
		try{
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
			con.close();

			}
			catch(SQLException | IOException ex){
				System.out.println("Error " + ex.getMessage());
			}
	}

}
