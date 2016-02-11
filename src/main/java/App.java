import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {}

  public static String getChange(Integer cents) {
    Integer pennies = 0;
    String returnString;
    while (cents > 0) {
      pennies += 1;
      cents --;
    }
    if (pennies == 1) {
      returnString = "1 penny";
    } else {
      returnString = String.format("%d pennies", pennies);
    }
    return returnString;
  }
}
