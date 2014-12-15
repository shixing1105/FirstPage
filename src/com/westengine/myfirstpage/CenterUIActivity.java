package com.westengine.myfirstpage;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.westengine.myforstpage.fragment.ContentFragment;
import com.westengine.myforstpage.fragment.LeftMenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class CenterUIActivity extends SlidingFragmentActivity implements OnClickListener {

	private ImageView btn_selding_menu;
	private ImageView btn_add;
	private TextView tv_my_first_page;
	private TextView tv_hot_first_page;
	private ImageView iv_doit;
	private ImageView iv_my_first_pager;
	private ImageView iv_hot_first_page;
	
	private PopupWindow popupWindow;
	
	// ������Fragment�ı�ʶ
	private final String CONTENT_FRAGMENT_TAG = "content";
	// ���˵�Fragment�ı�ʶ
	private final String LEFT_MENU_FRAGMENT_TAG = "left_menu";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ȥ����, һ��Ҫ��setContentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		btn_selding_menu = (ImageView) findViewById(R.id.btn_selding_menu);
		btn_add = (ImageView) findViewById(R.id.btn_add);
		tv_my_first_page = (TextView) findViewById(R.id.tv_my_first_page);
		tv_hot_first_page = (TextView) findViewById(R.id.tv_hot_first_page);
		iv_doit = (ImageView) findViewById(R.id.iv_doit);
		iv_my_first_pager = (ImageView) findViewById(R.id.iv_my_first_pager);
		iv_hot_first_page = (ImageView) findViewById(R.id.iv_hot_first_page);
		
		setContentView(R.layout.main_content); // ���������沼��
		setBehindContentView(R.layout.main_left_menu); // �������˵�����.
		
		SlidingMenu mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT); // ����ֻ�����˵����Ի�����.
		// ����������Ļ�����Ի������˵�
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setBehindOffset(200); // �ڻ���ʱ, �������������������Ļ��200������
		
		btn_selding_menu.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		tv_my_first_page.setOnClickListener(this);
		tv_hot_first_page.setOnClickListener(this);
		iv_doit.setOnClickListener(this);
	}

	//TODO
	private void initpopupWindow() {
		if(popupWindow == null){
			//setContentView(R.layout.popupwindow);
			final View convertView = View.inflate(getApplicationContext(), R.layout.popupwindow,null);
			TextView tv_share = (TextView) convertView.findViewById(R.id.tv_share);
			TextView tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
			
			tv_share.setOnClickListener(this);
			tv_delete.setOnClickListener(this);
			
			popupWindow = new PopupWindow(this);
			popupWindow.setWidth(180);
			popupWindow.setHeight(180);
			//popupWindow.setBackgroundDrawable(background);
			popupWindow.setContentView(convertView);
			
			popupWindow.setOutsideTouchable(true);
			popupWindow.setFocusable(true);
			popupWindow.setAnimationStyle(R.style.popWindowAnim);
		}
		popupWindow.showAsDropDown(iv_doit, 0, 0);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_selding_menu:
			Toast.makeText(this, "�����໬�˵�,�ԵȾͿ���", 0).show();
			break;
		case R.id.btn_add:
			//Toast.makeText(this, "�����໬���ģ��ҳ��,�ԵȾͿ���", 0).show();
			initFragment();
			break;
		case R.id.tv_my_first_page:
			Toast.makeText(this, "���ҵĳ�ҳ����,�ԵȾͿ���", 0).show();
			tv_my_first_page.setTextColor(getResources().getColor(R.color.pressed));
			iv_my_first_pager.setImageResource(R.drawable.mine_btn_selected);
			tv_hot_first_page.setTextColor(getResources().getColor(R.color.normal));
			iv_hot_first_page.setImageResource(R.drawable.hot_btn_deselected);
			break;
		case R.id.tv_hot_first_page:
			Toast.makeText(this, "�����ų�ҳ����,�ԵȾͿ���", 0).show();
			tv_my_first_page.setTextColor(getResources().getColor(R.color.normal));
			iv_my_first_pager.setImageResource(R.drawable.mine_btn_deselected);
			tv_hot_first_page.setTextColor(getResources().getColor(R.color.pressed));
			iv_hot_first_page.setImageResource(R.drawable.hot_btn_selected);
			break;
		case R.id.iv_doit:
			initpopupWindow();
			break;
		case R.id.tv_share:
			//tv_share.setImageResource(R.drawable.share_s_btn);
			break;
		case R.id.tv_delete:
			break;
		default:
			break;
		}
	}

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		//ft.replace(R.id.fl_main_content, new ContentFragment(), CONTENT_FRAGMENT_TAG);
		ft.replace(R.id.fl_main_left_menu, new LeftMenuFragment(), LEFT_MENU_FRAGMENT_TAG);
		ft.commit();
	}
	/**
	 * ��ȡ��ҳ��Ĳ˵�Fragment
	 * @return
	 */
	public LeftMenuFragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment leftMenuFragment = (LeftMenuFragment) fm.findFragmentByTag(LEFT_MENU_FRAGMENT_TAG);
		return leftMenuFragment;
	}
	
/*	*//**
	 * alt + shift + J ��ע��
	 * ��ȡ�����������fragment
	 * @return
	 *//*
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(CONTENT_FRAGMENT_TAG);
		return contentFragment;
	}*/
}

