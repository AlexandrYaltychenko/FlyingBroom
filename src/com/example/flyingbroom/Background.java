package com.example.flyingbroom;

import android.graphics.Canvas;
import android.graphics.Matrix;

public class Background extends Object{
	private float cloud;

	public Background(float s1, float s2) {
		super(s1, s2);
		cloud=0f;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void Draw(Canvas canvas){
		  if (cloud<-MainActivity.Engine.sizeX)
			  cloud=0;
		  if (x<-MainActivity.Engine.sizeX)
			  x=0;
		  Matrix matrix = new Matrix();
		  matrix.reset();
		  matrix.setTranslate(0, 0);
		  matrix.preScale(sx*MainActivity.Engine.sizeX/width, sy*MainActivity.Engine.sizeY/height);
		  canvas.drawBitmap(bmps[0], matrix, paint);
		  matrix.reset();
		  matrix.setTranslate(x, y);
		  matrix.preScale(sx*MainActivity.Engine.sizeX/width, sy*MainActivity.Engine.sizeY/height);
		  canvas.drawBitmap(bmps[1], matrix, paint);
		  matrix.reset();
		  matrix.setTranslate(x+MainActivity.Engine.sizeX, y);
		  matrix.preScale(sx*MainActivity.Engine.sizeX/width, sy*MainActivity.Engine.sizeY/height);
		  canvas.drawBitmap(bmps[1], matrix, paint);
		  matrix.reset();
		  matrix.setTranslate(cloud, y);
		  matrix.preScale(sx*MainActivity.Engine.sizeX/width, sy*MainActivity.Engine.sizeY/height);
		  canvas.drawBitmap(bmps[2], matrix, paint);
		  matrix.reset();
		  matrix.setTranslate(cloud+MainActivity.Engine.sizeX, y);
		  matrix.preScale(sx*MainActivity.Engine.sizeX/width, sy*MainActivity.Engine.sizeY/height);
		  canvas.drawBitmap(bmps[2], matrix, paint);
		  if (MainActivity.paused) return;
		  MoveY(vspeed);
		  MoveX(hspeed);
		  cloud+=1.2f*hspeed*MainActivity.speeder;
	}

}
