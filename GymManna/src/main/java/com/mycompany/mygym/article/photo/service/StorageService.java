package com.mycompany.mygym.article.photo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	private String uploadDir = "/Users/seungjeon/uploads"; // 디렉터리를 직접 지정

	public String store(MultipartFile file) throws IOException {

		// 파일명 중복 방지를 위해 UUID를 사용
		String fileName = UUID.randomUUID().toString() + "."
				+ StringUtils.getFilenameExtension(file.getOriginalFilename());
		
		Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);
		
		File saveFile = new File(uploadDir + "/" + fileName);
		file.transferTo(saveFile);
		
		return targetLocation.toUri().toString(); // 파일 URL을 리턴
	}
}
