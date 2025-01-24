package kr.co.game.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;


@Mapper
public interface adminMapper {

	int getTotalCount();

	List<adminDTO> getAllPeople(pageInfoDTO pi);


	List<Integer> getUserNo();

	int changeRole(Map<String, Object> siba);

}
