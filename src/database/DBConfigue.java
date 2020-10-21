package database;

public class DBConfigue {
	
	// member 
	private String dbName, dbPass;
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://localhost:3306/javadb?&serverTimezone=Asia/Seoul&useSSL=false";
	
	public DBConfigue() {
		this.dbName = "root";
		this.dbPass = "45812Qlgks!";
	}
	
	// getter and setter
	
	public String getdbName() {
		return this.dbName;
	}
	
	public String getdbPass() {
		return this.dbPass;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}
	
}
