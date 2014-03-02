package net.fernandezgodinho.pedro.dovahzuldictionary;

public class DictionaryObject implements Comparable<DictionaryObject> {
	static boolean compareEnglish;
	final String english;
	final String dovahzul;
	final String canon;
	final String wordClass;
	final String notes;

	DictionaryObject(String english, String dovahzul, String canon,
			String wordClass, String notes) {
		this.english = english;
		this.dovahzul = dovahzul;
		this.canon = canon;
		this.wordClass = wordClass;
		this.notes = notes;
	}

	public String getEnglish() {
		return english;
	}

	public String getDovahzul() {
		return dovahzul;
	}

	public String getCanon() {
		return canon;
	}

	public String getWordClass() {
		return wordClass;
	}

	public String getNotes() {
		return notes;
	}

	static void setCompareEnglish(boolean sort) {
		compareEnglish = sort;
	}

	@Override
	public String toString() {
		return english + "" + "dovahzul" + "" + canon + "" + wordClass + ""
				+ notes;
	}

	@Override
	public int compareTo(DictionaryObject arg0) {
		if (compareEnglish) {
			return this.getEnglish().compareToIgnoreCase(arg0.getEnglish());
		} else {
			return this.getDovahzul().compareToIgnoreCase(arg0.getDovahzul());
		}
	}

}
