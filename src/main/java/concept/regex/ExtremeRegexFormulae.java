package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Word Boundary: Boundary matchers like '^' '$' and '\b' are called anchors which means they result in zero-length match.
 * Any place where a word character is adjacent to non-word character is called a word boundary where word characters are defined by
 * the word character class \w. This means '^' and '$' are special cases of \b. In Java, \b is backspace character so for regex
 * we use \\b or \\B. Negation of \b is \B which means all the places where a word character is adjacent to another word character
 * is qualified as \B.
 * 
 * Negate: [] is called character class in regex. [^] is called negate character class.
 * Few in-built negate classes are: \W = [^\w], \S = [^\s], \D = [^\d], \B = [^\b]
 * Note: negate character class can only be used to negate characters but if you wish to negate words then use negative look arounds.
 *
 * Non-Greedy: Repetition quantifiers in regex by default are greedy which means they try to match as many reps as possible. To 
 * change this default behavior from greedy to non-greedy we use another quantifier which is also called reluctant/lazy/non-greedy
 * quantifier denoted by '?'.
 * Note: Is also applicable to optional quantifier '?'. A non-greedy optional quantifier would look like this '(regex)??'.
 * Though it seems as it doesn't have any practical use.
 *
 */
public class ExtremeRegexFormulae {

	public static void main(String[] args) {
		
		// Example usage of word boundaries \b and \B
		//wordBoundary();
		
		// Example usage of negate character class and technique to negate words
		//negateSet();
		
		// Example usage of non-greedy quantifier with repetition quantifiers
		nonGreedy();
		
		/*
		 * powerful use of back reference
		 */
		
		/*
		 * named groups and their back reference usage
		 */

		/*find duplicates
		 * 
		 * refer: https://www.youtube.com/watch?v=87PJH2cJLX0
		 * 
		 */
		
		/* get just first match
		 * (?<!abc.*)abc
		 * 
		 * bonus: get all except first  (?<=abc.*)abc
		 */
		
		/*last match
		 * abc(?!.*abc)
		 * 
		 * bonus: get all except last abc(?=.*abc)
		 * refer: https://frightanic.com/software-development/regex-match-last-occurrence/
		 * refer https://jwcooney.com/2014/03/03/regular-expression-to-get-the-last-instance-of-a-word/
		 */
		
		/*nth Match
		 * (?:.*?abc){2}.*?(abc)
		 * 
		 * Bonus: can also be written as (?:.*?(abc)){3} since it capture group for last occurence only
		 * 
		 */

		// conditional regex 
		
	}

	/**
	 * Given a sentence, print individual words as per regex.
	 * 
	 * Given a sentence find words that contains the word 'ough'.
	 * 
	 * Given a sentence find the count of occurrences of 'ts' and 'st' combined inside words. 
	 */
	private static void wordBoundary() {
		String sentence = "OMG! is this real ? Plz tell me you're lying -2- me.";
		System.out.println("----Words as per regex----");
		// words as per regex
		String regex1 = "\\b\\w+";
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher(sentence);
		while(matcher.find()) {
			System.out.print(matcher.group() + " ");
		}
		
		
		sentence = "ought bought cough rough draught taught dough";
		System.out.println("\n\n----Words that contains 'ough'----");
		// Words that contains 'ough'
		String regex2 = "\\b\\w*ough\\w*";
		matcher.usePattern(Pattern.compile(regex2));	// for the same matcher object use different pattern
		matcher.reset(sentence);						// for the same matcher object use different string
		while(matcher.find()) {							// matcher object can be completely re-used whereas pattern object cannot
			System.out.print(matcher.group() + " ");	// as pattern is compiled and it makes more sense to create new object
		}
		
		
		sentence = "Constants Lists paste dent alloy Circumstances Stupid Tsunami".toLowerCase();
		System.out.println("\n\n----Count of occurrences of ts and st----");
		// Count of occurrences of ts or st
		String regex3 = "\\B(st|ts)\\B";
		matcher.usePattern(Pattern.compile(regex3));
		matcher.reset(sentence);
		short count = 0;
		while(matcher.find()) {
			count++;
		}
		System.out.println("Count: " + count);
	}

	/**
	 * Character Negate: Given email ids find the ids which consists of only letters.
	 * 
	 * Word Negate: Given email ids find all the non gmail ids.
	 */
	private static void negateSet() {
		String emailIds[] = {"modricluka10@madrid.co", "luka.modric@croatia.co", "lukamodric@gmail.com", 
				"modric10luka@gmail.com", "04sergio.r@madrid.com", "sergio.ramos@gmail.com", "marcelo@madrid.com", 
				"aman@1football.com", "cr7@juventus.italy", "james@fakegmail.com", "rodriguez@gmailfake.c"};
		
		// Character Negate
		System.out.println("----Character Negate----");
		String regex1 = "^([a-zA-Z]*[^\\d\\.\\s][a-zA-Z]*)@";	// Pattern explained: there should be no digit/space/dot
																// surrounded by letters, occurring before '@'. If match happens
																// then id will be captured in group(1) and entire match in group(0)
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher("");
		
		for(String s: emailIds) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t" + matcher.group(1));
			}
		}
		
		
		// Word Negate
		System.out.println("----Word Negate----");
		String regex2 = "@(?!gmail\\.)([\\w\\d]+)";	// Pattern explained: we use negative look ahead which says that
													// '@' should not be immediately followed by 'gmail.'
													// which then again is followed by at least one word or digit and capture it.
		
		matcher.usePattern(Pattern.compile(regex2));	// re-use existing matcher object for a different pattern.
		
		for(String s: emailIds) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t" + matcher.group(1));
			}
		}
	}

	/**
	 * Given HTML tag find content of its anchor.
	 * 
	 * Given binary string find the least binary substring surrounded with '1's such that whole string is 4 to 8 bits.
	 */
	private static void nonGreedy() {
		String htmlTag= "<a div='preface' href='link to source'></a>";
		String regex1 = "<.*>";		// default/greedy regex with '*' repetition quantifier
		String regex2 = "<.*?>";	// non-greedy regex with '*' repetition quantifier
		Matcher matcher1 = Pattern.compile(regex1).matcher(htmlTag);
		Matcher matcher2 = Pattern.compile(regex2).matcher(htmlTag);
		if(matcher1.find() && matcher2.find()) {
			System.out.println(matcher1.group());	// problem here is that it catches both start and end tags.
			System.out.println(matcher2.group());	// problem solved here by using non-greedy to match as less as possible.
		}
		
		
		String binary = "101010010001000";
		regex1 = "1(1|0){2,6}1";	// default/greedy regex with '{m,n}' repetition quantifier
		regex2 = "1(1|0){2,6}?1";	// non-greedy regex with '{m,n}' repetition quantifier
		// consider using non-capturing group for above regex like: "1(?:1|0){2,6}1" and "1(?:1|0){2,6}?1"
		matcher1 = Pattern.compile(regex1).matcher(binary);
		matcher2 = Pattern.compile(regex2).matcher(binary);
		if(matcher1.find() && matcher2.find()) {
			System.out.println(matcher1.group());	// problem here is that it doesn't catch least. It goes for max match.
			System.out.println(matcher2.group());	// problem solved here by using non-greedy to match as less as possible.
		}
	}

	
	
	
}
