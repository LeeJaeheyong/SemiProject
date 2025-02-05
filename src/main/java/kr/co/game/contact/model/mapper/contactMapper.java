package kr.co.game.contact.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.game.contact.model.dto.contactDTO;
import kr.co.game.contact.model.dto.faqDTO;

@Mapper
public interface contactMapper {

	    int contactEnroll(contactDTO contact);

		List<faqDTO> categoryNo();
	}


	

