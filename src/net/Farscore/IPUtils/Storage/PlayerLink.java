package net.Farscore.IPUtils.Storage;

public class PlayerLink {
	
	public String _name;
	public String _ip;
	public long _onlineSince;
	
	public String returnOnline()
	{
		if(!(_onlineSince == 0))
		{
			long different = System.currentTimeMillis() - _onlineSince;
			
			long secondsInMilli = 1000;
			long minutesInMilli = secondsInMilli * 60;
			long hoursInMilli = minutesInMilli * 60;
			long daysInMilli = hoursInMilli * 24;
	
			long elapsedDays = different / daysInMilli;
			different = different % daysInMilli;
	
			long elapsedHours = different / hoursInMilli;
			different = different % hoursInMilli;
	
			long elapsedMinutes = different / minutesInMilli;
			different = different % minutesInMilli;
	
			long elapsedSeconds = different / secondsInMilli;
			
			return elapsedDays + " day" + (elapsedDays > 1 ? "s" : "") + ", " + elapsedHours + " hour" +  (elapsedHours > 1 ? "s" : "") + ", " + elapsedMinutes + " minute" + (elapsedMinutes > 1 ? "s" : "");
		}
		else
		{
			return "";
		}
	}
	
	public PlayerLink(String name, String ip)
	{
		_name = name;
		_ip = ip;
		
		if(Configuration.getBooleanFromMemory("timeOnlineTrack"))
		{
			_onlineSince = System.currentTimeMillis();
		}
		else
		{
			_onlineSince = 0;
		}
	}
	
}
