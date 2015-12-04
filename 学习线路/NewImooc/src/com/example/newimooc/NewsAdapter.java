package com.example.newimooc;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter implements OnScrollListener{

	private List<NewsBean> mList;
	private LayoutInflater mInflater;
	private LoadImage loadImage;
	private int mStart;
	private int mEnd;
	public static String[] URLS;
	private boolean mFirstIn;
	public NewsAdapter(Context context, List<NewsBean> list, ListView listView)
	{
		mFirstIn = true;
		mList = list;
		mInflater = LayoutInflater.from(context);
		loadImage = new LoadImage(listView);
		URLS = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			URLS[i] = list.get(i).newsIconUrl;
		}
		listView.setOnScrollListener(this);
		
	}
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.layout_item, null);
			viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.tv_title =  (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tv_content =  (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(viewHolder);
			
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.iv_icon.setImageResource(R.drawable.ic_launcher);
		viewHolder.iv_icon.setTag(mList.get(position).newsIconUrl);//将imageView与url进行绑定,解决同一个列表项的图片会跳动的问题，因为之前的图片有残留
//		new LoadImage().showImageByThread(viewHolder.iv_icon, mList.get(position).newsIconUrl);
		loadImage.showImageByAsycnTask(viewHolder.iv_icon, mList.get(position).newsIconUrl);
		viewHolder.tv_title.setText(mList.get(position).newsTitle);
		viewHolder.tv_content.setText(mList.get(position).newsContent);
		
		return convertView;
	}
	
	class ViewHolder{
		public TextView tv_title, tv_content;
		public ImageView iv_icon;
	}

	//列表滑动过程中都会调用
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mStart = firstVisibleItem;
		mEnd = mStart + visibleItemCount;
		if (mFirstIn && visibleItemCount > 0)
		{
			mFirstIn = false;
			loadImage.loadImages(mStart, mEnd);
		}
	}
	//状态变化后调用
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE)
		{
			//停止状态，加载可见项
			loadImage.loadImages(mStart, mEnd);
		}
		else
		{
			//停止加载任务
			loadImage.cancelAllTasks();
		}
	}

}
