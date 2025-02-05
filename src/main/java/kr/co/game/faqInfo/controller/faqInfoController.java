package kr.co.game.faqInfo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.game.faqInfo.model.dto.faqInfoDTO;
import kr.co.game.faqInfo.model.dto.faqInfoPageInfoDTO;
import kr.co.game.faqInfo.model.dto.faqSearchDTO;
import kr.co.game.faqInfo.model.service.faqInfoServiceImpl;
import kr.co.game.faqInfo.util.faqInfoPagination;

@Controller
@RequestMapping("/game")
public class faqInfoController {

    private final faqInfoServiceImpl faqInfoService;
    private final faqInfoPagination faqInfoPage;

    public faqInfoController(faqInfoServiceImpl faqInfoService, faqInfoPagination faqInfoPage) {
        this.faqInfoService = faqInfoService;
        this.faqInfoPage = faqInfoPage;
    }

    @GetMapping("/faqInfo/form")
    public String faqInfoForm(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @ModelAttribute faqInfoPagination faqInfoPage,
            @ModelAttribute faqSearchDTO faqSearchDTO,
            Model model) {

        try {
            // 검색어 기본값 처리
            boolean isSearchEmpty = faqSearchDTO.getSearchText() == null || faqSearchDTO.getSearchText().trim().isEmpty();
            if (isSearchEmpty) {
                faqSearchDTO.setSearchText("");
            }

            // 총 게시글 수와 페이지 정보 계산
            int postCount = faqInfoService.faqInfoCount(faqInfoPage);
            int pageLimit = 10;
            int boardLimit = 10;

            // FAQ 목록 조회
            Map<String, Object> result = faqInfoService.getFaqList(
                    faqInfoPage,
                    currentPage,
                    postCount,
                    pageLimit,
                    boardLimit);

            // 데이터 추출 및 조건 처리
            faqInfoPageInfoDTO fpi = (faqInfoPageInfoDTO) result.get("fpi");
            List<faqInfoDTO> postsResult = (List<faqInfoDTO>) result.get("posts");

            if (!isSearchEmpty) {
                postsResult = postsResult.stream()
                    .filter(post -> post.getFaqInfo().contains(faqSearchDTO.getSearchText()))
                    .collect(Collectors.toList());
            }

            // 검색 결과 확인
            if (postsResult.isEmpty()) {
                System.out.println("검색 결과가 없습니다. 1:1문의를 이용해 주세요.");
                model.addAttribute("errorMessage", "검색 결과가 없습니다. 1:1문의를 이용해 주세요.");
            } else {
                System.out.println("첫 번째 FAQ 정보: " + postsResult.get(0).getFaqInfo());
            }

            // 모델에 데이터 추가
            model.addAttribute("posts", postsResult);
            model.addAttribute("fpi", fpi);
            model.addAttribute("faqSearchDTO", faqSearchDTO); // 검색어 유지

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "FAQ 검색 중 오류가 발생했습니다.");
            return "errorPage"; // 에러 페이지로 이동
        }

        // FAQ 목록 페이지로 이동
        return "faq/faq";
    }
}
