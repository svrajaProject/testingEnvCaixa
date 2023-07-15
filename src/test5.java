public class test5 {

	public String apiCall(String url) {
		System.out.println("API calling....");
		return "false";
	}

	public void checkImageDisplay(String connect_status) {
		String result = funCallTimer(connect_status);
		if (result.equals("true")) {
			System.out.println("Display check image");
		} else {
			System.out.println("There is no checks image!");
		}
	}

	public String funCallTimer(String connect_status) {
		Thread thread = new Thread();
		String response = null, flag = null;
		int count = 1;
		if (connect_status.equals("false")) {
			while (count < 5){
				try {
					Thread.sleep(5000);
					flag = apiCall(connect_status);

				} catch (Exception e) {
					thread.interrupt();
				}
				if (flag.equals("true")) {
					response = "true";
					break;
				} 
				else {
					response = "false";
				}
				count++;
			}
		} else {
			response = "true";
		}
		return response;
	}
//Test
	public static void main(String[] args) throws InterruptedException {
		test5 obj = new test5();
		String URL = "http://172.19.0.21:8003/CCDataResAPI/get-customer-details/status/jobId";
		String connect_stataus = obj.apiCall(URL);
		obj.checkImageDisplay(connect_stataus);
	}
}