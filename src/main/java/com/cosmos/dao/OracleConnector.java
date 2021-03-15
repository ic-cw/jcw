package com.cosmos.dao;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class OracleConnector {
	//필드
	static private OracleConnector sql = new OracleConnector();   
//    public Connection connDB = null;
    

	//생성자
   private OracleConnector() {
	  
   }
   
   //객체주소 리턴 메소드
   public static OracleConnector getInstance() {
	      return sql;
   }
   
   public Connection getConnect() {
	   Properties prop = new Properties();
	   //cosmos.properties에서 정보 가져오기
	   String path = OracleConnector.class.getResource("cosmos.properties").getPath();
//	   System.out.println(path);
	   
       Connection conn = null;
       
       try {
    	  path = URLDecoder.decode(path, "utf-8");
    	  prop.load(new FileReader(path));
          Class.forName(prop.getProperty("driver"));
          conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pw"));
//          this.connDB = conn;
       } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return conn;
   }
}
