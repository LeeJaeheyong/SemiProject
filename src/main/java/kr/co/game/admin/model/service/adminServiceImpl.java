package kr.co.game.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;
import kr.co.game.admin.model.mapper.adminMapper;
import kr.co.game.admin.util.adminPagination;

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
	public Map<String, Object> getAllGames(adminPagination adminPagination, 
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
}
