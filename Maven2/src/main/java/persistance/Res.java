package persistance;

public class Res {
	private String name;
	private double sum;
	public Res(){   }
	public Res(String name, double sum){
		this.name = name;
		this.sum = sum;
	}
	public String getName() { return name; }
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public void setName(String name) {
		this.name = name;
	}
}

