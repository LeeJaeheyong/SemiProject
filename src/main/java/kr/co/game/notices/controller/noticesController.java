package kr.co.game.notices.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.game.notices.model.dto.noticesDTO;
import kr.co.game.notices.model.dto.noticesPageInfoDTO;
import kr.co.game.notices.model.dto.noticesSearchDTO;
import kr.co.game.notices.model.service.noticesServiceImpl;
import kr.co.game.notices.util.noticesPagination;

@Controller
@RequestMapping("/game")
public class noticesController {
	
	private final noticesServiceImpl noticesService;
	private final noticesPagination noticesPage;
	
	public noticesController(noticesServiceImpl noticesService, noticesPagination noticesPage) {
		this.noticesService = noticesService;
		this.noticesPage = noticesPage;
	}
	
	@GetMapping("/notices/form")
	public String noticesForm(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							  @ModelAttribute noticesSearchDTO noticesSearchDTO,
							  Model model) {

			int postCount = noticesService.getTotalCount(noticesSearchDTO);
			int pageLimit = 5;
			int boardLimit = 5;

			Map<String, Object> result = noticesService.getAllPosts(noticesPage, 
												  					currentPage,
												  					postCount,
												  					pageLimit,
												  					boardLimit,
												  					noticesSearchDTO);
			
			noticesPageInfoDTO npiResult = (noticesPageInfoDTO) result.get("npi");
			List<noticesDTO> npostsResult = (List<noticesDTO>) result.get("nposts");

	            // 모델에 데이터 추가
	            model.addAttribute("nposts", npostsResult);
	            model.addAttribute("npi", npiResult);
	            model.addAttribute("noticesSearchDTO", noticesSearchDTO); 
		
		return "notices/notices";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
