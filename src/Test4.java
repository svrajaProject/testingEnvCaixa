import java.io.IOException;

public class Test4 {

	public String obrhGetForExternalSource(String u) {
		System.out.println("Every 5sec calls....");
		return u;
	}

	public void exe(String u) throws InterruptedException, IOException {

		String resURL = u;
		String con_status = null;
		
		while (true) {
			
			String current_status = null;
			
			con_status = obrhGetForExternalSource(resURL);
			
			if (con_status == "false") {
				System.out.println("Status: " + con_status);
				break;
			} else {
				int count = 1;
				while (count<3) {
					Thread thread = new Thread();
					try {
						Thread.sleep(5000);
						current_status = obrhGetForExternalSource(resURL);
						if (current_status=="false") {
							break;
						}
					} catch (Exception e) {
						thread.interrupt();
					}
					count++;
				}
			}
		}
	}

	public static void main(String[] args) {

//		String test = "\"http://172.19.0.21:8003/CCDataResAPI/get-customer-details/status/jobId\"";
//		System.out.println(test);
//		test = test.substring(0, test.length()-0);
//		System.out.println(test);

		Test4 obj = new Test4();
		String u = "true";
		try {
			try {
				obj.exe(u);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
