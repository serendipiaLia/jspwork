package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		// 필드 생성
		String drvierClass = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/jwebdb?serverTime=Asia/Seoul";
		String user = "jweb";
		String password = "pwjweb";
		
		try {
			// 오라클 연결 드라이버
			Class.forName(drvierClass); //위에서 drvierClass 변수 선언했으므로 ()안에 변수이름
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("db연결 성공! " + conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}