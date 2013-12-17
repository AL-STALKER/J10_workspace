
import java.util.Date;

public class DepoSimple extends Deposit {
	
	public DepoSimple(String nameValue, String idValue, int typeValue, Date dateValue, int durationValue, double sumValue, double intrRateValue){
		super(nameValue, idValue, durationValue, dateValue, durationValue, intrRateValue, intrRateValue);
	}

	public DepoSimple() { }
	
	public double getInterest() {
		int daysInYear = getDaysInYear();
		double duration = getDuration();
		double interest = getDepoSum() * (getInterestRate() / 100.0) * (duration / daysInYear);
		
		if (interest > 0.0) return interest;

		return -1.0;
	}

}
