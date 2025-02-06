package kr.co.game.register.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.register.dto.signupDTO;

@Mapper
public interface registerMapper {
	
	int userIdCheck(String userId);
	
	int signup(signupDTO signupDTO);

	signupDTO loginin(signupDTO signupDTO);

	signupDTO findId(signupDTO signupDTO);

	int findPassword(signupDTO signupDTO);

	void changePassword(@Param("signupDTO") signupDTO signupDTO, @Param("randomPassword") String randomPassword);
}
