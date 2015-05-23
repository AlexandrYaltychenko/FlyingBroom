package com.example.flyingbroom;

import android.graphics.Bitmap;

public class Texture {
	private Bitmap bmp;
	private String ID;
	Texture(String ID, Bitmap b){
		setID(ID);
		setBitmap(b);
	}
	public void setBitmap(Bitmap b){
		bmp=b;
	}
	public Bitmap getBitmap(){
		return bmp;
	}
	public void setID(String s){
		ID=s;
	}
	public String getID(){
		return ID;
	}
}
