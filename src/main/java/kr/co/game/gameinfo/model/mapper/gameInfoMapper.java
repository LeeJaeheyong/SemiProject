package kr.co.game.gameinfo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.dto.FileDTO;
import kr.co.game.gameinfo.model.dto.gameDetailDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;
import kr.co.game.gameinfo.model.dto.pageInfoDTO;

@Mapper
public interface gameInfoMapper {

	int getTotalCount();

	List<gameInfoDTO> getAllGames(@Param("pi")pageInfoDTO pi, 
								  @Param("check") int check,
								  @Param("pub") String pub,
								  @Param("gen") String gen);

	int uploadFile(FileDTO fileDTO);

	List<gameInfoDTO> getGenres();
	List<gameInfoDTO> getsecondGenres();

	List<gameInfoDTO> getpublisher();

	gameInfoDTO getGame(int gameNo);

	int newGenre(gameInfoDTO gameInfoDTO);

	int detailUpdate(@Param("gameInfoDTO") gameInfoDTO gameInfoDTO,
					 @Param("gameDetailDTO") gameDetailDTO gameDetailDTO);

	int enrollGame(gameInfoDTO gameInfoDTO);

	int getGerneNo(gameInfoDTO gameInfoDTO);

	int getGameNo(gameInfoDTO gameInfoDTO);

	int getGameNoo(String name);

	int secondGenreUpdate(gameInfoDTO gameInfoDTO);


	

}
