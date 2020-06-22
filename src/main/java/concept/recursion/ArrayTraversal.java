package concept.recursion;

/**
 * Traversing array using recursion. 
 */
public class ArrayTraversal {

	public static void main(String[] args) {
		
		int arr[] = {10, 20, 30, 40, 50, 60, 70, 80};
		
		// traversing from left to right
		leftToRight(arr, 0);
		
		System.out.println();
		
		// traversing from right to left
		rightToLeft(arr, arr.length-1);
		
		// find sum of all array elements using recursion
		int sum = sum(arr, 0, arr.length-1);
		System.out.println("\n" + sum);
		
		// well formed recursion construct for sum
		int sSum = sophisticatedSum(arr, 0);
		System.out.println(sSum);

	}

	private static void leftToRight(int[] arr, int leftIndex) {
		if(leftIndex>=0 && leftIndex<arr.length) {
			System.out.print(arr[leftIndex] + " ");
			leftToRight(arr, leftIndex+1);
		}
	}

	private static void rightToLeft(int[] arr, int rightIndex) {
		if(rightIndex<arr.length && rightIndex>=0) {
			System.out.print(arr[rightIndex] + " ");
			rightToLeft(arr, rightIndex-1);
		}
	}
	
	private static int sum(int[] arr, int start, int end) {
		if(start>end) {
			return 0;	// recursion termination condition
		}
		return arr[start] + sum(arr, start+1, end);
	}
	
	private static int sophisticatedSum(int arr[], int start) {
		int sum = 0;
		if(start<arr.length)	// recursion termination condition
			sum = sum + arr[start] + sophisticatedSum(arr, start+1);
		
		return sum;	// have exactly one return statement. avoid multiple return statements.
	}

}
