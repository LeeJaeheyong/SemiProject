package kr.co.game.notices.util;

import org.springframework.stereotype.Component;

import kr.co.game.notices.model.dto.noticesPageInfoDTO;
import kr.co.game.notices.model.dto.noticesSearchDTO;

@Component
public class noticesPagination {
	
		public noticesPageInfoDTO getnoticesList(int listCount, int currentPage,
							  	  int pageLimit, int boardLimit, noticesSearchDTO noticesSerchDTO) {

			int maxPage = (int)(Math.ceil((double)listCount/boardLimit));


			int startPage = (currentPage-1) / pageLimit * pageLimit + 1;


			int endPage = startPage+pageLimit-1;


			int row = listCount-(currentPage-1)*boardLimit;


			int offset = (currentPage - 1) * boardLimit;
			
//			int limit = offset+boardLimit-1;

			if (endPage > maxPage) {
	            endPage = maxPage;
	        }

			return new noticesPageInfoDTO(listCount, currentPage, pageLimit, boardLimit,
										  maxPage, startPage, endPage, row, offset, boardLimit);
		}
	
}
