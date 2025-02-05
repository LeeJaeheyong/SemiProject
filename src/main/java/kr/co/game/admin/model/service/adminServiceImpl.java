package kr.co.game.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.faqDTO;
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
}
