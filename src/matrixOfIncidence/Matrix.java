package matrixOfIncidence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Matrix {
	
	public static int totalNumberOfWords = 0;
	private static HashMap<String, int[]> matrix = new HashMap<String, int[]>(); 
	
	public static void insertWord(String word, int documentId){
		if (matrix.containsKey(word)){
			matrix.get(word)[documentId] += 1;
		} else {
			int[] docs = new int[_run.Test.fileNames.length];
			docs[documentId] += 1;
			matrix.put(word, docs);
		}
	}
	
	public static Set<String> getAllTerms() {															// Sorted array with terms
		return new TreeSet<String>(matrix.keySet());
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write matrix to file
		PrintWriter writer = new PrintWriter("output\\matrix.txt", "UTF-8");
		for(String cur : getAllTerms()){
			writer.println(Arrays.toString(matrix.get(cur)) + " === " + cur );
		}
		writer.println("Unique words in collection: " + getAllTerms().size());
		writer.close();
		
		System.out.println("\nUnique words in collection: " + getAllTerms().size());
	}
	
	public static void serialize() {																	// Serialization 
	      try {
	          FileOutputStream fileOut = new FileOutputStream("output\\matrix.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(matrix);
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in output\\matrix.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static void searchWord(String key){															// Search in collection
		key = key.toLowerCase();
		if (matrix.containsKey(key)){
			System.out.println("Next documents contain this word: " + Arrays.toString(matrix.get(key))); 
		} else {
			System.out.println("Word does not exist in the collection!");
		}
	}

	public static void getSizeOfPlainTextFile(){														// Size of file
		File dict = new File("output\\matrix.txt");
		System.out.println("The size of text matrix is: " + dict.length()/1024 + " KB");
	}
	
	public static void getSizeOfSerializedFile(){														// Size of file
		File dict = new File("output\\matrix.ser");
		System.out.println("The size of serialized matrix is: " + dict.length()/1024 + " KB");
	}

	public static int[] word(String string) {
		return matrix.get(string);
	}

	public static int[] operatorAND(int[] one, int[] two) {
		int[] result = new int[one.length];
		for (int i=0; i<one.length; i++) {
			if (one[i]!=0 && two[i]!=0)
				result[i]=1;
		}
		return result;
	}

	public static int[] operatorOR(int[] one, int[] two) {
		int[] result = new int[one.length];
		for (int i=0; i<one.length; i++) {
			if (one[i]!=0 || two[i]!=0)
				result[i]=1;
		}
		return result;
	}

	public static int[] operatorNOT(String string) {
		int[] toCopy = matrix.get(string);
		int[] result = new int[toCopy.length];
		for (int i=0; i<result.length; i++)
			result[i] = toCopy[i];
		for(int i=0; i<result.length; i++) {
			if(result[i]==0)
				result[i]=1;
			else 
				result[i]=0;
		}
		return result;
	}
	
}
