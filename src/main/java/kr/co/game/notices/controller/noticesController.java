package kr.co.game.notices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class noticesController {
	
	@GetMapping("/notices/form")
	public String noticesForm() {
		return "notices/notices";
	}
}
