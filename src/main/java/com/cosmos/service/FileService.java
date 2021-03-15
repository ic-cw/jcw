package com.cosmos.service;


import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.springframework.web.multipart.*;

import com.cosmos.dto.*;


public interface FileService {
	
	public void fileUpload(MultipartFile files, HttpSession sess, int bno);
	
	public void fileDel(String uploadPath, String saveFileName);
	
	public int fileDelete(int bno);
}
