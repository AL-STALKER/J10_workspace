import java.util.*;

public class DepositCapital extends Deposit {
	
	public DepositCapital(String nameValue, String idValue, int typeValue, Date dateValue, int durationValue, double sumValue, double intrRateValue) {
		super(nameValue, idValue, durationValue, dateValue, durationValue, intrRateValue, intrRateValue);
	}

	public DepositCapital() { }
	public double getInterest(){
		int dayN = 0;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		int daysInYear = getDaysInYear();
		double interest = 0.0;
		double capital = 0.0;
		int periodDay = 0;
		double dayCf = 0.0;
		
		while (true){
			month++;
			if (month > 11) month = 0;
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			periodDay = calendar.get(Calendar.DAY_OF_MONTH) - day + 1;
			if ((dayN + periodDay) > getDuration()) {
				break;
			}
			dayCf = periodDay;
			interest = (getDepoSum() + capital) * (getInterestRate() / 100.0) * (dayCf / daysInYear);
			capital += interest;
            day = 1;
            dayN += periodDay;
		}
		dayCf = getDuration() - dayN;
		interest = (getDepoSum() + capital) * (getInterestRate() / 100.0) * (dayCf / daysInYear);
		capital += interest;
		
		return capital;
	}
}

