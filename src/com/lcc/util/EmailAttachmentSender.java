package com.lcc.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
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

public class EmailAttachmentSender {

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message, String[] attachFiles) throws Exception {
		// set SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// create a new session with an authenticator
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, authenticator);
		// create a new e-mail message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));

		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		//create message part
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(message,"text/html;charset=UTF-8");
		
		//create multi-part
		Multipart multipart = new MimeMultipart();  
        multipart.addBodyPart(mimeBodyPart); 
        
        //adds attachments
        if (attachFiles !=null && attachFiles.length>0) {
			for(String filePath: attachFiles){
				MimeBodyPart attachPart = new MimeBodyPart();  
				  
                try {  
                    attachPart.attachFile(filePath);  
                } catch (IOException ex) {  
                    ex.printStackTrace();  
                }  
                multipart.addBodyPart(attachPart);  
			}
		}
        // sets the multi-part as e-mail's content  
        msg.setContent(multipart);  
        // sends the e-mail  
        Transport.send(msg);  
	 }
	
	 public  void send(String toMail , String username  ) {   
	        String host = "smtp.126.com";  
	        String port = "25";  
	        String mailFrom = "nietaooldman@126.com";  
	        String password = "tao8419552";  
	  
	        String mailTo = toMail;  
	        String subject = "subject";  
	        	        
	        String message = "<a href=\"http://localhost:8080/Shop/from/user_save!usersave?username="+ username + "\">点击</a>"; 
	        String[] attachFiles = null;
	        try {  
	            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,  
	                    subject, message, attachFiles);  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	        }  
	    }  

}
