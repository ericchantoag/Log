package hk.com.tasteofasia.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FileLog {
	public static final String LOG_FOLDER = "./logs";
	public static PrintStream getOutputStream() {
		try {
			Path folderPath = Paths.get(LOG_FOLDER);
			if (!Files.exists(folderPath)) {
				Files.createDirectory(folderPath);
			}
			return new PrintStream(new FileOutputStream(LOG_FOLDER + "/" + LocalDate.now() + ".txt", true)); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Change to console log");
		}

		return null;
	}
}
