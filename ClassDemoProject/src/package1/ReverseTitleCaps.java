package package1;

public class ReverseTitleCaps {
// capitalize the last letter of each word and print string to standard out
	public static void main(String[] args) {
		String s="hello hi good morning I am enjoying programming with java";
		StringBuilder sb=new StringBuilder();
		// putting string into String array by " " divider
		String ar[]=s.split(" "); 
		//enhanced for loop to treat each index in the array
		for(String s1:ar) {
			 // append the word less the last letter
			sb.append(s1.substring(0, s1.length()-1) );
			//upperCase the last letter and also append
			sb.append(Character.toUpperCase(s1.charAt(s1.length()-1))).append(" ");
		}
		System.out.println(sb);
		
	} 

}
