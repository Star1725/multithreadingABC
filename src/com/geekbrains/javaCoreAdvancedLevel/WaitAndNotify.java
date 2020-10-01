package com.geekbrains.javaCoreAdvancedLevel;

public class WaitAndNotify {
    private final Object mon = new Object();
    private volatile char currentChar = 'A';

    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        Thread t1 = new Thread(() -> {
            waitAndNotify.printChar('A', 'B');
        });
        Thread t2 = new Thread(() -> {
            waitAndNotify.printChar('B', 'C');
        });
        Thread t3 = new Thread(() -> {
            waitAndNotify.printChar('C', 'A');
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public synchronized void printChar(char printChar, char nextChar){
        //synchronized(mon){
            for (int i = 0; i < 5; i++) {
                while (currentChar != printChar){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (currentChar == 'C') System.out.print(currentChar + " ");
                else System.out.print(currentChar);
                currentChar = nextChar;
                this.notifyAll();
            }
        //}
    }
}
