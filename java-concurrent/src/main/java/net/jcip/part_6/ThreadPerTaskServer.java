package net.jcip.part_6;


import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPerTaskServer {
    public static void main(String[] args) throws IOException {
//        socketTest();
        probablePrimeTest();
    }

    private static void socketTest() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket accept = socket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
            Thread thread = new Thread();
            thread.interrupt();
            ExecutorService pool = Executors.newCachedThreadPool();
            ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        }
    }

    private static void probablePrimeTest() {
        BigInteger one = BigInteger.ONE;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append("---").append(one = one.nextProbablePrime()).append("-----");
            if (i % 10 == 9) {
                builder.append("\r\n");
            }
        }

        System.out.println(builder.toString());
    }
}
