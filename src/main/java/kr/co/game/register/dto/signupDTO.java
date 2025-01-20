package kr.co.game.register.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class signupDTO {

	private int userNo;
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private String userRole;
	private String userBirthdate;
	
}
