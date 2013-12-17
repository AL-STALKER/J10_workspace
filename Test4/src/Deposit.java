import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Deposit implements Comparable<Deposit>{
	private String bankName;
	private String depositID;
	private int depositType;
	private Date startDate;
	private int duration;
	private double depoSum;
	private double interestRate;
	private double interest;
	
	public int compareTo(Deposit depo) {
        return new Double(interest).compareTo(depo.getInterest());
    }
	
	protected int getDaysInYear(){ 
		   GregorianCalendar calendar = new GregorianCalendar();
		   calendar.setTime(startDate); 
		   GregorianCalendar yearEnd = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31);
		   int days = yearEnd.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
		   calendar.add(GregorianCalendar.YEAR, 1); 
		   days += calendar.get(Calendar.DAY_OF_YEAR);
		   return days; 
	} 
	public Deposit() {
		this.interest = -1.0;
	}
	
	public Deposit(String nameValue, String idValue, int typeValue, Date dateValue, int durationValue, double sumValue, double intrRateValue) {
		bankName = nameValue;
		depositID = idValue;
		if (typeValue == 1 || typeValue == 2) {
		depositType = typeValue;
		} else {
			depositType = 1;
		}
		startDate = dateValue;
		depoSum = sumValue;
		interestRate = intrRateValue;
		interest = -1;
	}
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDepositID() {
		return depositID;
	}

	public void setDepositID(String depositID) {
		this.depositID = depositID;
	}

	public int getDepositType() {
		return depositType;
	}

	public void setDepositType(int depositType) {
		this.depositType = depositType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getDepoSum() {
		return depoSum;
	}

	public void setDepoSum(double depoSum) {
		this.depoSum = depoSum;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date value){
		startDate = value;
	}
	
	public double getInterest(){
		return -1.0;
	}
	
	public void setInterest(double value){
		interest = value;
	}

}
