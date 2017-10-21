package com.example.concurrent.thread;


public class ThreadTest{
	public static void main(String[] args) throws Exception{
//		ignore();
		step_1();
	}

	private static void ignore() {
		final Thread thread = new Thread("1");
		final Thread thread1 = new Thread("1");
		System.out.println("thread1.getName() = " + thread1.getName());
		System.out.println("thread1 = " + thread1);
		System.out.println("thread = " + thread.getName());
		System.out.println("thread = " + thread);

		final boolean equals = thread.equals(thread1);
		System.out.println("equals = " + equals);

		final Thread thread2 = new Thread();
	}

	private static void step_1() throws InterruptedException {
		final Thread thread = new Thread(new TestRunnable());
		System.out.println("thread = " + translateState(thread.getState()));

		thread.start();

		System.out.println("thread = " + translateState(thread.getState()));
		Thread.sleep(2000);
		System.out.println("thread = " + translateState(thread.getState()));

	}
	
	
	private static class TestRunnable implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " thread's state is " + translateState(Thread.currentThread().getState()));
		}
	}

	private static String translateState(Thread.State state) {
		switch(state) {
			case NEW: return "new";
			case RUNNABLE: return "new";
			case BLOCKED: return "new";
			case WAITING: return "new";
			case TIMED_WAITING: return "new";
			case TERMINATED: return "new";
		}
		return "";
	}
}
