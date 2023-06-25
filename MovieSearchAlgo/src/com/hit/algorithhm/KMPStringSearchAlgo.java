package com.hit.algorithhm;

import java.util.ArrayList;

public class KMPStringSearchAlgo implements IAlgoStringSearch {
	
	@Override
	public ArrayList<Integer> search(String text, String pattern)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!text.isBlank() && !pattern.isBlank() && text.length() >= pattern.length())
			{
			String lowerCaseText = text.toLowerCase();
		    String lowerCasePattern = pattern.toLowerCase();
	        // Compute the longest proper prefix array
	        int[] longestPrefixSuffix = computeLPSArray(lowerCasePattern);
	        int i = 0;  // index for text[]
	        int j = 0;  // index for pattern[]
	        while (i < lowerCaseText.length()) {
	            // When the characters match, increment both pointers
	            if (lowerCasePattern.charAt(j) == lowerCaseText.charAt(i)) {
	                j++;
	                i++;
	            }
	            // When the full pattern has been found
	            if (j == lowerCasePattern.length()) {
	                // Record the index of the start of the pattern
	                result.add(i - j);
	                // Reset the pattern pointer using the LPS array
	                j = longestPrefixSuffix[j - 1];
	            }
	            // When a mismatch after j matches
	            else if (i < lowerCaseText.length() && lowerCasePattern.charAt(j) != lowerCaseText.charAt(i)) {
	                // If the pattern pointer is not at the start
	                if (j != 0)
	                    // Reset the pattern pointer using the LPS array
	                    j = longestPrefixSuffix[j - 1];
	                else
	                    // If the pattern pointer is at the start, move the text pointer to the right
	                    i = i + 1;
	            }
	        }
        }
        return result;
	}
	private int[] computeLPSArray(String pattern) 
	{
		int[] longestPrefixSuffix = new int[pattern.length()];
        int length = 0;  // Length of the previous longest prefix suffix
        int i = 1;
        longestPrefixSuffix[0] = 0;  // LPS is always 0 for the first character

        // The loop calculates LPS for pattern[1] to pattern[m-1]
        while (i < pattern.length()) {
            // When the current character matches with the character at 'length' position
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                longestPrefixSuffix[i] = length;
                i++;
            } else {
                // If length is not 0, fall back to the previous longest prefix suffix
                if (length != 0) {
                    length = longestPrefixSuffix[length - 1];
                } else {
                    longestPrefixSuffix[i] = length;
                    i++;
                }
            }
        }
        return longestPrefixSuffix;
    }

}
