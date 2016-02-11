import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App{
  public static void main(String[] args) {}

  public static String getChange(Integer cents) {
    HashMap<Integer, String> coinStrings = new HashMap<Integer, String>();
     coinStrings.put(0, "quarter");
     coinStrings.put(1, "dime");
     coinStrings.put(2, "nickel");
     coinStrings.put(3, "penny");

    HashMap<Integer, Integer> coinValues = new HashMap<Integer, Integer>();
     coinValues.put(0, 0);
     coinValues.put(1, 0);
     coinValues.put(2, 0);
     coinValues.put(3, 0);

    // Integer quarters = 0;
    // Integer dimes = 0;
    // Integer nickels = 0;
    // Integer pennies = 0;
    // ArrayList<Integer> coins = new ArrayList<Integer>();
    //   coins.add(quarters);
    //   coins.add(dimes);
    //   coins.add(nickels);
    //   coins.add(pennies);

    String returnString = "";
    while (cents > 24) {
      if(!coinValues.containsKey(0)) {
        coinValues.put(0, 1);
      } else {
        coinValues.put(0, coinValues.get(0) + 1);
      }
      cents -= 25;
    }
    while (cents > 9) {
      if(!coinValues.containsKey(1)) {
        coinValues.put(1, 1);
      } else {
        coinValues.put(1, coinValues.get(1) + 1);
      }
      cents -= 10;
    }
    while (cents > 4) {
      if(!coinValues.containsKey(2)) {
        coinValues.put(2, 1);
      } else {
        coinValues.put(2, coinValues.get(2) + 1);
      }
      cents -= 5;
    }
    while (cents > 0) {
      if(!coinValues.containsKey(3)) {
        coinValues.put(3, 1);
      } else {
        coinValues.put(3 ,coinValues.get(3) + 1);
      }
      cents --;
    }

    for (int i = 0; i < 4; i++) {
        if (coinValues.get(i) > 0) {
          if (coinValues.get(i) == 1) {
          returnString += ("1 " + coinStrings.get(i));
          } else {
            returnString += String.format("%d " + coinStrings.get(i) + "s ", coinValues.get(i));
          }
        }
    }


    // if (quarters >= 1) {
    //   if (quarters == 1) {
    //     returnString += "1 quarter";
    //   } else {
    //     returnString += String.format("%d quarters ", quarters);
    //   }
    // }
    // if (dimes >= 1) {
    //   if (dimes == 1) {
    //     returnString += "1 dime";
    //   } else {
    //     returnString += String.format("%d dimes ", dimes);
    //   }
    // }
    // if (nickels >= 1) {
    //   if (nickels == 1) {
    //     returnString += "1 nickel";
    //   } else {
    //     returnString += String.format("%d nickels ", nickels);
    //   }
    // }
    // if (pennies >= 1) {
    //   if(pennies == 1) {
    //     returnString += "1 penny";
    //   } else {
    //     returnString += String.format("%d pennies", pennies);
    //   }
    // }
    return returnString.trim();
  }
}
