package concept.regex;

/**
 * 
 *
 */
public class ExtremeRegexFormulae {

	public static void main(String[] args) {

		
		/*negate set 
		 * https://regex101.com/r/oXfIXX/1
		 * 
		 */
		
		/*greedy non-greedy
		 * https://stackoverflow.com/questions/3075130/what-is-the-difference-between-and-regular-expressions
		 * \d{1,3}?
		 */
		
		/*
		 * non-capturing
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
		
		/*
		 * powerful use of back reference
		 */
		
		/*find duplicates
		 * 
		 * refer: https://www.youtube.com/watch?v=87PJH2cJLX0
		 * 
		 */
		
		// conditional regex 
		
		// (?>) - atomic group
		
	}

}
