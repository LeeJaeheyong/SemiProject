package kr.co.game.faqInfo.util;

import org.springframework.stereotype.Component;

import kr.co.game.faqInfo.model.dto.faqInfoPageInfoDTO;


@Component
public class faqInfoPagination {
	
	public faqInfoPageInfoDTO getFaqList(int listCount, int currentPage,
			   							 int pageLimit, int boardLimit) {

		int maxPage = (int)(Math.ceil((double)listCount/boardLimit));


		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;


		int endPage = startPage+pageLimit-1;


		int row = listCount-(currentPage-1)*boardLimit;


		int offset = (currentPage-1)*boardLimit+1;
		int limit = offset+boardLimit-1;

		if(endPage>maxPage) {
			endPage = maxPage;
		}

		return new faqInfoPageInfoDTO(listCount, currentPage, pageLimit, boardLimit,
		   maxPage, startPage, endPage, row, offset, limit);
}
}
