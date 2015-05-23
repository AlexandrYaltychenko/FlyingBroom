package com.example.flyingbroom;

import android.graphics.Canvas;
import android.util.Log;

public class LevelMenuScene extends Scene{
	private Object town,hills,mountain;
	public LevelMenuScene(Game ENGINE){
		super(ENGINE);
		town = new Object(0.3f,0.1f);
		hills = new Object(0.3f,0.1f);
		mountain = new Object(0.3f,0.1f);
		town.setBitmaps(Engine.textures.get("m5"));
		hills.setBitmaps(Engine.textures.get("m6"));
		mountain.setBitmaps(Engine.textures.get("m10"));
	}
	public void Town(){
		Engine.textures.removePrefix("back");
		Engine.textures.addTextures("back11");
		Engine.textures.addTextures("back12");
		Engine.textures.addTextures("back13");
		Engine.initGameScreen(0);
	    Engine.setState(1);	
	}
	public void Hills(){
		Engine.textures.removePrefix("back");
		Engine.textures.addTextures("back21");
		Engine.textures.addTextures("back22");
		Engine.textures.addTextures("back13");
		Engine.initGameScreen(1);
	    Engine.setState(1);	
	}
	public void Mountain(){
		Engine.textures.removePrefix("back");
		Engine.textures.addTextures("back21");
		Engine.textures.addTextures("back32");
		Engine.textures.addTextures("back13");
		Engine.initGameScreen(2);
	    Engine.setState(1);	
	}
	public void ProcessTouch(int mx, int my, int down){
		super.ProcessTouch(mx, my, down);
		if (town.isAt(mx,my) && down==1){
			Log.d("SELECT","TOWN");
			Town();
		}
		if (hills.isAt(mx, my) && down==1){
			Log.d("SELECT","HILLS");
			Hills();
		}
		if (mountain.isAt(mx, my) && down==1){
			Log.d("SELECT","MOUNTAIN");
			Mountain();
		}
	}
	public void RenderScene(Canvas canvas){
		Engine.applysize(canvas);
		MainActivity.speeder=1f;
		MainActivity.Backmenu.Draw(canvas);
		town.setCors(0.35f*Engine.sizeX,0.30f*Engine.sizeY);
		town.Draw(canvas);
		hills.setCors(0.35f*Engine.sizeX,0.45f*Engine.sizeY);
		hills.Draw(canvas);
		mountain.setCors(0.35f*Engine.sizeX,0.60f*Engine.sizeY);
		mountain.Draw(canvas);
	}
}
