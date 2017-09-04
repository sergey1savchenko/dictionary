package dictionary_matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Indexation {

	static String s;																	

	public static void scanFile(File file) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String symbol = bufferedReader.readLine();

		while (symbol != null) {  														

			String[] words = new String[symbol.split(" ").length];
			words = symbol.split(" ");

			for(int i=0; i<words.length; i++){
				words[i] = cleanMe(words[i]);
			}
			for(String word : words){
				if(word!=null){
					Dictionary.insertWord(word, file.getName());
				}																
			}

			symbol = bufferedReader.readLine(); 											
		}

		System.out.println("Indexation of file "+file.getName()+" completed!");

		bufferedReader.close();
	}

	private static String cleanMe(String s){
		return s.replaceAll("[^a-zA-Z]", "");
	}

}