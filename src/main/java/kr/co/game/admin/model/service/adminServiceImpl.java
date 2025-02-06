package kr.co.game.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.contactDTO;
import kr.co.game.admin.model.dto.faqDTO;
import kr.co.game.admin.model.dto.noticeDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;
import kr.co.game.admin.model.mapper.adminMapper;
import kr.co.game.admin.util.adminPagination;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;

@Service
public class adminServiceImpl implements adminService{
	private final adminMapper adminMapper;
	
	public adminServiceImpl(adminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	@Override
	public int getTotalCount() {
		return adminMapper.getTotalCount();
	}
	@Override
	public Map<String, Object> getAllPeople(adminPagination adminPagination, 
										   int currentPage, 
										   int postCount,
										   int pageLimit, 
										   int boardLimit
										   ){
		pageInfoDTO pi = adminPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<adminDTO> user = adminMapper.getAllPeople(pi);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", pi);
		result.put("user", user);
		return result;
	}
	@Override
	public int changeRole(List<String> category, List<Integer> check) {
		// 1. 변경된 유저만
		// 2. category를 배열형태로 바까서 
		if(check.isEmpty()) {
			return 0;
		}
		List<Map<String,Object>> huh = new ArrayList<>();
		
		for(int i=0; i<category.size(); i++) {
			Map<String,Object> siba= new HashMap<>();
			siba.put("category", category.get(i));
			siba.put("check", check.get(i));
			huh.add(siba);
		}
		int result =adminMapper.changeRole(huh);
		
		return result;
	}
	@Override
	public List<Integer> getUserNo() {
		List<Integer> result = adminMapper.getUserNo();
		return result;
	}
	@Override
	public Map<String, Object> getAllfaq(adminPagination adminPagination, 
											 int currentPage, 
											 int postCount,
											 int pageLimit, 
											 int boardLimit) {
		pageInfoDTO faqPage = adminPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<faqDTO> faq = adminMapper.getAllFaq(faqPage);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", faqPage);
		result.put("faq",faq);
		
		return result;
	}
	@Override
	public List<faqDTO> getCategory() {
		List<faqDTO> result = adminMapper.getCategory();
		return result;
	}
	@Override
	public int enrollFAQ(faqDTO faqDTO) {
		int result = adminMapper.enrollFAQ(faqDTO);
		return result;
	}
	@Override
	public int deleteFAQ(int faqNo) {
		int result = adminMapper.deleteFAQ(faqNo);
		return result;
	}
	@Override
	public Map<String, Object> getAllGames(adminPagination adminPagination, int currentPage, int postCount,
			int pageLimit, int boardLimit) {
		pageInfoDTO gamedbPage = adminPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<gameInfoDTO> games = adminMapper.getAllGames(gamedbPage);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", gamedbPage);
		result.put("game",games);
		return result;
	}
	@Override
	public int deleteGameInfo(int gameNo) {
		
		int secondGenre = adminMapper.deleteSecondGenre(gameNo);
		int image = adminMapper.deleteImage(gameNo);
		int detail = adminMapper.deleteDetail(gameNo);
		int result = adminMapper.deleteGameInfo(gameNo);
		return 0;
	}
	@Override
	public Map<String, Object> getAllNotices(adminPagination adminPagination, int currentPage, int postCount,
			int pageLimit, int boardLimit) {
		pageInfoDTO noticePage = adminPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<noticeDTO> notices = adminMapper.getAllNotices(noticePage);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", noticePage);
		result.put("notice",notices);
		return result;
	}
	@Override
	public int getTotalNotice() {
		int result = adminMapper.getTotalNotice();
		return 0;
	}
	@Override
	public Map<String, Object> getAllinquiries(adminPagination adminPagination, int currentPage, int postCount,
			int pageLimit, int boardLimit) {
		pageInfoDTO inquiryPage = adminPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<contactDTO> inquiries = adminMapper.getAllinquiries(inquiryPage);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", inquiryPage);
		result.put("inquiry",inquiries);
		return result;
	}
	@Override
	public contactDTO getInquiry(int contactNo) {
		
		return adminMapper.getInquiry(contactNo);
	}
	@Override
	public int answer(String answerText, int contactNo, String userId) {
		int userNum = adminMapper.getUserNum(userId);
		int answerRE = adminMapper.answerRE(contactNo);
		int answer = adminMapper.answer(answerText,userNum,contactNo);
		int react = adminMapper.react(contactNo);
		return answer;
	}
}
