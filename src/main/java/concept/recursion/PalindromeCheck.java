package concept.recursion;

public class PalindromeCheck {

	public static void main(String[] args) {
		
		System.out.println(isPalindrome("racecar"));	// true
		
		System.out.println(isPalindrome("raceecar"));	// true
		
		System.out.println(isPalindrome("a"));			// true
		
		System.out.println(isPalindrome("ab"));			// false
		
		System.out.println(isPalindrome(""));			// true
		
		System.out.println(isPalindrome("collection"));	// false
		
	}

	private static boolean isPalindrome(String string) {
		if(string.length()<=1) {	// termination condition on when to return true
			return true;
		}
		
		if(string.charAt(0)==string.charAt(string.length()-1)) {	// condition to recurse
			return isPalindrome(string.substring(1, string.length()-1));
		}
		
		return false;	// termination condition on when to return false
	}
}

