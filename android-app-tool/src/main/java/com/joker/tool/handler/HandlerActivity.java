package com.joker.tool.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.joker.tool.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "HandlerActivity";

    private Handler sendHandler ;
    private Handler receiveHandler;
    private List<String> strings = new ArrayList<>();
    private final String message = "Message --> ";
    private int i ;
    private ListView mList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mList = (ListView) findViewById(R.id.list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        mList.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sendHandler = HandlerFactory.newHandler(
                new HandlerFactory.Messenger() {
                    @Override
                    void handleMessage(Message msg) {
                        Log.d("sendHandler", "handleMessage() called with: msg = [" + msg.what + "]");
                        switch (msg.what) {
                            case 1: {
                                HandlerActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        strings.add(message + (i++));
                                        mAdapter.notifyDataSetChanged();
                                    }
                                });
                                break;
                            }
                        }
                    }
                }
        ,TAG);

        receiveHandler = HandlerFactory.newHandler(
                new HandlerFactory.Messenger() {
                    @Override
                    void handleMessage(Message msg) {
                        Log.d("receiveHandler", "handleMessage() called with: msg = [" + msg.what + "]");
                    }
                }
        ,TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerFactory.terminateHandler(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void send(View view) {
        sendHandler.sendEmptyMessage(1);


//        Looper.class;
//        Handler.class; new Handler();
//        Messenger.class;
//        MessageQueue;
    }
}
