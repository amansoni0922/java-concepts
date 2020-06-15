package concept.bitmanipulation;

import java.util.BitSet;

/**
 * A lot of times we are required to do some bit manipulations and bit operations like 'AND' 'OR' 'XOR'. 
 * BitSet is an in-built Java library to store, manipulate and operate over bits more efficiently and provides in-built methods
 * for bit operations. This class implements an array of bits that grows as needed. Each component of the bit set has a boolean 
 * value. The bits of a BitSet are indexed by nonnegative integers. Individual indexed bits can be examined, set, or cleared. 
 * One BitSet may be used to modify the contents of another BitSet through logical AND, logical inclusive OR and logical 
 * exclusive OR operations (XOR). By default, all bits in the set initially have the value false.
 * 
 * Length vs Size in BitSet:
 * Size of the BitSet is the memory it occupies whereas length of the BitSet is the logical length of the BitSet.
 * 
 * Reference: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/BitSet.html
 * 
 * A good replacement for boolean arrays.
 * Reference: https://stackoverflow.com/questions/605226/boolean-vs-bitset-which-is-more-efficient
 * 
 * One should consider using BitSet when:
 * 1. Bit operations and manipulations are needed.
 * 2. Boolean arrays. (BitSet is memory efficient as compared to boolean but is not time efficient)
 */
public class BitSetDemo {

	public static void main(String[] args) {
		
		// Lets create a bit set object:
		BitSet bs = new BitSet();
		
		// Lets try to print the empty bit set:
		System.out.println("Empty bs: " + bs);
		
		// Lets see the length of bit set:
		System.out.println("Length of bs is: " + bs.length()); // of course we are expecting 0 to be the length of bs
		
		// Lets see how much memory it occupied:
		System.out.println("Size: " + bs.size() / 8 + " Bytes");	// JVM allocates 8 Bytes by default to BitSet object
		
		System.out.println("----------------------------------------------");
		
		// Lets set some bits:
		bs.set(11);
		bs.set(6);
		
		// Lets print the bit set, its length and size again:
		System.out.println("bs: " + bs);
		System.out.println("Length: " + bs.length());
		System.out.println("Size: " + bs.size() / 8 + " Bytes");
		
		System.out.println("----------------------------------------------");
		
		// 8 bytes = 64 bits, lets set bits beyond 64 bits and see what happens:
		bs.set(90);
		System.out.println("bs: " + bs);
		System.out.println("Length: " + bs.length());
		System.out.println("Size: " + bs.size() / 8 + " Bytes");
		
		/*
		 *  Above we observed that it auto expanded the bit set size
		 */
		
		System.out.println("----------------------------------------------");
		
		// Lets print the individual bits:
		System.out.println("Bit at index 10: " + bs.get(10));
		System.out.println("Bit at index 11: " + bs.get(11));
		
		/*
		 *  Above we observed that bit set uses true/false to represent individual bits.
		 *  So this can be used as a memory efficient replacement for boolean arrays. Though, speed wise this won't be efficient.
		 *  Refer answer by 'tagore84' on: https://stackoverflow.com/questions/605226/boolean-vs-bitset-which-is-more-efficient
		 */
		
		System.out.println("----------------------------------------------");
		
		// bit set can be used in streams also:
		System.out.println("Print stream of indexes of set bits:");
		bs.stream().forEach(System.out::println);
		
		/*
		 * There are lot other methods that are quite useful like: clear, set, next..., previous..., flip, etc.
		 */
		
		System.out.println("----------------------------------------------");
		
		// Lets create bit set with given number of bits:
		int n = 5;
		BitSet bits1 = new BitSet(n);
		
		// Lets print the bit set, its length and size:
		System.out.println("bits1: " + bits1);
		System.out.println("Length: " + bits1.length());
		System.out.println("Size: " + bits1.size() / 8 + " Bytes");
		
		/*
		 * What we observe is that giving the length n doesn't really matter.
		 * It might have some implication on JVM level but not much sense on higher level.
		 */
		
		System.out.println("----------------------------------------------");
		
		// Lets set some bits:
		bits1.set(3);
		bits1.set(5);
		
		// Since this class is cloneable, we can create a copy of bit set:
		BitSet bits2 = (BitSet) bits1.clone();	// since clone() returns object of Object class we need to down cast it to BitSet
		
		// since bits1 and bits2 are same their xor would result in all clear state:
		bits1.xor(bits2);
		System.out.println("After XOR with its clone");
		System.out.println("bits1: " + bits1);
		System.out.println("bits2: " + bits2);
		
		System.out.println("----------------------------------------------");
		
		// Lets print the bit representation for given bit set:
		for(int i=0;i<bits2.length();i++)
			System.out.print(bits2.get(i)? 1:0);
		System.out.println();
		
		// Lets flip the first four bits and print again:
		bits2.flip(0, 4);
		for(int i=0;i<bits2.length();i++)
			System.out.print(bits2.get(i)? 1:0);
		System.out.println();
		
		/*
		 * [IMPORTANT] Observe that for given 0-4 the bits flipped are 0-3
		 */
		
		/*
		 * XOR has special properties and is the most asked in programming.
		 * Say we have input bit set inpB then:
		 * inpB XOR with 0 is reflexive. Meaning, the output will be exact same set of bits as input.
		 * inpB XOR with 1 is toggle. Meaning, the output will have all the bits toggled in input.
		 * 
		 * A bit XOR itself is 0. Meaning, the output is 0 if the bits are same. Matching.
		 * A bit XOR complement of itself is 1. Meaning, the output is 1 if the bits are different. Non-matching.
		 */
		
		System.out.println("----------------------------------------------");
		
	}
}
