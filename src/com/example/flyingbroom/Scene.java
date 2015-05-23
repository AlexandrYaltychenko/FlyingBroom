package com.example.flyingbroom;

public abstract class Scene{
	protected Game Engine;
	protected float k;
	public Scene(Game ENGINE){
		setEngine(ENGINE);
		calcK();
	}
	protected Game getEngine(){
		return Engine;
	}
	protected void setEngine(Game ENGINE){
		Engine=ENGINE;
	}
	protected void calcK(){
		k=Engine.sizeX/1024.f;
	}
	protected void ProcessTouch(int mx, int my, int down){
		
	}
}
