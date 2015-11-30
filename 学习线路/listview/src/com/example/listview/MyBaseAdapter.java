package com.example.listview;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	private static final String TAG = "MyBaseAdapter";
	private List<ItemBean> m_List;
	private LayoutInflater mInflater;
	
	private long time = 0;
	
	public MyBaseAdapter(Context context, List<ItemBean> list)
	{
		m_List = list;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_List.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_List.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		long start = System.nanoTime();
		//����ʽ������������������������������������
		//û��ʹ�û��棬ϵͳ���ش�
		/*View view = mInflater.inflate(R.layout.item, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
		TextView title = (TextView) view.findViewById(R.id.tv_title);
		TextView content = (TextView) view.findViewById(R.id.tv_content);
		
		ItemBean itemBean = m_List.get(position);
		imageView.setImageResource(itemBean.ImageResID);
		title.setText(itemBean.ItemTitle);
		content.setText(itemBean.ItemContent);
		long end = System.nanoTime();
		time += end - start;
		LOG("sumTime " + time);
		return view;*/
		//����ʽ������������������������������������
		
		//��ͨʽ������������������������������������
		//�����������View,��findViewById��ռ�úܴ���Դ
		/*if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item, null);
		}
		ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView content = (TextView) convertView.findViewById(R.id.tv_content);
		
		ItemBean itemBean = m_List.get(position);
		imageView.setImageResource(itemBean.ImageResID);
		title.setText(itemBean.ItemTitle);
		content.setText(itemBean.ItemContent);
		long end = System.nanoTime();
		time += end - start;
		LOG("sumTime " + time);
		return convertView;*/
		//��ͨʽ������������������������������������
		
		//����ʽ������������������������������������
		//ͨ��ViewHolder��������
		ViewHolder viewHolder;
		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
			viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(viewHolder);//��viewHolder��convertView����
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ItemBean itemBean = m_List.get(position);
		viewHolder.imageView.setImageResource(itemBean.ImageResID);
		viewHolder.tv_title.setText(itemBean.ItemTitle);
		viewHolder.tv_content.setText(itemBean.ItemContent);
		long end = System.nanoTime();
		time += end - start;
		LOG("sumTime " + time);
		return convertView;
		//����ʽ������������������������������������
	}
	
	class ViewHolder
	{
		public ImageView imageView;
		public TextView tv_title;
		public TextView tv_content;
	};

	private void LOG(String string) {
		// TODO Auto-generated method stub
		Log.d(TAG, string);
	}
}
