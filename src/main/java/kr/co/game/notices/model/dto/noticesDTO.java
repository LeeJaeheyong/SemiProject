package kr.co.game.notices.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class noticesDTO {
	
	private int noticeNo; // 공지사항 번호
	private int userNum; // 유저 번호
	private String noticeTitle; // 공지사항 제목
	private String noticeInfo; // 공지사항 내용
	private String createdDate; // 작성일 sysdate
	private String noticeUp = "N"; // ?
	
	
}

