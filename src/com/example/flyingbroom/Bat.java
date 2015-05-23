package com.example.flyingbroom;

import java.util.Random;

import android.util.Log;

public class Bat extends Object {
	private Random rand = new Random();
	public Bat(float s1, float s2) {
		super(s1, s2);
		x=MainActivity.Engine.sizeX;
		y=(MainActivity.Engine.sizeY/10.f)*rand.nextInt(10);
		Log.d("NewBat","Ycor: "+y);
		setHspeed(-7f*MainActivity.Engine.sizeX/1024.f*MainActivity.speeder);
		setVspeed(-1.5f*MainActivity.Engine.sizeX/1024*MainActivity.speeder);
		frame = rand.nextInt(9);
	}

}
