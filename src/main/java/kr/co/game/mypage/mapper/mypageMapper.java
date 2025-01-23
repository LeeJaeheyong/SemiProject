package kr.co.game.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.mypage.dto.mypageDTO;

@Mapper
public interface mypageMapper {

	public mypageDTO userInfoSelect(String myId);
	
	// 2개이상 넘길때 param
	public int update(@Param("myId") String myId, 
					  @Param("mypageDTO") mypageDTO mypageDTO);

	public int delete(String myId);

	public String getPassword(String myId);

}
