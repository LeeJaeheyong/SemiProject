package kr.co.game.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.mypage.dto.mypageContactDTO;
import kr.co.game.mypage.dto.mypageDTO;
import kr.co.game.mypage.dto.mypageFileDTO;

@Mapper
public interface mypageMapper {

	public mypageDTO userInfoSelect(String myId);
	
	// 2개이상 넘길때 param
	public int update(@Param("myId") String myId, 
					  @Param("mypageDTO") mypageDTO mypageDTO);

	public int delete(String myId);

	public String getPassword(String myId);

	public int enrollFile(@Param("mypage") mypageFileDTO mypagefileDTO, 
			               @Param("userId") String userId, 
			               @Param("no") int no);

	public int getId(String userId);
	
	public void deleteFile(int id);

	public mypageFileDTO updatePro(int userNo);

	public mypageFileDTO fileCheck(int userNo);

	public List<mypageContactDTO> AllList(int userNo);

	public int getTotalCount(int userNo);
	
	
}
