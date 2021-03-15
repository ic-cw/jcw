package com.cosmos.service;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.cosmos.dao.*;
import com.cosmos.dto.*;

@Service
public class FileServiceImple implements FileService {

	private static final Logger log = LoggerFactory.getLogger(FileServiceImple.class);
	
	@Autowired
	FileDao fileDao;
	
	@Override
	public void fileUpload(MultipartFile files, HttpSession sess, int bno) {
		UploadFile uploadFile = new UploadFile();
		
		//파일업로드
		if(files != null){				
			long fileSize = files.getSize();
				
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
			uploadFile.setBoardNo(Integer.toString(bno));
			
			fileDao.insertFile(uploadFile);
		}		
	}

	
	@Override
	public void fileDel(String uploadPath, String saveFileName) {		
		 
		File file = new File(uploadPath + File.separator + saveFileName);	
		System.out.println(uploadPath);
		if(file.exists()) {
			//파일삭제
		 	file.delete();
		}
	}


	@Override
	public int fileDelete(int bno) {
		
		return  fileDao.delFile(bno);
	}

}
