package persistance.MavenMerchant;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Merchant {
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
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Collection<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Collection<Payment> payments) {
		this.payments = payments;
	}
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;
	private String name;
	private double charge;
	private int period;
	private double total;
	@OneToMany(mappedBy="merchant")
	private Collection<Payment> payments;
	
	public Merchant() { }
	public String getStringForPrint(){
		String txt = "id = " + id + ";   name = '" + name + "';   charge = ";
		txt += "" + charge + ";   period = " + period + ";   total = " + total;
	    return txt;
	    }
}
