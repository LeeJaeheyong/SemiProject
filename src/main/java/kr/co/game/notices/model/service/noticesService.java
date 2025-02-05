package kr.co.game.notices.model.service;

import java.util.Map;

import kr.co.game.notices.model.dto.noticesSearchDTO;
import kr.co.game.notices.util.noticesPagination;

public interface noticesService {
	
	public Map<String, Object> getAllPosts (noticesPagination noticesPage, 
			 								int currentPage,
			 								int postCount,
			 								int pageLimit,
			 								int boardLimit, 
											noticesSearchDTO noticesSearchDTO);

	int getTotalCount(noticesSearchDTO noticesSearchDTO);
	
}
