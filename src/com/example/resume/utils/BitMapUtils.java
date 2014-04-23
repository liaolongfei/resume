package com.example.resume.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class BitMapUtils {
	
	 public static void storeImageToSDCARD(Bitmap colorImage, String ImageName,String path) {
		 
		  File file = new File(path);
		  if (!file.exists()) {
		   file.mkdirs();
		  }
		  File imagefile = new File(file, ImageName);
		   try {
		    imagefile.createNewFile();
		    FileOutputStream fos = new FileOutputStream(imagefile);
		    colorImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
		    fos.flush();
		    fos.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		
	 }

	 
	 
	 public static Bitmap getAvatarBitmap(String path) {
		 
		Options options=new Options();  
        options.inSampleSize=1;   
        Bitmap bm = null;
        try {
        	File f = new File(path); 
        	if(!f.exists())
        	{
        		return null;
        	}
        	
        	bm=BitmapFactory.decodeFile(path, options);  
        	return bm;
		   } catch (Exception e) {
			   return null;
		   }
		
	 }

}
