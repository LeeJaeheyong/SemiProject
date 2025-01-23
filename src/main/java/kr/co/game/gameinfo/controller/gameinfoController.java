package kr.co.game.gameinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class gameinfoController {

	@GetMapping("/gameinfo/form")
		public String gameinfoForm() {
			return "gameinfo/gameinfo";
		}
	
}
