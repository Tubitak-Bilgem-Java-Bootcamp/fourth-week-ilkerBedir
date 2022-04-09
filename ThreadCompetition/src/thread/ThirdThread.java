package thread;

import java.util.ArrayList;

import static demo.Main.evenNumbers;
import static demo.Main.oddNumbers;

public class ThirdThread extends Thread {
  @Override
  public void run() {
    ArrayList<Integer> between5000To7500 = new ArrayList<>();
    for (int i = 5001; i <= 7500; i++) {
      between5000To7500.add(i);
    }
    for (int i = 0; i < between5000To7500.size(); i++) {
      Integer number = between5000To7500.get(i);
      if (number % 2 == 0) {
        evenNumbers.add(number);
      } else {
        oddNumbers.add(number);
      }
    }
  }
}
