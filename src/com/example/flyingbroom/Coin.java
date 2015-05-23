package com.example.flyingbroom;

import java.util.Random;
import android.util.Log;

public class Coin extends Object {
	private boolean eaten;
	private float effect;
	private int type;
	private Random rand = new Random();
	public Coin(float s1, float s2) {
		super(s1, s2);
		x=MainActivity.Engine.sizeX;
		y=(MainActivity.Engine.sizeY/10.f)*rand.nextInt(10);
		Log.d("NewCoin","Ycor: "+y);
		setHspeed(-4f*MainActivity.Engine.sizeX/1024.f*MainActivity.speeder);
		setVspeed(-1f*MainActivity.Engine.sizeX/1024*MainActivity.speeder);
		eaten=false;
	}
	public void setEffect(float r){
		effect=r;
	}
	public boolean isEaten(){
		return eaten;
	}
	public void setType(int t){
		this.type=t;
	}
	public int getType(){
		return type;
	}
	public void Eat(){
		eaten=true;
		if ((int)effect==0)
			MainActivity.Engine.GameGoldInc(1);
		else
			MainActivity.Engine.GameHPInc(effect);
	}
	

}
