package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonRegexApplications {

	public static void main(String[] args) {
		
		// find and replace
		csvToTsv();
		
		// find and modify
		capitalizeRomans();

		// complete manipulation
		syntxJavaToPy();
		
		
		/*
		 * Regex Limitations:
		 * 1. Overlapping pattern: Not possible to find overlapping pattern with pure regex. Support from programming language is needed
		 * 		to reset the regex cursor after every successful match. There is no way to indicate to regex regex to reset the cursor
		 * 		back to second position of last matched.
		 * 		References:
		 * 			-> https://stackoverflow.com/questions/20833295/how-can-i-match-overlapping-strings-with-regex/47184193
		 * 			-> https://stackoverflow.com/questions/320448/overlapping-matches-in-regex
		 * 
		 * 2. Palindrome: Looking for palindrome is a recursive task and Java flavor for regex doesn't support recursion. In general,
		 * 		regex is a finite set automata and so only if the upper bound on the palindrome length is given then its possible to match
		 * 		palindrome. Reference: 
		 * 			https://medium.com/analytics-vidhya/coding-the-impossible-palindrome-detector-with-a-regular-expressions-cd76bc23b89b
		 * 
		 * 3. Except Nth occurrence: This might not have a big time real life application but no harm in a mention as this is also a 
		 * 		consequence of finite set automata which means this case is also possible only we know the upper bound that is if we know
		 * 		the total number of occurrences. Otherwise, this also requires support from programming language.
		 * 
		 * 4. Conditional Regex: Java flavor for regex doesn't support conditional regex. Java is a high level programming language and
		 * 		so decision making constructs should be used in programming language rather than regex pattern.
		 * 
		 */
	}
	
	
	/**
	 * Given csv text convert to tsv.
	 */
	private static void csvToTsv() {
		String csv = "Luka,Marcelo,Sergio,Cristiano,Karim,Toni";
		
		Matcher matcher = Pattern.compile(",").matcher(csv);
		String tsv = matcher.replaceAll(m -> "\t");	// using lambda. though internally this also uses Matcher methods appendReplacement()
		System.out.println("using Matcher: " + tsv);// and appendTail(). Here m contains the matched sequence which gets replaced by "\t"
		
		// In such cases a simpler way would be to use the String replaceAll method. String replaceAll doesn't support Func Interface.
		tsv = csv.replaceAll(",", "\t");
		System.out.println("using String: " + tsv);
	}
	
	/**
	 * Given a text capitalize all the roman numerals that it contains.
	 */
	private static void capitalizeRomans() {
		String text = "primary classes: i ii iii iv, middle classes: vi vii viii, high school: ix xi are non-boards";
		String regex = "\\b([ivx]+)";	// here parenthesis are used to capture the roman in a group.
		
		Matcher matcher = Pattern.compile(regex).matcher(text);
		String correctedText = matcher.replaceAll(m -> m.group().toUpperCase());	// replace matched sequence by upper case of group
		System.out.println(correctedText);											// of matched.
	}
	
	/**
	 * Given function names in Java convert each to respective Python convention.
	 */
	private static void syntxJavaToPy() {
		String[] funcNames = {"getSmallestDigit","findGreatestCommonDivisor","fetchTop100Results","calculate","log10"};
		
		String regex = "[A-Z]?([a-z]+|\\d+)";	// the idea is to capture each word as a match and then call lower case on it.
		Matcher matcher = Pattern.compile(regex).matcher("");	// initialize empty matcher whenever matcher is being reset in loop.
		
		for(String s: funcNames) {
			matcher.reset(s);
			StringBuilder sb = new StringBuilder("");
			while(matcher.find()) {
				sb = sb.append("_").append(matcher.group().toLowerCase());
			}
			sb.deleteCharAt(0);	// delete the extra "_" at the beginning of sb
			System.out.println(sb);
		}
	}
	
}
