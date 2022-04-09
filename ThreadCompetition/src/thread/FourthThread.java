package thread;

import java.util.ArrayList;

import static demo.Main.evenNumbers;
import static demo.Main.oddNumbers;

public class FourthThread extends Thread{
  @Override
  public void run() {
    ArrayList<Integer> last2500 = new ArrayList<>();
    for (int i = 7501; i <= 10000; i++) {
      last2500.add(i);
    }
    for (int i = 0; i < last2500.size(); i++) {
      Integer number = last2500.get(i);
      if (number %2==0){
        evenNumbers.add(number);
      }else{
        oddNumbers.add(number);
      }
    }
  }
}
