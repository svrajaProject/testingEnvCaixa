public class Sheduler {
	
	static boolean status = false;
	
	public static boolean onC(boolean status) {
		boolean flag = status;
		System.out.println("Complete Status: " + flag);
	return flag;
	}

	public static void main(String args[]) throws InterruptedException {

		Thread thread = new Thread();
		int count = 1;
		while (!status) {
			try {
				Thread.sleep(5000);
				onC(status);
			} catch (Exception e) {
				thread.interrupt();
			}
			if(count==3) break;
			count++;
		}
		System.out.println("Complete Status: " + status);
	}
}