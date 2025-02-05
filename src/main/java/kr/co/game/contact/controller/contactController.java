package kr.co.game.contact.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.co.game.contact.model.dto.contactDTO;
import kr.co.game.contact.model.dto.faqDTO;
import kr.co.game.contact.model.service.contactServiceImpl;

@Controller
@RequestMapping("/game")
public class contactController {

    private final contactServiceImpl contactService;

    public contactController(contactServiceImpl contactService) {
        this.contactService = contactService;
    }

    // 문의 작성 폼 페이지 (GET 요청)
    @GetMapping("/contact/enroll/form")
    public String contactEnrollForm(Model model) {
    	List<faqDTO> category = contactService.categoryNo();
    	model.addAttribute("category", category);
    	System.out.println(category.get(0).getCategoryNo());
        return "contact/contact"; 
    }


    @PostMapping("/contact/enroll")
    public String contactEnroll(@ModelAttribute contactDTO contactDTO, HttpSession session, RedirectAttributes redirectAttributes) {
    							
    	
        Integer userNum = (Integer) session.getAttribute("userNum");

        if (userNum == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/game/loginupForm";
        }

        contactDTO.setUserNum(userNum); // 🔥 userNum을 DTO에 설정
        System.out.println("문의 등록 시도: " + contactDTO.getContactTitle() + ", userNum: " + userNum);

        int result = contactService.enroll(contactDTO);

        if (result > 0) {
            System.out.println("문의 등록 성공!");
        } else {
            System.out.println("문의 등록 실패!");
        }

        return "redirect:/game/mypageQuery"; // 문의 작성 후 다시 폼으로 이동
    }
}
