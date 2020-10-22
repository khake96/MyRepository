package arraysPractice;

import java.util.Arrays;

public class ArrayDemo {
	
	private int ar[] = {2,5,77,45,90,345,3323,78,67,55,23};
	// private int holderArray[]=new int[20];
	private int populatedCount = 0;
	private int insertionValue = 12;
	private int insertionPosition = 4;

	public static void main(String[] args) {

		ArrayDemo a = new ArrayDemo();
		
		// Copy the array into another larger array
		System.out.println(Arrays.toString(a.ar));
		int holderArray[] = Arrays.copyOf(a.ar, a.ar.length+10);
		System.out.println(Arrays.toString(holderArray));
		
		//set populatedCount value
		int c=holderArray.length-1;
		int j = 0;
		
		while(c>0) {
			if (holderArray[c]==0) {
				j++;
				c--;
			} else {
				a.populatedCount = holderArray.length-j;
				break;
			}
		}
		System.out.println("Populated Count is: " + a.populatedCount);
		
		//Insert value into array
		for(int i=a.populatedCount; i>a.insertionPosition-1; i--) {
			holderArray[i]=holderArray[i-1];
			}
		holderArray[a.insertionPosition-1] = a.insertionValue;
		a.populatedCount++;
		System.out.println(Arrays.toString(holderArray));
		System.out.println("Populated Count is: " + a.populatedCount);
	}
		

}


