package kr.co.game.contact.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.game.contact.model.dto.contactDTO;
import kr.co.game.contact.model.dto.faqDTO;
import kr.co.game.contact.model.mapper.contactMapper;

@Service
public class contactServiceImpl implements contactService {

    private final contactMapper contactMapper;

    public contactServiceImpl(contactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

        @Override
        public int enroll(contactDTO contact) {
            return contactMapper.contactEnroll(contact);
        }
        
        @Override
		public List<faqDTO> categoryNo() {
			return contactMapper.categoryNo();
		}
    }


    
//    public int enroll(contactDTO contact) {
//        System.out.println("문의 저장: " + contact.getContactTitle()); // 디버깅 로그
//        return contactMapper.contactEnroll(contact);
//    }

