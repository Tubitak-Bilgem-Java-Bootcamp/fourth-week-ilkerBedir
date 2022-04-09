package thread;

import java.util.ArrayList;

import static demo.Main.evenNumbers;
import static demo.Main.oddNumbers;

public class MyThread extends Thread {
  private ArrayList<Integer> numbers;

  public MyThread(int startIndex, int finishIndex) {
    this.numbers = new ArrayList<>();
    for (int i = startIndex; i <= finishIndex; i++) {
      this.numbers.add(i);
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < numbers.size(); i++) {
      Integer number = numbers.get(i);
      //Senkronize blok ekleme
      synchronized (MyThread.class) {
        if (number % 2 == 0) {
          evenNumbers.add(number);
        } else {
          oddNumbers.add(number);
        }
      }

    }
  }
}

