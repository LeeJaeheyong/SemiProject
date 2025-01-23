package kr.co.game.admin.model.service;

import java.util.Map;

import kr.co.game.admin.util.adminPagination;

public interface adminService {

	int getTotalCount();

	Map<String, Object> getAllGames(adminPagination adminPagination, int currentPage, int postCount, int pageLimit,
			int boardLimit);

}
