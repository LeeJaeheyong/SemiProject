package kr.co.game.faqInfo.model.service;

import java.util.Map;

import kr.co.game.faqInfo.util.faqInfoPagination;


public interface faqInfoService {
	
	Map<String, Object> getFaqList(faqInfoPagination faqInfoPage, int currentPage, int postCount,
									int pageLimit, int boardLimit);

	int faqInfoCount(faqInfoPagination faqInfoPage);
}
