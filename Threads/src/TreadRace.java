
public class TreadRace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunClass runny = new RunClass();
		Thread threadyRun = new Thread(runny);
		ThreadClass thready = new ThreadClass();
		
		thready.setPriority(10);
		threadyRun.start();
		System.out.println("Thread #1 prority = " + threadyRun.getPriority());
		thready.setThread(threadyRun);
		thready.start();
		System.out.println("Thread #2 prority = " + thready.getPriority());
	}

}
