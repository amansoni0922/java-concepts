package concept.regex;

/**
 * 
 * Basic/fundamental regex operations can be achieved using the built-in string methods
 * There are four such built-in string methods that support regex:
 * 1. str = str.replaceAll("regex", "replacement")		// as we have seen in VowelsCount.java
 * 2. str = str.replaceFirst("regex", "replacement")	// replaces just first matched
 * 3. boolean res = str.matches("regex")				// returns true only if the whole string can be matched
 * 4. String s[] = str.split("regex")					// splits string on "regex" and returns array of strings
 * 
 * 
 * Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 */
public class StringMethodsRegex {

	public static void main(String[] args) {
		
		// matches("regx")
		String regx = "^[a-zA-Z][a-zA-Z0-9_]*@[a-zA-Z0-9]+\\.[a-zA-Z]+";
		System.out.println("greenstar180@gmail.com".matches(regx));		// true
		System.out.println("180greenstar@gmail.com".matches(regx));		// false
		
		
		// split("regex")
		String regex =  "@|\\.";	// "[@\\.]" will also work 
		String str[] = "greenstar180@gmail.com".split(regex);
		System.out.println("ID: " + str[0] + "\nDomain: " + str[1] + "\nSubdomain: " + str[2]);
		
	}

}
