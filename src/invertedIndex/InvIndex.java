package invertedIndex;

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

public class InvIndex {
	
	public static int totalNumberOfWords = 0;
	
	public static HashMap<String, TreeSet<String>> invIndex = new HashMap<String, TreeSet<String>>(); 
	
	public static void insertWord(String key, String value){
		if (invIndex.containsKey(key)){
			invIndex.get(key).add(value);
		} else {
			TreeSet<String> set = new TreeSet<String>();
			set.add(value);
			invIndex.put(key, set);
		}
	}
	
	public static Set<String> getAllTerms() {															// Sorted array with terms
		return new TreeSet<String>(invIndex.keySet());
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write matrix to file
		PrintWriter writer = new PrintWriter("output\\invIndex.txt", "UTF-8");
		for(int i=0; i<_run.Test.fileNames.length; i++) {
			//writer.print 
		}
		for(String cur : getAllTerms()){
			writer.println(invIndex.get(cur) + "	" + cur );
		}
		writer.println("Unique words in collection: " + getAllTerms().size());
		writer.close();
		
		System.out.println("Unique words in collection: " + getAllTerms().size());
	}
	
	public static void serialize() {																	// Serialization 
	      try {
	          FileOutputStream fileOut = new FileOutputStream("output\\invIndex.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(invIndex);
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in output\\invIndex.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static void searchWord(String key){															// Search in collection
		key = key.toLowerCase();
		if (invIndex.containsKey(key)){
			System.out.println("Next documents contain this word: " + invIndex.get(key)); 
		} else {
			System.out.println("Word does not exist in the collection!");
		}
	}

	public static void getSizeOfPlainTextFile(){														// Size of file
		File invIndex = new File("output\\invIndex.txt");
		System.out.println("The size of text invIndex is: " + invIndex.length()/1024 + " KB");
	}
	
	public static void getSizeOfSerializedFile(){														// Size of file
		File invIndex = new File("output\\invIndex.ser");
		System.out.println("The size of serialized invIndex is: " + invIndex.length()/1024 + " KB");
	}

	public static TreeSet<String> word(String string) {
		return invIndex.get(string);
	}

	public static TreeSet<String> operatorAND(TreeSet<String> one, TreeSet<String> two) {
		TreeSet<String> result = new TreeSet<String>();
		for (String s : one) {
			if (two.contains(s))
				result.add(s);
		}
		return result;
	}

	public static TreeSet<String> operatorOR(TreeSet<String> one, TreeSet<String> two) {
		TreeSet<String> result = new TreeSet<String>();
		for (String s : one) {
				result.add(s);
		}
		for (String s : two) {
				result.add(s);
		}
		return result;
	}

	public static TreeSet<String> operatorNOT(String string) {
		TreeSet<String> result = new TreeSet<String>();
		for (String s : _run.Test.fileNames) {
			if (!invIndex.get(string).contains(s)) 
				result.add(s);
		}
		return result;
	}
	
}
