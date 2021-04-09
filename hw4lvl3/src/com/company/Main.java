package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.objects.XObject;

public class Main {

    static volatile char baseLetter = 'A';

    public static void main(String[] args) {
        Object monitor = new Object();

        class printLetter implements Runnable{
        private char currentLetter;
        private char nextLetter;

        public printLetter( char currentLetter, char nextLetter) {
            this.currentLetter = currentLetter;
            this.nextLetter = nextLetter;
        }

        @Override
            public void run() {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (baseLetter != currentLetter) {
                            monitor.wait();
                        }
                        System.out.println(currentLetter);
                        baseLetter = nextLetter;
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        }
        new Thread(new printLetter('A','B')).start();
        new Thread(new printLetter('B','C')).start();
        new Thread(new printLetter('C','A')).start();
    }
}
