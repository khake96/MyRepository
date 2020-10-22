package stringDemo;

public class CreateUpperCaseEnds {
	
	public static void main(String[] args) {
		
		String input = " this is a longish string with some white space.   ";
		StringBuffer sb = new StringBuffer();
		String[] arr = input.trim().split(" ");

		for(String word:arr) {
			
			sb.append(word.substring(0,word.length()-1)).append(Character.toUpperCase(word.charAt(word.length()-1))).append(" ");

		}
		System.out.println(sb.toString().trim());
	}

}
