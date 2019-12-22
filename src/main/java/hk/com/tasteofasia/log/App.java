package hk.com.tasteofasia.log;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello World Console");
		System.out.println("Hello World Console 2");
		DatabaseLog.initDbConfig("localhost\\SQLExpress", "1433", "LogTest", "log", "12345678", "DatabaseLog", "log");
		System.setOut(DatabaseLog.getOutputStream());
		System.out.println("Hello World Database");
		System.out.println("Hello World Database 2");
		System.setOut(FileLog.getOutputStream());
		System.out.println("Hello World Log");
		System.out.println("Hello World Log 2");
	}

}
