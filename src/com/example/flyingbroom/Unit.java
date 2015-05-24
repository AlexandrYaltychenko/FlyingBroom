package com.example.flyingbroom;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Matrix;

public class Unit extends Object{
	private int my;
	@Override
	public void MoveY(float Y){
		y+=Y*MainActivity.speeder;
		if (y<0)
			y=0;
		if (y>MainActivity.Engine.sizeY-1.5*h)
			y=(float) (MainActivity.Engine.sizeY-1.5*h);
	}

	public Unit(float s1, float s2) {
		super(s1, s2);
		my=0;
	}
	@Override
	public boolean isCollision(Object obj){
		if (obj.X()<0.25*w)
		if (obj.X()>x+0.95*w || obj.Y()+obj.H()<y+0.3*h || obj.Y()>y+0.9*h)
		return false;
			else
		return true;
		if (obj.X()<0.85*w)
		if (obj.X()>x+0.95*w || obj.Y()+obj.H()<y+0.1*h || obj.Y()>y+0.9*h)
		return false;
			else
		return true;
		if (obj.X()>x+0.95*w || obj.Y()+obj.H()<y+0.4*h || obj.Y()>y+0.9*h)
		return false;
			else
		return true;
	}
	public void ProcessCollisions(ArrayList <Coin> r){
		int i=0;
		Coin l;
		while (i<r.size()){
			l=(Coin)r.get(i);
			if (l.isEaten()==false && isCollision(l)){
				l.setVspeed(-10f);
				l.Eat();
				
			}
			i++;
			
			
		}
	}
	public void Move(){
		if (Math.abs(y-my)<0.2*h){
			  return;
		}
		 if (y<my)
			  MoveY(vspeed);
		 else
			 MoveY(-vspeed);
	}
	@Override
	public void Draw(Canvas canvas){		  
		  Matrix matrix = new Matrix();
		  matrix.reset();
		  w=sx*MainActivity.Engine.sizeX;
		  h=sy*MainActivity.Engine.sizeY;
		  matrix.preScale(w/width, h/height);
		  matrix.postTranslate(x, y);
		  canvas.drawBitmap(bmps[frame], matrix, paint);
		  if (MainActivity.paused) return;
		  MoveY(vspeed);
		  if (animated && MainActivity.Engine.timer.nextFrame())
				 frame=frame>1?0:frame+1;	
	}

}
