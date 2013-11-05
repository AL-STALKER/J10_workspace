import java.io.IOException;
import java.io.PrintStream;


public class RunClass implements Runnable {
	public void run() {
		System.out.println("This is a Runnable object!");
		getIterNum();
	}
	public void getIterNum() {
		PrintStream outF = null;
		int i = 0;
		
		try { 
			outF = new PrintStream("testFile");
	        outF.println("This is a test record");
	    } catch(IOException e) {
	    	//processing code  
	    } 
		finally { 
			if (outF != null) {
				outF.close();
			}
		} 

		while(i < 10) {
			System.out.println("Tread #1, iter = " + i);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			i++;
		}
	}
}
