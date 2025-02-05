package kr.co.game.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.faqDTO;
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
		System.out.println(faqDTO.getCategoryNo());
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
	
}
