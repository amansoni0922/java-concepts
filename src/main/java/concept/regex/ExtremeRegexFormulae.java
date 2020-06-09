package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Regex usage can be broadly classified into following categories:
 * 1. Validation: check if a given string completely matches a pattern. Ex: password validation, email validation
 * 					All the matches() methods in Java are for validation.
 * 2. Search: look for existence of a pattern in a given string. This pattern may exist in string N times or may exist exactly one time
 * 				or may not exist at all. This can be checked by using repeated calls to find() in Java.
 * 3. Find & Replace: look for a pattern in a given string and replace with a replacement string for all occurrences or Nth occurrence.
 * 						For this Java provides methods replaceFirst() and replaceAll() in String and Matcher classes.
 * 4. Find & Modify: look for a pattern in the given string, capture it, modify it and then replace original with this one. Do this for
 * 						all occurrences or Nth occurrence. For this Java supports regex variables '$N' in replaceFirst() and replaceAll()
 * 						provided by String and Matcher. Additionally, Matcher class from Java9 onwards supports functional interface
 * 						lambda expression in replaceFirst() and replaceAll(). Ex: matcher.replaceAll(m -> m.group().toUpperCase());
 * 
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
 * Back-References: There are times when we want to capture a pattern using regex and would like to check for the existence of the same
 * pattern ahead in the given string. This can be achieved by simply repeating the regex in the pattern. For ex: look for digits in the
 * given string for which the regex would be "(\d+).+(\d+)". But there are times when we would like to capture the occurrence of the 
 * exact string literal that matched the regex. For this, regex provides the feature to backreference a match where match will be enclosed
 * in a capturing group. For ex: look for digits and if the exact digits are repeated and for which regex would be "(\d+).+(\1)".
 * 
 * Regex Variables: There are times when we would like replace some or all string with the exact literal that has been matched by our
 * given regex pattern then for those scenarios regex provides special variables $1,$2.. so on where the number refers to the capturing
 * group. These are also called captured reference variables. These variables are read-only regex variables and so the content of $N 
 * cannot be accessed by Java but only regex.
 * 
 * Named Group: Regex has its own memory where it stores the capturing group. As we know by now there captured grouped can be referenced
 * for replacement or can be back-referenced using the numbers. But regex also provides a more neat way for doing so using the naming
 * groups. Regex can have a capturing group with name for which syntax is (?<'name'>regex). Though this will require more regex memory
 * but if space is not an issue and regex pattern clarity is priority then one must use named groups. Named group can be back-referenced
 * using \k<'name'> and be used for captured-reference using ${'name'}. 
 * 
 */
public class ExtremeRegexFormulae {

