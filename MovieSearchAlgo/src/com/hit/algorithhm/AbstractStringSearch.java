package com.hit.algorithhm;

import java.util.ArrayList;

public abstract class AbstractStringSearch {
	
	private IAlgoStringSearch stringSearchAlgorithem;
	
	public AbstractStringSearch(IAlgoStringSearch stringSearchAlgoType)
	{
		this.stringSearchAlgorithem = stringSearchAlgoType;
	}
	
	public void setAlgoType(IAlgoStringSearch stringSearchAlgoType) 
	{
		this.stringSearchAlgorithem = stringSearchAlgoType;
	}
	
	public ArrayList<Integer> search(String text, String pattern)
	{
		return stringSearchAlgorithem.search(text, pattern);
	}
}
