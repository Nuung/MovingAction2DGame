package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	private DBConfigue dbConfigue;
	private Connection con;
	private Statement st; // connect에 SQL문장 실행하는 방법을 정해주는 객체
	private ResultSet rs; // SQL 결과 받아오는 객체
	
	// 기본 접속 시도해주는 Class
	public DBConnection() {
		// DB conntecion 관련 유저이름, 비밀번호 보안강화를 위한 계층작업
		dbConfigue = new DBConfigue();
		
		// DB 커넥션 예외 처리 try - catch
		try {
			Class.forName("com.mysql.jdbc.Driver"); // library의 Dirver Class참조
			con = DriverManager.getConnection("JDBC:mysql://localhost:3306/searching", 
					dbConfigue.getdbName(), dbConfigue.getdbPass());
			st = con.createStatement();
			
		} catch(Exception e) {
			System.out.println("DB error : " + e.getMessage());
		} // try - catch
	} // 생성자
	
	// SQL, 실행 기본 구조
	public boolean isAdmin(String gooname) {
		try {
			String SQL = "SELECT * FROM `market` WHERE `gooname` = '"+ gooname +"'";
			rs = st.executeQuery(SQL); // rs가 SQL 구문의 결과 행 값들을 가지게 된다.
			if(rs.next()) { // SQL구문의 결과값이 존재한다면
				return true;
			}
		} catch(Exception e) {
			System.out.println(" DB searching error (in SQL 구문) : " + e.getMessage());
		} // try - catch
		return false;
	} // isAdmin()
	
	public ResultSet getResultSet(String gooname) {
		try {
			String SQL = "SELECT * FROM `market` WHERE `gooname` = '"+ gooname +"'";
			rs = st.executeQuery(SQL); // rs가 SQL 구문의 결과 행 값들을 가지게 된다.
			if(rs.next()) { // SQL구문의 결과값이 존재한다면
				return rs;
			} // if
		} catch(Exception e) {
			System.out.println(" DB searching error (in SQL 구문) : " + e.getMessage());
		} // try - catch
		return rs;
	}
	
} // DBConnection Class
