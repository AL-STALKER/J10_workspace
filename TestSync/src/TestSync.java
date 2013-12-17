public class TestSync implements Runnable {

	synchronized void hit(long n) {
		for(int i = 1; i < 3; i++)
			System.out.print(n + "-" + i + " ");
		}

	public static void main(String[] args) {
		new Thread(new TestSync()).start();
		new Thread(new TestSync()).start();
	}

	public void run() {
		hit(Thread.currentThread().getId());
	}

}