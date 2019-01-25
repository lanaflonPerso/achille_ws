package achille.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private Properties props;
	private Session session;
	private String messageAuto;
	
	public EmailService() {
		
	   this.props = new Properties();
	   this.props.put("mail.smtp.auth", "true");
	   this.props.put("mail.smtp.starttls.enable", "true");
	   this.props.put("mail.smtp.host", "smtp.gmail.com");
	   this.props.put("mail.smtp.port", "587");
	   
	   this.session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("application.achille@gmail.com", "Eugene2017");
	      }
	   });
	   
	   this.messageAuto = "Ce message vous a été envoyé automatiquement, merci de ne pas répondre";
	   
	}
	
	
	public void sendMail(String subject, String content, String recipient) throws AddressException, MessagingException, IOException {
		
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress(recipient, false));
	   
	   String fullContent = content + System.getProperty("line.separator") + System.getProperty("line.separator") + this.messageAuto;
	   
	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	   msg.setSubject("[Achille] " + subject);
	   msg.setContent(fullContent, "text/html");
	   msg.setSentDate(new Date());

	   Transport.send(msg);   

	}
	
	
	public void sendmail() throws AddressException, MessagingException, IOException {


		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("lemeur.burban@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lemeur.burban@gmail.com"));
		   msg.setSubject("[Achille] Documents manquants");
		   msg.setContent("Ce message vous a été envoyé automatiquement, merci de ne pas répondre", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Ce message vous a été envoyé automatiquement, merci de ne pas répondre", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   Transport.send(msg);   
		}

}
