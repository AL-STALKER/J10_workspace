
public class ThreadClass extends Thread {
	Thread testThread = null;
	public void run() {
		System.out.println("This is a Threads object!");
		getIterNum();
	}
	public void setThread(Thread value) {
		testThread = value;
	}
	public void getIterNum() {
		//Thread threadTest = new Thread();
		int i = 0;
		
		while(i < 10) {
			System.out.println("Tread #2, iter = " + i);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (i == 5) {
//				try {
//					testThread.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			i++;
		}
	}
}
