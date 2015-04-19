package darkwindstom.java.email.sample;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail implements Runnable {

	public String username = "user@gmail.com";
	public String password = "password";
	public boolean debug = false;

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String From = "";

	public void setFrom(String from) {
		this.From = from;
	}

	public String Subject = "";
	public String emailAddress;
	public String html;

	public SendEmail(String Subject) {
		this.Subject = Subject;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public void run() {
		SendException(this.emailAddress, this.html);
	}

	public void SendException(String emailAddress, String html) {

		try {
		
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.setProperty("mail.user", this.username);
		    props.setProperty("mail.password", this.password);

			
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SendEmail.this.username, SendEmail.this.password);
				}
			});
						
			String strEmail = emailAddress;
				
			session.setDebug(true);			
			
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("from mail address", "mail title", "UTF-8"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strEmail));
			message.setSubject(this.Subject);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(html, "text/html; charset=UTF-8");

			Multipart email = new MimeMultipart();
			email.addBodyPart(textPart);

			message.setContent(email);

			Transport.send(message);
		
			Thread.sleep(5000L);
			
			System.out.println("success");
		
		} catch (MessagingException e) {			
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
			
		} catch (InterruptedException e) {
			System.out.println(e);
			
		} catch (Exception e) {			
			System.out.println(e);
			
		}
		
	}
}
