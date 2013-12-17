package app;

public class TestRead {

	public static void main(String[] args) {
		ListDepo test = new ListDepo();
		test.read("Depo.txt");
		System.out.println(test.getPrincipalFor());
	}

}
