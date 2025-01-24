package kr.co.game.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.game.register.dto.signupDTO;
import kr.co.game.register.service.registerService;


@Controller
@RequestMapping("/game")
public class registerController {
	private final registerService registerService;
	
	public registerController(registerService registerService) {
		this.registerService = registerService;
	}
	
	// 회원가입 페이지 이동
	@GetMapping("/loginupForm")
	public String loginupForm() {
		return "/login/loginup";
	}
	
	// 아이디 중복체크
	// key : username
	// value : asdf
	@PostMapping("/userIdCheck") 			// key : username			// value : asdf	
	public @ResponseBody String userIdCheck(@RequestParam("username") String userId) {
		System.out.println("userId = " + userId);
		String checkResult = registerService.userIdCheck(userId);
		return checkResult;
	}
	
	// 회원가입 클릭시
	@PostMapping("/loginup")
	public String loginup(signupDTO signupDTO) {

		registerService.signup(signupDTO);
		
		return "/login/loginupSuccess";
	}
	
	// 로그인 페이지 이동
	@GetMapping("/logininForm")
	public String signinForm() {
		return "/login/loginin";
	}
	
	// 로그인 클릭시
	@PostMapping("/loginin")
	public String loginin(signupDTO signupDTO, HttpSession session) {
		
		signupDTO loginUser = registerService.loginin(signupDTO);
		
		if(loginUser != null) {
			System.out.println(loginUser.getUserId());
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("role", loginUser.getUserRole());
			
			return "redirect:/game/main/form";
		} else {
			return "redirect:/game/logininForm";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/game/main/form";
	}
	
}	
