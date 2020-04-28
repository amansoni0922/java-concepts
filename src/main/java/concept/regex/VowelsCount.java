package concept.regex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Question</h1> Given strings, count the no. of vowels (including repetitions).
 *
 */
public class VowelsCount {

	public static void main(String[] args) {

		// testCases
		String[] tc = { "Elephant", "Cats & Dogs", "aaa bbb iii", "123  abe 123", "aman#soni" };

		// Header Row and Header Separation
		System.out.println("String\t\t\tvowCount\tvowCountRgx");
		System.out.println("----------------------------------------------------");

		for (String s : tc)
			System.out.println(s + "\t\t" + vowCount(s) + "\t\t" + vowCountRgx(s));
	}

	/**
	 * Method to count vowels using comparison. Uses HashSet to store vowels
	 */
	private static int vowCount(String s) {
		Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
		int count = 0;
		for (char c : s.toCharArray())
			if (vowels.contains(c))
				count++;
		return count;
	}

	/**
	 * Method to count vowels using regex. Remove all '#' from string and then
	 * replace all vowels with '#'. Finally count the no. of '#' left in the string
	 */
	private static int vowCountRgx(String s) {
		s = s.replaceAll("#", "");
		s = s.replaceAll("[aeiou]", "#");	// or "[aeiou]{1,1}"
		int count = 0;
		for (char c : s.toCharArray())
			if (c == '#')
				count++;

		return count;
	}

}
