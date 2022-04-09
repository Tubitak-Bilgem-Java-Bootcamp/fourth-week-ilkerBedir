package thread;

import java.util.ArrayList;

import static demo.Main.evenNumbers;
import static demo.Main.oddNumbers;

public class SecondThread extends Thread{
  @Override
  public void run() {
    ArrayList<Integer> between2500To5000 = new ArrayList<>();
    for (int i = 2501; i <= 5000; i++) {
      between2500To5000.add(i);
    }
    for (int i = 0; i < between2500To5000.size(); i++) {
      Integer number = between2500To5000.get(i);
      if (number %2==0){
        evenNumbers.add(number);
      }else{
        oddNumbers.add(number);
      }
    }

  }
}
