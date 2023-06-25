package com.hit.algorithhm;

import java.util.ArrayList;

public class NaiveStringSearchAlgo implements IAlgoStringSearch {

	@Override
	public ArrayList<Integer> search(String text, String pattern)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!text.isBlank() && !pattern.isBlank() && text.length() >= pattern.length())
		{
		    // Convert both text and pattern to lower case to make the search case-insensitive
		    String lowerCaseText = text.toLowerCase();
		    String lowerCasePattern = pattern.toLowerCase();
	
		    // Get the lengths of the text and the pattern
		    int M = lowerCasePattern.length();
		    int N = lowerCaseText.length();
	
		    // Loop over the text
		    for (int i = 0; i <= N - M; i++) {
		        int j;
	
		        /* For current index i, check for pattern match */
		        // Check if the characters at the current position in the text and the pattern match
		        for (j = 0; j < M; j++)
		            if (lowerCaseText.charAt(i + j) != lowerCasePattern.charAt(j))
		                break;
	
		        // If we've gotten to the end of the pattern, we've found a match
		        if (j == M)
		            result.add(i); // pattern found, add starting index to result list
		    }
	    }
	    return result;
	}
	
}
