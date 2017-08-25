package com.example.projectn;

import com.example.projectn.R;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener  {
	private Gacha gacha = new Gacha(this, getBaseContext());
	private GrowCharacter grow;
	int suji;
	private String[] rea = new String[]{"SR","SSR","NR","SEACRET","N","R","UR","L"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button okButton = (Button)findViewById(R.id.gacha);
		okButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
						suji = gacha.execute();
				        TextView textView = new TextView(getApplicationContext());
				        textView.setText(" "+rea[suji]);
				        textView.setTextSize(100f);
				        setContentView(textView);
				    }

			
		});
		
		//ステータス画面
		Button statusButton = (Button)findViewById(R.id.statusButton1);
		statusButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, StatusActivity.class);
				intent.putExtra("growthStage", grow.getGrowthStage());
				startActivity(intent);
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
		grow.onLocationChanged(location);
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
