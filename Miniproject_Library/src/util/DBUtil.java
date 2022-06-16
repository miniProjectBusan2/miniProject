package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	public class DBUtil {
		// [step 01 Drive로딩] static 필드에서 로딩해줌
		static {
			try {
				// com.mysql.cj.jdbc.Driver 대소문자 지켜줘야 한다. 표준 표현임
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// DB 연결 기능 메소드 
		public static Connection getConnection () throws SQLException {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/scott","scott","tiger");
			return conn;
		}
		public static void close(ResultSet rset,Statement stmt,Connection conn) {
			try {
				if(rset!=null) {
						rset.close();
						rset=null;}
					if(stmt!=null) {
					stmt.close();
					stmt=null;}
					if(conn!=null) {
						conn.close();
						conn=null;}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}