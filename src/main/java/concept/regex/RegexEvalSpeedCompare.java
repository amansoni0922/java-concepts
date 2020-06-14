package concept.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex is a whole engine in itself that works on a given string to look for a pattern that we have described using regex constructs.
 * Like any regular programming language in regex also the pattern can be interpreted or compiled before it gets processed by the engine.
 * Java is a compiled language so in Java regex are compiled before being processed by the engine. Compiling regex has advantages like
 * speed and re-usability. Online regex platforms like regex101 are examples of regex where the pattern is not compiled but interpreted.
 * Interpreting the pattern perhaps slow but is useful for these platforms as users can see the results at every change they make to 
 * regex pattern in pattern box. From user experience perspective having to compile the pattern and run would have been a lot time taking
 * and so is not apt in such use cases. Where as in Java where performance is priority compiling is significantly better.
 * 
 * Anywhere in Java where the string argument supports regex the call can be traced back to methods of Matcher class.
 * For: "some str".matches("regex") 
 * 									calls Pattern.matches(regex, this) internally that again
 * 									calls m.matches() after creating the respective pattern matcher objects
 * 
 * So all the methods whose calls can be traced back to Matcher require to create Pattern Matcher objects. By creating the pattern
 * object we are actually compiling the pattern and Matcher object is used to match using the compiled pattern. Since at each call
 * new pattern matcher objects are created so this results in recompiling of pattern again and again. Pattern compiling is an expensive
 * task and if possible should be reduced. It cannot be avoided where every time a different pattern needs to be matched but it can be
 * avoided in places where same pattern needs to be matched against multiple strings. To do so we can directly use the pattern matchers.
 * 
 * 
 * Reference: https://stackoverflow.com/questions/2469244/whats-the-difference-between-string-matches-and-matcher-matches
 */
public class RegexEvalSpeedCompare {

	/**
	 * Given 100 Strings, count the number of strings that has at least one special character.
	 * Where special character need to look for can be '%', '#', '&'. 
	 */
	public static void main(String[] args) {
		
		// 100 random strings
		String[] arr = {"oyypplb", "xmnff%n", "jdtx", "jgqlhw%t", "ukaqb", "jccpi", "sgwc", "fyo%x", "x#", "gwmne", 
				"uni&", "jciwy", "gkmpk", "efrd%du", "nubnhrva", "mojag%ff", "ctg&h", "nvkkya", "ekmp&cs", "ttad%h", 
				"qjpxiok", "eyl", "mabd", "rghtk&ss", "fam", "pwxswh&b", "drwlnfu", "sifc&m", "wdutrs", "rb%c", 
				"c%r", "ndmce%", "cvvfa%f", "ymex&bm", "&a", "fvpg", "pp&m", "nwsh&", "vft%ln", "sg%o", 
				"xwiv#bx", "qfx", "vvkqss", "vpebmmd", "er%m", "vxrt", "clksvd", "twgo%k", "camaxvkon", "div", 
				"fxdvi#", "oknjic&", "aqn", "upxo&", "mhfr%d", "kj#h", "ffj%om", "ekayvgbhr", "r&", "x&js", 
				"eee&", "mw", "tigkm#", "o", "sv%", "p", "kbck", "dbsyf", "yd&u", "ddasjdsad&dsjda##jhdajsd%jnjsad%",  
				"hdkbr", "qyc", "pl", "q", "xfsrx", "m%", "kltdx", "edgy&sr", "bkfdk%it", "h", 
				"bqlqa", "eo&", "be%q", "gghf", "vad", "rrhv", "qniy", "q", "qn&wi", "wapx%kf", 
				"egtxra%u", "mjo&b", "sr", "vlyet&g", "tykdnxg%tj", "acbub", "qjrku", "iax&or", "nau", "dmrdt" };
		
		
		// regex to match. Returns true if the string contains any of '%', '#', '&' else returns false.
		String regex = "([a-z]*[%#&]+[a-z]*)+";
		
		
		// Using str.matches(regex)
		long start = System.currentTimeMillis();
		usualWay(arr, regex);	// function call Usual way
		long end = System.currentTimeMillis();
		long usualWay = end-start;
		
		// Using matcher.matches()
		start = System.currentTimeMillis();
		efficientWay(arr, regex);	// function call Efficient way
		end = System.currentTimeMillis();
		long efficientWay = end-start;
		
		System.out.println("Usual way time taken: " + usualWay + " milliseconds");
		System.out.println("Efficient way time taken: " + efficientWay + " milliseconds");
		System.out.println("Difference: " + (usualWay-efficientWay) + " milliseconds");
		System.out.println("Efficient way is atleast " + (usualWay/efficientWay) + " times faster than Usual way in this case");
		
	}

	
	private static void usualWay(String[] arr, String regex) {
		// we run the same computation 1000 times
		for(int i=0;i<1000;i++) {
			int count = 0;
			for(String s: arr)
				if(s.matches(regex))	// here every call to matches() requires to compile the pattern 
					count++;
					
			System.out.println(count);	// hence total number of times pattern compiled here is 1000*100 times
		}
	}

	private static void efficientWay(String[] arr, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("");
		
		// we run the same computation 1000 times
		for(int i=0;i<1000;i++) {
			int count = 0;
			for(String s: arr) {
				matcher.reset(s);		// just reset the string to be matched
				if(matcher.matches())	// here the already compiled pattern is being re-used
					count++;
			}
			System.out.println(count);	// hence total number of times pattern compiled here is exactly 1 time.
		}
	}

}
