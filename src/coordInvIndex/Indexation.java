package coordInvIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Indexation {

	public static void scanFile(File file) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		long position = 0;

		while (line != null) {  														

			String[] words = line.split(" ");

			for(String word : words){
				if(word!=null){
					CoordInvIndex.insertWord(cleanMe(word).toLowerCase(), file.getName(), position++);
				}																
			}

			line = bufferedReader.readLine(); 											
		}

		System.out.println("Indexation of file "+file.getName()+" completed!");

		bufferedReader.close();
	}

	private static String cleanMe(String s){
		return s.replaceAll("[^a-zA-Z0-9]", "");
	}

}
