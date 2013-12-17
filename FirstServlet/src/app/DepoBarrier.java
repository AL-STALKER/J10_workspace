package app;

import java.util.Date;

public class DepoBarrier extends DepoBase{
	private static final long serialVersionUID = 1L;
	public static final double MIDDLE_SUM = 50000.0;
	public static final double UPPER_SUM = 100000.0;
	
	public DepoBarrier(Date startDate, int dayLong, double sum, double interestRate){
		super(startDate, dayLong, sum, interestRate);
	}

	public DepoBarrier(){   }
	
	public double getInterest(){
		int daysInYear = getDaysInYear();
		double localRate = interestRate;
		double dayCf = dayLong;
		if (sum > MIDDLE_SUM){
			localRate++;
		}
		if (sum > UPPER_SUM){
			localRate++;
		}
		interest = sum * (localRate / 100.0) * (dayCf / daysInYear);
		return interest;
	}
}
