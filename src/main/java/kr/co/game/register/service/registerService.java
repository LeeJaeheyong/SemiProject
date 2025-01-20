package kr.co.game.register.service;

import kr.co.game.register.dto.signupDTO;

public interface registerService {
	
	public String userIdCheck(String userId);
	
	public int signup(signupDTO signupDTO);
	
	public signupDTO loginin(signupDTO signupDTO); 
}
