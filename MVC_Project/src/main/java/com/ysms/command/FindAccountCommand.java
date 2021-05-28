package com.ysms.command;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.ShareVar_login;
import com.ysms.dao.Dao_Login;

public class FindAccountCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String inputedEmail = null;
		String inputedID = null;
		String result = null;
		Dao_Login dao = new Dao_Login();
		String mailSendReuslt = "false";
		
		
		inputedEmail = request.getParameter("email");
		inputedID = request.getParameter("id");
		
		if (inputedID == null || inputedID.equals("")) {
			result = dao.findAccount(inputedEmail);
			mailSendReuslt = sendEmail(inputedEmail, result, 0);
		} else {
			result = dao.findAccount(inputedEmail, inputedID);
			mailSendReuslt = sendEmail(inputedEmail, result, 1);
		}
		
		request.setAttribute("mailSendReuslt", mailSendReuslt);
		System.out.println("mailSendReuslt : " + mailSendReuslt);
	}
	
	public String sendEmail(String inputedEmail, String result, int type) {
		
		if(result == null || result.equals("")) {
			return "false";
		}
		
		// mail server 설정
		String host = "smtp.gmail.com";
		String user = ShareVar_login.hostID; // 자신의 네이버 계정
		String password = ShareVar_login.hostPW;// 자신의 네이버 패스워드
		
		// 메일 받을 주소
		String to_email = inputedEmail;
		System.out.println("inputedEmail : " + inputedEmail);

		// SMTP 서버 정보를 설정한다.
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", host);
		//google - TLS : 587, SSL: 467
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);
		
		// email 전송
		try {
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("안녕하세요. 너공나공의 계정찾기 결과를 알려드립니다.", "UTF-8");
			// 메일 내용
			if(type == 0) {
				msg.setText("아이디 :" + result );
			} else {
				msg.setText("비밀번호 :" + result );
			}
			
			Transport.send(msg);
			System.out.println("이메일 전송 완료");
			return "true";
		} catch (AddressException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			return "false";
		} catch (MessagingException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			return "false";
		}
	}

}
