package com.mycompany.mygym.article.photo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@Service
//public class StorageService {
//
//	private String uploadDir = "/Users/seungjeon/uploads"; // 디렉터리를 직접 지정
//
//	public String store(MultipartFile file) throws IOException {
//
//		// 파일명 중복 방지를 위해 UUID를 사용
//		String fileName = UUID.randomUUID().toString() + "."
//				+ StringUtils.getFilenameExtension(file.getOriginalFilename());
//		
//		Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);
//		
//		File saveFile = new File(uploadDir + "/" + fileName);
//		file.transferTo(saveFile);
//		
//		return targetLocation.toUri().toString(); // 파일 URL을 리턴
//	}
//}

@Service
public class StorageService {

	@Value("${upload.path}")
	private String uploadDir; // 외부 프로퍼티 파일에서 값을 읽어와서 할당

	public String store(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "."
				+ StringUtils.getFilenameExtension(file.getOriginalFilename());
		Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);

		Files.createDirectories(targetLocation.getParent());
		Files.write(targetLocation, file.getBytes());

		// 서버에서 파일에 접근할 수 있는 URL로 변환하여 반환
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName).toUriString();
	}
}
