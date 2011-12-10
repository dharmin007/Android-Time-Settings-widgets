package com.droidappster.dials;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class DialsDemoActivity extends Activity implements OnTouchListener, OnCheckedChangeListener{
	
	private DialViewHour dialViewHour;
	private TextView txtTime;
	private DialViewMinute dialViewMinute;
	private RadioButton rbAM;
	private SmartTime time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dialViewHour = (DialViewHour) findViewById(R.id.dialViewHour1);
        dialViewMinute = (DialViewMinute) findViewById(R.id.dialViewMinute1);
        rbAM = (RadioButton) findViewById(R.id.rbAM);
        ((RadioGroup)findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(this);
        txtTime = (TextView) findViewById(R.id.txtTime);
        time = new SmartTime();
        updateTime();
        dialViewHour.setOnTouchListener(this);
        dialViewMinute.setOnTouchListener(this);
    }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
	        updateTime();
		}
		return false;
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

        updateTime();
	}
	private void updateTime()
	{
        String test;
        time.formatTime(dialViewHour.hour, dialViewMinute.minute);
        test = time.time + (rbAM.isChecked()?" AM":" PM");
        txtTime.setText(test);		
	}
}