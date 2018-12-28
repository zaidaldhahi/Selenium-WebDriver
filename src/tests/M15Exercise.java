package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class M15Exercise {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
// Magic code goes here
	System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://www.adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testM15Exercise() throws Exception {
    driver.get(baseUrl + "/HotelApp/index.php");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("zaidtest");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("zaidtest2018");
    driver.findElement(By.id("login")).click();
    new Select(driver.findElement(By.id("location"))).selectByVisibleText("Sydney");
    new Select(driver.findElement(By.id("hotels"))).selectByVisibleText("Hotel Hervey");
    new Select(driver.findElement(By.id("room_type"))).selectByVisibleText("Super Deluxe");
    driver.findElement(By.id("datepick_out")).clear();
    driver.findElement(By.id("datepick_out")).sendKeys("25/10/2019");
    driver.findElement(By.id("Submit")).click();
 // Verifications
    int night_price = Integer.parseInt(driver.findElement(By.xpath(".//*[@id='price_night_0']")).getAttribute("value").replace("AUD $ ", ""));
    int total_price = night_price*7+10;
    assertEquals(total_price, Integer.parseInt(driver.findElement(By.xpath(".//*[@id='total_price_0']")).getAttribute("value").replace("AUD $ ", "")));
  
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
