package kr.co.game.notices.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class noticesSearchDTO {
	
	private String category = "title";
	private String searchText = "";
	
}
