package net.jcip.part_15.item_1;

public class ConRunnable implements Runnable {
    private int i;
    @Override
    public void run() {
        for(;;){
            System.out.println(Thread.currentThread() + "  i++ = " + i++);
        }
    }

    public static void main(String[] args) {
        Runnable run = new ConRunnable();
        final Thread t1 = new Thread(run);
        final Thread t2 = new Thread(run);
        t1.start();
        t2.start();
    }
}