package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * There exists exactly one in-built regex library in Java called java.util.regex
 * which contains classes named Pattern and Matcher to do regex operations.
 * 
 * Use of this library is more advance and efficient than the in-built string methods described in StringMethodsRegex.java
 * 
 * Notable methods of Pattern and Matcher classes:
 * Pattern.compile("regex")	// compile() is a static method that compiles the given regex and returns a pattern object so 
 * 							// no need to create an object of Pattern using its constructor. Just call compile() using class name. 
 * 
 * Pattern.matcher(string)	// matcher() is not a static method and so needs to be called using the pattern object returned by
 * 							// static call on compile() above. The method matcher() returns a Matcher object and this is the only
 * 							// possible way to create a Matcher object as its constructors are hidden (private). This implies, to create
 * 							// a matcher object one has to create a pattern object first. This matcher object then can be used to do 
 * 							// required regex ops.
 * 
 * Pattern.matches("regex", "string")	// This method is exactly same as String.matches and in-fact String.matches internally calls 
 * 							// Pattern.matches itself. The method matches("regex", "string") is a static method of Pattern class and so no
 * 							// need to create Pattern object using compile. Although, the implementation of this method does compile the
 * 							// pattern. This makes it less efficient in cases where same pattern needs to be matched with multiple strings.
 * 							// Since str.matches("regex") method also uses this method so that too is just as efficient as this one. 
 * 							// String matches() is called on a string with regex as argument whereas Pattern matches() is called using a
 * 							// a static call with string and regex both as argument. There is absolutely no difference performance wise and
 * 							// so one doesn't have preference over another. Readability wise String matches() is more understandable.
 * 
 * Matcher.matches()		// This one is a non static public method. Which means that we need object of Matcher to call this method.
 * 							// Matcher's constructors are hidden and the only way to create matcher object is to create using Pattern object.
 * 							// This method uses the compiled pattern object to match the string. Its a validation method and so it returns
 * 							// true or false depending on if the whole string follows the given compiled regex pattern.
 * 							// Unlike str.matches(regex) and Pattern.matches(regex, string), matcher.matches() doesn't require to compile
 * 							// regex at each call and so its significantly efficient to use this when same regex needs to be re-used for
 * 							// multiple strings.
 * 
 * Matcher.find()			// returns true if, and only if, a substring of the input string matches given regex pattern.
 * 
 * Matcher.group()			// returns a substring of the input string. This substring is the one that matched the given regex pattern.
 * 							// group() or group(0) will return full match. group(i) will return the match in group i where 1<=i<=n.
 * 
 * 
 * 
 * SideNote: Pattern Matcher classes are serializable classes and Matcher is not a thread safe class.
 * Also, there is no method to count the total number of matches. [groupCount() counts the number of groups in the regex pattern].
 * To know the total number of matches keep calling find() and increment count on every successful match.
 * Refer this on match count: https://stackoverflow.com/questions/7378451/how-can-i-count-the-number-of-matches-for-a-regex
 * Reference: https://docs.oracle.com/javase/7/docs/api/index.html
 * Reference with examples: https://twiserandom.com/java/regex/regex-in-java-part-two-the-matcher-class/
 *
 */
public class AboutPatternMatcher {

	public static void main(String[] args) {
		
		// Pattern Matcher Example Demos
		
		// matcher.matches()
		Example1();
		
		// matcher.lookingAt()
		Example2();
		
		// matcher.find() and region methods
		Example3();
		
		// matcher.group() groupCount() start() and end()
		Example4();
		
		// Handling overlapping matches
		Example5();
		
		
		/* Few more interesting and possibly useful methods to explore:
		 * 1. requireEnd()
		 * 2. quoteReplacement(String s)
		 * 3. reset(CharSequence input): Resets this matcher with a new input sequence. When same pattern needs to be run on a different
		 * 						input then instead of creating a new matcher object using pattern.matcher one should prefer using existing
		 * 						matcher object using this reset() method. So that there will be at least one less object for gc to clean.
		 * 4. useTransparentBounds(boolean): When creating a regex we can use look around constructs to check if there is a pattern before
		 * 										or after the regex we are trying to match without capturing this pattern. If we pass true 
		 * 										to this method it will allow the look around modifiers to see beyond the set boundaries and
		 * 										if we pass false it will not allow them to see beyond the boundaries. By default, a matcher
		 * 										uses opaque bounds.
		 * 5. useAnchoringBounds(boolean): When the start and end region is set and we want them to act as anchoring regions so the start 
		 * 									of the region will be matched by the '^' meta character and the end of the region will be matched
		 * 									by the '$' meta character, we can use the useAnchoringBounds method. If we pass true to this
		 * 									method, this means that the region bounds will act as anchoring bounds and if we pass false this
		 * 									means they will not. By default, a matcher uses anchoring region boundaries.
		 * Ref: https://twiserandom.com/java/regex/regex-in-java-part-two-the-matcher-class/
		 * Ref: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html
		 */
	}

