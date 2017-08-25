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
	private int[] charaImgs = new int[]{R.drawable.chara1, R.drawable.chara2, R.drawable.chara3, R.drawable.chara4, R.drawable.chara5, R.drawable.chara6, R.drawable.chara7};
	private ImageView ultraImg;
	
	public GrowCharacter(MainActivity mainActivity, Context context) {
		// TODO Auto-generated constructor stub
		handler = new Handler();
	    this.context = context;
	    activity = mainActivity;
	    
		ultraImg = (ImageView)mainActivity.findViewById(R.id.charaImg2);
		ultraImg.setAlpha(0.f);
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		//座標取得
		lastLa = nowLa;
		lastLong = nowLong;
		nowLa = location.getLatitude();
		nowLong = location.getLongitude();
		//初期状態だけ急激に成長するのを防止
		if(updateCnt == 0){
			lastLa = nowLa;
			lastLong = nowLong;
		}

		//成長させる
		growthStage += Math.hypot(nowLa - lastLa, nowLong - lastLong);
		
		if((int)(growthStage) >= nowCharaImg && nowCharaImg < charaImgs.length - 1){
			//画像の切り替え
			nowCharaImg = Math.min((int)(growthStage), charaImgs.length - 1);
			ImageView img = (ImageView)activity.findViewById(R.id.charaImg);
			img.setImageResource(charaImgs[nowCharaImg]);
		}
		else if(nowCharaImg >= charaImgs.length - 1){
			float alpha = Math.min((1.f - (float)(charaImgs.length - growthStage)) / 2, 1.f);
			ultraImg.setAlpha(alpha);
		}
		
		//デバッグ表示
		TextView textView1 = (TextView)activity.findViewById(R.id.levelText);
		textView1.setText("緯度:" + nowLa + ", 経度:" + nowLong + ", 成長:" + growthStage);
		updateCnt++;
	}
	
	public int getGrowthStage(){
		return (int)(growthStage);
	}
}
