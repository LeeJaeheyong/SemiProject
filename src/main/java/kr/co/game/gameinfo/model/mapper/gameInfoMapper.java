package kr.co.game.gameinfo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.game.gameinfo.model.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;

@Mapper
public interface gameInfoMapper {

	int getTotalCount();

	List<gameInfoDTO> getAllGames(pageInfoDTO pi);

	int uploadFile(FileDTO fileDTO);

	List<gameInfoDTO> getGenres();
	List<gameInfoDTO> getsecondGenres();
	

}
