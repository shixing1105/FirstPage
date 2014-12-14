package com.westengine.myfirstpage;

import java.util.ArrayList;
import java.util.List;

import com.westengine.myfirstpage.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class GuideUI extends Activity implements OnPageChangeListener, OnClickListener {

	private List<ImageView> imageViewList;
	private ViewPager mViewPager;
	private LinearLayout llPointGroup; // �����
	private View selectPoint; // ѡ�еĵ�
	private int basicWidth; // �����֮��ļ��
	private Button btnStartExperience; // ��ʼ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ȥ����, һ��Ҫ��setContentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide);
		
		init();
	}

	private void init() {
		mViewPager = (ViewPager) findViewById(R.id.vp_welcome);
		btnStartExperience = (Button) findViewById(R.id.open);
		llPointGroup = (LinearLayout) findViewById(R.id.ll_welcome_point_group);
		selectPoint = findViewById(R.id.select_point);
		
		initData();
		
		// ������������ViewPager
		MyAdapter mAdapter = new MyAdapter();
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		btnStartExperience.setOnClickListener(this);
		
		// ��ȡ���
		selectPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			/**
			 * ֻҪ���ֵĲ���һ�ı�ͻᴥ���˷���.
			 */
			@Override
			public void onGlobalLayout() {
				// �ѵ�ǰ���¼����Ƴ�����.
				selectPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				
				// ��ȡ���
				basicWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
				System.out.println("basicWidth: " + basicWidth);
			}
		});
	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		int[] imageResIDs = {
				R.drawable.welcome01,
				R.drawable.welcome02,
				R.drawable.welcome03,
				R.drawable.welcome04,
				R.drawable.welcome05,
				R.drawable.welcome06
		};
		
		imageViewList = new ArrayList<ImageView>();
		
		ImageView iv;
		View v;
		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);
			
			// ÿѭ��һ��, ��LinearLayout���������һ��View.	
			v = new View(this);
			v.setBackgroundResource(R.drawable.point_normal);
			LayoutParams params = new LayoutParams(14, 14);
			if(i != 0) {
				params.leftMargin = 15;
			}
			v.setLayoutParams(params);
			llPointGroup.addView(v);
		}
	}
	
	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * container ����ViewPager
		 * object ���ǵ�ǰ��Ҫ���Ƴ���View
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			mViewPager.removeView(imageViewList.get(position));
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// ��ImageView��ӵ�ViewPager��, ���Ұ�ImageView���ػ�ȥ.
			ImageView imageView = imageViewList.get(position);
			container.addView(imageView);
			return imageView;
		}
	}

	/**
	 * ��ҳ�����ڻ�����ʱ�����˷���.
	 * @param position ��ǰ�������ǵڼ�ҳ
	 * @param positionOffset ҳ�滬���İٷֱ�
	 * @param positionOffsetPixels ҳ���ƶ�������
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
//		System.out.println("onPageScrolled: position=" + position + ", positionOffset=" + positionOffset);
		
		float leftMargin = basicWidth * (position + positionOffset);
//		System.out.println("leftMargin: " + leftMargin);
		
		RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) selectPoint.getLayoutParams();
		lp.leftMargin = (int) leftMargin;
		selectPoint.setLayoutParams(lp);
	}

	/**
	 * ���µ�ҳ�汻ѡ��ʱ����.
	 */
	@Override
	public void onPageSelected(int position) {
		if(position == imageViewList.size() -1) {
			btnStartExperience.setVisibility(View.VISIBLE);
		} else {
			btnStartExperience.setVisibility(View.GONE);
		}
	}

	/**
	 * ��ҳ�����״̬�ı�ʱ.
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		
	}

	@Override
	public void onClick(View v) {
		// �洢һ�����ݵ�����, ��ʶ�Ѿ��򿪹�����
		CacheUtils.putBoolean(this, StartUI.IS_FIRST_OPEN, false);
		
		// ����ҳ��
		startActivity(new Intent(this, MainUI.class));
		
		finish();
	}
}
