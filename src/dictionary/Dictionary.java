package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.TreeSet;

public class Dictionary {

	public static int totalNumberOfWords = 0;
	private static TreeSet<String> dictionary = new TreeSet<String>(); 

	public static void insertWord(String word){
		dictionary.add(word);
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write dictionary to file
		PrintWriter writer = new PrintWriter("output\\dictionary.txt", "UTF-8");
		for(String cur : dictionary){
			writer.println(cur);
		}
		writer.println("Number of unique words in collection: " + dictionary.size());
		writer.close();

		System.out.println("Number of unique words in collection: " + dictionary.size());
	}

	public static void serialize() {																	// Serialization 
		try {
			FileOutputStream fileOut = new FileOutputStream("output\\dictionary.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(dictionary);
			out.close();
			fileOut.close();
			System.out.printf("Serialized dictionary is saved in output\\dictionary.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void getSizeOfPlainTextFile(){														// Size of text file
		File dict = new File("output\\dictionary.txt");
		System.out.println("The size of text dictionary is: " + dict.length()/1024 + " KB");
	}

	public static void getSizeOfSerializedFile(){														// Size of serialized file
		File dict = new File("output\\dictionary.ser");
		System.out.println("The size of serialized dictionary is: " + dict.length()/1024 + " KB");
	}

}