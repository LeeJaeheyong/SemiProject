package kr.co.game.mypage.util;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.game.mypage.dto.mypageFileDTO;

@Component
public class MypageFileUpload {
public void uploadFile(MultipartFile file, mypageFileDTO fileDTO, String folderName) throws IOException {
		
		
		// 원본 파일 이름
		String originalFileName = file.getOriginalFilename();
		
		// 새로운 파일 이름
		String changeName = UUID.randomUUID().toString() + "." + getFileExtension(originalFileName);
		// 파일이 서버에 저장될 위치경로
		Path path = Paths.get(fileDTO.getLOCAL_PATH() + "\\" + folderName + "\\" + changeName);
		
		// 파일 저장
		Files.write(path, file.getBytes());
		
		fileDTO.setOriginalName(originalFileName);
		fileDTO.setChangeName(changeName);
		fileDTO.setExtension(getFileExtension(originalFileName));
		fileDTO.setSize(file.getSize());
	}
	// 확장자 구하는 메서드
	private String getFileExtension(String fileName){
		int dotIndex = fileName.lastIndexOf('.');
		return dotIndex ==-1 ? "" : fileName.substring(dotIndex+1);
	}
	public void deleteFile(String local_PATH, String folderName, String fileName) throws IOException {
		Path path=Paths.get(local_PATH+"\\"+folderName+"\\"+fileName);
		Files.delete(path);
		
	}
}
