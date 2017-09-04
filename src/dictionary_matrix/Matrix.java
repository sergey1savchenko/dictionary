package dictionary_matrix;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Matrix {

	public static void buildMatrix() throws FileNotFoundException, UnsupportedEncodingException {

		int i = 0;

		String a = "abcdefghijklmnopqrstuvwxyz";
		char abc[] = a.toCharArray();

		PrintWriter writer = new PrintWriter("output\\matrix.txt", "UTF-8");

		writer.println("Files in order: ");

		for (String curFile : Test.fileNames){											// Legend
			writer.println(abc[i++]+" = "+curFile);
		}
		writer.println("");

		for (int k=1; k<=i; k++){														// Files
			writer.print(abc[k]);
		}
		writer.println("");

		for (String curWord : Test.allWords){											// Build the Matrix
			writer.println(Index.getIndex(curWord)+" "+curWord);
		}
		writer.close();
	}	

	public static HashMap<String, String> getHashMatrix(){

		HashMap<String, String> matrix = new HashMap<String, String>(); 

		for (String curTerm : Test.allWords){											// Key = term
			matrix.put(curTerm, Index.getIndex(curTerm));
		}

		return matrix;
	}
}