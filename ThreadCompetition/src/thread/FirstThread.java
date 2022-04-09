package thread;

import demo.Main;

import java.util.ArrayList;
import static demo.Main.evenNumbers;
import static demo.Main.oddNumbers;

public class FirstThread extends Thread{
  @Override
  public void run() {
    ArrayList<Integer> first2500 = new ArrayList<>();
    for (int i = 1; i <= 2500; i++) {
      first2500.add(i);
    }
    for (int i = 0; i < first2500.size(); i++) {
      Integer number = first2500.get(i);
      if (number %2==0){
        evenNumbers.add(number);
      }else{
        oddNumbers.add(number);
      }
    }
  }
}
