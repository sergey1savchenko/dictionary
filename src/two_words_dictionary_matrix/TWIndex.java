package two_words_dictionary_matrix;

import java.util.LinkedHashSet;

public class TWIndex {

	public static String getIndex(String term){
		String docs [] = Test.fileNames;
		String result = "";

		LinkedHashSet<String> value = TWDictionary.words.get(term);
		term = term.toLowerCase();
		for(String curFile : docs){
			if(TWDictionary.words.containsKey(term)){
				if (value.contains(curFile.toLowerCase())){
					result = result+"1";
				}
				else{
					result = result+"0";
				}
			}
			else{
				result = result+"0";
			}
		}	
		return result;
	}

}