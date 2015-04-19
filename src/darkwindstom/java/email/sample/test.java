package darkwindstom.java.email.sample;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
						
			int a = 1 / 0;
			
		} catch (Exception ex) {
			// TODO: handle exception
			
			SendEmail send = new SendEmail("發生例外錯誤");		
			SendExceptionMail("To mail address", send, ex);

		}

	}

	public static void SendExceptionMail(String sendmail_admin, SendEmail send, Exception ex) {

		StringBuffer sbf = new StringBuffer();
		
		for (int i = 0; i < ex.getStackTrace().length; i++) {
			sbf.append(ex.getStackTrace()[i] + "<br/>");
		}
		
		send.setEmailAddress(sendmail_admin);
		send.setHtml(sbf.toString());

		new Thread(send).start();
	}
	
}
