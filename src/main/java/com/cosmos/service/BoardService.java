package com.cosmos.service;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.web.multipart.*;

import com.cosmos.dto.*;


public interface BoardService {
	
	//게시판 리스트
	ArrayList<Board> getBoardList();
	
	//게시물 등록
	int insertBoard(Board board, MultipartFile files, HttpSession sess);
	
	//게시물 수정
	void updateBoard(Board board);
	
	//게시물 삭제
	int deleteBoard(int bno);
	
	//게시물 내용확인
	Board getBoard(int bno);
	
	//게시물 조회
	Board[] searchBoard(String criteria, String keyword);

	
}
