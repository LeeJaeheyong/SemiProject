package kr.co.game.register.service;

import java.util.Random;

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
    
    public String randomPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                                       .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                       .limit(targetStringLength)
                                       .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                       .toString();
        return generatedString;
      }

    // 회원가입 아이디 중복검사
    @Override
    public String userIdCheck(String userId) {

        int result = registerMapper.userIdCheck(userId);

        if(result == 0) {
            return "ok";
        } else {
            return "no";
        }
    }

    // 회원가입 패스워드 암호화
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

    // 로그인
    @Override
    public signupDTO loginin(signupDTO signupDTO) {
        signupDTO loginUser = registerMapper.loginin(signupDTO);

        if(loginUser != null) {
        	if(passwordEncoder.matches(signupDTO.getUserPassword(), loginUser.getUserPassword())) {
        		return loginUser;
        	}
        }

        return null;
    }
    
    // 아이디 찾기
    @Override
    public signupDTO findId(signupDTO signupDTO) {
    	return registerMapper.findId(signupDTO);
    }
    
    // 비밀번호 찾기
    @Override
    public String findPassword(signupDTO signupDTO) {
    	
    	// 1. 아이디, 이름, 이메일 입력했을 때 일치하는 데이터가 있는지 COUNT(*)
    	int result = registerMapper.findPassword(signupDTO);
    	// 2. 있으면 임시 비밀번호를 알려줘야 하니까 랜덤 문자열 생성하고 update
    	if(result == 1) {
    		String randomPassword = randomPassword();
    		
    		String encodePassword = passwordEncoder.encode(randomPassword);
    		    		
    		registerMapper.changePassword(signupDTO, encodePassword);
    		
    		String changePassword = "임시비밀번호는  " + randomPassword + "  입니다";
    		
    		return changePassword;
    	}
    	// 3. 임시비밀전호 유저한테 model에 담아서 뿌려줌
    	return "아이디, 이름, 이메일을 확인해주세요";
    }
    
}

