package kr.co.game.contact.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class contactDTO {
    private int contactNo;       // 문의번호
    private int categoryNo;      // 카테고리 번호
    private int userNum;         // 유저 번호
    private String contactTitle; // 문의 제목
    private String contactInfo;  // 문의 내용
    private String createdDate;  // 작성일
    private String answerRe = "N"; // 답변 여부 (기본값 "N")
}
