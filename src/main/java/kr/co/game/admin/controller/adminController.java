package kr.co.game.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		int boardLimit = 6;
		
		Map<String, Object> result = adminService.getAllGames(adminPagination, 
																  currentPage,
																  postCount,
																  pageLimit,
																  boardLimit
																  );
		pageInfoDTO piResult = (pageInfoDTO)result.get("pi");
		List<gameInfoDTO> userList = (List<gameInfoDTO>)result.get("user");
		
		model.addAttribute("pi",piResult);	
		model.addAttribute("user",userList);
		return "admin/admin";
	}
	
}
