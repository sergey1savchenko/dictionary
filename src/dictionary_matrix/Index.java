package dictionary_matrix;

import java.util.LinkedHashSet;

public class Index {

	public static String getIndex(String term){
		
		String docs [] = Test.fileNames;
		String result = "";

		LinkedHashSet<String> value = Dictionary.words.get(term);
		term = term.toLowerCase();
		
		for(String curFile : docs){
			if(Dictionary.words.containsKey(term)){
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