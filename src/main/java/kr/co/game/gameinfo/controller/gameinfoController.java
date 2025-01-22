package kr.co.game.gameinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.gameinfo.model.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;
import kr.co.game.gameinfo.model.service.gameInfoServiceImpl;
import kr.co.game.gameinfo.util.gameinfoPagination;

@Controller
@RequestMapping("/game")
public class gameinfoController {
	public final gameInfoServiceImpl gameinfoService;
	public final gameinfoPagination gameinfoPagination;
	
	public gameinfoController(gameInfoServiceImpl gameinfoService, gameinfoPagination gameinfoPagination) {
		this.gameinfoService = gameinfoService;
		this.gameinfoPagination = gameinfoPagination;
	}
	
	
	@GetMapping("/gameinfo/form")
		public String gameinfoForm(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
								   Model model) {
		int postCount = gameinfoService.getTotalCount();
		int pageLimit = 5;
		int boardLimit = 6;
		
		// 1. pageInfoDTO 대신 currentPage, postCount, pageLimit, boardLimit 다 인자로 작성
		// 2. 배열로 만들어서 넣어주는 방법
		Map<String, Object> result = gameinfoService.getAllGames(gameinfoPagination, 
																  currentPage,
																  postCount,
																  pageLimit,
																  boardLimit);
		pageInfoDTO piResult = (pageInfoDTO)result.get("pi");
		List<gameInfoDTO> gameList = (List<gameInfoDTO>)result.get("games");
		List<gameInfoDTO> genreList = gameinfoService.getGenres();
		List<gameInfoDTO> secondGenreList = gameinfoService.getsecondGenres();
		
		model.addAttribute("genres",genreList);
		model.addAttribute("secondgenres",secondGenreList);
		model.addAttribute("pi",piResult);	
		model.addAttribute("games",gameList);	
		
			return "gameinfo/gameinfo";
		}
		@PostMapping("/gameinfo/upload")
		public String fileUpload(FileDTO fileDTO,
								 @RequestParam("file") MultipartFile file) {
			gameinfoService.uploadFile(fileDTO,file);
			return "gameinfo/gameinfo";
		}
}
