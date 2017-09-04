package two_words_dictionary_matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

public class TWDictionary {

	public static HashMap<String, LinkedHashSet<String>> words = new HashMap<String, LinkedHashSet<String>>();

	public static void insertPair(String key, String value){
		key = key.toLowerCase(); 
		value = value.toLowerCase();
		if (words.containsKey(key)){
			words.get(key).add(value);
		}
		else {
			LinkedHashSet<String> set = new LinkedHashSet<String>();
			set.add(value);
			words.put(key, set);
		}
	}

	public static void buildArr() {
		int count = 0;
		Test.allWords = new String [words.size()];
		for(Entry<String, LinkedHashSet<String>> entry : TWDictionary.words.entrySet()) {
			String key = entry.getKey();
			Test.allWords [count] = key;
			count++;
		}
		Arrays.sort(Test.allWords);
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("2wordIndex\\dictionary.txt", "UTF-8");
		for(String cur : Test.allWords){
			writer.println(cur + " " + words.get(cur));
		}
		writer.println("Unique pairs in collection: " + Test.allWords.length);
		writer.close();
		System.out.println("\nUnique pairs in collection: " + Test.allWords.length);
	}

	public static void searchWord(String key){
		key = key.toLowerCase();
		if (words.containsKey(key)){
			System.out.println("Next documents contain this pair: "+words.get(key));
		}
		else {
			System.out.println("Pair does not exist!");
		}
	}

	public static void getSize(){
		File dict = new File("2wordIndex\\dictionary.txt");
		System.out.println("The size of 2Wdictionary is: "+dict.length()+ " bytes");
	}

}