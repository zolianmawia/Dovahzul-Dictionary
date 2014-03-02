package net.fernandezgodinho.pedro.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.fernandezgodinho.pedro.dovahzuldictionary.DictionaryObject;

public class ArrayHelpers {
	public ArrayHelpers() {
	}

	public int searchForOneEnglish(DictionaryObject[] arrayToSearch,
			DictionaryObject[] menuItsIn, int menuIndex) {
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (menuItsIn[menuIndex].getEnglish().toLowerCase()
					.equals(arrayToSearch[i].getEnglish().toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	public int[] searchWithExactMatchEnglish(DictionaryObject[] arrayToSearch,
			String search) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (equalsEnglish(arrayToSearch[i].getEnglish().toLowerCase()
					.trim(), search.toLowerCase())) {
				index.add(i);
			}
		}
		return convertIntegers(index);
	}

	public boolean equalsEnglish(String s, String e) {
		// .split(",")[0].trim().split("/")[0].trim();
		for (int i = 0; i < s.split(",").length; i++) {
			for (int j = 0; j < s.split(",")[i].trim().split("/").length; j++) {
				if(s.split(",")[i].trim().split("/")[j].trim().equals(e)){
					return true;
				}
			}
		}
		return false;
	}

	public int[] searchWithExactMatchDovahzul(DictionaryObject[] arrayToSearch,
			String search) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (arrayToSearch[i].getDovahzul().toLowerCase().trim()
					.equals(search.toLowerCase())) {
				index.add(i);
			}
		}
		return convertIntegers(index);
	}

	public int[] searchWithoutExactMatchEnglish(
			DictionaryObject[] arrayToSearch, String search) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (arrayToSearch[i].getEnglish().toLowerCase().trim()
					.contains(search.toLowerCase())) {
				index.add(i);
			}
		}
		return convertIntegers(index);
	}

	public int[] searchWithoutExactMatchDovahzul(
			DictionaryObject[] arrayToSearch, String search) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (arrayToSearch[i].getDovahzul().toLowerCase().trim()
					.contains(search.toLowerCase())) {
				index.add(i);
			}
		}
		return convertIntegers(index);
	}

	public String[] arrayFromArraySearchEnglish(
			DictionaryObject[] arrayToSearch, int[] arrayWithIndexes) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < arrayWithIndexes.length; i++) {
			results.add(arrayToSearch[arrayWithIndexes[i]].getEnglish());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] arrayFromArraySearchDovahzul(
			DictionaryObject[] arrayToSearch, int[] arrayWithIndexes) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < arrayWithIndexes.length; i++) {
			results.add(arrayToSearch[arrayWithIndexes[i]].getDovahzul());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] arrayFromArraySearchCanon(DictionaryObject[] arrayToSearch,
			int[] arrayWithIndexes) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < arrayWithIndexes.length; i++) {
			results.add(arrayToSearch[arrayWithIndexes[i]].getCanon());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] arrayFromArraySearchClass(DictionaryObject[] arrayToSearch,
			int[] arrayWithIndexes) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < arrayWithIndexes.length; i++) {
			results.add(arrayToSearch[arrayWithIndexes[i]].getWordClass());
		}
		return results.toArray(new String[results.size()]);
	}

	public DictionaryObject[] allStartedByEnglish(
			DictionaryObject[] arrayToSearch, String letter) {
		ArrayList<DictionaryObject> results = new ArrayList<DictionaryObject>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (arrayToSearch[i].getEnglish().toLowerCase()
					.startsWith(letter.toLowerCase())) {
				results.add(arrayToSearch[i]);
			}
		}
		return results.toArray(new DictionaryObject[results.size()]);
	}

	public DictionaryObject[] allStartedByDovahzul(
			DictionaryObject[] arrayToSearch, String letter) {
		ArrayList<DictionaryObject> results = new ArrayList<DictionaryObject>();
		for (int i = 0; i < arrayToSearch.length; i++) {
			if (arrayToSearch[i].getDovahzul().toLowerCase()
					.startsWith(letter.toLowerCase())) {
				results.add(arrayToSearch[i]);

			}
		}
		return results.toArray(new DictionaryObject[results.size()]);
	}

	public String[] replaceStringOnArray(String[] array, String replace,
			String replaceBy) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(replace)) {
				array[i] = array[i].replaceAll(replace, replaceBy);
			}
		}
		return array;
	}

	public String[] stringArrayFromGetDovahzul(DictionaryObject[] dictionary) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			results.add(dictionary[i].getDovahzul());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] stringArrayFromGetEnglish(DictionaryObject[] dictionary) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			results.add(dictionary[i].getEnglish());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] stringArrayFromGetCanon(DictionaryObject[] dictionary) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			results.add(dictionary[i].getCanon());
		}
		return results.toArray(new String[results.size()]);
	}

	public String[] stringArrayFromGetClass(DictionaryObject[] dictionary) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			results.add(dictionary[i].getWordClass());
		}
		return results.toArray(new String[results.size()]);
	}

	private int[] convertIntegers(List<Integer> integers) {
		int[] ret = new int[integers.size()];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

	@Override
	public String toString() {
		return "This is ArrayHelpers";
	}
}
