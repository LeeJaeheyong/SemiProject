package kr.co.game.cintactcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class contactController {
	
	@GetMapping("/contact/form")
	public String contactForm() {
		return "contact/contact";
	}
}
