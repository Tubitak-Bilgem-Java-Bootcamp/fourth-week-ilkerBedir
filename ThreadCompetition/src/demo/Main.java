package demo;

import thread.MyThread;

import java.util.ArrayList;

public class Main {
  //Ortak tek ve çift listeler
  public static ArrayList<Integer> oddNumbers = new ArrayList<>();
  public static ArrayList<Integer> evenNumbers = new ArrayList<>();

  public static void main(String[] args) {
    ArrayList<Thread> threads = new ArrayList<>();
    //Thread Oluşturma, Sayıları başlangıç ve bitiş indexlerini alır
    threads.add(new MyThread(1, 2500));
    threads.add(new MyThread(2501, 5000));
    threads.add(new MyThread(5001, 7500));
    threads.add(new MyThread(7501, 10000));
    //threadler çalışıyor
    for (int i = 0; i < threads.size(); i++) {
      threads.get(i).start();
    }

    System.out.println("--------TEK SAYILAR-------");
    for (int i = 0; i < oddNumbers.size(); i++) {
      System.out.println(oddNumbers.get(i));
    }

    System.out.println("--------ÇİFT SAYILAR-------");
    for (int i = 0; i < evenNumbers.size(); i++) {
      System.out.println(evenNumbers.get(i));
    }

    System.out.println("Tek Sayıların Adeti : " + oddNumbers.size());
    System.out.println("Çift Sayıların Adeti : " + evenNumbers.size());
  }
}
