package kr.co.game.admin.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class noticeDTO {
	private int noticeNo;
	private String userId;
	private String noticeTitle;
	private String noticeInfo;
	private String createDate;
	private String noticeUp;
}
