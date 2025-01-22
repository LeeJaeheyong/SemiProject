package kr.co.game.mypage.service;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageDTO;
import kr.co.game.mypage.mapper.mypageMapper;

@Service
public class mypageServiceImpl implements mypageService {
	private final mypageMapper mypageMapper;
	private PasswordEncoder passwordEncoder;
	private final FileUpload fu;
	
	public mypageServiceImpl(mypageMapper mypageMapper, PasswordEncoder passwordEncoder, FileUpload fu) {
		this.mypageMapper = mypageMapper;
		this.passwordEncoder = passwordEncoder;
		this.fu = fu;
	}
	
	@Override
	public mypageDTO userInfoSelect(String myId) {
		return mypageMapper.userInfoSelect(myId);
	}
	
	@Override
	public void update(String myId, mypageDTO mypageDTO) {
//		System.out.println(mypageDTO.getUserPassword());
//		System.out.println(mypageDTO.getUserPhoneNumber());
//		System.out.println(myId);
		
		// 패스워드 암호화
		String updatePassword = mypageDTO.getUserPassword();
		
		String encryptedPassword = passwordEncoder.encode(updatePassword);
		
		mypageDTO.setUserPassword(encryptedPassword);
		
		System.out.println(encryptedPassword + "하하하");
		
		mypageMapper.update(myId, mypageDTO);

	}
	
	@Override
	public int delete(String userId, String userPassword, String myId) {
		
		String inputPassword = userPassword;
		String password = mypageMapper.getPassword(myId);
		
		System.out.println(inputPassword);
		System.out.println(password);
		
		if(passwordEncoder.matches(inputPassword, password) && userId.equals(myId)) {
			int result = mypageMapper.delete(myId);
			return result;
		} else {
			return 0;
		}
		
	}		
	
	@Override
	public int enroll(MultipartFile file) {
		int result = 0;
		
		result = mypageMapper.enroll();
		
		if(result == 1 && file != null && !file.isEmpty()) {
			try {
				fu.uploadFile(file, "free");
				boardMapper.enrollFile(boardDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
