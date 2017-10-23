package com.example.java.util;


import java.util.Timer;
import java.util.TimerTask;

public class TimerTest{
	public static void main(String[] args) {
		final Timer timer = new Timer();
		final TimerTask timerTask = new TimerTask(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("Work is finished");
				}
			}
		};

		timer.schedule(timerTask, 1000);
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					timer.cancel();
				}
			}
		}).start();
	}
}
