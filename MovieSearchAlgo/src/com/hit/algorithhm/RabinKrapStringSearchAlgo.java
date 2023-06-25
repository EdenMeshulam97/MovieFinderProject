package com.hit.algorithhm;

import java.util.ArrayList;

public class RabinKrapStringSearchAlgo implements IAlgoStringSearch {

	private int d = 256;  // number of characters in the input alphabet
    private int q = 101;  // A prime number
	
	@Override
	public ArrayList<Integer> search(String text, String pattern)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!text.isBlank() && !pattern.isBlank() && text.length() >= pattern.length())
		{
	        // Convert both text and pattern to lower case to make the search case-insensitive
	        String lowerCaseText = text.toLowerCase();
	        String lowerCasePattern = pattern.toLowerCase();
	
	        int patternLength = lowerCasePattern.length();
	        int textLength = lowerCaseText.length();
	        int patternHash = 0; // hash value for pattern
	        int textHash = 0; // hash value for text
	
	        // Calculate the hash value of pattern and first window of text
	        for (int i = 0; i < patternLength; i++) {
	            patternHash = (d * patternHash + lowerCasePattern.charAt(i)) % q;
	            textHash = (d * textHash + lowerCaseText.charAt(i)) % q;
	        }
	
	        // Slide the pattern over text one by one
	        for (int i = 0; i <= textLength - patternLength; i++) {
	            // Check the hash values of current window of text and pattern.
	            // If the hash values match then only check for characters one by one
	            if (patternHash == textHash) {
	                int j;
	                for (j = 0; j < patternLength; j++) {
	                    if (lowerCaseText.charAt(i + j) != lowerCasePattern.charAt(j))
	                        break;
	                }
	
	                // If patternHash == textHash and pattern[0...patternLength-1] = text[i, i+1, ...i+patternLength-1]
	                if (j == patternLength)
	                    result.add(i);
	            }
	
	            // Calculate hash value for next window of text: Remove leading digit, add trailing digit
	            if (i < textLength - patternLength) {
	                textHash = recalculateHash(lowerCaseText, i, i + patternLength, textHash, patternLength);
	            }
	        }
		}
        return result;	
    }
	
	// Helper method to calculate the new hash value when rolling
    private int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int patternLength) 
    {
        int newHash = (d * (oldHash - str.charAt(oldIndex) * (int)Math.pow(d, patternLength - 1)) + str.charAt(newIndex)) % q;
        if (newHash < 0) {
            newHash += q;
        }
        return newHash;
    }
	
}
