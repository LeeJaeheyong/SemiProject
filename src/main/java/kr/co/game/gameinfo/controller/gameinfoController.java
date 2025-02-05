package kr.co.game.gameinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;
import kr.co.game.gameinfo.model.service.gameInfoServiceImpl;
import kr.co.game.gameinfo.util.gameinfoPagination;


@Controller
@RequestMapping("/game")
public class gameinfoController {
	private final gameInfoServiceImpl gameinfoService;
	private final gameinfoPagination gameinfoPagination;
	
	public gameinfoController(gameInfoServiceImpl gameinfoService, gameinfoPagination gameinfoPagination) {
		this.gameinfoService = gameinfoService;
		this.gameinfoPagination = gameinfoPagination;
	}

	@GetMapping("/gameinfo/form")
		public String gameinfoForm(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
								   @RequestParam(value="publisher", defaultValue="") String pub,
								   @RequestParam(value="firstGenre", defaultValue="") String gen,
								   Model model) {
		int postCount = gameinfoService.getTotalCount();
		int pageLimit = 5;
		int boardLimit = 6;
		
		Map<String, Object> result = gameinfoService.getAllGames(gameinfoPagination, 
																  currentPage,
																  postCount,
																  pageLimit,
																  boardLimit,
																  pub,
																  gen);
		pageInfoDTO piResult = (pageInfoDTO)result.get("pi");
		List<gameInfoDTO> gameList = (List<gameInfoDTO>)result.get("games");
		List<gameInfoDTO> genreList = gameinfoService.getGenres();
		List<gameInfoDTO> secondGenreList = gameinfoService.getsecondGenres();
		List<gameInfoDTO> publisherList = gameinfoService.getpublisher();
		
		model.addAttribute("genres",genreList);
		model.addAttribute("secondgenres",secondGenreList);
		model.addAttribute("pi",piResult);	
		model.addAttribute("games",gameList);	
		model.addAttribute("publisher",publisherList);	
		
			return "gameinfo/gameinfo";
		}


		@PostMapping("/gameinfo/upload")
		public String fileUpload(FileDTO fileDTO,
								 @RequestParam("file") MultipartFile file) {
			gameinfoService.uploadFile(fileDTO,file);
			return "gameinfo/gameinfo";
		}
		@GetMapping("gameinfo/detail/form")
		public String gameDetail(@RequestParam("gameNo")int gameNo,Model model) {
			gameInfoDTO result = gameinfoService.getGame(gameNo);
			model.addAttribute("game", result);
			return "detail/detail";
		}
		
		@GetMapping("/gameinfo/enroll/form")
		public String gameEnrollform(Model model) {
			List<gameInfoDTO> first = gameinfoService.getGenres();
			List<gameInfoDTO> second = gameinfoService.getsecondGenres();
			List<gameInfoDTO> publisher = gameinfoService.getpublisher();
			model.addAttribute("first", first);
			model.addAttribute("second", second);
			model.addAttribute("publisher", publisher);
			return "gameinfo/gameEnroll";
		}
		@PostMapping("/gameinfo/enroll")
		public String gameEnroll(gameInfoDTO gameInfodto,
								 FileDTO fileDTO,
								 @RequestParam("file") MultipartFile file,
								 @RequestParam("newGenre")String newGerne) {
			int result = gameinfoService.enroll(gameInfodto,newGerne);
			gameinfoService.uploadFile(fileDTO, file);
			return "admin/admin";
		}
	}


