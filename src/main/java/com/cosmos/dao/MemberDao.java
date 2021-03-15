package com.cosmos.dao;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.cosmos.dto.*;

import oracle.jdbc.*;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class MemberDao {
	//db연결한 정보를 가져와서 crud하는것
	
	//상수
	final Connection dbconn = OracleConnector.getInstance().getConnect();
	PreparedStatement stmt = null;
	
	//회원 등록
	public int insertMember(Member m){
		
		
		int rs = 0;
//		
//		String sql = "INSERT INTO member(id, pw, name, gender, email, post, "
//					+ "addr1, addr2, addr3, intro)";
//			   sql += "VALUES(?,?,?,?,?,?,?,?,?,?)";
//			   
		//방법1
		CallableStatement stmt = null;
		String sql = "{call p_insertMember(?,?,?,?,?,?,?,?,?,?)}";
		
		try {
			//stmt = dbconn.prepareStatement(sql);
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, m.getId());
			stmt.setString(2, m.getPw());
			stmt.setString(3, m.getName());
			stmt.setString(4, m.getGender());
			stmt.setString(5, m.getEmail());
			stmt.setString(6, m.getPost());
			stmt.setString(7, m.getAddr1());
			stmt.setString(8, m.getAddr2());
			stmt.setString(9, m.getAddr3());
			stmt.setString(10, m.getIntro());
			
			rs = stmt.executeUpdate();
			
			if(rs > 0 && m.getHobby() != null) {
				//취미저장
				
//				sql = "insert into hobby(hobby_no, member_id, code_val)";
//				sql += "values(hobby_no.nextval, ?, ?)";
				
				//방법2
				sql = "begin p_addHobby(?,?); END;";
				
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, m.getId());
				
				for(int i = 0 ; i<m.getHobby().length ; i++) {
					stmt.setString(2, m.getHobby()[i]);
					rs = stmt.executeUpdate();
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//회원수정
	public int updateMember(Member m){
		CallableStatement stmt = null;
		
		
		int rs = 0;
		
		String sql = "{call p_update_member(?,?,?,?,?,?,?,?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, m.getName());
			stmt.setString(2, m.getGender());
			stmt.setString(3, m.getEmail());
			stmt.setString(4, m.getPost());
			stmt.setString(5, m.getAddr1());
			stmt.setString(6, m.getAddr2());
			stmt.setString(7, m.getAddr3());
			stmt.setString(8, m.getIntro());
			stmt.setString(9, m.getId());
			
			rs = stmt.executeUpdate();
			
			
			if(rs > 0 && m.getHobby() != null) {
				//취미저장
//				String sql1 = "DELETE FROM hobby WHERE member_id = ?";
				String sql1 = "{call p_delete_hobby(?)}";
//				PreparedStatement stmt1 = dbconn.prepareStatement(sql1);
				CallableStatement stmt1 = dbconn.prepareCall(sql1);
				stmt1.setString(1, m.getId());
				stmt1.executeUpdate();
								
//				sql = "insert into hobby(hobby_no, member_id, code_val)";
				sql = "{call p_addHobby(?,?)}";
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, m.getId());
				
				for(int i = 0 ; i<m.getHobby().length ; i++) {
					if(m.getHobby()[i] != null ) {
					stmt.setString(2, m.getHobby()[i]);
					rs = stmt.executeUpdate();
					}
					
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//회원삭제
	public int deleteMember(String id){
		
		
		int rs = 0;
		
		String sql = "DELETE FROM hobby WHERE member_id = ?";
		
		try {
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
			
			sql = "DELETE FROM MEMBER WHERE id = ?";
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	//회원조회
	public Member[] getMemberList() {
	     
	      ResultSet rs= null;
	      
	      Member[] mArr = new Member[100];
	      try {      
	    	  String sql = "SELECT * FROM member m, hobby h ";
	    	  		 sql += "where m.id = h.member_id(+) order by m.id, h.code_val";
	         //커서를 옮겨야할 때는 이걸 꼭 해야함  ↓↓↓↓↓↓↓↓
	    	 stmt = dbconn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		 							 ResultSet.CONCUR_UPDATABLE);   
	         rs = stmt.executeQuery();
	         
	         //레코드 갯수파악
	         //커서를 마지막으로 옮겨서 
	         //rs.last();
	         //마지막 행번호를 변수에 저장
	         //int rows = rs.getRow();
	         
	         //커서를 제일 앞으로(rs.first() 보다 한칸 앞 빈공간)
	         rs.beforeFirst();
	         
	         
	         int i = 0;
	         while(rs.next()) {
	            Member m = new Member();
	            mArr[i++] = m;
	            String id = rs.getString("id");
	            m.setId(rs.getString("id"));
	            m.setName(rs.getString("name"));
	            m.setPw(rs.getString("pw"));
	            m.setGender(rs.getString("gender"));
	            m.setEmail(rs.getString("email"));
	            
	            if(rs.getString("hobby_no") != null) {
	            
		            String[] hobby = new String[10];
		            int j = 0;
		            
		            do {
		            	hobby[j++] = rs.getString("code_val");
		            	rs.next();
		            	
		            	//마지막 행인지 검사필요
		            	//System.out.println("현재행 :" + rs.getRow());
		            	if(rs.getRow() == 0) {
		            		break;
		            	}
		            }while(id.equals(rs.getString("id")));
		            	
		            m.setHobby(hobby);
		            
		            //do-while문에서 rs.next()를 했기때문에 다시 커서를 하나 전으로 돌림
		            rs.previous();
	            }
	            
	         }
	         
	            
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return mArr ;
	      
	   }
	
	//1인 회원정보 출력
	public Member memberList(String id) {
		String sql = "select * from member m, hobby h "
				+ "where m.id = h.member_id(+) and m.id = ?";
		Member m = new Member();
		try {
			
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setPw(rs.getString("pw"));
				m.setGender(rs.getString("gender"));
				m.setEmail(rs.getString("email"));
				m.setPost(rs.getString("post"));
				m.setAddr1(rs.getString("addr1"));
				m.setAddr2(rs.getString("addr2"));
				m.setAddr3(rs.getString("addr3"));
				m.setIntro(rs.getString("intro"));
				String[] hobby = new String[10];				
				int i = 0;
				
				if(rs.getString("code_val") != null) {
					do {
						hobby[i++] = rs.getString("code_val");
					
					}while(rs.next());
				m.setHobby(hobby);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
		
	}
	
	//로그인
	public Map<String, String> loginPr(Member member) {
		 CallableStatement stmt = null;
		  ResultSet rs= null;
		  
		  Map<String, String> map = new HashMap<String, String>();
	      
	      String sql = "{call p_loginMember(?,?)}";
	      try {      
	         stmt = dbconn.prepareCall(sql);
	         stmt.setString(1, member.getId());
	         stmt.registerOutParameter(2, OracleTypes.CURSOR);
	         stmt.executeQuery();
	         rs = (ResultSet)stmt.getObject(2);
	         
	         if(rs.next()) {
	            if(rs.getString("pw").equals(member.getPw())) {
	            	map.put("stat", "ok");
	            	map.put("name", rs.getString("name"));
	            	System.out.println(map.get("stat"));
	            } else {
	            	map.put("stat", "pwFail");
	            	System.out.println(map.get("stat"));
	            }
	            
	         }else {
	        	 map.put("stat", "idFail");
	        	 System.out.println(map.get("stat"));
	         }
	            
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return map;
	}
	
	
	//아이디 중복검사
	public int idCheck(String id) {
//		ResultSet rs= null;
		  int stat = 0;
	      
//	      String sql = "SELECT count(*) cnt FROM member where id = ?";
		  //프로시저 사용
		  String sql = "{call p_idCheck(?,?)}";
		  CallableStatement stmt = null;
	      try {      
//	    	 stmt = dbconn.prepareStatement(sql);
	         stmt = dbconn.prepareCall(sql);
	         stmt.setString(1, id);
	         stmt.registerOutParameter(2, OracleTypes.INTEGER);
	         stmt.executeQuery();
	         //rs = stmt.executeQuery();
	         
//	         rs.next();
//	         stat = rs.getInt("cnt");
	         stat = stmt.getInt(2);
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return stat;
		
	}


}
