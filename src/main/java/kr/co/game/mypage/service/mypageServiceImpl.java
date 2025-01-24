package kr.co.game.mypage.service;


import java.io.IOException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageContactDTO;
import kr.co.game.mypage.dto.mypageDTO;
import kr.co.game.mypage.dto.mypageFileDTO;
import kr.co.game.mypage.mapper.mypageMapper;
import kr.co.game.mypage.util.MypageFileUpload;


@Service
public class mypageServiceImpl implements mypageService {
	private final mypageMapper mypageMapper;
	private PasswordEncoder passwordEncoder;
	private final MypageFileUpload fu;
	
	public mypageServiceImpl(mypageMapper mypageMapper, PasswordEncoder passwordEncoder, MypageFileUpload fu) {
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
	public int enroll(MultipartFile file, String userId, String picture) {
		
		int id = mypageMapper.getId(userId);
		
		mypageFileDTO mypagefileDTO = new mypageFileDTO();
		
		// 1. 기존에 사진을 올렸는지 확인
		//  SELECT * FROM USER_PROFILE WHERE user_id = #{userId}
		//  조회된 결과 resultMap 다 담고
		mypageFileDTO result = mypageMapper.fileCheck(id);
		//  객체가 null인지 아닌지 판단
		
		// picture가 eidt면
		if(picture.equals("edit")) {
			System.out.println("하하하핳");
			//  기존에 사진을 올렸으면 1, 올린적이 없으면 0
			if(result != null) {
				mypageMapper.deleteFile(id);
				try {
					fu.deleteFile(result.getLOCAL_PATH(), "userPro", result.getChangeName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 2. 1번에서 반환된 값이 1이면 기존꺼 삭제 -> 업로드, 0이면 업로드
			
			// 파일 업로드
			// mypageFileDTO myapgefileDTO 객체 생성해서 두번째 인자로 넘겨주기
			try {
				
				fu.uploadFile(file, mypagefileDTO, "userPro");
				
				// DB 저장
				mypageMapper.enrollFile(mypagefileDTO, userId, id);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return 0;
			
		} else {
			mypageMapper.deleteFile(id);
		}
		
		return 0;
	}
	
	@Override
	public mypageFileDTO updatePro(String userId) {
		int userNo = mypageMapper.getId(userId);
		return mypageMapper.updatePro(userNo);
	}
	
	@Override
	public List<mypageContactDTO> AllList(String userId) {
		
		int userNo = mypageMapper.getId(userId);
		
		System.out.println("mypageService 단" + userNo);

		return mypageMapper.AllList(userNo);
	}
	
	
	
	
	
}
