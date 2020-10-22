package package2;

public class OperatorExample {
	
	public static void main(String[] args) {
		
		long l = 123L;
		double d = 5495;
		int x = -122;
		short y = 43;
		
		System.out.println("Division example: "+(l*d));
		System.out.println("Post-increment: " + x++);
		System.out.println("Now x is: " + x);
		System.out.println("Pre-increment: " + ++x);
		System.out.println("Now x is: " + x);
		++x;
		System.out.println("Now x is: " + x);
		x=2;
		System.out.println("y%x: " + y%x);
		
		if(y%x==0 || x<=-100) {
			System.out.println("y is even and x is very negative");
		}else System.out.println("y is odd and x is positive or not very negative");
		
		
	}

}
