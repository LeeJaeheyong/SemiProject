package kr.co.game.admin.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class contactDTO {
	private int contactNo;
	private String userId;
	private String faqCategory;
	private String contactTitle;
	private String contactInfo;
	private String createDate;
	private String answer;
}
