package com.buisness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ZipHandler {
	
	static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS = (PROTOCOL = TCP)(HOST = 10.160.192.184)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodlyb)))";
	static final String Oracleusername = "ezto";
	static final String Oraclepassword = "eztoqalv";
	static final String GET_MEDIA = "SELECT  datasize, data, width, height FROM media WHERE mediaID=? ORDER BY block";
	
	
	public static void main(String[] args) {
		Connection con = null;
		long mediaId = 0l;
		try{	
			con = getConnection();
			createZipFile(con, mediaId);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(con != null){
					con.close();
				}
				
			}catch(Exception ex){
				System.out.println(" Exception in releasing Connection resources.");
				ex.printStackTrace();
			}
		}
	}
	
	public static void createZipFile(Connection con, long mediaId) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet ls = null;
		try{
			pstmt = con.prepareStatement(GET_MEDIA);
			pstmt.setLong(1, mediaId);
			ls = pstmt.executeQuery();
			
			for(int i =0; ls.next(); i++){
				
			}
			
		}catch(Exception ex){
			
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(ls != null){
					ls.close();
				}
			}catch(Exception ex){
				System.out.println(" Exception in releasing PreparedStatement and ResultSet resources.");
				ex.printStackTrace();
			}
		}
	}
	
	
	
	public static Connection getConnection() throws Exception{
		try{
			Class.forName(Oracledriver);
			return DriverManager.getConnection(Oracleurl,Oracleusername,Oraclepassword);
		}catch(Exception ex){
			System.out.println(" Exception while creating Connection...");
			throw ex;
		}
	}
	
}
