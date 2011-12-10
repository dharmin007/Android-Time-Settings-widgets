package com.droidappster.dials;

import java.util.Date;

public class SmartTime {

	public String ampm;
	public String time;
	public SmartTime() {
		// TODO Auto-generated constructor stub
	}
    public void formatTime(Date date)
    {
        formatTime(date.getHours(),date.getMinutes());        
    }
    public void formatTime(int hour,int minute)
    {
        String hours="",minutes;
        
        if(hour==12)
        	ampm="PM";
        else if(hour>12)
        {	
        	hour-=12;
        	ampm = "PM";
        }
        else
        {
        	ampm = "AM";
        }
        if(hour==0)
        {
        	hour=12;
        	ampm="AM";
        }
        if(hour<10)
        	hours="0"+hour;
        else
        	hours=""+hour;
        minutes = String.valueOf(minute);
        if(minutes.length()==1)
        	minutes = "0"+minutes;
        
        time = hours+":"+minutes;
    }
    public String timeDifference(Date current,Date alarmDate)
    {
    	long l = alarmDate.getTime() - current.getTime();
    	int minutes,hours,days;
    	minutes = (int)l / (1000 * 60)+1;
    	hours = minutes/60;
    	days = hours/24;
    	String output = "Time remaining before the alarm is ";
    	if(days>0)
    	{
    		output += days+" days, ";
    		hours = (int)l / (1000 * 60*60*24)-days ;
    	}
    	if(hours>0)
    	{
    		output += hours + " hours and ";
    		//minutes = (int)l / (1000 * 60*60)-hours;
    	}
    	output+=minutes+" minutes";
    	return output;
    }
}
