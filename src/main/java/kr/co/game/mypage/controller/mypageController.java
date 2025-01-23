package kr.co.game.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageDTO;
import kr.co.game.mypage.dto.mypageFileDTO;
import kr.co.game.mypage.service.mypageService;

@Controller
@RequestMapping("/game")
public class mypageController {
	private final mypageService mypageService;
	
	public mypageController(mypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	// 내 프로필 
	@GetMapping("/mypageForm")
	public String mypageForm(@SessionAttribute("userId") String userId,
							 Model model) {
		
		// 프로필 사진
		mypageFileDTO result = mypageService.updatePro(userId);
		
		model.addAttribute("post", result);
		
		return "/mypage/mypagePro";
	}
	
	// 사진 적용
	@PostMapping("/mypageForm/enroll")
	public String mypageFormEnroll(@SessionAttribute("userId") String userId,
								   @RequestParam("file") MultipartFile file) {
									
		// userId 넘기고
		int result = mypageService.enroll(file, userId);
		
		return"redirect:/game/mypageForm";
	}	 
	
	// 내 정보 변경 화면
	@GetMapping("/mypageUpdateForm") 
	public String mypageUpdateForm(@SessionAttribute(required=false,name="userId") String myId, Model model) {
		mypageDTO result = mypageService.userInfoSelect(myId);
		model.addAttribute("myData", result);
		return "/mypage/mypageInfo";
	}
	
	// 내 정보 수정 클릭시
	@PostMapping("/mypageUpdate")
	public String mypageUpdate(@SessionAttribute(required=false,name="userId") String myId, mypageDTO mypageDTO) {
		mypageService.update(myId, mypageDTO);
		
		return "/mypage/mypagePro";
	}
	
	// 회원 탈퇴 화면
	@GetMapping("/mypageDeleteForm") 
	public String mypageDeleteForm(@SessionAttribute(required=false, name="userId") String myId, Model model) {
		
		mypageDTO result = mypageService.userInfoSelect(myId);
		
		model.addAttribute("myData", result);
		return "/mypage/mypageDelete";
	}
	
	// 회원 탈퇴 눌렀을 때
	// loalhost:8080/game/myPageDelete?userId=islandtim
	@PostMapping("/mypageDelete")
	public @ResponseBody int mypageDelete(@RequestParam("userId") String userId,
									      @RequestParam("userPassword") String userPassword,
									      @SessionAttribute(required=true,name="userId") String myId) {
		// 1. RequestParam으로 userId도 받아오기
		
		// myId : 세션이 있는(로그인 한) 사용자의 id
		// userId : ajax로 요청한 사용자의 id
		// userPassword : ajax로 요청한 사용자의 비밀번호
		System.out.println("111111111111111111" + userId);
		System.out.println("222222222222222222" + userPassword);
		System.out.println("333333333333333333" + myId);
		
		int result = mypageService.delete(userId, userPassword, myId);
		
		if(result == 1) {
			System.out.println("회원 탈퇴완료");
		} else {
			System.out.println("응 탈퇴 못해~");
		}
		
		return result;
	
	}
	
	// 내 문의 리스트
	@GetMapping("/mypageQuery") 
	public String mypageQuery(@ModelAttribute mypageDTO mypageDTO, Model model) {
		
		return "/mypage/mypageInquiry";
	}
	
}
