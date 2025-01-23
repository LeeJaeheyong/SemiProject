package kr.co.game.gameinfo.model.dto;

import kr.co.game.dto.FileDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class gameInfoDTO {

	private int gameNo;
	private String gameName;
	private String firstGenre;
	private String secondGenre;
	private String ReleaseDate;
	private String publisher;
	private int gameScore;
	FileDTO fileDTO = new FileDTO();
}
