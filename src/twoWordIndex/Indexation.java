package twoWordIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Indexation {
	
	public static void scanFile(File file) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		String temp = "";
		
		while (line != null) {  														

			String[] words = line.split(" ");

			for(int i=0; i<(words.length-1); i++){
				if(words[i]!=null){
					temp = cleanMe(words[i]).toLowerCase() + " " + cleanMe(words[i+1]).toLowerCase();
					TwoWordIndex.insertWord(temp, file.getName());
					temp = "";
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
