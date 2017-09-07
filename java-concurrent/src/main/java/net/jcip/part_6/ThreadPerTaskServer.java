package net.jcip.part_6;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPerTaskServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket accept = socket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
            ExecutorService pool = Executors.newCachedThreadPool();
            ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        }


    }
}
