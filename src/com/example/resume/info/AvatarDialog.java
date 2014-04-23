package com.example.resume.info;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.resume.R;
import com.example.resume.utils.BitMapUtils;
import com.example.resume.utils.Global;

public class AvatarDialog extends Dialog {

	Context context;
	public AvatarDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
    public AvatarDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_show_avatar);
        ImageView avartar = (ImageView) findViewById(R.id.avatar);
        
		Bitmap bm = BitMapUtils.getAvatarBitmap(InfoActivity.avatar_path);
		if(bm == null)
		{
			Drawable avatardrawable = context.getResources().getDrawable(R.drawable.avatar);
			avartar.setImageDrawable(avatardrawable);
			
		}else
		{
			avartar.setImageBitmap(bm);
		}
    }

}
