package persistance.MavenMerchant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	private double charge;
	private double total;
	private String goods;
	private java.sql.Date dt;
	
	@ManyToOne
	@JoinColumn(name="merchantId")
	private Merchant merchant;
	
	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public java.sql.Date getDt() {
		return dt;
	}

	public void setDt(java.sql.Date dt) {
		this.dt = dt;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public int getID() {
		return ID;
	}
	
	public Payment(double newCharge, double newTotal, String newGoods, java.sql.Date newDT) {
		charge = newCharge;
		total = newTotal;
		goods = newGoods;
		dt = newDT;
	}

	public Payment() {
		charge = 0;
		total = 0;
		goods = "Empty";
		dt = new java.sql.Date(10000);
	}
	public String getStringForPrint(){
		String txt = "id = " + ID + ";   charge = '" + charge + "';   total = ";
		txt += "" + total + ";   goods = " + goods + ";   dt = " + dt;
	    
		return txt;
	}
}
