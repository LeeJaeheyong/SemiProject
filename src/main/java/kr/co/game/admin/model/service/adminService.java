package kr.co.game.admin.model.service;

import java.util.List;
import java.util.Map;

import kr.co.game.admin.model.dto.ChangeDTO;
import kr.co.game.admin.model.dto.contactDTO;
import kr.co.game.admin.model.dto.faqDTO;
import kr.co.game.admin.util.adminPagination;

public interface adminService {

	int getTotalCount();

	Map<String, Object> getAllPeople(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);



	List<Integer> getUserNo();

	int changeRole(List<String> category, List<Integer> check);

	Map<String, Object> getAllfaq(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);

	List<faqDTO> getCategory();

	int enrollFAQ(faqDTO faqDTO);

	int deleteFAQ(int faqNo);

	Map<String, Object> getAllGames(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);

	int deleteGameInfo(int gameNo);

	Map<String, Object> getAllNotices(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);

	int getTotalNotice();

	Map<String, Object> getAllinquiries(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);

	contactDTO getInquiry(int contactNo);

}
