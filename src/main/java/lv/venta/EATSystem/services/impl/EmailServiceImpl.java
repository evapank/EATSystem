package lv.venta.EATSystem.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lv.venta.EATSystem.services.IEmailService;

public class EmailServiceImpl implements IEmailService{
	
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(String email, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject("Meeting notice");
		message.setText("Scheduled meeting for " + dateTimeStart + " to " + dateTimeEnd);
		
		mailSender.send(message);
		
	}

}
