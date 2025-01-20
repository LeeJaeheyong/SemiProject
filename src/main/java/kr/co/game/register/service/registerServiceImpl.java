package kr.co.game.register.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.game.register.dto.signupDTO;
import kr.co.game.register.mapper.registerMapper;

@Service
public class registerServiceImpl implements registerService {
	private final registerMapper registerMapper;
	private PasswordEncoder passwordEncoder;
	
	public registerServiceImpl(registerMapper registerMapper, PasswordEncoder passwordEncoder) {
		this.registerMapper = registerMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 아이디 중복검사 
	@Override
	public String userIdCheck(String userId) {
		
		int result = registerMapper.userIdCheck(userId);
		
		if(result == 0) {
			return "ok";
		} else {
			return "no";
		}
	}
	
	@Override
	public int signup(signupDTO signupDTO) {
		
		int userIdCheck = registerMapper.userIdCheck(signupDTO.getUserId());
		
		// 패스워드 암호화
		if(userIdCheck != 1) {
			String encodePassword = passwordEncoder.encode(signupDTO.getUserPassword());
			signupDTO.setUserPassword(encodePassword);
			
			return registerMapper.signup(signupDTO);
		} else {
			return 0;
		}
	}
	
	@Override
	public signupDTO loginin(signupDTO signupDTO) {
		signupDTO loginUser = registerMapper.loginin(signupDTO);
		
		System.out.println(signupDTO.getUserPassword());
		System.out.println(loginUser.getUserPassword());
		
		if(passwordEncoder.matches(signupDTO.getUserPassword(), loginUser.getUserPassword())) {
			return loginUser;
		}
		
		return null;
	}
	
	
	
	
	
}
