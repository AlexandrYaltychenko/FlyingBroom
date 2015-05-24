package com.example.flyingbroom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

public class Game{

    public Timer timer;
	public TexturePack textures;
	public boolean reselect;
	private int state;
	public int mx,my;
	public int sizeX,sizeY;
	protected int CoinsNum;
	Context context;
	private GameScene GameScreen;
	private MainMenuScene MenuScreen;
	private LevelMenuScene LevelScreen;
	private LoadingScene LoadScreen;
	private DrawView DV;
	  
	public Game(Context context){
		Log.d("IMP","ENGINE CONSTRUCTOR");
		mx=my=0;
		textures = new TexturePack(context);
		timer = new Timer();
		this.context = context;
		reselect=false;
	}
	public void RunThread(){
		Log.d("IMP","RUNNING THREAD FROM ENGINE");
		DV = MainActivity.DV;
	}
	public void Pause(){
	}
	public void initLoadScreen(){
		LoadScreen = new LoadingScene(this);
	}
	public void initGameScreen(int state){
		GameScreen = new GameScene(this,state);
	}
	public void initLevelScreen(){
		LevelScreen = new LevelMenuScene(this);
	}
	public DrawView getLayout(){
		return DV;
	}
	public void setState(int s){
		state=s;
	}
	public int getState(){
		return state;
	}
	public void initMenuScreen(){
		MenuScreen = new MainMenuScene (this);
	}
	public void LoadTextures(){
		textures.addTextures("witch1","witch2","witch3");
		textures.addTextures("moon1");
		textures.addTextures("gr1","gr2","gr3");
		textures.addTextures("d1","d2","d3");
		textures.addTextures("menu","m1","m2","m3","m4","m5","m6","m7","m8","m9","m10");
		textures.addTextures("c1","c2","c3");
		textures.addTextures("c1","c2","c3");
		textures.addTextures("bat1","bat2","bat3","bat4","bat5","bat6","bat7","bat8","bat9");
	}

	public void setMouseCors(int x,int y, int down){
		mx=x;
		my=y;
		switch(state){
		case 0:MenuScreen.ProcessTouch(x,y,down); break;
		case 1:GameScreen.ProcessTouch(x,y,down);break;
		case 2:LevelScreen.ProcessTouch(x,y,down);break;
		default:;break;
		}
		
	}
	public void applysize(Canvas canvas){
		sizeX=canvas.getWidth();
		sizeY=canvas.getHeight();
	}

	public void GameGoldInc(int s){
		if (GameScreen==null) return;
		GameScreen.incGold(s);
	}
	public void GameHPInc(float s){
		if (GameScreen==null) return;
		GameScreen.modifyHP(s);
	}
	public void LoadTex(int index, String src){		
		
	}

	public void MakeDraw(Canvas canvas){
		GameScreen.RenderScene(canvas);
	}
	public void MakeMenu(Canvas canvas){
		MenuScreen.RenderScene(canvas);
	}
	public void MakeLevelSelect(Canvas canvas){
		LevelScreen.RenderScene(canvas);
	}
	public void MakeLoader(Canvas canvas){
		LoadScreen.RenderScene(canvas);
	}
	public void ProcessLoading(){
			LoadTextures();
			MainActivity.Backmenu.setBitmaps(textures.get("menu"));
			initMenuScreen();
			setState(0);
			DV.makeThread();
	}
}
