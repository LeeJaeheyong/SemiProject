package kr.co.game.faqInfo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.game.faqInfo.model.dto.faqInfoDTO;
import kr.co.game.faqInfo.model.dto.faqInfoPageInfoDTO;
import kr.co.game.faqInfo.model.mapper.faqInfoMapper;
import kr.co.game.faqInfo.util.faqInfoPagination;

@Service
public class faqInfoServiceImpl implements faqInfoService{
	
	private final faqInfoMapper faqInfoMapper;
	
	public faqInfoServiceImpl(faqInfoMapper faqInfoMapper) {
		this.faqInfoMapper = faqInfoMapper;
	}
	
	
	@Override
	public Map<String, Object> getFaqList(faqInfoPagination faqInfoPage, 
			   								int currentPage, 
			   								int postCount,
			   								int pageLimit, 
			   								int boardLimit) {

		faqInfoPageInfoDTO fpi = faqInfoPage.getFaqList(postCount, currentPage, pageLimit, boardLimit);
		
		List<faqInfoDTO> faq = faqInfoMapper.getFaqList(fpi, faqInfoPage);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("fpi", fpi);
		result.put("posts", faq);
		
		return result;
}

	@Override
	public int faqInfoCount(faqInfoPagination faqInfoPage) {
		return faqInfoMapper.faqInfoCount(faqInfoPage);
	}
	
	
	
	
	
}
