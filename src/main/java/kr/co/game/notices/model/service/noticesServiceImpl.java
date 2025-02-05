package kr.co.game.notices.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.game.notices.model.dto.noticesDTO;
import kr.co.game.notices.model.dto.noticesPageInfoDTO;
import kr.co.game.notices.model.dto.noticesSearchDTO;
import kr.co.game.notices.model.mapper.noticesMapper;
import kr.co.game.notices.util.noticesPagination;

@Service
public class noticesServiceImpl implements noticesService{
	
	private final noticesMapper noticesMapper;
	
	public noticesServiceImpl(noticesMapper noticesMapper) {
		this.noticesMapper = noticesMapper;
	}
	
	@Override
	public int getTotalCount(noticesSearchDTO noticesSearchDTO) {
		return noticesMapper.getTotalCount(noticesSearchDTO);
	}
	
	@Override
	public Map<String, Object> getAllPosts(noticesPagination noticesPage, 
										   int currentPage,
										   int postCount,
										   int pageLimit,
										   int boardLimit, 
										   noticesSearchDTO noticesSearchDTO) {
		// 페이징 처리
		noticesPageInfoDTO npi = noticesPage.getnoticesList(postCount, currentPage, pageLimit, boardLimit, noticesSearchDTO);
		
		
		// 페이지에 따라서 필요한 게시글들만 SELECT
		List<noticesDTO> nposts = noticesMapper.getAllPosts(npi.getOffset(), boardLimit, noticesSearchDTO);
		
		Map<String, Object> result = new HashMap<>();
		result.put("npi", npi);
		result.put("nposts", nposts);
		
		return result;
	}
	
	
	
	
}
