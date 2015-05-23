package com.example.flyingbroom;

public class Timer {
	private long t1;
	private long t2;
	private long LastClick;
	private int delay;
	public void setDelay(int d){
		delay=d;
	}
	public void Update(){
		t2=System.currentTimeMillis();
		if (t2-t1>delay)
		t1=t2;
	}
	public Timer(){
		t1=t2=LastClick=System.currentTimeMillis();
		delay=10;
	}
	public void Click(){
		LastClick=System.currentTimeMillis();
	}
	public int TimeAfterClick(){
		return (int) (System.currentTimeMillis()-LastClick);
	}
	public boolean nextFrame(){
		if (System.currentTimeMillis() % 100 <30)
			return true;
		else
			return false;
	}
	public boolean nextCoin(){
		if (System.currentTimeMillis() % 100 >96/MainActivity.speeder)
			return true;
		else
			return false;
	}
	public boolean LastClickDelay(int ms){
		if (System.currentTimeMillis()-LastClick>=ms)
			return true;
		else
			return false;
	}
}
