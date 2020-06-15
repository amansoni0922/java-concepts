package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple regex pattern defines a general form for the substring that we are looking for in the input string. For instance,
 * consider regex pattern "[bc]at#?[0-9]{2}" which means to look for 'cat' or 'bat' may be followed by a '#' then followed by
 * exactly 2 digits. Any substring of the input string of this form is called a match. So basically a match is a substring of
 * the input string which follows the given simple regex pattern.
 * For input string: "bat#1234"			match = "bat#12"
 * For input string: "xyxyxycat#12456"	match = "cat#12" 
 * 
 * There are times when we are looking for a substring in the input string and there exists more than one match. But we are only
 * interested in a specific match and the criteria to determine that specific match depends on its surroundings. So now we are
 * looking for a specific match which is dependent on its surroundings but we don't want that surrounding to be part of our 
 * matched substring. To achieve this we need to go beyond simple regex and use the concepts of look arounds in the regex.
 * 
 * Regex provides constructs to look around the matched pattern on either side of the matched substring. To assert a surrounding
 * pattern that occurs before the match we use look-behind construct. In simple words when look-behind construct is used the regex
 * engine will look for pattern match first and if found then it will analyze the string preceding the match and if satisfied by
 * the pattern given in the look-behind construct then it considers this match as a successful match. Similarly for look-ahead
 * constructs it analyzes the string after the match. One thing to note here is that the look-behind constructs are given before
 * the main regex pattern. Similarly, look-ahead constructs are given after the main pattern.
 * 
 * In cases where there are multiple look ahead and look behind constructs then their ordering gets little fuzzy. 
 * Refer example by Max Shawabkeh in https://stackoverflow.com/questions/2126137/regex-lookahead-ordering
 * 
 * If your specific match depends on the string that follows the match then we use LookAhead.
 * If your specific match depends on the string that precedes the match then we use LookBehind.
 * 
 * Look-Around constructs:			Negative form:
 * Look-Ahead 	=>	 (?=regex)		(?!regex) 
 * Look-Behind 	=>	 (?<=regex)		(?<!regex)
 * 
 * References: 
 * 1. https://www.regular-expressions.info/refadv.html
 * 2. https://www.youtube.com/watch?v=DeIWR4gv1-8 (Regex Lookarounds - MidnightDBA)
 * 3. https://www.youtube.com/watch?v=Lp9emr81tdw (REGEX: Use Lookbehind for PRINT Statements - MidnightDBA)
 * 4. https://www.regular-expressions.info/lookaround.html
 * 5. https://javascript.info/regexp-lookahead-lookbehind
 * 6. [MUST READ] for look arounds ordering: https://stackoverflow.com/questions/2126137/regex-lookahead-ordering
 */
public class RegexLookArounds {

	public static void main(String[] args) {
		
		// look ahead example
		LookAhead();
		
		// look behind example
		LookBehind();
		
		// negative look ahead example
		NegativeLookAhead();
		
		// negative look behind example
		NegativeLookBehind();
		
	}

