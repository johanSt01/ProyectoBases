package application.services;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class EmailService {
	public static void enviarEmail(String emailDestino, String asunto, String cuerpo){
		//La direcci�n de correo de env�o
	    String remitente = "soportecamu@gmail.com";
	    //La clave de aplicaci�n obtenida
	    String claveemail = "gfdtlfdfmgvpwraq";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", claveemail);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestino));   //Se podr�an a�adir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, claveemail);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();

	        JOptionPane.showMessageDialog(null, "Se ha enviado una notificacion de estado de soporte al usuario con email: "+emailDestino);
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}

}
