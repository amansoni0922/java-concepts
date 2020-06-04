package concept.complexity.time;

/**
 * Like we have inverse of addition as subtraction and inverse of multiplication is division similarly we have inverse of exponentiation
 * as logarithm. In math, denoted by log which implicitly means log to base 10 and ln is used to denote log to base e aka natural log.
 * 
 * In computer science log by default means log to base 2 which is also denoted as lb.
 * 
 * To sum up, following is how different types of logs are denoted:
 * => Natural Log 	= ln = log to base e	(Natural / Euler)
 * => Common Log	= lg = log to base 10	(Mathematical)
 * => Binary Log	= lb = log to base 2	(Computer Science)
 * 
 */
public class LogarithmicTimeComplexities {

	public static void main(String[] args) {

		// Consider the following code:
		int N = 100;
		for (int i = 1; i < N; i = i * 2) {
			System.out.print(i + " ");	// code-block
		}
		System.out.println("\n-----------------------------------");
		/*
		 * In above, the code-block will run for i=1,2,4,8,16,32,64 which is in total 7 times.
		 * If N=1000, it would have run for 10 times. So the number of times code-block run is proportional to N.
		 * The loop variable i is doubled every iteration. We need to find how many times this doubling of loop variable i is required
		 * for it to be equal to or greater than N. Mathematically speaking, what power 2 must be raised to make i >= N. 
		 * Lets say its x. Then 2^x >= N. From math, x = logN, where log is to base 2. Log to base 2 is also written as lb.
		 * 
		 * Now the code-block will run once for i=1 and then will run for every doubling until it reaches or crosses N. 
		 * Which gives us run-time = 1 + lbN = 1 + log of N to base 2 = 1 + 6.6438 = 7.6438 = 7 (truncate fractional part).
		 * In the equation 1+lbN used for calculating frequency of code-block we just pick the highest growing part. In this case 1
		 * remains constant and there is just one growing part so the time complexity will be written as lbN aka logN by default in CS.
		 */
		
		
		// Consider the following code:
		for (int i = 1; i < N; i = i * 5) {
			System.out.print(i + " ");	// code-block
		}
		/*
		 * Here, the loop variable is being multiplied by 5. So here the code-block will run once for i=1 and then will run for every
		 * fifth multiple until it reaches or crosses N. Which gives run-time = 1 + log of N to base 5 = 1 + 2.8613 = 3.8613 = 3
		 * (truncate fractional part). So time complexity = highly growing variable in (1 + log of N to base 5) = log N to base 5.
		 * Now using different log bases for time complexity will make it difficult to make a comparison. So we convert the base 5 to 2.
		 * log N to base 5 = (log N to base 2) * (log 2 to base 5). Here, (log 2 to base 5) is a fixed constant value = 0.4306 so we
		 * drop it and finally the complexity can be written as just logN (log N to base 2).
		 * 
		 * Now its easy to compare different complexities but if you observe we see that for i*2 and i*5 both the complexity is same.
		 * This is desirable because we are interested in how the algorithm performs as the input grows and we are not interested in the
		 * individual time complexity for a given set of inputs. Both of the above loops perform same as the input grows and so their
		 * complexities are also same.
		 */
		
	}

}
