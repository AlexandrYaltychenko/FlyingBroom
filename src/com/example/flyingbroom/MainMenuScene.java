package com.example.flyingbroom;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;

public class MainMenuScene extends Scene{
	private Object start,records,author,exit;
	public MainMenuScene(Game ENGINE){
		super(ENGINE);
		Log.d("SCENE","MAINMENU");
		start = new Object(0.3f,0.1f);
		records = new Object(0.3f,0.1f);
		author = new Object(0.3f,0.1f);
		exit = new Object(0.3f,0.1f);		
		Log.d("SCENE","SETTING BITMAP START");
		start.setBitmaps(Engine.textures.get("m1"));
		records.setBitmaps(Engine.textures.get("m2"));
		author.setBitmaps(Engine.textures.get("m3"));
		exit.setBitmaps(Engine.textures.get("m4"));
	}
	public void button_Start(){
		Engine.setState(2);
		Engine.CoinsNum=0;
		Engine.initLevelScreen();
	}
	public void ProcessTouch(int mx, int my, int down){
		super.ProcessTouch(mx, my, down);
		if (start.isAt(mx, my) && down==0){
			button_Start();
		}
		if (exit.isAt(mx, my)  && down==0){
			((Activity)Engine.context).finish();
			System.exit(0);
		}
		
	}
	public void RenderScene(Canvas canvas){
		Engine.applysize(canvas);
		MainActivity.speeder=1f;
		MainActivity.Backmenu.Draw(canvas);
		start.setCors(0.35f*Engine.sizeX,0.45f*Engine.sizeY);
		start.Draw(canvas);
		records.setCors(0.35f*Engine.sizeX,0.55f*Engine.sizeY);
		records.Draw(canvas);
		author.setCors(0.35f*Engine.sizeX,0.65f*Engine.sizeY);
		author.Draw(canvas);
		records.setCors(0.35f*Engine.sizeX,0.75f*Engine.sizeY);
		records.Draw(canvas);
		exit.setCors(0.35f*Engine.sizeX,0.85f*Engine.sizeY);
		exit.Draw(canvas);
	}

}
