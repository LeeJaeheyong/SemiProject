package kr.co.game.notices.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.notices.model.dto.noticesDTO;
import kr.co.game.notices.model.dto.noticesSearchDTO;

@Mapper
public interface noticesMapper {

	    List<noticesDTO> getAllPosts(@Param("offset") int offset,
	                                 @Param("boardLimit") int boardLimit,
	                                 @Param("noticesSearchDTO") noticesSearchDTO noticesSearchDTO);
	

	int getTotalCount(noticesSearchDTO noticesSearchDTO);

	
}
