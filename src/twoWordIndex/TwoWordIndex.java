package twoWordIndex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class TwoWordIndex {
	
	private static HashMap<String, TreeSet<String>> twoWordIndex = new HashMap<String, TreeSet<String>>(); 
	
	public static void insertWord(String key, String value){
		if (twoWordIndex.containsKey(key)){
			twoWordIndex.get(key).add(value);
		} else {
			TreeSet<String> set = new TreeSet<String>();
			set.add(value);
			twoWordIndex.put(key, set);
		}
	}
	
	public static Set<String> getAllTerms() {															// Sorted array with terms
		return new TreeSet<String>(twoWordIndex.keySet());
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write 2WordIndex to file
		PrintWriter writer = new PrintWriter("output\\twoWordIndex.txt", "UTF-8");
		for(int i=0; i<_run.Test.fileNames.length; i++) {
			//writer.print 
		}
		for(String cur : getAllTerms()){
			writer.println(twoWordIndex.get(cur) + "	" + cur );
		}
		writer.println("Words pairs: " + getAllTerms().size());
		writer.close();
		
		System.out.println("Words pairs in collection: " + getAllTerms().size());
	}
	
	public static void serialize() {																	// Serialization 
	      try {
	          FileOutputStream fileOut = new FileOutputStream("output\\twoWordIndex.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(twoWordIndex);
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in output\\twoWordIndex.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static void searchWord(String key){															// Search in collection
		key = key.toLowerCase();
		if (twoWordIndex.containsKey(key)){
			System.out.println("Next documents contain this word: " + twoWordIndex.get(key)); 
		} else {
			System.out.println("Word does not exist in the collection!");
		}
	}

	public static void getSizeOfPlainTextFile(){														// Size of file
		File invIndex = new File("output\\twoWordIndex.txt");
		System.out.println("The size of text invIndex is: " + invIndex.length()/1024 + " KB");
	}
	
	public static void getSizeOfSerializedFile(){														// Size of file
		File invIndex = new File("output\\twoWordIndex.ser");
		System.out.println("The size of serialized invIndex is: " + invIndex.length()/1024 + " KB");
	}

	public static TreeSet<String> words(String string) {
		return twoWordIndex.get(string);
	}
	
	public static TreeSet<String> operatorAND(TreeSet<String> one, TreeSet<String> two) {
		TreeSet<String> result = new TreeSet<String>();
		for (String s : one) {
			if (two.contains(s))
				result.add(s);
		}
		return result;
	}
	
	public static TreeSet<String> phraseSearch(String input) {
		String[] inputWords = input.split(" ");
		TreeSet<String> result = words(inputWords[0]+" "+inputWords[1]);
		for (int i=1; i<(inputWords.length-1); i++) {
			result = operatorAND(result, words(inputWords[i]+" "+inputWords[i+1]));
		}
		return result;
	}
	
}