	/**
	 * matcher.matches() checks if the given entire test String satisfies the given regex pattern
	 */
	private static void Example1() {
		String regex = "^[a-zA-Z][a-zA-Z0-9_]*@[a-zA-Z0-9]+\\.[a-zA-Z]{1,3}"; // regex to check valid email
		
		String test1 = "amansoni77@gmail.com";
		String test2 = "77amansoni@gmail.com";
		
		Pattern pattern = Pattern.compile(regex);	// Compile the pattern just once to save time
		
		Matcher matcher = pattern.matcher(test1);	// Run the compiled pattern on test1
		System.out.println(matcher.matches());
		System.out.println(matcher.matches());
		System.out.println(matcher.matches());
		
		matcher = pattern.matcher(test2);			// Run the compiled pattern on test2
		System.out.println(matcher.matches());
		System.out.println(matcher.matches());
		System.out.println(matcher.matches());
	}
	

	/**
	 * matcher.lookingAt() returns true if, and only if, a prefix of the test String matches given regex pattern
	 */
	private static void Example2() {
		String regex = "^[a-zA-Z][a-zA-Z0-9_]*@[a-zA-Z0-9]+\\.[a-zA-Z]{1,3}"; // regex to check valid email
		
		Pattern pattern = Pattern.compile(regex);	// Compile the pattern just once to save time
		
		String test1 = "amansoni77@gmail.com";
		Matcher matcher = pattern.matcher(test1);	// Run the compiled pattern on test1
		System.out.print(matcher.matches() + "\t");
		System.out.print(matcher.lookingAt() + "\t");
		System.out.println(matcher.lookingAt());
		
		String test2 = "77amansoni@gmail.com";
		matcher = pattern.matcher(test2);			// Run the compiled pattern on test2
		System.out.print(matcher.matches() + "\t");
		System.out.print(matcher.lookingAt() + "\t");
		System.out.println(matcher.lookingAt());
		
		String test3 = "amansoni77@gmail.commerce";
		matcher = pattern.matcher(test3);			// Run the compiled pattern on test3	// suggestion: use matcher.reset() instead
		System.out.print(matcher.matches() + "\t");
		System.out.print(matcher.lookingAt() + "\t");
		System.out.println(matcher.lookingAt());
		
		// there doesn't exist a case where matches() is true and lookingAt() is false.
	}
	

	/**
	 * matcher.find() returns true if, and only if, a subsequence of the input sequence matches given regex pattern.
	 * matcher.find() looks for substring in the main string in a region. By default that region is from 0th index to last index of
	 * given string (basically whole string). This region can be modified using the region methods of the matcher object.
	 * On successful match (i.e..when matcher.find() returns 'true') more information can be obtained using the matcher methods.
	 * If matcher.find() returns 'false' and then group() or similar dependent methods are invoked then it throws IllegalStateException. 
	 */
	private static void Example3() {
		String testString = "lion#camel#cat#tiger#giraffe#bat#dog#caterpillar#elephant";
		
		String regex = "[bc]at";	// regex to look for cat or bat in the string
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(testString);
		System.out.println(matcher.find());		// "lion#camel#CAT#tiger#giraffe#bat#dog#caterpillar#elephant"
		System.out.println(matcher.find());		// "lion#camel#cat#tiger#giraffe#BAT#dog#caterpillar#elephant"
		System.out.println(matcher.find());		// "lion#camel#cat#tiger#giraffe#bat#dog#CATerpillar#elephant"
		System.out.println(matcher.find());		// no more substring matches the given regex pattern
		
		testString = "12345689";
		matcher = pattern.matcher(testString);	// Suggestion: use reset() instead so as to produce less garbage
		System.out.println(matcher.find());
		System.out.println(matcher.find());
		
		// region methods
		testString = "lion#camel#cat#tiger#giraffe#bat#dog#caterpillar#elephant";
		matcher = pattern.matcher(testString);	// Suggestion: use reset() instead so as to produce less garbage
		
		System.out.println("Default Region: " + matcher.regionStart() + "\t" + matcher.regionEnd()); // default region
		
		matcher.region(testString.length()/2, testString.length()); // modifying region to search pattern in second half of the string
		System.out.println("Modified Region: " + matcher.regionStart() + "\t" + matcher.regionEnd());	// modified region
	}

