package com.wewin.flowprinter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 欢迎界面
 * 
 * @author HCOU
 * @time 2013.05.27 17:25:00
 */
public class WelcomeActivity extends Activity {
	private Handler mHandler;
	private String xml_data;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		// 初始化界面
		initView();
	}

	public void initView() {
		Intent intent = getIntent();
		xml_data = intent.getStringExtra("xml_data");

		mHandler = new Handler();
		// 延迟3秒加载欢迎界面
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				goMainActivity();
			}
		}, 3000);
	}

	public void goMainActivity() {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("xml_data", xml_data);
		intent.putExtras(bundle);
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();// 将活动推向后台
	}
}