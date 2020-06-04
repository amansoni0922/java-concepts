package concept.complexity.time;

/**
 * Run-time performance of an application depends on its code. If put more technically we say depends on the algorithms used in the code.
 * The run-time of an algorithm may vary from machine to machine, language to language and several other unpredictable/uncertain factors.
 * To compare the run-time performance of two algorithms we need to know how the algorithm performs for various input keeping all the other
 * factors constant (same language, same machine). Keeping all other factors constant is not quite practical. All we want to know is 
 * how the code performs for the given set of inputs irrespective of the environment.
 * 
 * Thats where the concept of run-time complexities comes in. In computer science run-time complexities or in short time complexities
 * for a given code tells you how the run time grows as the size of the input grows. In simple words, it tells you the relation between
 * the input N and run-time T where T is not in seconds but T just means the number of repetition of a code block aka iterations. 
 * This relation between T and N are proportional which means as N grows then T also grows in proportion according to the relation. It 
 * doesn't tell anything about time in seconds it will take aka execution time and is independent of any external factors like machine 
 * or programming language. Time complexity T is measured in iterations whereas execution time R or ET is measured in milliseconds.
 * 
 * Common run-time complexities in order of their performance are:
 * Time Complexity			Name			Example
 * 		O(1)			Constant			HashMap
 * 		O(logN) 		Logarithmic			Binary Search
 * 		O(N) 			Linear				Linear Search 
 * 		O(NlogN) 		Linearithmic		Merge Sort
 * 		O(N^c) 			Polynomial			Ordinary Sort / Matrix Operations
 * 		O(c^N) 			Exponential			Power Sets
 * 		O(N!)			Factorial			Permutations
 *
 * 
 * Though there exist many more but not so common like: 
 * => O(N^(1/c)) happens when loop variable is raised to fractional power like square-root, cube-root
 * 				 NT Graph for this one is similar to logarithmic.
 * => log(logN)	 happens when loop variable is raised to non-negative integer power.
 * 
 * => log^cN     happens when individual logarithmic loops are nested. For ex: a loop of logN is nested inside another loop of logN
 * 				 then its total complexity = logN*logN = log^2N (log square N)
 *
 * Reference: geeksforgeeks for examples, calculation, graphs and explanation.
 *
 */
public class CommonTimeComplexities {

	public static void main(String[] args) {
		
		// https://www.geeksforgeeks.org/analysis-algorithms-set-5-practice-problems/
		
		// simple nlogN example
		
		// simple Nlog^2N (N log square N) example
		
		// a good c^N example (finding power sets)
		
		// a good N! example (finding permutations)
		
	}

}
