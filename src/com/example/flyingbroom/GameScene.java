package com.example.flyingbroom;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameScene extends Scene{
	private Paint p;
	private float HP;
	private float score;
	private int gold;
	private Object menu,restart;
	private int mode;
	private Unit Witch;	
	private Background Back;
	public ArrayList<Coin> coins = new ArrayList<Coin>();
	public ArrayList<Bat> bats = new ArrayList<Bat>();
	public GameScene(Game ENGINE, int mode){
		super(ENGINE);
		this.mode=mode;
		Witch = new Unit((float)0.25,(float)0.18);
		p = new Paint();
		menu = new Object(0.06f,0.09f);
		restart = new Object(0.06f,0.09f);
	    Back = new Background((float)1.0,(float)1.0);
	    Witch.setBitmaps(Engine.textures.get("witch1"),Engine.textures.get("witch2"),Engine.textures.get("witch3"));
	    
	    switch(this.mode){
	    case 0:Back.setBitmaps(Engine.textures.get("back11"),Engine.textures.get("back12"),Engine.textures.get("back13")); break;
	    case 1:Back.setBitmaps(Engine.textures.get("back21"),Engine.textures.get("back22"),Engine.textures.get("back13")); break;
	    case 2:Back.setBitmaps(Engine.textures.get("back21"),Engine.textures.get("back32"),Engine.textures.get("back13"));
	    }	    
		
	    menu.setBitmaps(Engine.textures.get("m7"));
		restart.setBitmaps(Engine.textures.get("m9"));
	    Back.setCors(0, 0);
	    Witch.setCors(0, 0);
	    
	    Log.d("MENU CORS",Engine.sizeX+" "+menu.W()+" "+2*k);
	    menu.UpdateSize();
	    restart.UpdateSize();
	    menu.setCors(Engine.sizeX-menu.W()-2*k, 2*k);
	    restart.setCors(menu.X()-restart.W()-2*k, 2*k);
	    Back.setHspeed(-3);
	    HP=100;
	    gold=0;	    
	    Log.d("IMP","GAME SCENE CREATED...");
	    MainActivity.paused=false;
	    MainActivity.speeder=1.5f;
	}
	public void verifyCoins(){
		int i=0;
		Coin m;
		while(i<coins.size()){
			m=coins.get(0);
			if (m.X()<-m.W() || m.Y()<-m.H())
				coins.remove(i);
			else
				i++;
		}
		
	}
	public void verifyBats(){
		int i=0;
		Bat m;
		while(i<bats.size()){
			m=bats.get(0);
			if (m.X()<-m.W() || m.Y()<-m.H())
				bats.remove(i);
			else
				i++;
		}
		
	}
	
	public void genCoins(){
		verifyCoins();
		if (Engine.timer.nextCoin()==false)
			return;
		if (coins.size()<20){
			coins.add(new Coin(0.05f,0.07f));
			genCoin((coins.get(coins.size()-1)));
		}
	}
	public void genBats(){
		Random rand = new Random();
		verifyBats();
		if (Engine.timer.nextCoin()==false)
			return;
		if (rand.nextInt(10)<8) return;
		if (bats.size()<10){
			bats.add(new Bat(0.1f,0.14f));
			genBat(bats.get(bats.size()-1));
		}
	}
	public void drawCoins(Canvas canvas){
		if (coins.size()==0) return;
		for (int i=0;i<coins.size();++i){
			coins.get(i).Draw(canvas);
		}		
	}
	public void drawBats(Canvas canvas){
		if (bats.size()==0) return;
		for (int i=0;i<bats.size();++i){
			bats.get(i).Draw(canvas);
		}		
	}
	public void modifyHP(float r){
		HP+=r;
		if (HP<0){			
			HP=0;
			Engine.setState(0);
		}
		else
		if (HP>100)
			HP=100;
	}
	public void genBat(Bat c){
		c.setBitmaps(Engine.textures.get("bat1"),
				Engine.textures.get("bat2"),
				Engine.textures.get("bat3"),
				Engine.textures.get("bat4"),
				Engine.textures.get("bat5"),
				Engine.textures.get("bat6"),
				Engine.textures.get("bat7"),
				Engine.textures.get("bat8"),
				Engine.textures.get("bat9"));
		
	}
	public void genCoin(Coin c){
		Random rand = new Random();
		int select=rand.nextInt(35);
		if (select<25){
			c.setType(rand.nextInt(3));
			switch(c.getType()){
			case 0:c.setBitmaps(Engine.textures.get("gr1"));break;
			case 1:c.setBitmaps(Engine.textures.get("gr2"));break;
			case 2:c.setBitmaps(Engine.textures.get("gr3"));break;
			}			
			c.setEffect(5);
			return;
		}
		if (select<30){
			c.setBitmaps(Engine.textures.get("d1"),Engine.textures.get("d2"),Engine.textures.get("d3"));
			c.setEffect(-30);
			return;
		}
		if (select<35){
			c.setBitmaps(Engine.textures.get("c1"),Engine.textures.get("c2"),Engine.textures.get("c3"));
			c.setEffect(0);
			return;
		}
	}
	public void MakeRestart(){
		gold=0;
		score=0;
		HP=100f;
		MainActivity.speeder=1.5f;
		coins.clear();
		Engine.CoinsNum=0;
		if (MainActivity.paused){
			MainActivity.paused=false;
			menu.setBitmaps(Engine.textures.get("m7"));
		}
		
	}
	public void addGold(){
		gold++;
	}
	public void incGold(int s){
		gold+=s;
	}
	public void addScore(){
		score+=0.5*MainActivity.speeder;
	}
	public void incScore(int s){
		score+=s;
	}
	public void ProcessTouch(int mx, int my, int down){
		super.ProcessTouch(mx,my,down);
		if (menu.isAt(mx, my) && down==1 && MainActivity.paused==false){
			MainActivity.paused=true;
			menu.setBitmaps(Engine.textures.get("m8"));
			Witch.setVspeed(0);
			return;
		}
		if (menu.isAt(mx, my) && down==1 && MainActivity.paused==true){
			MainActivity.paused=false;
			menu.setBitmaps(Engine.textures.get("m7"));
			Witch.setVspeed(0);
			return;
		}
		if (restart.isAt(mx, my) && down==1){
			MakeRestart();
			return;
		}
		if (down==1){
			if (my<Witch.Y()+Witch.H()/2)
				Witch.setVspeed(-15f*k);
			else
				Witch.setVspeed(15f*k);
		}
		else
			if (down==0)
				Witch.setVspeed(0);
     }
	public void RenderScene(Canvas canvas){
		Engine.applysize(canvas);
		p.setColor(Color.WHITE);
		Back.Draw(canvas);
		Witch.Draw(canvas);
		Witch.ProcessCollisions(coins);
		drawCoins(canvas);
		genCoins();
		drawBats(canvas);
		genBats();
		p.setTextSize(48*k);
		p.setColor(Color.BLACK);
		canvas.drawText("Score: "+(int)score+" Gold: "+(int)gold+" HP:"+(int)HP,48*k+2,48*k+2,p);
		p.setColor(Color.WHITE);
		canvas.drawText("Score: "+(int)score+" Gold: "+(int)gold+" HP:"+(int)HP,48*k,48*k,p);
		p.setColor(Color.GRAY);
		canvas.drawRect(0.05f*Engine.sizeX, 0.9f*Engine.sizeY,0.95f*Engine.sizeX, 0.95f*Engine.sizeY, p);
		p.setColor(Color.GREEN);
		canvas.drawRect(0.06f*Engine.sizeX, 0.91f*Engine.sizeY,0.06f*Engine.sizeX+0.88f*Engine.sizeX*HP/100f, 0.94f*Engine.sizeY, p);
		menu.Draw(canvas);
		restart.Draw(canvas);
		if (MainActivity.paused) return;
		addScore();
		modifyHP(-0.05f*MainActivity.speeder);
		if (MainActivity.speeder<3f)
		MainActivity.speeder+=0.0003;
	}
}