	/**
	 * Given a list of phone numbers with country codes determine if the phone number is a valid phone number. A valid phone number
	 * is exactly 10 digits long. If phone number is valid then print its country code. Country code can be 1 to 3 digits long.
	 * 
	 * Positive Look Ahead syntax is: X(?=Y), it means "look for X, but match only if followed by Y"
	 */
	private static void LookAhead() {
		String numbers[] = {"919740467017", "46160861608", "1234488844888", "9199931509", "95432154321", "91987654321000"};
		
		String regex = "^\\d{1,3}(?=\\d{10}$)";	// where ^\d{1,3} is our main pattern that we want to match i.e.. the country code.
												// and (?=\\d{10}$) is our look ahead pattern which asks regex engine to assert a
												// match if its followed by 10 digits and then end of line.
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("");	// creating a dummy matcher for now so that later we will be using in loop.
		
		for(String s:numbers) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t is a valid phone number with country code " + matcher.group()); 
				// Note: group() is same as group(0) that stores complete match
			}
			else {
				System.out.println(s + "\t is not a valid phone number");
			}
		}
	}

	/**
	 * Given a list of e-mail IDs determine if the e-mail ID is a valid e-mail ID. If valid then find its sub domain address.
	 * Its sub domain address will consist of 1 to 3 letters.
	 * 
	 * Positive Look Behind syntax is: (?<=Y)X, matches X, but only if there’s Y before it.
	 */
	private static void LookBehind() {
		
		String emailIds[] = { "amans77@gmail.com", "77amans@gmail.com", "aman.soni@gmail.com", "green.star@yahoo.co", 
				"amansoni@wiki.org", "aman@lenovo", "aman@.com", "aman.soni.com"};
		
		String regex = "(?<=@\\w{1,10}\\.)\\w{1,3}";	// where \w{1,3} is our main pattern that we intend to match i.e.. 
														// the sub domain and (?<=@\\w+\\.) is our look behind pattern 
														// which asks regex engine to assert if this pattern exists
														// before our main pattern match. 
		// Note: Java doesn't support variable length in look behind and so we have changed look behind pattern from variable
		// length form to fixed length form by replacing \w+ with \w{1,10} 
		
		/*
		 * [IMP] Note also that in most (probably all) implementations, lookbehinds have the limitation of being fixed-length.
		 * You can't use repetition/optionality operators like ?, *, and + in them. 
		 * Refer: https://stackoverflow.com/questions/2126137/regex-lookahead-ordering
		 */
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("");	// dummy matcher to use later in our loop.
		
		for(String s: emailIds) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t is a valid email id with sub domain " + matcher.group());
			}
			else {
				System.out.println(s + "\t is not a valid email id");
			}
		}	
	}

	/**
	 * Given a list of file names determine if a file is a non-image file. If its a non-image file then print the file name.
	 * Consider jpeg and jpg to be the only image file formats remaining all as non-image files.
	 * 
	 * Negative Look Ahead syntax is: X(?!Y), it means "search X, but only if NOT followed by Y".
	 */
	private static void NegativeLookAhead() {
		String fileNames[] = { "avengers.mov", "sunrise.jpeg", "tulips.jpg", "recording123.mp4", 
				"beat it.mp3", "cat.gif.png", "random.jpg.jpeg.animation" };
		
		String regex = "([a-z\\d][\\w\\. ]*)\\.(?!jpg|jpeg)";	// where ([a-z\\d][\\w\\. ]*)\\. is our main pattern of which
															// the file name pattern to determine file name is enclosed in parenthesis
															// to capture it in group(1). Whole match will be captured in group(0).
		// decoding ([a-z\\d][\\w\\. ]*)\\.
		// [a-z\\d] => file name should start with a letter or digit
		// [\\w\\. ]* => followed by zero or any number of word character or "." or " ". Note: \w => [a-zA-Z0-9_]
		// () => parenthesis is used to capture the above pattern in a group
		// \\. => this above whole pattern is followed by a "."
		// (?!jpg|jpeg) =? Negative look ahead to ignore match if the above "." is followed by "jpg" or "jpeg"
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("");
		
		for(String s: fileNames) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t\t is a non-image file with file name: " + matcher.group(1));
			}
			else {
				System.out.println(s + "\t\t is an image file");
			}
		}
		
		// Below is a more simple example of negative look ahead to understand difference between immediate look. 
		NegativeLookAhead_SimpleExample();
	}

	private static void NegativeLookAhead_SimpleExample() {
		String input = "aabbccddeeffgghhiijjkk\n\n"
				 + "aabbcc123ddeeffgghhiijjkk\n\n"
				 + "aabb123ccddeeffgghhiijjkk\n\n"
				 + "aabbccddee123ffgghhiijjkk\n\n"
				 + "aabbccddeeffgghhiijjkk";
		
		String regex1 = "bb(?!\\d+)";	// Look for 'bb' but should not be followed by numbers immediately.
		String regex2 = "bb(?!\\w+\\d+)";	// Look for 'bb' but should not be followed by numbers anywhere ahead in the input string.

		Pattern pattern = Pattern.compile(regex1, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find())
		    System.out.println(matcher.group());
		
		System.out.println("--------------");
		
		matcher.usePattern(Pattern.compile(regex2, Pattern.MULTILINE));
		matcher.reset();	// this is essentially used to reset the position of the matcher's find() back to start and
		while (matcher.find())	// reset few other parameters used by Matcher class internally ex: group counts.
		    System.out.println(matcher.group());
	}
	

	/**
	 * Given a list of file names determine if a file name is a valid file name. A valid file name consists of letters digits and
	 * underscore followed by '.' followed by extension name of length 3 or 4. Extension name comprise of letters or digits.
	 * If a file name is valid then print its extension with '.' preceding it.
	 * 
	 * Negative look Behind syntax is: (?<!Y)X, matches X, but only if there’s NO Y before it.
	 */
	private static void NegativeLookBehind() {
		String fileNames[] = {"avengers.mov", "troy.movie", "video.mpeg", "sunrise.jpeg", "tulips.jpg", "recording123.mp4",
				"....beatit.mp3", "beat .it.mp3", "beat it.mp3", "cat.gif.png", "random.animation"};
		
		String regex = "(?<!\\W.{0,100})\\.[a-z0-9]{3,4}$";	// where \.[a-z0-9]{3,4}$ is our main pattern that we intend to match
														// i.e.. the extension name and (?<!\W.*) is our negative look behind
														// pattern which asks regex engine to assert that this pattern should
														// not exist before our main pattern match. 
		
		// Note: As look behind implementation doesn't support repetition/optionality operators like ?, *, and + so this results in
		// exception on pattern compile. To get around this we use variable length construct with fixed lengths provided.
		// So we replace (?<!\W.*) with (?<!\W.{0,100})
		
		// decoding (?<!\\W.{0,100})\\.
		// (?<!X) is negative look behind construct which will ignore match if found X pattern where X = \\W.{0,100}
		// \\W will consume a non word character
		// .{0,100} since dot '.' means consume any character. This pattern means to consume all characters at max 100
		// after a non word character consumed before. This is done so as to consume the characters and establish a match.
		// \\. means followed by a literal dot '.'
		// Limitation: this regex will fail to validate file names beyond 101 characters. Lets say if we have a file name
		// starting with '-a-' followed by 100 more 'a's then this regex won't work. 
		
		/*
		 * [IMP] Note: this is a good example where the above pattern works perfectly and as expected in Java program but doesn't
		 * work in regex101.com. As pointed out in the positive look behind also that the look behind pattern doesn't support meta
		 * characters like * + ? so this results in the inconsistency in the results among Java and regex101.
		 * 
		 * Moreover, this inconsistency is also due to the fact that the regex pattern is interpreted in regex101 while in Java
		 * the regex pattern in compiled which makes the Java regex highly accurate.
		 */
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("");
		
		for(String s: fileNames) {
			matcher.reset(s);
			if(matcher.find()) {
				System.out.println(s + "\t\t is a valid file name with extension: \t" + matcher.group());
			}
			else {
				System.out.println(s + "\t\t is not a valid file name");
			}
		}
	}

}
