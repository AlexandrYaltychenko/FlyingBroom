package com.example.flyingbroom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TexturePack {
private ArrayList<Texture>textures = new ArrayList<Texture>();
private Context context;
TexturePack(Context context){
	this.context=context;
}
public Bitmap get(String src){
	for (Texture i:textures){
		if (i.getID()==src)
			return i.getBitmap();			
	}
	return null;
}
public Bitmap get(int index){
	if (index<textures.size())
	return textures.get(index).getBitmap();
	return null;
}
public void addTextures(String...src){
	for (String i:src){
		textures.add(new Texture(i,BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(i , "drawable", context.getPackageName()))));
	}
}
public void removeTexture(String src){
	for (int i=0; i<textures.size();i++){
		if (textures.get(i).getID()==src)
			textures.remove(i);
	}
}
public void removePrefix(String...src){
	for (Texture i:textures)
		for (String j:src)
			if (i.getID().contains(j))
				i=null;
}
}
