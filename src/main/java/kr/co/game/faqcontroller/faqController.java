package kr.co.game.faqcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class faqController {
	
	@GetMapping("/faq/form")
	public String faqForm() {
		return "faq/faq";
	}
}
