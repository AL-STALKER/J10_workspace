import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class Test3 {
	
	public static Connection getConnection() throws IOException, SQLException {
		Connection conn = null;
	   	Properties props = new Properties();
		InputStreamReader in = new InputStreamReader(new FileInputStream("appProperties.txt"), "UTF-8");
	   
		props.load(in); 
	    
		in.close();  
		
	    String connString = props.getProperty("DBConnectionString");
	    conn = DriverManager.getConnection(connString);
	    
	    return conn;
	}
	
	public static ArrayList<Deposit> getDepo(double value) {
		ArrayList<Deposit> listDepo = new ArrayList<Deposit>();
		Deposit depo;
		try{
			Connection con = getConnection();
			String sql = "SELECT bankName, depositID, depositType, startDate, duration, depoSum, interestRate FROM deposits WHERE depoSum > ?"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, value);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				
				String bnm = rs.getString("bankName");
				String bID = rs.getString("bankID");
				int dt = rs.getInt("depositType");
				Date st = rs.getDate("startDate");
				int dr = rs.getInt("duration");
				double ds = rs.getDouble("depoSum");
				double ir = rs.getDouble("interestRate");
				
				if(dt == 1) {
					depo = new DepoSimple(bnm, bID, dt, st, dr, ds, ir);
				} else {
					depo = new DepositCapital(bnm, bID, dt, st, dr, ds, ir);
				}
				
				depo.getInterest();
				
				printDepo(depo);
				
				listDepo.add(depo);
			}
			
			con.close();

			}
			catch(SQLException | IOException ex){
				System.out.println("Error " + ex.getMessage());
			}
		
		return listDepo;
	}
	
	public static ArrayList<Deposit> sortDepoList(ArrayList<Deposit> list) {
		Collections.sort(list);
	
		return list;
	}
	
	public static void printDepoList(ArrayList<Deposit> list) {
		for(Deposit depo: list) {
//        	maturity.setTime(depo.getStartDate());
//        	maturity.add(maturity.DAY_OF_MONTH, depo.getDuration());
        	//System.out.println(depo.getBankName() + "\t" + depo.getDepositID() + "\t" + maturity.getTime() + "\t" + depo.getDepoSum() + "\t" + depo.getInterest());
        	System.out.println(depo.getBankName() + "\t" + depo.getDepositID() + "\t" + depo.getDepoSum() + "\t" + depo.getInterest());
        }
	
	}
	
	public static void printDepo(Deposit depo) {
		System.out.println(depo.getBankName() + "\t" + depo.getDepositID() + "\t" + depo.getDepoSum() + "\t" + depo.getInterest());
	}
	
	static void writeDepo(String fileId, ArrayList<Deposit> list) {
        PrintStream outF = null;
        Locale ukrLocale = new Locale("ru", "RU");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.m.yy", ukrLocale);
        Calendar maturity = Calendar.getInstance(ukrLocale);
        
        try {
            outF = new PrintStream(fileId);
            outF.println("BankName\tID\tMaturityDate\tDepositSum\tInterest");
            
            for(Deposit depo: list) {
            	maturity.setTime(depo.getStartDate());
            	maturity.add(maturity.DAY_OF_MONTH, depo.getDuration());
            	System.out.println(depo.getBankName());
            	outF.println(depo.getBankName() + "\t" + depo.getDepositID() + "\t" + maturity.getTime() + "\t" + depo.getDepoSum() + "\t" + depo.getInterest());
            }
        } catch(IOException e) {
            System.out.println("Error " + e.getMessage()); 
        } finally { 
            if (outF != null) outF.close();
        }
    }

	public static void main(String[] args) {
		ArrayList<Deposit> depoList = getDepo(1000.00);
		
//		printDepoList(depoList);
//		sortDepoList(depoList);
//		printDepoList(depoList);
//		writeDepo("report.txt", depoList);
	}

}
