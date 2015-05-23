package com.example.flyingbroom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Object {
	protected float x,y; //Коориданаты спрайта
	protected int width,height; //Ширина и высота исходного битмапа
	protected float vspeed,hspeed; //Вертикальная и горизонтальная скорости объекта
	protected float w,h; //Ширина и высота битмапа, адаптированного под данный экран
	protected float sx,sy; //Ширина и высота битмапа, выраженные в долях от ширины и высоты экрана
	protected Bitmap[] bmps; //Битмап
	protected boolean animated;
	protected long lastframe=0;
	protected Paint paint = new Paint(); //Рисовалка
	protected int frame=0;
	public Object(float s1,float s2){ //Конструктор
		x=y=0;
		sx=s1;
		sy=s2;
		vspeed=hspeed=0;
		frame=0;
	}
	public int W(){
		return (int)w;
	}
	public int H(){
		return (int)h;
	}
	public void setVspeed(float r){ //Установка вертикальной скорости
		vspeed=r;
	}
	public void setHspeed(float r){//Установка горизонтальной скорости
		hspeed=r;
	}
	public float Y(){//Возврат координаты Y
		return y;
	}
	public float X(){//Возврат координаты X
		return x;
	}
	public boolean NextFrame(){
		long t = System.currentTimeMillis();
		if (t-lastframe>30){
			lastframe=System.currentTimeMillis();
			return true;
		}
		else
			return false;
	}
	public void setBitmaps(Bitmap... a){
		if (a.length>1)
			animated=true;
		else
			animated=false;
		bmps=new Bitmap[a.length];
		for (int i=0; i<a.length; i++){
			bmps[i]=a[i];
		}
		width=bmps[0].getWidth();
		height=bmps[0].getHeight();
	}
	public void setCors(float x1, float y1){//Задание координат для отображения
		x=x1;
		y=y1;
	}
	public void MoveY(float Y){//Перемещение объекта по координате Y на заданное число пикселей
		y+=Y*MainActivity.speeder;
	}
	public void MoveX(float X){//Перемещение объекта по координате X на заданное число пикселей
		x+=X*MainActivity.speeder;
	}
	public boolean isAt(float x1, float y1){
		if (x1<x || x1>x+w || y1<y || y1>y+h)
			return false;
		else
			return true;
	}
	public boolean isCollision(Object ob2){
		if ( x > ob2.X()+ob2.W() || x+w < ob2.X() || y > ob2.Y()+ob2.H() || y+h < ob2.Y())
			return false;
		else
			return true;
	}
	public void UpdateSize(){
		  w=sx*MainActivity.Engine.sizeX;//Обновление текущих ширины и высоты битмапа, адаптированных под разрешение экрана
		  h=sy*MainActivity.Engine.sizeY;
	}
	public void Draw(Canvas canvas){//Прорисовка объекта
		  Matrix matrix = new Matrix();//Создание матрицы для искажений
		  UpdateSize();
		  matrix.reset();
		  matrix.setTranslate(x, y);//Перемещение матрицы в соответствии с текущими координатами объекта
		  matrix.preScale(w/width, h/height);//Масштабирование
		  canvas.drawBitmap(bmps[frame], matrix, paint);//Вывод на канвас
		  if (MainActivity.paused) return;
		  MoveY(vspeed);
		  MoveX(hspeed);
		  if (animated && NextFrame())
				 frame=frame>bmps.length-2?0:frame+1;	
	}
}
