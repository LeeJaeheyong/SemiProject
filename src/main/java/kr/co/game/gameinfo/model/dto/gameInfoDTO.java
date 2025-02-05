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
	private String releaseDate;
	private String publisher;
	private int publisherNo;
	private int gameScore;
	FileDTO fileDTO = new FileDTO();
	gameDetailDTO gameDetailDTO = new gameDetailDTO();
}
