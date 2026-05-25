package lv.venta.EATSystem.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lv.venta.EATSystem.services.IEmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins ="http://localhost:3000")
@Transactional
public class EmailController {

	@Autowired
	IEmailService emailService;
	
	@GetMapping("/send")
	public void sendEmail (@Valid @RequestBody String email, @Valid @RequestBody LocalDateTime dateTimeStart, @Valid @RequestBody LocalDateTime dateTimeEnd) {
		emailService.sendEmail(email, dateTimeStart, dateTimeEnd);
	}
}
