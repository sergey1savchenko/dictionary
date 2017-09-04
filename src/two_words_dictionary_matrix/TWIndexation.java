package two_words_dictionary_matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TWIndexation {

	static String s;
	static String builder = "";

	public static void scanBook(File file) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String symbol = bufferedReader.readLine();

		while (symbol != null) {  														

			String[] words = new String[symbol.split(" ").length];
			words = symbol.split(" ");

			for(int i=0; i<words.length; i++){
				words[i] = cleanMe(words[i]);
			}

			for(int i=0; i<(words.length-1); i++){
				if(words[i+1]!=null){
					builder = words[i]+" "+words[i+1];
					TWDictionary.insertPair(builder, file.getName());
				}																
			}

			symbol = bufferedReader.readLine(); 											
		}

		System.out.println("Indexation of file: "+file.getName()+" completed!");
		bufferedReader.close();
	}

	private static String cleanMe(String s){
		return s.replaceAll("[^a-zA-Z]", "");
	}

}