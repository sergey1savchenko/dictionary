package coordInvIndex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CoordInvIndex {
	
	private static HashMap<String, HashMap<String, TreeSet<Long>>> coordInvIndex = new HashMap<String, HashMap<String, TreeSet<Long>>>(); 
	
	public static void insertWord(String word, String doc, long position){
		if (coordInvIndex.containsKey(word)){
			if(coordInvIndex.get(word).containsKey(doc)) {			// є слово і документ => додати нове входження
				coordInvIndex.get(word).get(doc).add(position);
			}else{													// є слово нема документа => додати док і входження
				TreeSet<Long> tmp = new TreeSet<Long>();
				tmp.add(position);
				coordInvIndex.get(word).put(doc, tmp);
			}
		} else {													// немає такого слова
			HashMap<String, TreeSet<Long>> entry = new HashMap<String, TreeSet<Long>>();
			TreeSet<Long> tmp = new TreeSet<Long>();
			tmp.add(position);
			entry.put(doc, tmp);
			coordInvIndex.put(word, entry);
		}
	}
	
	public static Set<String> getAllTerms() {															// Sorted array with terms
		return new TreeSet<String>(coordInvIndex.keySet());
	}

	public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {		// Write matrix to file
		PrintWriter writer = new PrintWriter("output\\coordInvIndex.txt", "UTF-8");
		for(int i=0; i<_run.Test.fileNames.length; i++) {
			//writer.print 
		}
		for(String cur : getAllTerms()){
			writer.println(coordInvIndex.get(cur) + "	" + cur );
		}
		writer.println("Unique words in collection: " + getAllTerms().size());
		writer.close();
		
		System.out.println("Unique words in collection: " + getAllTerms().size());
	}
	
	public static void serialize() {																	// Serialization 
	      try {
	          FileOutputStream fileOut = new FileOutputStream("output\\coordInvIndex.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(coordInvIndex);
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in output\\coordInvIndex.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static void searchWord(String key){															// Search in collection
		key = key.toLowerCase();
		if (coordInvIndex.containsKey(key)){
			System.out.println("Next documents contain this word: " + coordInvIndex.get(key)); 
		} else {
			System.out.println("Word does not exist in the collection!");
		}
	}

	public static void getSizeOfPlainTextFile(){														// Size of file
		File coordInvIndex = new File("output\\coordInvIndex.txt");
		System.out.println("The size of text coordInvIndex is: " + coordInvIndex.length()/1024 + " KB");
	}
	
	public static void getSizeOfSerializedFile(){														// Size of file
		File coordInvIndex = new File("output\\coordInvIndex.ser");
		System.out.println("The size of serialized coordInvIndex is: " + coordInvIndex.length()/1024 + " KB");
	}

	public static HashMap<String, TreeSet<Long>> word(String string) {
		return coordInvIndex.get(string);
	}

	public static HashMap<String, TreeSet<Long>> ANDwithDistance(HashMap<String, TreeSet<Long>> one, HashMap<String, TreeSet<Long>> two, int d) {
		HashMap<String, TreeSet<Long>> result = new HashMap<String, TreeSet<Long>>();
		
		Set<String> docsFromOne = one.keySet();
		for(String tempDoc : docsFromOne) {
			if (two.keySet().contains(tempDoc)) {
				TreeSet<Long> entriesFromOne = one.get(tempDoc);
				TreeSet<Long> entriesFromTwo = two.get(tempDoc);
				TreeSet<Long> entriesFromTwoMinusDist = new TreeSet<Long>();
				for(Long i : entriesFromTwo)
					entriesFromTwoMinusDist.add(i-d);
				for(Long j : entriesFromOne) {
					if(entriesFromTwoMinusDist.contains(j)) {
						if(result.containsKey(tempDoc)) {
							result.get(tempDoc).add(j);
						}else {
							TreeSet<Long> positions = new TreeSet<Long>();
							positions.add(j);
							result.put(tempDoc, positions);
						}
					}
				}
			}
		}
		
		return result;
	}

	public static TreeSet<String> search(ArrayList<String> words, ArrayList<Integer> distances) {
		HashMap<String, TreeSet<Long>> tmp = new HashMap<String, TreeSet<Long>>();
		
		tmp = word(words.get(0));
		int distanceCounter = 0;
		
		for(int i=1; i<words.size(); i++) {
			distanceCounter += distances.get(i-1);
			tmp = ANDwithDistance(tmp, word(words.get(i)), distanceCounter);
		}
		
		TreeSet<String> result = tmp.keySet().stream().collect(Collectors.toCollection(TreeSet::new));
		
		return result;
	}
	
}
