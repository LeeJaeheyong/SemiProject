package kr.co.game.faqInfo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class faqInfoDTO {
	private int faqNo;
	private int categoryNo;
	private String faqTitle;
	private String faqInfo;
	private String createdDate;
	
	private String faqCategory;
}
