package com.example.projectn;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectn.MainActivity;
import android.content.Context;
import android.location.Location;

public class GrowCharacter {
	private Handler handler;
	private Context context;
	private MainActivity activity;
	
	private float growthStage = 0.f;
	private double lastLa = 0.0, lastLong = 0.0;
	private double nowLa = 0.0, nowLong = 0.0;
	private Integer nowCharaImg = -1;
	private int updateCnt = 0;
	private int[] charaImgs = new int[]{R.drawable.chara1, R.drawable.chara2, R.drawable.chara3, R.drawable.chara4};

	
	public GrowCharacter(MainActivity mainActivity, Context context) {
		// TODO Auto-generated constructor stub
		handler = new Handler();
	    this.context = context;
	    activity = mainActivity;
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		//À•WŽæ“¾
		lastLa = nowLa;
		lastLong = nowLong;
		nowLa = location.getLatitude();
		nowLong = location.getLongitude();
		//‰Šúó‘Ô‚¾‚¯‹}Œƒ‚É¬’·‚·‚é‚Ì‚ð–hŽ~
		if(updateCnt == 0){
			lastLa = nowLa;
			lastLong = nowLong;
		}

		//¬’·‚³‚¹‚é
		growthStage += Math.hypot(nowLa - lastLa, nowLong - lastLong);
		
		if((int)(growthStage) >= nowCharaImg && nowCharaImg < 3){
			nowCharaImg = Math.min((int)(growthStage), 3);
			
			ImageView img = (ImageView)activity.findViewById(R.id.charaImg);
			img.setImageResource(charaImgs[nowCharaImg]);
		}
		
		TextView textView1 = (TextView)activity.findViewById(R.id.textView1);
		textView1.setText("ˆÜ“x:" + nowLa + ", Œo“x:" + nowLong + ", ¬’·:" + growthStage);
		updateCnt++;
	}
}
