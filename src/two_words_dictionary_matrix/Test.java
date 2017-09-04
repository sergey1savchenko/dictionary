package two_words_dictionary_matrix;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static String allWords [];
	public static String[] fileNames;
	static final String src = "input";													// Files

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		File folder = new File(src);													
		final String[] mask = {".txt", ".fb2"};											// Mask

		String[] files = folder.list(new FilenameFilter() {								
			@Override 
			public boolean accept(File folder, String name) {
				for(String s : mask)
					if(name.toLowerCase().endsWith(s)) return true;
				return false;
			}
		});

		fileNames = files;

		for(String fileName : files){
			System.out.println("\nFile: "+fileName + " loaded;");							
			File curFile  = new File (src +"\\"+fileName);								 
			TWIndexation.scanBook(curFile);												
		}

		TWDictionary.buildArr();
		TWDictionary.writeToFile();
		TWDictionary.getSize();															// DICTIONARY
		System.out.println("Dictionary is built!");

		TWMatrix.buildMatrix();															// MATRIX
		System.out.println("Matrix is built!");

		System.out.println("Try to search some pair of words: ");
		String toSearch =  scan.nextLine();
		TWDictionary.searchWord(toSearch.toLowerCase());

		System.out.println("Try to get index of pair of words: ");
		String toSearch1 =  scan.nextLine();
		System.out.println(TWIndex.getIndex(toSearch1.toLowerCase()));

		scan.close();

	}

}