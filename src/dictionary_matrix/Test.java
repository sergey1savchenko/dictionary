package dictionary_matrix;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	static String allWords [];
	static String[] fileNames;
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
			Indexation.scanFile(curFile);												
		}

		Dictionary.buildArr();
		Dictionary.writeToFile();
		Dictionary.getSize();
		System.out.println("Dictionary is built!");

		Matrix.buildMatrix();
		System.out.println("Matrix is built!");

		System.out.println("Try to search some word: ");
		System.out.print("\nSearch word: ");
		String toSearch =  scan.nextLine();
		Dictionary.searchWord(toSearch.toLowerCase());

		System.out.println("Try to get index of some word: ");
		System.out.print("\nGet Index of word: ");
		String toSearch1 =  scan.nextLine();
		System.out.println(Index.getIndex(toSearch1.toLowerCase()));

		scan.close();

	}

}