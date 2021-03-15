package com.cosmos.service;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.cosmos.controller.user.*;
import com.cosmos.dao.*;
import com.cosmos.dto.*;

@Service
public class BoardServiceImple implements BoardService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImple.class);
	
	
	@Autowired
	BoardDao dao;
	
	
	@Override
	public ArrayList<Board> getBoardList() {
		return dao.getBoardList();
	}


	@Override
	public void updateBoard(Board board) {
		
		dao.updateBoard(board);		
	}

	@Override
	public int deleteBoard(int bno) {
		return dao.deleteBoard(bno);
	}

	@Override
	public Board getBoard(int bno) {
		 return dao.getBoard(bno);
	}

	@Override
	public Board[] searchBoard(String criteria, String keyword) {
		return null;
	}

	@Override
	public int insertBoard(Board board, MultipartFile files, HttpSession sess) {
		
		UploadFile uploadFile = new UploadFile();
									
		//파일업로드
		long fileSize = files.getSize();
		
		if(fileSize > 0){				
				
			String uploadPath = sess.getServletContext().getRealPath("resources/upload/");							
			log.info("파일 업로드 경로:" + uploadPath);
			
			String fileName = files.getOriginalFilename();
			
			UUID uid = UUID.randomUUID();
			
			String saveFileName = uid.toString() + "_" + fileName; 
			
			File saveFile = new File(uploadPath, saveFileName);
			
			try {
				files.transferTo(saveFile);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			uploadFile.setFileName(fileName);
			uploadFile.setSaveFileName(saveFileName);
			uploadFile.setFileSize(Long.toString(fileSize));
			uploadFile.setFileType(files.getContentType());
			uploadFile.setFilePath(uploadPath);
		}													
		return dao.insertBoard(board, uploadFile);
	}

		
}
