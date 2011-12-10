package com.droidappster.dials;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DialViewHour extends View {
	public int hour=0;
	Context context;
	public boolean init = false;
	public DialViewHour(Context context) {
		super(context);
		this.context = context;
	}
	public DialViewHour(Context context,AttributeSet attrs)
	{
		super(context,attrs);		
		this.context = context;
	}
	public DialViewHour(Context context,AttributeSet attrs,int defaultStyle)
	{
		super(context,attrs,defaultStyle);
		this.context = context;
	}
	  @Override
	  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

	    int measuredWidth = measure(widthMeasureSpec);
	    int measuredHeight = measure(heightMeasureSpec);

	    int d = Math.min(measuredWidth, measuredHeight);

	    setMeasuredDimension(d, d);
	  }
	  private int measure(int measureSpec) {
	    int result = 0; 

	    // Decode the measurement specifications.
	    int specMode = MeasureSpec.getMode(measureSpec);
	    int specSize = MeasureSpec.getSize(measureSpec); 

	    if (specMode == MeasureSpec.UNSPECIFIED) {
	      // Return a default size of 200 if no bounds are specified. 
	      result = 120;
	    } else {
	      // As you want to fill the available space
	      // always return the full available bounds.
	      result = specSize;
	    } 
	    return result;
	  }
	  int xPos;
	  int yPos;
	  int measure,mid;
	  @Override 
	  protected void onDraw(Canvas canvas) {
		  Paint p,dialPaint;
		  int[] gradientColors=new int[2];
		  measure = getMeasuredHeight()-6;
		  mid = (measure>>1)+2;
		  
		  //Change the colors to change the gradient of the hour dial
		  gradientColors[0]=Color.LTGRAY;
		  gradientColors[1]=Color.BLACK;
		  
		  float[] gradientPos = new float[2];
		  gradientPos[0]=0.0f;
		  gradientPos[1]=1.0f;
		  RadialGradient shader = new RadialGradient(mid,mid,mid,gradientColors,gradientPos,TileMode.CLAMP);
		  dialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		  dialPaint.setShader(shader);
		  dialPaint.setShadowLayer(5, 1, 1,Color.DKGRAY);
		  dialPaint.setAlpha(150);
		  canvas.drawOval(new RectF(3,3,measure,measure), dialPaint);
		  
		  p = new Paint(Paint.ANTI_ALIAS_FLAG);		  
		  p.setColor(Color.WHITE);
		  p.setStrokeWidth(4);

		  if(xPos==0 && yPos==0)
		  {
			  xPos = mid;
		  }
		  p.setTextSize(13);
		  for(int i=1;i<=12;i++)
		  {
			  canvas.rotate(30, mid, mid);
			  canvas.drawText(String.valueOf(i), mid-5, 13, p);
		  }
		  int degree;
		  if(!init)
		  {
			 degree = setDegree();
		  }
		  else
		  {
			 degree = setDegreeHour();
		  }
		  p.setShadowLayer(3.5f, 0,0, Color.BLACK);
		  canvas.drawText(hour+ " Hours", mid-20, mid+20, p);
		  canvas.rotate(-degree,mid,mid);
		  canvas.drawLine(mid,mid,mid,6,p);
		  canvas.rotate(degree,mid,mid);
		  p.setColor(Color.GRAY);
		  canvas.drawOval(new RectF(mid-5,mid-5,mid+5,mid+5), p);		  
		  canvas.save();
		  canvas.restore();		  
	  }
	  private int setDegreeHour()
	  {
		  float dy,dx;
		  dy = mid-yPos;
		  dx = mid-xPos;
		  int degree = (int)Math.toDegrees(Math.atan2(dx, dy));
		  if(degree>-15&&degree<15)
		  {
			  degree = 0;
			  hour = 0;
		  }
		  else if(degree>-45&&degree<-15)
		  {
			  degree = -30;
			  hour = 1;
		  }
		  else if(degree>-75&&degree<-45)
		  {
			  degree = -60;
			  hour = 2;
		  }
		  else if(degree>-105&&degree<-75)
		  {
			  degree = -90;
			  hour = 3;
		  }
		  else if(degree>-135&&degree<-105)
		  {
			  degree = -120;
			  hour = 4;
		  }
		  else if(degree>-165&&degree<-135)
		  {
			  degree = -150;
			  hour = 5;
		  }
		  else if(degree<-165 || degree>165)
		  {
			  degree = -180;
			  hour = 6;
		  }
		  else if(degree<165&&degree>135)
		  {
			  degree = 150;
			  hour = 7;
		  }
		  else if(degree>105&&degree<135)
		  {
			  degree = 120;
			  hour = 8;
		  }
		  else if(degree>75&&degree<105)
		  {
			  degree = 90;
			  hour = 9;
		  }
		  else if(degree>45&&degree<75)
		  {
			  degree = 60;
			  hour = 10;
		  }
		  else if(degree>15&&degree<45)
		  {
			  degree = 30;
			  hour = 11;
		  }
		  return degree;
	  }
	  private int setDegree()
	  {
		  int degree=0;
		  switch(hour)
		  {
		  	case 0:
		  	case 12:degree = 0;
		  			break;
		  	case 1:
		  	case 13:degree = -30;
		  			break;
		  	case 2:
		  	case 14:degree = -60;
		  			break;
		  	case 3:
		  	case 15:degree = -90;
		  			break;
		  	case 4:
		  	case 16:degree = -120;
		  			break;
		  	case 5:
		  	case 17:degree = -150;
		  			break;
		  	case 6:
		  	case 18:degree = -180;
		  			break;
		  	case 7:
		  	case 19:degree = 150;
		  			break;
		  	case 8:
		  	case 20:degree = 120;
		  			break;
		  	case 9:
		  	case 21:degree = 90;
		  			break;
		  	case 10:
		  	case 22:degree = 60;
		  			break;
		  	case 11:
		  	case 23:degree = 30;
		  			break;
		  }
		  return degree;
	  }
	  @Override
	  public boolean onTouchEvent(MotionEvent event){
				  xPos = (int)event.getX();
				  yPos = (int)event.getY();
				  invalidate();	  
				init = true;
		  return true;	  
	  }
}
