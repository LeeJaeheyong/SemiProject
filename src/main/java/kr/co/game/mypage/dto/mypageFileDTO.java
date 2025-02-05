package kr.co.game.mypage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class mypageFileDTO {
	private final String LOCAL_PATH = "C:\\Dev\\spring_work_sapce\\SpringProject-3\\src\\main\\resources\\static\\uploads";
	private final String RESOURCES_PATH = "/uploads/userPro";
	
	private int no;
	private String changeName;
	private String originalName;
	private String uploadDate;
	private String extension;
	private long size;
}
