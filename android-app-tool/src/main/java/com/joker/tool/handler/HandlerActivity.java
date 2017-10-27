package com.joker.tool.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.joker.tool.R;

import java.util.ArrayList;
import java.util.List;

public class HandlerActivity extends AppCompatActivity{
	private static final String TAG = "HandlerActivity";

	private Handler sendHandler = HandlerFactory.newInstance(this,new HandlerFactory.MessageConsumer(){
		@Override
		protected void handleMessage(Message msg) {
			switch(msg.what) {
				case 1:
					mAdapter.add((String)msg.obj);
					break;
			}
		}
	});

	private Handler secondHandler = HandlerFactory.newHandler(new HandlerFactory.MessageConsumer(){
		@Override
		protected void handleMessage(Message msg) {
			switch(msg.what) {
				case 2:
					final String s = (String)msg.obj;
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							mAdapter.add(s);
							mAdapter.notifyDataSetChanged();
						}
					});
					break;
			}
		}
	},TAG);
	private final String message = "Message --> ";
	private int i;
	private ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler);

		ListView list = (ListView)findViewById(R.id.list);
		List<String> strings = new ArrayList<>();

		mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
		mAdapter.setNotifyOnChange(true);
		list.setAdapter(mAdapter);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	private void initHandler() {
		sendHandler = HandlerFactory.newHandler(new HandlerFactory.MessageConsumer(){
			@Override
			protected void handleMessage(Message msg) {

				Log.d("sendHandler","handleMessage() called with: msg = [" + msg.what + "]");
				switch(msg.what) {
					case 1: {
						HandlerActivity.this.runOnUiThread(new Runnable(){
							@Override
							public void run() {
								mAdapter.add(message + (i++));
								mAdapter.notifyDataSetChanged();
							}
						});
						break;
					}
				}
			}
		},TAG);

		secondHandler = HandlerFactory.newHandler(new HandlerFactory.MessageConsumer(){
			@Override
			protected void handleMessage(Message msg) {
				Log.d("receiveHandler","handleMessage() called with: msg = [" + msg.what + "]");
			}
		},TAG);
	}

	@Override
	protected void onStop() {
		super.onStop();
		HandlerFactory.terminateHandler(TAG);
		secondHandler.removeCallbacksAndMessages(null);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void send(View view) {
		final Message message = Message.obtain(sendHandler);
		message.obj = "new Message --> " + i;
		message.what = 1;
		sendHandler.sendMessage(message);
	}

	public void clear(View view) {
		mAdapter.clear();
	}

	public void secondSend(View view) {
		if(sendHandler != null) {
			final Message message = Message.obtain(secondHandler);
			message.what = 2;
			message.obj = "second Message -->" + i;
			secondHandler.sendMessage(message);
		} else {
			mAdapter.add("Second Handler had been registered");
		}
	}

	public void unregister(View view) {
		HandlerFactory.terminateHandler(TAG);
	}
}
