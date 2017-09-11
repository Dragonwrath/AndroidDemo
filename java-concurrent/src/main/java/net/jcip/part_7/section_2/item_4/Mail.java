package net.jcip.part_7.section_2.item_4;


import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mail {

    boolean checkMain(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (checkMail(host)) {
                            hasNewMail.set(true);
                        }
                    }


                });
            }
        } finally {
            exec.shutdown();;
            final boolean b = exec.awaitTermination(timeout, unit);
        }

        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        return false;
    }
}
