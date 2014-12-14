package com.westengine.myfirstpage;

import com.westengine.myfirstpage.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class StartUI extends Activity {

	// �ж��û��Ƿ��ǵ�һ�δ�Ӧ��
	public static final String IS_FIRST_OPEN = "is_first_open";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		/**
		 * ģ��һ��ʱ��,������Ҫ�޸�Ϊ����ʱ���������
		 */
		// TODO
		new Thread() {
			public void run() {
				try {

					Thread.sleep(2000); // ���߳���Ҫ��Ⱦ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						enterWelcome(); // ������һ������
					}

				});

			};
		}.start();

	}

	private void enterWelcome() {
		boolean isFirstOpen = CacheUtils.getBoolean(StartUI.this,
				IS_FIRST_OPEN, true);
		if (isFirstOpen) {
			// ��ǰ�ǵ�һ�δ�, ��ת������ҳ��
			System.out.println("��ת������ҳ��");
			startActivity(new Intent(StartUI.this, GuideUI.class));
		} else {
			// �Ѿ��򿪹�, ��ת��������
			System.out.println("��ת��������");
			startActivity(new Intent(StartUI.this, MainUI.class));
		}
		finish();
	}
}
