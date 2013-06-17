package com.wewin.flowprinter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 主程序
 * 
 * @author HCOU
 * @date 2013-6-17
 */
public class MainActivity extends Activity {
	private long exitTime = 0;// 退出倒计时
	private Button print_btn;
	private Button cls_btn;
	private static EditText usr_InputNum, guanglu_Name, usr_Addr;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initData();
	}

	/**
	 * 解析XML流，填充输入框数据
	 * 
	 * @date 2013-6-17
	 */
	private void initData() {
		Intent intent = getIntent();
		String xml_data = intent.getStringExtra("xml_data");
		String[] strs = xml_data.split("\\$");
		if (strs.length >= 3) {
			usr_InputNum.setText(new String(strs[0]));
			guanglu_Name.setText(new String(strs[1]));
			usr_Addr.setText(new String(strs[2]));
		}
	}

	/**
	 * 初始化界面
	 * 
	 * @date 2013-6-14
	 */
	private void initView() {
		print_btn = (Button) findViewById(R.id.print_btn);
		print_btn.setOnClickListener(new BtnOnClickAdapter());
		cls_btn = (Button) findViewById(R.id.cls_btn);
		cls_btn.setOnClickListener(new BtnOnClickAdapter());

		usr_InputNum = (EditText) findViewById(R.id.txt_usr_inputNum);
		guanglu_Name = (EditText) findViewById(R.id.txt_guanglu);
		usr_Addr = (EditText) findViewById(R.id.txt_user_addr);
	}

	/**
	 * 按钮点击事件适配器
	 * 
	 * @author HCOU
	 * @date 2013-6-14
	 */
	class BtnOnClickAdapter implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.print_btn:// 打印按钮
				// 完成打印后回调APK，传数据
				goBackMainActivity();
				break;
			case R.id.cls_btn:// 清楚数据按钮
				clsTheAllContext();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 回调
	 * 
	 * @date 2013-6-17
	 */
	private void goBackMainActivity() {
		Intent mIntent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("status", "OK");
		mIntent.putExtras(bundle);
		ComponentName comp = new ComponentName("com.wewin.xmlsender",
				"com.wewin.xmlsender.XmlSenderActivity");
		mIntent.setComponent(comp);
		mIntent.setAction("android.intent.action.MAIN");
		startActivity(mIntent);

		finish();
	}

	/**
	 * 清楚数据
	 * 
	 * @date 2013-6-14
	 */
	public void clsTheAllContext() {
		usr_InputNum.setText("");
		guanglu_Name.setText("");
		usr_Addr.setText("");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序", 0).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}