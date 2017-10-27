package com.joker.tool.scanpicture;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.joker.tool.R;
import com.joker.tool.handler.HandlerFactory;

public class JobActivity extends AppCompatActivity{

	private Handler mHandler = HandlerFactory.newHandler(new HandlerFactory.MessageConsumer(){
		@Override
		protected void handleMessage(Message msg) {

		}
	},getLocalClassName());

	private Handler mTestHandler = new Handler();
	private Handler mSecondHandler = HandlerFactory.newInstance(this,new HandlerFactory.MessageConsumer(){
		@Override
		protected void handleMessage(Message msg) {

		}
	});
	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job);
		PhotosContentJob.scheduleJob(this);
	}
}
