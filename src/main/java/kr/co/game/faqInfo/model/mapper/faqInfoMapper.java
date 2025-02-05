package kr.co.game.faqInfo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.faqInfo.model.dto.faqInfoDTO;
import kr.co.game.faqInfo.model.dto.faqInfoPageInfoDTO;
import kr.co.game.faqInfo.util.faqInfoPagination;

@Mapper
public interface faqInfoMapper {
	
	List<faqInfoDTO> getFaqList(@Param("fpi") faqInfoPageInfoDTO fpi,
			   					@Param("faqInfoPage") faqInfoPagination faqInfoPage);
	
	int faqInfoCount(faqInfoPagination faqInfoPage);

	
}
