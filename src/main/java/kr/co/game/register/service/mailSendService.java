package kr.co.game.register.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.game.register.util.redisUtil;

@Service
public class mailSendService {
	
	@Autowired
	private JavaMailSender mailSender;
	private int authNumber;
	
	private final redisUtil redisUtil;
	
	public mailSendService(redisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

	// 인증번호 체크
	public boolean CheckAuthNum(String email, String authNum) {
		if(redisUtil.getData(email) == null) {
			return false;
		} else if(redisUtil.getData(email).equals(authNum)) {
			return true;
		} else {
			return false;
		}
	}
	
	// 인증번호 생성
	public void makeRandomNumber() {
		Random r = new Random();
		String randomNumber = "";
		
		for(int i=0; i<6; i++) {
			randomNumber += Integer.toString(r.nextInt(10));
		}
		
		authNumber = Integer.parseInt(randomNumber);
		System.out.println("mailSendService 단 랜덤 인증번호: " + authNumber);
	}
	
	@Value("${mail-naver-id}")
	private String mailNaverId;
	// mail을 어디서 보내는지, 어디로 보내는지, 인증 번호를 html 형식으로 어떻게 보내는지 작성
	public String joinEmail(String email) {
		System.out.println("-----" + mailNaverId + "-----");
		makeRandomNumber(); // 인증번호 생성
		String setFrom = mailNaverId; // email-config에 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "The Rank 회원가입 인증 이메일 입니다."; // 이메일 제목
		String content = 
						"The Rank를 방문해주셔서 감사합니다." +
						"<br><br>" +
						"인증 번호는 " + authNumber + "입니다." +
						"<br>" + 
						"인증번호는 5분 유효합니다."; // 이메일 내용
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber);
	}
	
	// 이메일 전송
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage(); // JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8"); // 이메일 메시지와 관련된 설정 수행
			helper.setFrom(setFrom); // 이메일의 발신자 주소 설정
			helper.setTo(toMail); // 이메일의 수신자 주소 설정
			helper.setSubject(title); // 이메일의 제목을 설정
			helper.setText(content, true); // 이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로함
			mailSender.send(message);
		} catch (MessagingException e) { // 이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류
										 // 이런한 경우 MessageingException이 발생
			e.printStackTrace(); // e.printStackTrace()는 예외를 기본 오류 스트림에 출력하는 메서드
		}
		
		// 5분 동안 redis 저장
		redisUtil.setDataExpire(toMail, Integer.toString(authNumber), 60*5L);
		
	}
	
}
