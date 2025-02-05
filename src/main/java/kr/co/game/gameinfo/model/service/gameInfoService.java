package kr.co.game.gameinfo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.game.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameDetailDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.util.gameinfoPagination;

public interface gameInfoService {

	int getTotalCount();

	Map<String, Object> getAllGames(gameinfoPagination gameinfoPagination, int currentPage, int postCount,
			int pageLimit, int boardLimit,String pub, String gen);

	List<gameInfoDTO> getGenres();

	List<gameInfoDTO> getsecondGenres();

	List<gameInfoDTO> getpublisher();

	gameInfoDTO getGame(int gameNo);

	int enroll(gameInfoDTO gameInfoDTO, gameDetailDTO gameDetailDTO, String newGerne);

	void uploadFile(FileDTO fileDTO, MultipartFile file, String name);

}
