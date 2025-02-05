package kr.co.game.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.faqDTO;
import kr.co.game.admin.model.dto.pageInfoDTO;
import kr.co.game.gameinfo.model.dto.gameInfoDTO;


@Mapper
public interface adminMapper {

	int getTotalCount();

	List<adminDTO> getAllPeople(pageInfoDTO pi);


	List<Integer> getUserNo();

	int changeRole(@Param("list") List<Map<String, Object>> dataSet);
//	int changeRole(@Param("list") Map<String,Object> dataSet);

	List<faqDTO> getAllFaq(pageInfoDTO inquiryPage);

	List<faqDTO> getCategory();

	int enrollFAQ(faqDTO faqDTO);

	int deleteFAQ(int faqNo);

	List<gameInfoDTO> getAllGames(pageInfoDTO gamedbPage);

}
