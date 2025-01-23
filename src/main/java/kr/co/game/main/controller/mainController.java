package kr.co.game.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class mainController {
	
	@GetMapping("/main/form")
	public String mainform() {
		return "main/main";
	}
	
	@GetMapping("/detail/form")
	public String detailForm() {
		return "detail/detail";

	}
}
