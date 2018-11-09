package database;

public class DBConfigue {
	private String dbName, dbPass;
	
	public DBConfigue() {
		this.dbName = "root";
		this.dbPass = "123456789";
	}
	
	public String getdbName() {
		return this.dbName;
	}
	
	public String getdbPass() {
		return this.dbPass;
	}
	
}
