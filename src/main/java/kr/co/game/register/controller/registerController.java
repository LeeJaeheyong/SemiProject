package kr.co.game.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.co.game.register.dto.signupDTO;
import kr.co.game.register.service.mailSendService;
import kr.co.game.register.service.registerService;


@Controller
@RequestMapping("/game")
public class registerController {
	private final registerService registerService;
	private final mailSendService mailSendService;
	
	public registerController(registerService registerService, mailSendService mailSendService) {
		this.registerService = registerService;
		this.mailSendService = mailSendService;
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
//	public String signinForm(@RequestParam(value="status", required=false) String status, Model model) {
		public String signinForm(@ModelAttribute(value="status") String status, Model model) {
		if(status != null) {
			model.addAttribute("status", status);
		} 
		
		return "/login/loginin";
	}
	
	// 로그인 클릭시
	@PostMapping("/loginin")
	public String loginin(signupDTO signupDTO, HttpSession session, RedirectAttributes redirectAttributes) {
		
		String userId = signupDTO.getUserId();
		String userPassword = signupDTO.getUserPassword();

//		System.out.println("-----------------------");
//		System.out.println(userId);
//		System.out.println(userPassword);
//		System.out.println("-----------------------");
		
		// ID, PASSWORD 둘다 있을 경우
		if(userId != "" && userPassword !="") {
			signupDTO loginUser = registerService.loginin(signupDTO);
			
			// 정상 로그인
			if(loginUser != null) {
				System.out.println(loginUser.getUserId());
				session.setAttribute("userId", loginUser.getUserId());
				session.setAttribute("role", loginUser.getUserRole());
				session.setAttribute("userNum", loginUser.getUserNo());
				
				return "redirect:/game/main/form";
			
			// 비정상 로그인
			} else {
				redirectAttributes.addFlashAttribute("status", "failed");
				
				return "redirect:/game/logininForm";
			}
		
		// PASSWORD가 없을 때
		} else if(userId != "" && userPassword == ""){
			redirectAttributes.addFlashAttribute("status", "passX");
			
			return "redirect:/game/logininForm";
		
		// ID가 없을 때	
		} else if(userId == "" && userPassword != ""){
			redirectAttributes.addFlashAttribute("status", "idX");
			
			return "redirect:/game/logininForm";
		
		// ID, PASSWORD 둘다 없을 경우	
		} else {
			redirectAttributes.addFlashAttribute("status", "idX");
			
			return "redirect:/game/logininForm";
		}
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/game/main/form";
	}
	
	// 아이디 찾기
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "/login/findId";
	}
	
	// 아이디 찾기 버튼 클릭시 
	@PostMapping("/findId")
	public String findId(signupDTO signupDTO, Model model) {
		
		signupDTO findUser = registerService.findId(signupDTO);
		
		if(findUser != null) {
			model.addAttribute(findUser);
//			model.addAttribute("findUser", findUser);
		}
		
		return "/login/findIdSuccess";
	}
	
	// 비밀번호 찾기
	@GetMapping("/findPasswordForm")
	public String findPasswordForm() {
		return "login/findPassword";
	}
	
	// 비밀번호 찾기 버튼 클릭시
	@PostMapping("/findPassword")
	public String findPassword(signupDTO signupDTO, Model model) {

		String changePassword = registerService.findPassword(signupDTO);
		
		if(changePassword=="아이디, 이름, 이메일을 확인해주세요") {
			model.addAttribute("boolean", false);
		
		} else {
			model.addAttribute("boolean", true);
		
		}
		
		model.addAttribute("changePassword", changePassword);
		
		return "login/findPasswordSuccess";
	}
	
	// 이메일 인증
	@PostMapping("/emailConfirm")
	@ResponseBody
	public String emailConfirm(@RequestParam("email") String email) {
		System.out.println("Controller 단 : 이메일 인증 요청이 들어옴");
		System.out.println("Controller 단 : 이메일 인증 이메일 : " + email);
		
		String result = mailSendService.joinEmail(email);
		
		System.out.println("Controller 단 result : " + result);
		
		return "ok";
	}
	
	@PostMapping("/mailAuthCheck")
	public @ResponseBody String AuthCheck(@RequestParam("email") String email, @RequestParam("authNum") String authNum) {
		System.out.println("Controller 단 : mailAuthCheck");
		System.out.println("Controller 단 : " + email);
		System.out.println("Controller 단 : " + authNum);
		
		Boolean checked = mailSendService.CheckAuthNum(email, authNum);
		if(checked) {
			return "ok";
		} else {
			throw new NullPointerException("xxxxxxxxxxxxxxxxxx");
		}
	}
	
}	