	public static void main(String[] args) {
		
		// Example usage of word boundaries \b and \B
		//wordBoundary();
		
		// Example usage of negate character class and technique to negate words
		//negateSet();
		
		// Example usage of non-greedy quantifier with repetition quantifiers
		//nonGreedy();
		
		// Examples of back-references
		//backReferences();
		
		// Examples of captured-references aka regex variables in Java
		//capturedReferences();
		
		// Named groups and its application in back-reference and captured-reference
		//namedGroups();
		
		// Example for replace of just first match and all except first match
		//justFirstMatched();
		
		// Example for replace of just last match and all except last match
		//justLastMatched();
		
		// Example for replacing Nth match and replacing everything except Nth match
		//nthMatched();
		
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

	/**
	 * Given a statement find all the words which might have got repeated by mistake. In a statement a lot of words like 'the' 
	 * and 'is' gets repeated somewhere in the statement but if a word gets repeated immediate next then its probably
	 * by mistake. Use this assumption and find all duplicated words.
	 * 
	 * Given a statement find all the words that are composed of unique letters. A word is composed of unique letters if
	 * no letter in its spelling gets repeated.
	 */
	private static void backReferences() {
		String statement = "This is is a sample statement for very very simple regex example usage and and demo program.";
		
		// duplicate words and immediate duplication
		System.out.println("----Repeated words----");
		String regex = "\\b(\\w+)\\s(\\1)";		// regex to find word and its duplicate separated by a space
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(statement);
		while(matcher.find()) {
			System.out.println("Word repeated:\t" + matcher.group(2) + "\tAt index: " + matcher.start(2));
		}
		
		
		// duplicate characters and anywhere duplication
		System.out.println("\n----Non-unique words----");
		regex = "\\w*(\\w)\\w*\\1\\w*";	// (\w) is the letter that gets repeated and we use \1 to back-reference the letter.
										// all the \w* used are to indicate that the letter that gets repeated can be the the beginning
										// or in between and there can be letters between the repeated letters.
		matcher.usePattern(Pattern.compile(regex));
		matcher.reset();	// reset the matchers cursor.
		while(matcher.find()) {
			System.out.println("Non unique word:  " + matcher.group() + "\tLetter " + matcher.group(1) + " gets repeated");
		}
	}

	/**
	 * Given a statement find and remove all the words which might have got repeated by mistake. In a statement a lot of words
	 * like 'the' and 'is' gets repeated somewhere in the statement but if a word gets repeated immediate next then its probably
	 * by mistake. Use this assumption and find all duplicated words and remove the duplicates.
	 * 
	 * Given a JSON string convert to XML.
	 */
	private static void capturedReferences() {
		// find and remove duplicates
		String statement = "This is is a sample statement for very very simple regex example usage and and demo program.";
		String regex = "\\b(\\w+)\\s\\1";	// notice here we capture exactly one group and later we reference that group using $1
		String correctedStmt = statement.replaceAll(regex, "$1");	// in the given string we match "someword sameword" and replace the
		System.out.println("Corrected Statement: " + correctedStmt);// match with just "someword" by referencing using $1. This happens 
																	// at every match in the given string and there are 3 such matches.
		
		
		// JSON to XML
		System.out.println("\n----JSON to XML----");
		String jsonStr = "{id:12,name:\"Marcelo\",position:\"LB\",playing11:true,nationality:\"Brazil\"}";
		String toXML = "\\{?(\\w+):(\\\"?\\w+\\\"?),?\\}?";	// curly braces with optional quantifiers are given so that wherever they
									// exist they should get consumed by the match so that on replace everything thats consumed gets
									// replaced and we don't have undesirable braces as left overs.
		Matcher matcher = Pattern.compile(toXML).matcher(jsonStr);
		while (matcher.find()) {	// lets try to find each key value pair and replace one by one
			System.out.println(matcher.group()); // print the pair
			System.out.println("<varName>$1</varName><value>$2</value>"); 	// so turns out that regex variables are only accessible
																			// in the context of regex and not otherwise.
		}
		// by regex context we mean it can only be used wherever regex string is supported ex: methods like replaceAll, replaceFirst, etc
		String xmlStr = matcher.replaceAll("<varName>$1</varName><value>$2</value>");
		System.out.println("\nxmlStr: " + xmlStr);
		// Since we are using the replaceAll method so no need to create matcher object and can be used with string object itself
		String xml = jsonStr.replaceAll(toXML, "<varName>$1</varName><value>$2</value>");
		System.out.println("\nxml: " + xml);
	}
	
	/**
	 * In a given statement print the duplicated words and remove the duplicates.
	 */
	private static void namedGroups() {
		// simple example: naming one group
		String statement = "This is is a sample statement for very very simple regex example usage and and demo program.";
		String regex = "\\b(?<rep>\\w+)\\s\\k<rep>";	// 'rep' is the group name given to the only captured group in the pattern
														// to reference group by name we use \k<'name'>
		Matcher matcher = Pattern.compile(regex).matcher(statement);
		System.out.println("----Duplicated words----");
		while(matcher.find()) {
			System.out.println(matcher.group("rep"));	// accessing the group by name in Java
		}
		
		// example: naming two groups
		regex = "\\b(?<original>\\w+)\\s(?<duplicate>\\k<original>)";	// here we have two groups. One captured original and another
																		// captures the duplicated word in the group named 'duplicate'
		matcher.usePattern(Pattern.compile(regex));
		matcher.reset();
		System.out.println("\n----Duplicated words with indices----");
		while(matcher.find()) {
			System.out.println("Orignal: " + matcher.group("original") + " found at: " + matcher.start("original"));
			System.out.println("Duplicate: " + matcher.group("duplicate") + " found at: " + matcher.start("duplicate"));
			System.out.println();	// accessing the group details using group name in Java
		}
		
		// example: usage with String methods
		regex = "\\b(?<original>\\w+)\\s\\k<original>";
		String correctedStmt = statement.replaceAll(regex, "${original}");	// captured-reference using group name syntax is ${'name'}
		System.out.println("----Corrected Statement----");
		System.out.println(correctedStmt);
	}
	
	private static void justFirstMatched() {
		String sampleStr = "Marcelo-12 Modric-10 Ronaldo-07 Ramos-04 Kroos-08 Casemiro-14 Madrid";
		
		// replace only first number with XX
		String regex = "(^.*?)(\\d+)";	// two groups, will keep $1 and discard $2
										// the idea is to consume all literals between the beginning of the line and first match in one
										// group and the first match in another group and then discard this matched group. Since the regex
										// requires beginning of the line to be consumed in one group so no other occurrences'll b matched
		String result = sampleStr.replaceAll(regex, "$1XX");
		System.out.println(result);
		
		// replace all except the first number with XX
		regex = "(^.*?\\d+)?(.*?)(\\d+)";	// three groups where group1 being optional which means $1 may be empty string.
											// the idea here is to capture a group from beginning of line to including first match
											// and make this group match as optional using '?' and then for two more non-optional groups
											// where one will consume the literals between two consecutive match and another will be the 
											// match that needs to be replaced.
		result = sampleStr.replaceAll(regex, "$1$2XX");
		System.out.println(result);
	}
	
	private static void justLastMatched() {
		String sampleStr = "Marcelo-12 Modric-10 Ronaldo-07 Ramos-04 Kroos-08 Casemiro-14 Madrid";
		
		// replace only last number with XX
		String regex = "(.*\\D)(\\d+)(.*$)";	// three groups, will keep $1 and $3 and will discard $2
												// the idea here is to consume everything greedily in a group till the last occurrence of
												// set of digits. Consume the set of digits in another group and then consume remaining
												// string in another group and then discard the group that has digits.
		String result = sampleStr.replaceAll(regex, "$1XX$3");
		System.out.println(result);
		
		// replace all except the last number with XX
		regex = "(\\d+)(?=.*\\d{2})";	// using positive look ahead to assert that a set of digits occurs in string anywhere ahead.
		result = sampleStr.replaceAll(regex, "XX");
		System.out.println(result);
	}
	
	private static void nthMatched() {
		String sampleStr = "Marcelo-12 Modric-10 Ronaldo-07 Ramos-04 Kroos-08 Casemiro-14 Madrid";
		
		// replace the number at 4th occurrence with XX.
		String regex = "((?:.*?\\d+){3})(.*?)(\\d+)(.*$)";	// total five groups of which one is non-capturing. discard $3.
		// decoding regex
		/*
		 * so in total there are four groups: ((?:.*?\\d+){3})	(.*?)	(\\d+)	and (.*$)
		 * ((?:.*?\\d+){3}) => where .*?\\d+ means consume any literals('.') any numbers of times('*') as less as possible('?') 
		 * 						till set of digits('\d+') and match this whole(?:) pattern thrice('{3}') and then consume this
		 * 						complete match in a group(complete-match). After this group is consumed your next match is the 4th match.
		 * (.*?)(\\d+)		=> where (.*?) means capture the next match which is 4th match literals lazily followed by set of digit 
		 * 						which is given by (\\d+).
		 * (.*$)			=> and lastly consume everything after the last matched which was 4th to the end of the string in a group.
		 */
		String result = sampleStr.replaceAll(regex, "$1$2XX$4");
		System.out.println(result);
		
		// given N, replace at Nth position.
		int N = 4;	// given
		String strN = String.valueOf(N-1);	// Note: String.valueOf(N) internally uses Integer.toString(N) so there is no difference
											// performance wise but one should use String.valueOf(x) so that later if tpye of x is changed
											// from int to any other say boolean then you don't need to make any change here.
		String buildRegex = "((?:.*?\\d+){" + strN + "})(.*?)(\\d+)(.*$)";
		String res = sampleStr.replaceAll(buildRegex, "$1$2XX$4");
		System.out.println(res);
		
		// replace all except 4th occurrence
		/*
		 * Not possible with pure regex and will require support from programming language.
		 * Possible only if the exact number of total occurrences is known.
		 */
		// Following is a way to replace all except nth occurrence with programming language support
		// N = 4, given above
		// sampleStr, given above
		regex = "(\\d+)";
		Matcher matcher = Pattern.compile(regex).matcher(sampleStr);
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			count++;
			if(count!=N) {
				matcher.appendReplacement(sb, "XX");	// this method keeps adding the main string to sb as it is and replaces the 
			}											// matched group with given "XX".
		}
		matcher.appendTail(sb);						// and finally after the last group match the remaining main string is appended here
		System.out.println(sb);
	}
}
