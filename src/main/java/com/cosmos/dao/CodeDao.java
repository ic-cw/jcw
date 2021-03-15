package com.cosmos.dao;

import java.sql.*;

import org.springframework.stereotype.*;

import com.cosmos.dto.*;

@Repository
public class CodeDao {

	final Connection dbconn = OracleConnector.getInstance().getConnect();
	public Code[] getCodeList(String category) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Code[] code = null;
		
		String sql = "select count(*) as cnt from code ";
			   sql += "where category = ?";
		
		try {
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, category);
			rs = stmt.executeQuery();
			rs.next();
			
			code = new Code[rs.getInt("cnt")];
			sql = "select code_val, code_name from code ";
			sql +="where category = ?";
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, category);
			rs = stmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				Code c = new Code();
				c.setCodeVal(rs.getString("code_val"));
				c.setCodeName(rs.getString("code_name"));
				code[i++] = c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

}
