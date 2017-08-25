package com.example.projectn;

import java.io.IOException;
import java.io.InputStream;

import com.example.projectn.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener  {
	private float growthStage = 0.f;
	private double lastLa = 0.0, lastLong = 0.0;
	private double nowLa = 0.0, nowLong = 0.0;
	private Integer nowCharaImg = -1;
	private int updateCnt = 0;
	private int[] charaImgs = new int[]{R.drawable.chara1, R.drawable.chara2, R.drawable.chara3, R.drawable.chara4};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button okButton = (Button)findViewById(R.id.gacha);
		okButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				LinearLayout linearLayout = new LinearLayout(getBaseContext());     //�P�D���j�A���C�A�E�g���C���X�^���X��
			    linearLayout.setBackgroundColor(Color.WHITE);           //�Q�D�w�i�F�̎w��
			    
			    setContentView(linearLayout);                           //�R�D���C�A�E�g���R���e���g�ɕ\������
				
			    //�R�[�h�Ńe�L�X�g�r���[�𐶐����Z�b�g
		        TextView textView = new TextView(getBaseContext());                 //�S�D�e�L�X�g�r���[�̃C���X�^���X�𐶐�
		        textView.setText("TEST OK!");                           //�T�D�\�����镶�����Z�b�g
		        textView.setTextSize(50);                               //�U�D�e�L�X�g�̃T�C�Y���Z�b�g
		        textView.setTextColor(Color.RED);                       //�V�D�e�L�X�g�̐F���Z�b�g
		        linearLayout.addView(textView);                         //�W�D���C�A�E�g�Ƀe�L�X�g�r���[��ǉ����ĕ\������
			}
		});
			    
	    LocationManager manager = (LocationManager)getSystemService(LOCATION_SERVICE);
	    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
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
		
		if((int)(growthStage) >= nowCharaImg && nowCharaImg < 4){
			nowCharaImg = (int)(growthStage);
			
			ImageView img = (ImageView)findViewById(R.id.charaImg);
			img.setImageResource(charaImgs[nowCharaImg]);
		}
		
		
		TextView textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setText("�ܓx:" + nowLa + ", �o�x:" + nowLong + ", ����:" + growthStage);
		updateCnt++;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
