package dictionary_matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

public class Dictionary {

	public static HashMap<String, LinkedHashSet<String>> words = new HashMap<String, LinkedHashSet<String>>(); 

	// HashMap _ WORD = key	WORD_INSERTIONS = value
	public static void insertWord(String key, String value){
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

	public static void buildArr() {																		// Sorted array with terms
		int count = 0;
		Test.allWords = new String [words.size()];
		for(Entry<String, LinkedHashSet<String>> entry : Dictionary.words.entrySet()) {
			String key = entry.getKey();
			Test.allWords [count] = key;
			count++;
		}
		Arrays.sort(Test.allWords);
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write dictionary to file
		PrintWriter writer = new PrintWriter("output\\dictionary.txt", "UTF-8");
		for(String cur : Test.allWords){
			writer.println(cur + " " + words.get(cur));
		}
		writer.println("Unique words in collection: " + Test.allWords.length);
		writer.close();
		System.out.println("\nUnique words in collection: " + Test.allWords.length);
	}

	public static void searchWord(String key){															// Search in collection
		key = key.toLowerCase();
		if (words.containsKey(key)){
			System.out.println("Next documents contain this word: "+words.get(key));
		}
		else {
			System.out.println("Word does not exist!");
		}
	}

	public static void getSize(){																		// Size of dictionary
		File dict = new File("output\\dictionary.txt");
		System.out.println("The size of dictionary is: "+dict.length()+ " bytes");
	}
}