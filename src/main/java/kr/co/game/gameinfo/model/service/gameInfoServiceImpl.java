package kr.co.game.gameinfo.model.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;
import kr.co.game.gameinfo.model.mapper.gameInfoMapper;
import kr.co.game.gameinfo.util.gameinfoPagination;
import kr.co.game.util.FileUpload;

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
										   int boardLimit, 
										   String pub, 
										   String gen) {
		int check =0;
		if(pub.isEmpty()!=true) {
			check=1;
		} else if(gen.isEmpty()!=true) {
			check=2;
		}
		
		pageInfoDTO pi = gameinfoPagination.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		List<gameInfoDTO> games = gameInfoMapper.getAllGames(pi,check,pub,gen);
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
	@Override
	public List<gameInfoDTO> getpublisher() {
		List<gameInfoDTO> result = gameInfoMapper.getpublisher();
		return result;
	}
	@Override
	public gameInfoDTO getGame(int gameNo) {
		gameInfoDTO result = gameInfoMapper.getGame(gameNo);
		return result;
	}
	@Override
	public int enroll(gameInfoDTO gameInfoDTO, String newGerne) {
		if(gameInfoDTO.getFirstGenre()=="해당없음") {
			gameInfoDTO.setFirstGenre(newGerne);
		int gerneUpdate = gameInfoMapper.newGenre(gameInfoDTO);
		}
		int publisher = gameInfoMapper.publsherUpdate(gameInfoDTO);
		int gameData = gameInfoMapper.enrollGame(gameInfoDTO);
		int detail = gameInfoMapper.detailUpdate(gameInfoDTO);
		
		return 0;
	}

}