	/**
	 * matcher.groupCount() returns integer which is the count of total number of capturing groups in the given regex pattern.
	 * Which means this number is based on the regex pattern provided and not the input string. For a given regex pattern the 
	 * number of capturing group is fixed and no matter what input string you provide the number remains same. On successful match
	 * that is when find() returns true then all the captured groups are stored and can be retrieved using the group(int) function.
	 * Total number of groups in a pattern is equal to the total number of pairs of parenthesis. Lets say if a pattern has 3 groups
	 * and a successful match occurred then the whole match is stored in group(0) and the respective groups are stored in group(1)
	 * group(2) and group(3). So when you call groupCount() it will return 3. That means to retrieve all groups using a for loop the
	 * check condition of the for loop should be i<=groupCount() and not i<groupCount() else you will miss the last captured group.
	 * 
	 * When you call group() then internally group(0) is being called. So group() and group(0) are exactly same.
	 * 
	 * Consider below example of regex pattern: (#?) implies the group can be # or an empty string whereas  
	 *  										(#)? implies the group consists of a # or the group doesn't exist at all.
	 * In the second case where the group doesn't exist the respective group(int) returns null. For first case it will return empty string.
	 */
	private static void Example4() {
		String testString = "lion#camel#cat#tiger#giraffe#bat#dog#caterpillar#elephant";
		
		String regex = "([bc]at)(#)?([a-z])";	// regex to look for cat or bat maybe followed by # then followed by one letter
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(testString);
		
		while(matcher.find()) {
			System.out.println(matcher.group());	// print whole match. whole match is stored in group(0).
			System.out.println(matcher.groupCount());	// total number of groups in the given pattern
			for(int i=0;i<=matcher.groupCount();i++) {	// note: i<=matcher.groupCount() so as to not miss the last captured group
				System.out.print(matcher.group(i) + "\t");	// print individual groups 
			}
			System.out.println("\n--------------------------------");
		}
		
		// start() end()
		matcher = Pattern.compile("cat").matcher(testString);	// look for occurrences of "cat" in the given string
		System.out.println("--------------------------------");
		while(matcher.find()) {
			System.out.println("Found at index: " + matcher.start() + "\tEnding at: " + matcher.end());
			// above line prints the start and end index of the substring that matched.
			
			// If the matched substring consists of groups then to know at what index a group was captured call the start(int) and end(int)
			// methods with integer argument where the integer represents the group number. 
			// Since the whole captured substring is stored in group(0) calling start() or start(0) will yield same result always.
		}
		
	}
	
	/**
	 * There is no default method to handle overlapping matches. 
	 * By default the overlapping matches are not recognized.
	 * The fundamental technique to recognize overlapping matches is to start the next match search from the second index
	 * of the previous successful match start.
	 * Refer:  https://stackoverflow.com/questions/14008897/why-doesnt-usetransparentbounds-search-outside-matchers-region
	 */
	private static void Example5() {
		String testString = "AdidasCosCoscoNikeReebokNiviaKipsta".toLowerCase();
		
		String regex = "cosco";			// Look for the company name 'cosco'
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(testString);
		
		int count = 0;
		while(matcher.find()) {
			count++;	// count number of matches
		}
		System.out.println(count);
		
		// Count overlapping matches
		count = 0;
		int i = 0;	// index
		while(matcher.find(i)) {	// find(i) means to look for match starting from index i in given input string
			count++;				// by default i.e.. when we call find() it looks from the 0 or last index of previous matched.
			i = matcher.start() + 1;// update i to start the next search from the second index to the start of last matched.
		}
		System.out.println(count);
	}
	
}
