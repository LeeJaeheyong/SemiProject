package kr.co.game.contact.model.service;

import java.util.List;

import kr.co.game.contact.model.dto.contactDTO;
import kr.co.game.contact.model.dto.faqDTO;

public interface contactService {
	
	public int enroll(contactDTO contact);

	List<faqDTO> categoryNo();
	
	
	
	
	}

	

