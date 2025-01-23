package kr.co.game.register.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.game.register.dto.signupDTO;

@Mapper
public interface registerMapper {
	
	int userIdCheck(String userId);
	
	int signup(signupDTO signupDTO);

	signupDTO loginin(signupDTO signupDTO);
}
