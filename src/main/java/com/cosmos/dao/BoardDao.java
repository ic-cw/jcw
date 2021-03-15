package com.cosmos.dao;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.cosmos.dto.*;

import oracle.jdbc.internal.*;


@Repository
public class BoardDao {
	
	final Connection dbconn = OracleConnector.getInstance().getConnect();
	
	public ArrayList<Board> getBoardList(){
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		String sql = "{call p_getBoardList(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(1);
			
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("board_no"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCount(rs.getInt("cnt"));
				b.setOpen(rs.getString("isopen"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("member_id"));
				
				list.add(b);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}

	public int insertBoard(Board board, UploadFile file) {
		CallableStatement stmt = null;
		String sql = "{call p_insertBoard(?,?,?,?,?,?,?,?,?)}";
		int rs = 0;
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setString(3, board.getId());
			stmt.setString(4, file.getFileName());
			stmt.setString(5, file.getSaveFileName());
			stmt.setString(6, file.getFileSize());
			stmt.setString(7, file.getFileType());
			stmt.setString(8, file.getFilePath());
			stmt.registerOutParameter(9,OracleTypes.INTEGER);
			stmt.executeUpdate();	
			
			rs = stmt.getInt(9);
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public Board getBoard(int bno) {
	      CallableStatement stmt = null;
	      ResultSet rsBoard = null;
	      ResultSet rsFile = null;
	      
	      Board b = new Board();
	      
	      String sql = "{call p_getBoard(?,?,?,?)}";
	      
	      try {
	         stmt = dbconn.prepareCall(sql);
	         stmt.setInt(1, bno);
	         stmt.registerOutParameter(2,OracleTypes.CURSOR);
	         stmt.registerOutParameter(3,OracleTypes.CURSOR);
	         stmt.registerOutParameter(4,OracleTypes.INTEGER);
	         stmt.executeQuery();
	         
	         rsBoard = (ResultSet)stmt.getObject(2);
	         rsFile = (ResultSet)stmt.getObject(3);
	         
	         
	         if(rsBoard.next()) {
	            
	            b.setBno(rsBoard.getInt("board_no"));
	            b.setTitle(rsBoard.getString("title"));
	            b.setContent(rsBoard.getString("content"));
	            b.setCount(rsBoard.getInt("cnt"));
	            b.setOpen(rsBoard.getString("isopen"));
	            b.setWdate(rsBoard.getString("wdate"));
	            b.setId(rsBoard.getString("member_id"));
	            
	            if(rsFile.next()) {
	            	UploadFile f = new UploadFile();
	            	f.setFileNo(rsFile.getString("file_no"));
	            	f.setFileName(rsFile.getString("filename"));
	            	f.setFilePath(rsFile.getString("filepath"));
	            	f.setFileSize(rsFile.getString("filesize"));
	            	f.setSaveFileName(rsFile.getString("savefilename"));
	            	
	            	b.setUpfile(f);
	            }
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return b;
	   }
	
	   public void updateBoard(Board board) {
	      CallableStatement stmt = null;
	      
	      String sql = "{call p_updateBoard(?,?,?)}";
	      try {
	         stmt = dbconn.prepareCall(sql);         
	         stmt.setString(1, board.getTitle());
	         stmt.setString(2, board.getContent());
	         stmt.setInt(3, board.getBno());
			 stmt.executeUpdate();	
	               
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	   }
	   public int deleteBoard(int bno) {
		      CallableStatement stmt =null;
		       
		       String sql = "{call p_deleteBoard(?)}";
		       int rs = 0; 
		       
		         try {
		            stmt=dbconn.prepareCall(sql);
		            stmt.setInt(1, bno);
		            rs = stmt.executeUpdate();
		            
		         } catch (SQLException e) {
		             
		            e.printStackTrace();
		         }
		      return rs;
		   }
	   
	   public void insertFileUp(UploadFile uploadFile, int bno) {
			CallableStatement stmt = null;
			String sql = "{call p_insertFileUp(?,?,?,?,?,?)}";		
			
			System.out.println("파일업데이트 게시물 번호 : " + bno);
			
			try {
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, uploadFile.getFileName());
				stmt.setString(2, uploadFile.getSaveFileName());
				stmt.setString(3, uploadFile.getFileSize());
				stmt.setString(4, uploadFile.getFileType());
				stmt.setString(5, uploadFile.getFilePath());
				stmt.setInt(6, bno);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			
		}
}

