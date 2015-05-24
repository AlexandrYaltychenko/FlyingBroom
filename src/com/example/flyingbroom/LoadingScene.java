package com.example.flyingbroom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LoadingScene extends Scene{

	public LoadingScene(Game ENGINE) {
		super(ENGINE);
	}
	public void RenderScene(Canvas canvas){
		Engine.applysize(canvas);
		Paint p=new Paint();
		p.setColor(Color.WHITE);
		canvas.drawRect(0, 0,Engine.sizeX,Engine.sizeY, p);
		p.setColor(Color.BLACK);
		p.setTextSize(32);
		canvas.drawText("Loading...", Engine.sizeX/2, Engine.sizeY/2, p);
		
	}

}
