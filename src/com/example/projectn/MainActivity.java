package com.example.projectn;

import com.example.projectn.R;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

//
public class MainActivity extends Activity implements LocationListener  {
	private float growthStage = 0.f;
	private double lastLa = 0.0, lastLong = 0.0;
	private double nowLa = 0.0, nowLong = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button okButton = (Button)findViewById(R.id.gacha);
		okButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				LinearLayout linearLayout = new LinearLayout(getBaseContext());     //１．リニアレイアウトをインスタンス化
			    linearLayout.setBackgroundColor(Color.WHITE);           //２．背景色の指定
			    
			    setContentView(linearLayout);                           //３．レイアウトをコンテントに表示する
				
			    //コードでテキストビューを生成＆セット
		        TextView textView = new TextView(getBaseContext());                 //４．テキストビューのインスタンスを生成
		        textView.setText("TEST OK!");                           //５．表示する文字をセット
		        textView.setTextSize(50);                               //６．テキストのサイズをセット
		        textView.setTextColor(Color.RED);                       //７．テキストの色をセット
		        linearLayout.addView(textView);                         //８．レイアウトにテキストビューを追加して表示する
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
		//座標取得
		if(nowLa == 0.0 && nowLong == 0.0){
			nowLa = location.getLatitude();
			nowLong = location.getLongitude();
			lastLa = nowLa;
			lastLong = nowLong;
		}
		else{
			lastLa = nowLa;
			lastLong = nowLong;
			nowLa = location.getLatitude();
			nowLong = location.getLongitude();
		}
		
		//成長させる
		growthStage += Math.hypot(nowLa - lastLa, nowLong - lastLong);
		
		TextView textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setText("緯度:" + nowLa + ", 経度:" + nowLong + ", 成長:" + growthStage);
		
		
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
