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
		//���W�擾
		lastLa = nowLa;
		lastLong = nowLong;
		nowLa = location.getLatitude();
		nowLong = location.getLongitude();
		//������Ԃ����}���ɐ�������̂�h�~
		if(updateCnt == 0){
			lastLa = nowLa;
			lastLong = nowLong;
		}

		//����������
		growthStage += Math.hypot(nowLa - lastLa, nowLong - lastLong);
		
		if((int)(growthStage) >= nowCharaImg && nowCharaImg < charaImgs.length - 1){
			//�摜�̐؂�ւ�
			nowCharaImg = Math.min((int)(growthStage), charaImgs.length - 1);
			ImageView img = (ImageView)activity.findViewById(R.id.charaImg);
			img.setImageResource(charaImgs[nowCharaImg]);
		}
		//���ɐi���`
		if(nowCharaImg >= charaImgs.length - 1){
			float alpha = Math.min((1.f - (float)(charaImgs.length - growthStage)) / 2, 1.f);
			ultraImg.setAlpha(alpha);
		}
		
		//�f�o�b�O�\��
		TextView textView1 = (TextView)activity.findViewById(R.id.levelText);
		textView1.setText("�ܓx:" + nowLa + ", �o�x:" + nowLong + ", ����:" + growthStage);
		updateCnt++;
	}
	
	public int getGrowthStage(){
		return (int)(growthStage);
	}
}
