package utils;

public class StringUtils {
	
	public static String longestCommonPrefix(String s1, String s2) {
		
		String shortest = s2;
		String longest = s1;
		
		if(s1.length() < s2.length()) {
			shortest = s1;
			longest = s2;
		}
		
		int index = 0;
		while((index < longest.length()) && (shortest.charAt(index) == longest.charAt(index))) index++;
			
		
		return longest.substring(0, index);
	}	
	
}
