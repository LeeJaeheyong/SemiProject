package kr.co.game.mypage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class mypageDTO {
	private int userNo;
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private String userRole;
	private String userBirthdate;
	private mypageFileDTO fileDTO = new mypageFileDTO();
}
