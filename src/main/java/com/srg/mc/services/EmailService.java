package com.srg.mc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.srg.mc.domain.Cliente;
import com.srg.mc.domain.Pedido;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendEmail(Pedido obj) {
		try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(obj.getCliente().getEmail());
            message.setSubject("Pedido confirmado! Código: " + obj.getId());
            message.setSentDate(new Date(System.currentTimeMillis()));
            message.setText(obj.toString());
            emailSender.send(message);

        } catch (MailException e){
           e.printStackTrace();
        } 
		
	}

	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		 SimpleMailMessage message = prepareNewPasswordEmail(cliente, newPass);
		 emailSender.send(message);
		 
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		 SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom(sender);
         message.setTo(cliente.getEmail());
         message.setSubject("Solicitacao de nova senha");
         message.setSentDate(new Date(System.currentTimeMillis()));
         message.setText("Nova senha: " + newPass);
         return message;
	}
}
