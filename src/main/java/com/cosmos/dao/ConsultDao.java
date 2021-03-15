package com.cosmos.dao;

import java.sql.*;

import com.cosmos.dto.*;

import oracle.jdbc.internal.*;

public class ConsultDao {
	
	final Connection dbconn = OracleConnector.getInstance().getConnect();
	PreparedStatement stmt = null;
	public int insertConsult(Consult c){
		int rs = 0;
		int seq = 0;
		//방법1
		CallableStatement stmt = null;
		String sql = "{call p_insertConsult(?,?,?,?,?,?,?,?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, c.getId());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getGender());
			stmt.setString(4, c.getP_num());
			stmt.setString(5, c.getPostn());
			stmt.setString(6, c.getAddr1());
			stmt.setString(7, c.getAddr2());
			stmt.setString(8, c.getAddr3());
			stmt.setString(9, c.getC_intro());
			
			rs = stmt.executeUpdate();
			seq = stmt.getInt("seqno");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seq;
	}
	
	public Consult getConsult(int bno) {
	      CallableStatement stmt = null;
	      ResultSet rs = null;
	      
	      Consult c = new Consult();
	      
	      String sql = "{call p_getConsult(?,?)}";
	      
	      try {
	         stmt = dbconn.prepareCall(sql);
	         stmt.setInt(1, bno);
	         stmt.registerOutParameter(2,OracleTypes.CURSOR);
	         stmt.executeQuery();
	         
	         rs = (ResultSet)stmt.getObject(2);
	         
	         
	         if(rs.next()) {
	            
	            c.setSeqno(rs.getString("seqno"));
	            c.setId(rs.getString("id"));
	            c.setP_num(rs.getString("p_num"));
	            c.setGender(rs.getString("gender"));
	            c.setPostn(rs.getString("postn"));
	            c.setAddr1(rs.getString("addr1"));	            
	            c.setAddr2(rs.getString("addr2"));	            
	            c.setAddr3(rs.getString("addr3"));	            
	            c.setC_intro(rs.getString("c_intro"));
	            c.setWdate(rs.getString("wdate"));
	            
	            
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return c;
	   }
}
