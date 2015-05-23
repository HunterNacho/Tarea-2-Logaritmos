package bookparser;

import java.io.*;

public class BookParser {
	
	private static final String REGEXP =  "[^a-zA-Z]";

	public static String parseTextFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    String line = null;
	    StringBuilder stringBuilder = new StringBuilder();

	    while ((line = reader.readLine()) != null) {
	    	line = line.replaceAll(REGEXP, " ");
	    	String[] sline = line.split(" ");
	    	for(String word : sline) {
	    		if (word.equals(""))
	    			continue;
	    		stringBuilder.append(word.toLowerCase() + " ");
	    	}
	    }
	    
	    reader.close();
	    return stringBuilder.toString().trim();
	}
}

