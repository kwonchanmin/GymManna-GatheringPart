package com.mycompany.mygym.article.gathering.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mycompany.mygym.article.gathering.service.ArticleService;
import com.mycompany.mygym.article.gathering.vo.Gathering;
import com.mycompany.mygym.article.gathering.vo.Post;
import com.mycompany.mygym.comment.gathering.service.CommentServiceImpl;
import com.mycompany.mygym.comment.gathering.vo.GComment;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService service;

	@Autowired
	private CommentServiceImpl Cservice;

	Logger log = LogManager.getLogger("case3");

	// 만나 센터 별 게시글 수 확인
	@GetMapping(value = "search", produces = "application/json; charset=UTF-8")
	public HashMap<Object, Object> searchCountHandler(@RequestParam("centerName") String centerName, Model model) {
		log.debug(centerName);
		Gathering gathering = new Gathering();
		gathering.setCenterName(centerName);

		List<Gathering> list = service.readText(gathering);

		List<Gathering> result = service.selectGnum(gathering);
		model.addAttribute("Gnum", result);
		model.addAttribute("countManna", list);

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("센터", list);
		map.put("게시글 번호", result);

		log.debug(list);

		return map;

	}

	// 만나 센터의 게시글 리스트
	@GetMapping(value = "searchlist", produces = "application/json; charset=UTF-8")
	public HashMap<Object, Object> searchListHandler(@RequestParam("centerName") String centerName,
			@RequestParam("articleGnum") Long articleGnum, Model model) {

		Gathering gathering = new Gathering();
		gathering.setCenterName(centerName);
		gathering.setArticleGnum(articleGnum);
		log.debug(articleGnum);

		List<Gathering> list = service.selectGatheringList(gathering);

		model.addAttribute("GList", list);

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("만나", list);

		log.debug(list);

		return map;
	}

	// 만나 게시글의 상세보기
	@GetMapping(value = "detail/{articleGnum}", produces = "application/json; charset=UTF-8")
	public HashMap<Object, Object> searchDetail(@PathVariable("articleGnum") Long articleGnum, Model model) {

		Gathering gathering = new Gathering();
		gathering.setArticleGnum(articleGnum);

		GComment comment = new GComment();
		comment.setArticleGnum(articleGnum);

		log.debug(articleGnum);

		// 게시글 상세 정보 불러오기
		Gathering info = service.selectInfo(gathering);

		// 게시글 댓글 리스트 불러오기
		List<GComment> list = Cservice.selectList(comment);

		log.debug(info);
		model.addAttribute("DInfo", info);
		model.addAttribute("CList", list);

		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map.put("gathering", info);
		map.put("comment", list);

		return map;

	}

	// 만나 게시글 작성
	@PostMapping(value = "create", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Gathering>> createArticle(@RequestParam String title, @RequestParam String content,
			@RequestParam String category, @RequestParam String userId, @RequestParam String centerName, Model model) {

		Gathering gathering = new Gathering();
		gathering.setArticleGtitle(title);
		gathering.setArticleGcontent(content);
		gathering.setCategoryType(category);
		gathering.setUserId(userId);
		gathering.setCenterName(centerName);

		List<Gathering> result = service.createArticle(gathering);

		log.debug(result);
		model.addAttribute("GList", result);

		return ResponseEntity.ok(result);
	}

	// 만나 게시글 상세페이지 수정
	@PutMapping(value = "update", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Gathering> updateArticle(@RequestParam Long articleGnum, @RequestParam String title,
			@RequestParam String content, @RequestParam String centerName, Model model) {

		Gathering gathering = new Gathering();
		gathering.setArticleGnum(articleGnum);
		gathering.setArticleGtitle(title);
		gathering.setArticleGcontent(content);
//		gathering.setCountPuser(countPuser);
		gathering.setCenterName(centerName);

		Gathering result = service.updateArticle(gathering);

		log.debug(result);

		model.addAttribute("DInfo", result);

		return ResponseEntity.ok(result);
	}

	// 만나 게시글 삭제
	@DeleteMapping(value = "delete", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Gathering>> deleteArticle(@RequestParam Long articleGnum, Model model) {

		Gathering gathering = new Gathering();
		gathering.setArticleGnum(articleGnum);

		List<Gathering> list = service.deleteArticle(gathering);

		return ResponseEntity.ok(list);

	}

	// 마이페이지 나의 만나 게시글
	@GetMapping(value = "searchMyManna/{userId}")
	public ResponseEntity<List<Gathering>> searchMyManna(@PathVariable("userId") String userId, Model model) {

		Gathering gathering = new Gathering();
		gathering.setUserId(userId);

		List<Gathering> list = service.selectMyManna(gathering);

		return ResponseEntity.ok(list);
	}

	// 만나 참여 신청
	@PostMapping(value = "joinUser")
	public ResponseEntity<Boolean> postUser(@RequestParam Long articleGnum, @RequestParam String userId, Model model) {

		Post post = new Post();
		post.setArticleGnum(articleGnum);
		post.setUserId(userId);

		Post result = service.selectUser(post);

		boolean count = false;
		if (result == null) {
			count = service.insertUser(post);
		}

		return ResponseEntity.ok(count);
	}

	// 만나 참여 신청 취소
	@DeleteMapping(value = "cencelUser")
	public ResponseEntity<Boolean> postCencleUser(@RequestParam Long articleGnum, @RequestParam String userId, Model model) {

		Post post = new Post();
		post.setArticleGnum(articleGnum);
		post.setUserId(userId);

		Post result = service.selectUser(post);

		boolean count = false;
		if (result != null) {
			count = service.deleteUser(post);
		}

		return ResponseEntity.ok(count);
	}

////	 만나 게시글 큐알코드
//    @GetMapping("qr")
//    public Object createQr(@RequestParam String url) throws WriterException, IOException {
//    	
//    	String path = System.getProperty("user.dir") + File.separator;
//    	
//    	File file = new File(path);
//    	
//    	// 
//    	// 큐알코드 이미지 생성
//    	int width = 200;
//    	int height = 200;
//    
//    	BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
//
//
//    	try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//       
//    		MatrixToImageWriter.writeToStream(matrix, "PNG", out);
//        // PNG 이미지가 응답 본문에 반환
//    	return ResponseEntity.ok()
//    			// 콘텐츠 유형
//                .contentType(MediaType.IMAGE_PNG)
//                .body(out.toByteArray());
//    	}
//	
//    }

//	// 만나 상세페이지 큐알코드(웹 상에서 큐알코드로 보는 방법)
//    @RequestMapping(value="qrCode" , produces = "application/json; charset=UTF-8")
//    public ResponseEntity<byte[]> makeqr(HttpServletRequest request, HttpSession session,@RequestParam Long articleGnum) throws WriterException, IOException{
//    	
//    	// 현재 서비스가 돌아가고 있는 서블릿 경로의 resources 폴더 찾기
//    	String root = request.getSession().getServletContext().getRealPath("resources");
//    	
//    	// 파일 경로
//    	String savePath = root +"\\qrCode\\";
//    	
//    	File file = new File(savePath);
//    	if(!file.exists()) {
//    		file.mkdirs();
//    	}
//    	
//    	// 링크할 URL 주소
//    	String url = "localhost:8080/mygym/article/detail?articleGnum="+articleGnum;
//    	
//    	// 링크 생성값
//    	String codeurl = new String(url.getBytes("UTF-8"), "ISO-8859-1");
//    			
//    	// QRCode 색상값
//        int qrcodeColor =   0xFF2e4e96; 
//        // QRCode 배경색상값  
//        int backgroundColor = 0xFFFFFFFF;
//        
//        // QR 코드 생성
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE, 200, 200); // 200, 200 => 넚이, 높이
//        
//        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
//        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//        
//        // 파일 이름에 저장한 날짜를 포함해주기 위해 date 생성
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String fileName=sdf.format(new Date())+ articleGnum;
//        
//        // 파일 경로, 파일 이름, 파일 확장자에 맡는 파일 생성
//        File temp = new File(savePath+fileName+"png");
//        
//        // ImageIO를 사용하여 파일쓰기
//        ImageIO.write(bufferedImage,"png",temp);
//        
//        byte[] imageBytes = Files.readAllBytes(temp.toPath());
//        
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        
//        return new ResponseEntity<>(imageBytes, headers,HttpStatus.OK);
//    }

	// 만나 상세페이지 큐알코드(파일이름으로 넘겨주기)

	@RequestMapping(value = "qrCode", produces = "application/json; charset=UTF-8")
	public String makeqr(HttpServletRequest request, HttpSession session, @RequestParam Long articleGnum)
			throws WriterException, IOException {

		// 현재 서비스가 돌아가고 있는 서블릿 경로의 resources 폴더 찾기
		String root = request.getSession().getServletContext().getRealPath("resources");

		// 파일 경로
		String savePath = root + "\\qrCode\\";

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 링크할 URL 주소
		String url = "localhost:8080/mygym/article/detail?articleGnum=" + articleGnum;

		// 링크 생성값
		String codeurl = new String(url.getBytes("UTF-8"), "ISO-8859-1");

		// QRCode 색상값
		int qrcodeColor = 0xFF2e4e96;
		// QRCode 배경색상값
		int backgroundColor = 0xFFFFFFFF;

		// QR 코드 생성
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE, 200, 200); // 200, 200 => 넚이, 높이

		MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor, backgroundColor);
		BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

		// 파일 이름에 저장한 날짜를 포함해주기 위해 date 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fileName = sdf.format(new Date()) + articleGnum;

		// 파일 경로, 파일 이름, 파일 확장자에 맡는 파일 생성
		File temp = new File(savePath + fileName + "png");

		// ImageIO를 사용하여 파일쓰기
		ImageIO.write(bufferedImage, "png", temp);

		byte[] imageBytes = Files.readAllBytes(temp.toPath());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return fileName + "png";
	}
}
