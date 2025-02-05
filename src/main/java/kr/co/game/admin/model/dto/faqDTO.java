package kr.co.game.admin.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class faqDTO {
	private int faqNo;
	private String faqCategory;
	private String faqTitle;
	private String createDate;
	private String faqInfo;
	private int categoryNo;
}
