package kr.co.game.mypage.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageContactDTO;
import kr.co.game.mypage.dto.mypageDTO;
import kr.co.game.mypage.dto.mypageFileDTO;

public interface mypageService {

	public mypageDTO userInfoSelect(String myId);

	public void update(String myId, mypageDTO mypageDTO);

	public int delete(String userId, String userPassword, String myId);

	public int enroll(MultipartFile file, String userId, String picture);
	
	public mypageFileDTO updatePro(String userId);

	public List<mypageContactDTO> AllList(String userId);
	
	
}
