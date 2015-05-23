package com.example.flyingbroom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ClickableViewAccessibility")
class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    public DrawThread drawThread;

    public DrawView(Context context) {
      super(context);
      Log.d("IMP","DRAWVIEW CONSTRUCTOR");
      getHolder().addCallback(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
        int height) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      int down;
      switch(event.getAction()){
      case MotionEvent.ACTION_UP:
    	  down=0; break;
      case MotionEvent.ACTION_DOWN:
    	  down=1; break;
      default: down=-1; break;
      }
      MainActivity.Engine.setMouseCors((int)event.getX(), (int)event.getY(), down);
  	  return true;
    }
    
    public void makeThread(){
  	  Log.d("IMP","CREATING THREAD");
        drawThread = new DrawThread(this.getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
      Log.d("IMP","SURFACE CREATED");
    	drawScreen();
    	MainActivity.Engine.ProcessLoading();
    }

	public void destroySurface(){
        drawThread.setRunning(false);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
      destroySurface();
  	Log.d("IMP","SURFACE DESTROYED");
    }
    
    public void drawScreen(){
    	SurfaceHolder surfaceHolder = this.getHolder();
    	Canvas canvas;
    	canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas(null);
            if (canvas == null)
            	return;
            MainActivity.Engine.MakeLoader(canvas);
          } finally {
            if (canvas != null) {
            	Log.d("IMP","NOT NULL");
              surfaceHolder.unlockCanvasAndPost(canvas);
              postInvalidate();
            }
            else
            Log.d("IMP","NULL");
    }
    }

    class DrawThread extends Thread {

      private boolean running = false;
      private SurfaceHolder surfaceHolder;
      public DrawThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
      }

      public void setRunning(boolean running) {
        this.running = running;
      }

      @Override
      public void run() {
        Canvas canvas = null;
        Log.d("IMPORTANT","RUN");
        while (running) {
        	Log.d("IMPORTANT","RUNNING");
        	while (MainActivity.Engine.getState()==-1 && running){
    	          canvas = null;
    	          try {
    	            canvas = surfaceHolder.lockCanvas(null);
    	            if (canvas == null)
    	              continue;
    	            MainActivity.Engine.MakeLoader(canvas);
    	          } finally {
    	            if (canvas != null) {
    	              surfaceHolder.unlockCanvasAndPost(canvas);
    	              postInvalidate();
    	            }
    	          }
    	          MainActivity.Engine.ProcessLoading();
          	}
        	while (MainActivity.Engine.getState()==0 && running){
  	          canvas = null;
  	          try {
  	            canvas = surfaceHolder.lockCanvas(null);
  	            if (canvas == null)
  	              continue;
  	            MainActivity.Engine.MakeMenu(canvas);
  	          } finally {
  	            if (canvas != null) {
  	              surfaceHolder.unlockCanvasAndPost(canvas);
  	            }
  	          }
        	}
        	while (MainActivity.Engine.getState()==1 && running){
    	          canvas = null;
    	          try {
    	            canvas = surfaceHolder.lockCanvas(null);
    	            if (canvas == null)
    	              continue;
    	            MainActivity.Engine.MakeDraw(canvas);
    	            MainActivity.Engine.timer.Update();
    	          } finally {
    	            if (canvas != null) {
    	              surfaceHolder.unlockCanvasAndPost(canvas);
    	            }
    	          }
          	}
        	while (MainActivity.Engine.getState()==2 && running){
	          canvas = null;
	          try {
	            canvas = surfaceHolder.lockCanvas(null);
	            if (canvas == null)
	              continue;
	            MainActivity.Engine.MakeLevelSelect(canvas);
	          } finally {
	            if (canvas != null) {
	              surfaceHolder.unlockCanvasAndPost(canvas);
	            }
	          }
          }
        }
      }
    }

  }
