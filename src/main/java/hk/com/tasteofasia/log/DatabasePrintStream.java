package hk.com.tasteofasia.log;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePrintStream extends PrintStream {
	private Connection connection;
	private String tableName;
	private String logColumn;

	public DatabasePrintStream(String host, String port, String dbName, String username, String password, String tableName, String columnName) {
		super(FileLog.getOutputStream());
		this.tableName = tableName;
		this.logColumn = columnName;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbName;
			this.connection = DriverManager.getConnection(connUrl, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String log) {
		try {
			Statement stmt = this.connection.createStatement();
			stmt.execute(String.format("INSERT INTO %s(%s) VALUES (\'%s\')", this.tableName, this.logColumn, log));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void print(String log) {
		this.write(log);
    }

	@Override
    public void println(String log) {
		this.write(log);
    }
}
