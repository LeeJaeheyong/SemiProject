package kr.co.game.mypage.controller;

import java.util.List;

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

import kr.co.game.mypage.dto.mypageContactDTO;
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
								   @RequestParam("picture") String picture,
								   @RequestParam("file") MultipartFile file) {
		
		System.out.println(picture);
		// userId 넘기고
		int result = mypageService.enroll(file, userId, picture);
		
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
	public String mypageQuery(@SessionAttribute(required=false,name="userId") String userId,
							  Model model) {
		System.out.println("Controller 단 : 응답완료!");

		List<mypageContactDTO> list = mypageService.AllList(userId);
		int totalCount = mypageService.getTotalCount(userId);
		
		for(int i=0; i<list.size(); i++) {
			mypageContactDTO contact = list.get(i);
	        System.out.println("Controller 단 : " + contact.getAnswer());
	        System.out.println("Controller 단 : " + contact.getAnswerDate());
	        System.out.println("Controller 단 : " + contact.getAnswerRe());
	        System.out.print("Controller 단 : " + contact.getContactInfo());
	        System.out.println("Controller 단 : " + contact.getContactNo());
	        System.out.println("Controller 단 : " + contact.getContactTitle());
	        System.out.println("Controller 단 : " + contact.getCreateDate());
		}

		model.addAttribute("list", list);
		model.addAttribute("count", totalCount);
		
		return "/mypage/mypageInquiry";
	}
	
}
