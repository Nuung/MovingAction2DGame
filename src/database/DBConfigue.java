package database;

public class DBConfigue {
	
	// member 
	private String dbName, dbPass;
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://localhost:3306/javadb?characterEncoding=UTF-8&serverTimezone=UTC";
	
	public DBConfigue() {
		this.dbName = "root";
		this.dbPass = "45812qlgks!#";
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
