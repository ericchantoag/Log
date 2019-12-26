package hk.com.tasteofasia.log;

import java.io.PrintStream;
import java.sql.SQLException;

public class DatabaseLog {
	private static String host;
	private static String port = "1433";
	private static String dbName;
	private static String username;
	private static String password;
	
	private static String tableName;
	private static String columnName;
	private static PrintStream dbStream;
	
	public static void initDbConfig(String host, String port, String dbName, String username, String password, String tableName, String columnName) {
		DatabaseLog.host = host;
		DatabaseLog.port = port;
		DatabaseLog.dbName = dbName;
		DatabaseLog.username = username;
		DatabaseLog.password = password;
		DatabaseLog.tableName = tableName;
		DatabaseLog.columnName = columnName;
	}

	public static PrintStream getOutputStream() {
		if (DatabaseLog.host == null ||
			DatabaseLog.port == null ||
			DatabaseLog.dbName ==  null ||
			DatabaseLog.username == null ||
			DatabaseLog.password == null ||
			DatabaseLog.tableName == null ||
			DatabaseLog.columnName == null)
		{
			throw new UnsupportedOperationException("must call initDbConfig before getOutputStream");
		}
		
		try {
			if (DatabaseLog.dbStream == null || !(DatabaseLog.dbStream instanceof DatabasePrintStream)) {
				DatabaseLog.dbStream = new DatabasePrintStream(host, port, dbName, username, password, tableName, columnName);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Change to file log");
			DatabaseLog.dbStream = FileLog.getOutputStream();
		}
		return DatabaseLog.dbStream;
	}
}
