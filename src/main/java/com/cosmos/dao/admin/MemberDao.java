package com.cosmos.dao.admin;

import java.sql.*;
import java.util.*;

import com.cosmos.dao.*;
import com.cosmos.dto.*;

import oracle.jdbc.*;
import oracle.jdbc.internal.OracleTypes;

public class MemberDao {
	
	//상수
	final Connection dbconn = OracleConnector.getInstance().getConnect();
	PreparedStatement stmt = null;
	

	//로그인
	public Map<String, String> loginPr(String id, String pw) {
		 CallableStatement stmt = null;
		  ResultSet rs= null;
		  
		  Map<String, String> map = new HashMap<String, String>();
	      
	      String sql = "{call p_loginMember2(?,?)}";
	      try {      
	         stmt = dbconn.prepareCall(sql);
	         stmt.setString(1, id);
	         stmt.registerOutParameter(2, OracleTypes.CURSOR);
	         stmt.executeQuery();
	         rs = (ResultSet)stmt.getObject(2);
	         
	         
	         if(rs.next()) {
	            if(rs.getString("pw").equals(pw)) {
	            	map.put("stat", "ok");
	            	map.put("name", rs.getString("name"));
	            } else {
	            	map.put("stat", "pwFail");
	            }
	            
	         }else {
	        	 map.put("stat", "idFail");
	         }
	            
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return map;
	}
	
	//회원수 가져오기
		public Map<String, String> setCount() {
			
			PreparedStatement stmt = null;
			PreparedStatement stmt2 = null;
			ResultSet rs= null;
			ResultSet rs2= null;
			  
			Map<String, String> map = new HashMap<String, String>();
		      
		    String sql = "select count(*) from member where isDel='Y'";
		    String sql2 = "select count(*) from member where isDel='N'";
		      try {      
		         stmt = dbconn.prepareCall(sql);
		         stmt2 = dbconn.prepareCall(sql2);
		         rs = stmt.executeQuery();
		         rs2 = stmt2.executeQuery();
		         rs.next();
		         rs2.next();
		         map.put("reg", rs.getString(1));
		         map.put("del", rs2.getString(1));
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		      return map;
		}
	

	
	//회원리스트 출력
	public ArrayList<Member> listMemberdao() {
		  ResultSet rs = null;

	      PreparedStatement stmt = null;
	      ArrayList<Member> mArr = new ArrayList<>();
	      try {
	         String sql = "select id, name,"
	         		+ " DECODE(gender,'M','남','F','여') as isgender,"
	         		+ " DECODE(grade,'0','일반회원','1','운영자','2','관리자') grade,"
	         		+ " to_char(wdate,'YYYY/MM/DD') wdate,"
	         		+ " nvl(TO_CHAR(deldate, 'YY-MM-DD'),' ') deldate"
	         		+ " from member"
	         		+ " order by wdate desc";
	         stmt = dbconn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Member m = new Member();
	            m.setId(rs.getString("id"));
	            m.setName(rs.getString("name"));
	            m.setGender(rs.getString("isgender"));
	            m.setGrade(rs.getString("grade"));
	            m.setWdate(rs.getString("wdate"));
	            m.setDeldate(rs.getString("deldate"));
	            
	            mArr.add(m);
	         } 
      	  }catch (SQLException e) {
	         e.printStackTrace();
      	  }
		  return mArr;
	}
	
	//회원찾기
		public ArrayList<Member> listMemberSearchdao(String user, String u_name) {
			ResultSet rs = null;
			CallableStatement stmt = null;
			System.out.println(user);
			System.out.println(u_name);
		    ArrayList<Member> mArr = new ArrayList<>();
		    try {
		    	String sql = "{call p_getMemSearchList(?,?,?)}";
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, user);
				stmt.setString(2, u_name);
				stmt.registerOutParameter(3, OracleTypes.CURSOR);
				stmt.executeQuery();
				
				rs = (ResultSet)stmt.getObject(3);
				
				while(rs.next()) {
					Member m = new Member();
					m.setId(rs.getString("id"));
					m.setName(rs.getString("name"));
					m.setGender(rs.getString("isgender"));
					m.setGrade(rs.getString("grade"));
					m.setWdate(rs.getString("wdate"));
					m.setDeldate(rs.getString("c_deldate"));
					
					mArr.add(m);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    return mArr;
		}

	

	
	//회원삭제 요청 리스트
	public ArrayList<Member> listDelMemberdao() {
		ResultSet rs = null;

	      PreparedStatement stmt = null;
	      ArrayList<Member> mArr = new ArrayList<>();
	      try {
	         String sql = "select id,"
	         		+ "       name,"
	         		+ "       DECODE(gender,'M','남','F','여') as isgender,"
	         		+ "       DECODE(grade,'0','일반회원','1','운영자','2','관리자') grade,"
	         		+ "       to_char(wdate,'YYYY/MM/DD') wdate,"
	         		+ "       nvl(TO_CHAR(deldate, 'YY-MM-DD'),' ') deldate,"
	         		+ "       isDel"
	         		+ "    from member"
	         		+ "    where isDel = 'Y'"
	         		+ "    order by deldate, wdate desc";
	         stmt = dbconn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Member m = new Member();
	            m.setId(rs.getString("id"));
	            m.setName(rs.getString("name"));
	            m.setGender(rs.getString("isgender"));
	            m.setGrade(rs.getString("grade"));
	            m.setWdate(rs.getString("wdate"));
	            m.setDeldate(rs.getString("deldate"));
	            
	            mArr.add(m);
	         } 
    	  }catch (SQLException e) {
	         e.printStackTrace();
    	  }
		  return mArr;
	}
	
	//회원삭제요청 검색
		public ArrayList<Member> listDelMemberSearchdao(String d_date) {
			ResultSet rs = null;
			CallableStatement stmt = null;
		    ArrayList<Member> mArr = new ArrayList<>();
		    try {
		    	String sql = "{call p_getDelMemList(?,?)}";
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, d_date);
				stmt.registerOutParameter(2, OracleTypes.CURSOR);
				stmt.executeQuery();
				
				rs = (ResultSet)stmt.getObject(2);
				
				while(rs.next()) {
					Member m = new Member();
					m.setId(rs.getString("id"));
					m.setName(rs.getString("name"));
					m.setGender(rs.getString("isgender"));
					m.setWdate(rs.getString("wdate"));
					m.setIsDel(rs.getString("isDel"));
					m.setDeldate(rs.getString("c_deldate"));
					m.setGrade(rs.getString("grade"));
					
					mArr.add(m);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    return mArr;
		}

}
