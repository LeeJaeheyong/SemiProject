package kr.co.game.gameinfo.model.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.gameinfo.model.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;
import kr.co.game.gameinfo.model.mapper.gameInfoMapper;
import kr.co.game.gameinfo.util.FileUpload;
import kr.co.game.gameinfo.util.gameinfoPagination;

@Service
public class gameInfoServiceImpl implements gameInfoService{
	private final gameInfoMapper gameInfoMapper;
	private final FileUpload fu;
	
	public gameInfoServiceImpl(gameInfoMapper gameinfoMapper,FileUpload FileUpload) {
		this.gameInfoMapper = gameinfoMapper;
		this.fu= FileUpload;
	}
	
	@Override
	public int getTotalCount() {
		return gameInfoMapper.getTotalCount();
	}
	@Override
	public Map<String, Object> getAllGames(gameinfoPagination gameinfoPagination, 
										   int currentPage, 
										   int postCount,
										   int pageLimit, 
										   int boardLimit) {
		
		pageInfoDTO pi = gameinfoPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<gameInfoDTO> games = gameInfoMapper.getAllGames(pi);
		Map<String, Object> result = new HashMap<>();
		result.put("pi", pi);
		result.put("games", games);
		return result;
	}
	@Override
	public void uploadFile(FileDTO fileDTO, MultipartFile file) {
		try {
			fu.uploadFile(file,fileDTO,"gameinfo");
			int result = gameInfoMapper.uploadFile(fileDTO);
			if(result==1) {
				System.out.println("굿잡");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<gameInfoDTO> getGenres() {
		List<gameInfoDTO> result = gameInfoMapper.getGenres();
		return result;
	}
	@Override
	public List<gameInfoDTO> getsecondGenres() {
		List<gameInfoDTO> result = gameInfoMapper.getsecondGenres();
		return result;
	}

}
