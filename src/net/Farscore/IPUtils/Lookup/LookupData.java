package net.Farscore.IPUtils.Lookup;

import java.util.ArrayList;

public class LookupData {
	
	public ArrayList<String> _data;
	
	public Integer getTotal()
	{
		return _data.size();
	}
	
	public ArrayList<String> getData()
	{
		return _data;
	}
	
	public LookupData(ArrayList<String> data)
	{
		_data = data;
	}
	
}
