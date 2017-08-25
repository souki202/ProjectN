package com.example.projectn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StatusActivity extends Activity {
	private Integer[] atk = new Integer[]{10, 18, 30, 45, 64, 89, 115, 130, 9999};
	private Integer[] def = new Integer[]{7, 13, 21, 32, 48, 70, 102, 115, 9999};
	private Integer[] intel = new Integer[]{2, 4, 8, 20, 255, 592, 2048, 2550, 9999};
	private Integer growthStage = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		Intent intent = getIntent();
		growthStage = intent.getIntExtra("growthStage", 0);
		
		//ステータス表示
		growthStage = Math.min(growthStage, 8);
		
		TextView level = (TextView)findViewById(R.id.levelValue);
		level.setText(((Integer)(growthStage + 1)).toString());
		
		TextView atkValue = (TextView)findViewById(R.id.atkValue);
		atkValue.setText(atk[growthStage].toString());

		TextView defValue = (TextView)findViewById(R.id.defValue);
		defValue.setText(def[growthStage].toString());
		
		TextView intelValue = (TextView)findViewById(R.id.intelValue);
		intelValue.setText(intel[growthStage].toString());

		//画面を戻す
		Button backButton = (Button)findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
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
}
