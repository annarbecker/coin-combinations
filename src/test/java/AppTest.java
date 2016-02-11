import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("");
  }

  @Test
  public void getChange_changeCentToPenny_penny() {
    App testApp = new App();
    assertEquals("1 penny", testApp.getChange(1));
  }

  @Test
  public void getChange_changeCentsToPennies_pennies() {
    App testApp = new App();
    assertEquals("2 pennies", testApp.getChange(2));
  }

  @Test
  public void getChange_changeCentsToNickel_nickel() {
    App testApp = new App();
    assertEquals("1 nickel", testApp.getChange(5));
  }

  @Test
  public void getChange_changeCentsToDime_dime() {
    App testApp = new App();
    assertEquals("1 dime", testApp.getChange(10));
  }

  @Test
  public void getChange_changeCentsToDime_dimes() {
    App testApp = new App();
    assertEquals("2 dimes", testApp.getChange(20));
  }

  @Test
  public void getChange_changeCentsToQuarter_quarter() {
    App testApp = new App();
    assertEquals("1 quarter", testApp.getChange(25));
  }

  @Test
  public void getChange_changeCentsToMixedCoins_coins() {
    App testApp = new App();
    assertEquals("3 quarters 2 dimes 4 pennies", testApp.getChange(99));
  }
}
