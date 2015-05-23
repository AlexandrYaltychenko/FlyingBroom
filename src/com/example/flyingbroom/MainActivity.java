package com.example.flyingbroom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
 public static Game Engine;
 public static Object Backmenu;
 public static float speeder;
 public static boolean gaming=false;
 public static boolean paused=false;
 public static DrawView DV;
@Override
  protected void onCreate(Bundle savedInstanceState) {
Log.d("IMPORTANT","ONCREATE! ");
    super.onCreate(savedInstanceState);
	    if (gaming==false){
	    	DV = new DrawView(this);
	    	Engine = new Game(this);
	    	Engine.setState(-1);
		    Backmenu = new Object(1f,1f);
	    	Engine.initLoadScreen();
	    	Engine.RunThread();
	    }
	    else
	    	Engine.RunThread();
    setContentView(Engine.getLayout());
  }
  
  @Override   
  protected void onResume() {
	  Log.d("IMPORTANT","RESUME GAMING: "+MainActivity.gaming);
	  super.onResume();
  }
  
  @Override   
  protected void onStart() {
	  Log.d("IMPORTANT","START GAMING: "+MainActivity.gaming);
	  super.onStart();
  }

  @Override
  protected void onPause() {
	  super.onPause();
	  Engine.Pause();
	  Log.d("IMPORTANT","PAUSE GAMING: "+MainActivity.gaming);
  }
  @Override
  public void onBackPressed() {
	  switch(Engine.getState()){
	  case 0:System.exit(0); break;
	  case 1:Engine.setState(2); break;
	  case 2:Engine.setState(0); break;
	  }
    	  
  }

}
