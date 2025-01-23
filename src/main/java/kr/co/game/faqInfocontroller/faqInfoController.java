package kr.co.game.faqInfocontroller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.game.faqInfo.model.dto.faqInfoDTO;
import kr.co.game.faqInfo.model.dto.faqInfoPageInfoDTO;
import kr.co.game.faqInfo.model.service.faqInfoServiceImpl;
import kr.co.game.faqInfo.util.faqInfoPagination;

@Controller
@RequestMapping("/game")
public class faqInfoController {
	
	private final faqInfoServiceImpl faqInfoService;
	private final faqInfoPagination faqInfoPage;
	
	public faqInfoController(faqInfoServiceImpl faqInfoService,
							 faqInfoPagination faqInfoPage) {
		this.faqInfoService = faqInfoService;
		this.faqInfoPage = faqInfoPage;
	}
	
	@GetMapping("/faqInfo/form")
	public String faqInfoForm(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
						  @ModelAttribute faqInfoPagination faqInfoPage,
						  Model model) {
		
		int postCount = faqInfoService.faqInfoCount(faqInfoPage);
		int pageLimit = 10;
		int boardLimit = 5;

		// 1. pageInfoDTO 대신 currentPage, postCount, pageLimit, boardLimit 다 인자로 작성
		// 2. 배열로 만들어서 넣어주는 방법
		Map<String, Object> result = faqInfoService.getFaqList(faqInfoPage, currentPage, postCount, pageLimit, boardLimit);

		faqInfoPageInfoDTO fpi = (faqInfoPageInfoDTO) result.get("fpi");
		
		List<faqInfoDTO> postsResult = (List<faqInfoDTO>) result.get("posts");
		System.out.println(postsResult.get(0).getFaqInfo());
		
		model.addAttribute("posts", postsResult);
		model.addAttribute("fpi", fpi);

		return "faq/faq";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
