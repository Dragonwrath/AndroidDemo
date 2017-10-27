package com.joker.tool.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joker.tool.R;
import com.joker.tool.dialog.DialogActivity;

public class NotificationActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
	}

	public void send(View view) {
		NotificationFactory.defaultNotice(this, "hahahhahaha", new Intent(this, DialogActivity.class));
	}

	int progress = 0;

	public void update(View view) {
		NotificationFactory.defaultProgressNotice(this,"This Progress ", 100, progress += 10, null);
	}

	public void bigText(View view) {
//		NotificationFactory.defaultBigTextNotice(this,"This Progress ",null);
	}
}
