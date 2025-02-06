package kr.co.game.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.contactDTO;
import kr.co.game.admin.model.dto.faqDTO;
import kr.co.game.admin.model.dto.noticeDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;
import kr.co.game.admin.model.service.adminServiceImpl;
import kr.co.game.admin.util.adminPagination;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;

@Controller
@RequestMapping("/game")
public class adminController {
	private final adminServiceImpl adminService;
	private final adminPagination adminPagination;
	
	public adminController(adminServiceImpl adminService,adminPagination adminPagination) {
		this.adminService = adminService;
		this.adminPagination = adminPagination;
	}

	@GetMapping("/admin/form")
	public String adminForm(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							Model model) {
		int postCount = adminService.getTotalCount();
		int pageLimit = 5;
		int boardLimit = 100;
		
		Map<String, Object> result = adminService.getAllPeople(adminPagination, 
																  currentPage,
																  postCount,
																  pageLimit,
																  boardLimit
																  );
		pageInfoDTO piResult = (pageInfoDTO)result.get("pi");
		List<adminDTO> userList = (List<adminDTO>)result.get("user");
		
		model.addAttribute("pi",piResult);	
		model.addAttribute("user",userList);
		
		Map<String, Object> faqResult = adminService.getAllfaq(adminPagination, 
				  currentPage,
				  postCount,
				  pageLimit,
				  boardLimit
				  );
		pageInfoDTO faqPage = (pageInfoDTO)faqResult.get("pi");
		List<faqDTO> faqList = (List<faqDTO>)faqResult.get("faq");
		
		model.addAttribute("faqPage",faqPage);	
		model.addAttribute("faq",faqList);
		Map<String, Object> gameSearchResult = adminService.getAllGames(adminPagination, 
																	  currentPage,
																	  postCount,
																	  pageLimit,
																	  boardLimit
																	  );
		pageInfoDTO gamedbPage = (pageInfoDTO)gameSearchResult.get("pi");
		List<gameInfoDTO> gamedbList = (List<gameInfoDTO>)gameSearchResult.get("game");
		
		model.addAttribute("gamePage",gamedbPage);	
		model.addAttribute("gameDB",gamedbList);
		int noticeCount = adminService.getTotalNotice();
		Map<String, Object> noticeResult = adminService.getAllNotices(adminPagination, 
																	  currentPage,
																	  noticeCount,
																	  pageLimit,
																	  boardLimit);
		pageInfoDTO noticePage = (pageInfoDTO)noticeResult.get("pi");
		List<noticeDTO> noticeList = (List<noticeDTO>)noticeResult.get("notice");
	
		model.addAttribute("noticePage",noticePage);	
		model.addAttribute("notice",noticeList);
		Map<String, Object> inquiryResult = adminService.getAllinquiries(adminPagination, 
				  currentPage,
				  noticeCount,
				  pageLimit,
				  boardLimit);
		pageInfoDTO inquiryPage = (pageInfoDTO)inquiryResult.get("pi");
		List<contactDTO> inquiryList = (List<contactDTO>)inquiryResult.get("inquiry");
		
		model.addAttribute("inquiryPage",inquiryPage);	
		model.addAttribute("inquiry",inquiryList);
		return "admin/admin";
	}
	@PostMapping("/admin/rolechange")
	public String rolechange(@RequestParam("cate")List<String> category,
							 @RequestParam("chk")List<Integer> check,
							 Model model) {
		int result = adminService.changeRole(category,check);
		if(result==1) {
			System.out.println("goodJob!");
		}
		
		
		return "redirect:/game/admin/form";
	}
	@PostMapping("/admin/faq/enroll")
	public String faqEnroll(faqDTO faqDTO,Model model) {
		int result = adminService.enrollFAQ(faqDTO);
		return "redirect:/game/admin/form";
	}
	@GetMapping("/admin/faq/enroll/form")
	public String faqEnrollForm(Model model) {
		List<faqDTO> categoryList = adminService.getCategory();
		model.addAttribute("category", categoryList);
		return "faq/faqEnroll";
	}
	@GetMapping("/admin/faq/delete")
	public String faqDelete(@RequestParam("faqNo")int faqNo) {
		int result = adminService.deleteFAQ(faqNo);
		return "redirect:/game/admin/form";
	}
	@GetMapping("admin/gameinfo/delete")
	public String gameinfoDelete(@RequestParam("gameNo")int gameNo) {
		int result = adminService.deleteGameInfo(gameNo);
		return "redirect:/game/admin/form";
	}
	@GetMapping("/admin/inquiry/form")
	public String inquiryForm(@RequestParam("contactNo")int contactNo,Model model) {
		contactDTO inquiry = adminService.getInquiry(contactNo);
		model.addAttribute("inquiry", inquiry);
		return "/contact/answer";
	}
	@PostMapping("/admin/inquiry/answer")
	public String answer(@RequestParam("answerText")String answerText,
						 @RequestParam("contactNo")int contactNo,
						 @RequestParam("userId")String userId,
						 Model model) {
		int answer = adminService.answer(answerText,contactNo,userId);
		model.addAttribute("answer", true);
		return "redirect:/game/admin/form";
	}
	
}
