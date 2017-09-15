package net.jcip.part_15.item_1;


import java.util.ArrayList;
import java.util.List;

public class InterviewTest {
    public static void main(String[] args) {
        forText();
        Runnable run = new ConRunnable();
    }

    static void forText() {
        int j = 2;
        for (int i = 7 ; i >  0; i -= 2) {
            j *= 2;
        }
        System.out.println(j);
    }


    static class HasStatic {
        private static int x = 100;

        public static void main(String[] args) {
            HasStatic h1 = new HasStatic();
            h1.x++;
            HasStatic h2 = new HasStatic();
            h2.x++;
            h1 = new HasStatic();
            h1.x++;
            HasStatic.x--;
            System.out.println("x = " + x);
            List list  = new ArrayList();
        }
    }
}
