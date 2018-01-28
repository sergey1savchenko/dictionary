package coordInvIndex;

public class Term implements Comparable<Term>{
	
	private String word;
	private int[] documents;
	
	public Term(String word, int sizeOfCollection) {
		this.word = word;
		this.documents = new int[sizeOfCollection];
	}
	
	public String getWord() {
		return word;
	}
	
	public void addDocument(int indexOfDocument) {
		documents[indexOfDocument] += 1;
	}
	
	public int[] getDocsRow() {
		return documents;
	}

	@Override 
	public int compareTo(Term arg) {
		return arg.getWord().compareTo(this.word);
	}
	
	@Override 
	public boolean equals(Object other) {
	    if (!(other instanceof Term)) {
	      return false;
	    }
	    Term otherTerm = (Term) other;
	    return word.equals(otherTerm.getWord());
	  }

	@Override 
	public int hashCode() {
		return word.hashCode();
	}
	
}
