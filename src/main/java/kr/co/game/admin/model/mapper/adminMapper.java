package kr.co.game.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.admin.model.dto.adminDTO;
import kr.co.game.admin.model.dto.contactDTO;
import kr.co.game.admin.model.dto.faqDTO;
import kr.co.game.admin.model.dto.noticeDTO;
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

	int deleteGameInfo(int gameNo);

	int deleteDetail(int gameNo);

	int deleteImage(int gameNo);

	int deleteSecondGenre(int gameNo);

	List<noticeDTO> getAllNotices(pageInfoDTO noticePage);

	int getTotalNotice();

	List<contactDTO> getAllinquiries(pageInfoDTO inquiryPage);

	contactDTO getInquiry(int contactNo);

	int answerRE(int contactNo);

	int answer(@Param("answerText")String answerText,
			   @Param("userNum")int userNum,
			   @Param("contactNo")int contactNo);

	int getUserNum(String userId);

	int react(int contactNo);

}
