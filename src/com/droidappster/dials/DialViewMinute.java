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

public class DialViewMinute extends View {
	Context context;
	public boolean init = false;
	public int minute=0;
	public DialViewMinute(Context context) {
		super(context);
		init(context);
	}
	public DialViewMinute(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		init(context);		
	}
	public DialViewMinute(Context context,AttributeSet attrs,int defaultStyle)
	{
		super(context,attrs,defaultStyle);
		init(context);
	}
	private void init(Context context)
	{
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
	  int yPos,mid;
	  Paint p;
	  @Override 
	  protected void onDraw(Canvas canvas) {
		  int measure;
		  Paint dialPaint;
		  int[] gradientColors=new int[2];
		  measure = getMeasuredHeight()-6;
		  mid = (measure>>1)+2;
		  //Change the colors to change the gradient of the minute dial
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

		  if(xPos==0 && yPos==0)
		  {
			  xPos = mid;
		  }
		  if(measure<160)
			  p.setTextSize(10);
		  else
			  p.setTextSize(15);
		  for(int i=1;i<=60;i++)
		  {
			  canvas.rotate(6, mid, mid);
			  if(i%5 == 0)
			  {
				  if(i!=60)
				  {
					  canvas.drawText(String.valueOf(i), mid-5, 20, p);
				  }
				  else
					  canvas.drawText("0", mid-2, 20, p);
				  canvas.drawLine(mid,8, mid, 0 ,p);
			  }
			  else
				  canvas.drawLine(mid, 5,mid, 0, p);
		  }
		  int degree;
		  if(init)
		  {
			  degree = setDegreeHour();
		  }
		  else
		  {
			  degree = setDegree();
		  }		  
		  p.setShadowLayer(3.5f, 0,0, Color.BLACK);
		  canvas.drawText(minute+" Minutes", mid-20,mid+20,p);
		  adjustHand(canvas,degree);
		  p.setColor(Color.GRAY);
		  canvas.drawOval(new RectF(mid-5,mid-5,mid+5,mid+5), p);
		  canvas.save();
		  canvas.restore();		  
	  }
	  private int setDegree()
	  {
		  int degree=0;
		  if(minute<=30)
			  degree = -(minute*6);
		  else
		  {
			  switch(minute)
			  {
			  	case 31:degree = 174;
			  			break;
			  	case 32:degree = 168;
			  			break;
			  	case 33:degree = 162;
	  					break;
			  	case 34:degree = 156;
	  					break;
			  	case 35:degree = 150;
	  					break;
			  	case 36:degree = 144;
			  			break;
			  	case 37:degree = 138;
	  			break;
			  	case 38:degree = 132;
	  			break;
			  	case 39:degree = 126;
	  			break;
			  	case 40:degree = 120;
	  			break;
			  	case 41:degree = 114;
	  			break;
			  	case 42:degree = 108;
	  			break;
			  	case 43:degree = 102;
	  			break;
			  	case 44:degree = 96;
	  			break;
			  	case 45:degree = 90;
	  			break;
			  	case 46:degree = 84;
	  			break;
			  	case 47:degree = 78;
	  			break;
			  	case 48:degree = 72;
	  			break;
			  	case 49:degree = 66;
	  			break;
			  	case 50:degree = 60;
	  			break;
			  	case 51:degree = 54;
	  			break;
			  	case 52:degree = 48;
	  			break;
			  	case 53:degree = 42;
	  			break;
			  	case 54:degree = 36;
	  			break;
			  	case 55:degree = 30;
	  			break;
			  	case 56:degree = 24;
	  			break;
			  	case 57:degree = 18;
	  			break;
			  	case 58:degree = 12;
	  			break;
			  	case 59:degree = 6;
	  			break;
			  }
		  }
		  return degree;
	  }
	  private int setDegreeHour()
	  {
		  float dy,dx;
		  dy = mid-yPos;
		  dx = mid-xPos;
		  int degree = (int)Math.toDegrees(Math.atan2(dx, dy));
		  if(degree<-176)
		  {
			  degree = 180;
			  minute = 30;
		  }
		  else if(degree<-168)
		  {
			  degree = -173;
			  minute=29;
		  }
		  else if(degree<-162)
		  {
			  degree = -167;
			  minute=28;
		  }
		  else if(degree<-156)
		  {
			  degree = -161;
			  minute=27;
		  }
		  else if(degree<-150)
		  {
			  degree = -155;
			  minute=26;
		  }
		  else if(degree<-144)
		  {
			  degree = -150;
			  minute=25;
		  }
		  else if(degree<-138)
		  {
			  degree = -144;
			  minute=24;
		  }
		  else if(degree<-132)
		  {
			  degree = -138;
			  minute=23;
		  }
		  else if(degree<-126)
		  {
			  degree = -132;
			  minute=22;
		  }
		  else if(degree<-120)
		  {
			  degree = -126;
			  minute=21;
		  }
		  else if(degree<-114)
		  {
			  degree = -120;
			  minute=20;
		  }
		  else if(degree<-108)
		  {
			  degree = -114;
			  minute=19;
		  }
		  else if(degree<-102)
		  {
			  degree = -108;
			  minute=18;
		  }
		  else if(degree<-96)
		  {
			  degree = -102;
			  minute=17;
		  }
		  else if(degree<-90)
		  {
			  degree = -96;
			  minute=16;
		  }
		  else if(degree<-84)
		  {
			  degree = -90;
			  minute=15;
		  }
		  else if(degree<-78)
		  {
			  degree = -84;
			  minute=14;
		  }
		  else if(degree<-72)
		  {
			  degree = -78;
			  minute=13;
		  }
		  else if(degree<-66)
		  {
			  degree = -72;
			  minute=12;
		  }
		  else if(degree<-60)
		  {
			  degree = -66;
			  minute=11;
		  }
		  else if(degree<-54)
		  {
			  degree = -60;
			  minute=10;
		  }
		  else if(degree<-48)
		  {
			  degree = -54;
			  minute=9;
		  }
		  else if(degree<-42)
		  {
			  degree = -48;
			  minute=8;
		  }
		  else if(degree<-36)
		  {
			  degree = -42;
			  minute=7;
		  }
		  else if(degree<-30)
		  {
			  degree = -36;
			  minute=6;
		  }
		  else if(degree<-24)
		  {
			  degree = -30;
			  minute=5;
		  }
		  else if(degree<-18)
		  {
			  degree = -24;
			  minute=4;
		  }
		  else if(degree<-12)
		  {
			  degree = -18;
			  minute=3;
		  }
		  else if(degree<-6)
		  {
			  degree = -12;
			  minute=2;
		  }
		  else if(degree<-3)
		  {
			  degree = -6;
			  minute=1;
		  }
		  else if(degree>-3&&degree<3)
		  {
			  degree = 0;
			  minute=0;
		  }
		  else if(degree<9)
		  {
			  degree = 6;
			  minute = 59;
		  }
		  else if(degree<15)
		  {
			  degree = 12;
			  minute = 58;
		  }
		  else if(degree<21)
		  {
			  degree = 18;
			  minute = 57;
		  }
		  else if(degree<27)
		  {
			  degree = 24;
			  minute = 56;
		  }
		  else if(degree<33)
		  {
			  degree = 30;
			  minute = 55;
		  }
		  else if(degree<39)
		  {
			  degree = 36;
			  minute = 54;
		  }
		  else if(degree<45)
		  {
			  degree = 42;
			  minute = 53;
		  }
		  else if(degree<51)
		  {
			  degree = 48;
			  minute = 52;
		  }
		  else if(degree<57)
		  {
			  degree = 54;
			  minute = 51;
		  }
		  else if(degree<63)
		  {
			  degree = 60;
			  minute = 50;
		  }
		  else if(degree<69)
		  {
			  degree = 66;
			  minute = 49;
		  }
		  else if(degree<75)
		  {
			  degree = 72;
			  minute = 48;
		  }
		  else if(degree<81)
		  {
			  degree = 78;
			  minute = 47;
		  }
		  else if(degree<87)
		  {
			  degree = 84;
			  minute = 46;
		  }
		  else if(degree<93)
		  {
			  degree = 90;
			  minute = 45;
		  }
		  else if(degree<99)
		  {
			  degree = 96;
			  minute = 44;
		  }
		  else if(degree<105)
		  {
			  degree = 102;
			  minute = 43;
		  }
		  else if(degree<111)
		  {
			  degree = 108;
			  minute = 42;
		  }
		  else if(degree<117)
		  {
			  degree = 114;
			  minute = 41;
		  }
		  else if(degree<123)
		  {
			  degree = 120;
			  minute = 40;
		  }
		  else if(degree<129)
		  {
			  degree = 126;
			  minute = 39;
		  }
		  else if(degree<135)
		  {
			  degree = 132;
			  minute = 38;
		  }
		  else if(degree<141)
		  {
			  degree = 138;
			  minute = 37;
		  }
		  else if(degree<147)
		  {
			  degree = 144;
			  minute = 36;
		  }
		  else if(degree<153)
		  {
			  degree = 150;
			  minute = 35;
		  }
		  else if(degree<159)
		  {
			  degree = 156;
			  minute = 34;
		  }
		  else if(degree<165)
		  {
			  degree = 162;
			  minute = 33;
		  }
		  else if(degree<171)
		  {
			  degree = 168;
			  minute = 32;
		  }
		  else if(degree<177)
		  {
			  degree = 174;
			  minute = 31;
		  }
		  else if(degree<180)
		  {
			  degree = 180;
			  minute = 30;
		  }
		  return degree;
	  }
	  private void adjustHand(Canvas canvas,float degree)
	  {
		  canvas.rotate(-degree,mid,mid);
		  p.setStrokeWidth(4);
		  canvas.drawLine(mid,mid,mid,0,p);
		  canvas.rotate(degree,mid,mid);
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
