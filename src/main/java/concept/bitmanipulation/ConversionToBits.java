package concept.bitmanipulation;

public class ConversionToBits {

	public static void main(String[] args) {
		
		int a=6, b=-6, c=0;
		
		String as = Integer.toBinaryString(a);
		String bs = Integer.toBinaryString(b);
		String cs = Integer.toBinaryString(c);
		
		System.out.println(as + "\t" + bs + "\t" + cs);
		
		//int var = 0;
		int min = Byte.MIN_VALUE; //Integer.MIN_VALUE-var;
		int max = Byte.MAX_VALUE; //Integer.MAX_VALUE+var;
		
		System.out.println(min);
		System.out.println(max);
		
		System.out.println(Integer.toBinaryString(min));
		System.out.println(Integer.toBinaryString(max));
		
		int num = -3;
		System.out.println(Integer.toBinaryString(num));
		num = num >>31;
		System.out.println(num);
		System.out.println(Integer.toBinaryString(num));
		
	}

}
