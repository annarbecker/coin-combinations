import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get ("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/coinCombinations.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get ("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/change.vtl");

      String stringCents = request.queryParams("userCents");
      Integer cents = Integer.parseInt(stringCents);
      String userChange = App.getChange(cents);

      model.put("userChange", userChange);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

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
          returnString += ("1 " + coinStrings.get(i)) + " ";
          } else {
            returnString += String.format("%d " + coinStrings.get(i) + "s ", coinValues.get(i));
          }
        }
    }

    return returnString.trim();
  }
}
