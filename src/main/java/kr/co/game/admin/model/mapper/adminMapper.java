package kr.co.game.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;


@Mapper
public interface adminMapper {

	int getTotalCount();

	List<adminDTO> getAllPeople(pageInfoDTO pi);

}
