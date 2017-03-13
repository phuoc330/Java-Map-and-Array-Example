
//Phuoc Nguyen
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PnguyenA06 {

	public static void main(String[] args) {

		String text = "";
		StringBuilder strBuilderText = new StringBuilder();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// Start read
		input.next();
		String stop; // A string to hold the last line read from the console
		do {
			stop = input.nextLine();
			strBuilderText.append(" ");
			strBuilderText.append(stop);

		} while (!stop.contains("*** LAST LINE ***"));
		// string builder to string
		text = strBuilderText.toString();
		// Split the text
		String[] words = text.split(" ");
		ArrayList<String> wordlist = new ArrayList<String>();
		// Fill the array with items from the word list.
		for (String word : words) {
			/*
			 * using unicode matching to get rid symbols (googled).replaces
			 * symbols with a space
			 */
			String cleanword = word.replaceAll("[^\\p{L}\\p{Nd}]", " ");
			// trim
			cleanword.trim();
			cleanword.toLowerCase();
			wordlist.add(cleanword);
		}
		// replacing symbols with space made new words like ago—never to
		// "ago never"; need to re do to split it again
		StringBuilder strBuilderNtext = new StringBuilder();
		// loop to transform arraylist to string builder
		for (String s : wordlist) {
			strBuilderNtext.append(s);
			strBuilderNtext.append(" ");
		}
		String Ntext = strBuilderNtext.toString();
		words = Ntext.split(" ");
		wordlist = new ArrayList<String>();
		for (String word : words) {
			wordlist.add(word.toLowerCase());
		}
		// remove all empty lists, and the single string S and T; letters used
		// for apostrophes.
		wordlist.removeAll(Arrays.asList(null, ""));
		wordlist.removeAll(Arrays.asList("t", ""));
		wordlist.removeAll(Arrays.asList("s", ""));
		// remove LAST LINE from *** LAST LINE ***
		wordlist.remove(wordlist.size() - 2);
		wordlist.remove(wordlist.size() - 1);
		// Hashmap to store values and keys
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		// adding a value to any duplicates (googled)
		for (String t : wordlist) {
			Integer count = wordCount.get(t);
			if (count == null) {
				wordCount.put(t, 1);
			} else {
				wordCount.put(t, count + 1);
			}
		}

		// new comparator to compare values and not keys as default for treemap
		// (googled)
		class MyComparator implements Comparator<Object> {
			Map<String, Integer> mymap;

			// object to insert the map i made (googled)
			public MyComparator(Map<String, Integer> mymap) {
				this.mymap = mymap;
			}

			// compare the values in hashmap named map (googled)
			public int compare(Object a, Object b) {
				if (mymap.get(b) == mymap.get(a))
					return 1;
				else
					return mymap.get(b).compareTo(mymap.get(a));
			}
		}
		// end object

		// compare values in hashmap wordcount by inserting into comparator
		MyComparator wordcompare = new MyComparator(wordCount);
		// new tree map with newly sorted values
		Map<String, Integer> sortedMap = new TreeMap<String, Integer>(wordcompare);
		// put sorted data into treemap
		sortedMap.putAll(wordCount);
		// print values and keys
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			System.out.println(entry.getValue() + " " + entry.getKey());
		}

	}

}
