package kr.co.game.mypage.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageDTO;

public interface mypageService {

	public mypageDTO userInfoSelect(String myId);

	public void update(String myId, mypageDTO mypageDTO);

	public int delete(String userId, String userPassword, String myId);

	public int enroll(MultipartFile file);
	
	
}
