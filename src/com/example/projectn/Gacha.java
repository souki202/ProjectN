package com.example.projectn;

import android.os.Handler;
import com.example.projectn.MainActivity;
import android.content.Context;

public class Gacha {
	private Handler handler;
	private Context context;

	public Gacha(MainActivity mainActivity, Context context) {
		// TODO Auto-generated constructor stub
		handler = new Handler();
	    this.context = context;
	}

	public int execute() {
		double rand = Math.random()*9;
		int num= (int)rand;

		return num;
	}
}
