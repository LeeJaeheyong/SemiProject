package kr.co.game.faqInfo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class faqSearchDTO {
	private String category = "title";
	private String searchText = "";
}