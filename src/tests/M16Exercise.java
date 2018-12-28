package tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Properties;
import java.io.FileInputStream;
import functions.HotelApp_BusinessFunctions;


public class M16Exercise extends HotelApp_BusinessFunctions {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sAppURL, sSharedUIMapPath;

  @Before
  public void setUp() throws Exception {
// Magic code goes here
	System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    //baseUrl = "http://www.adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
// Load configuration file.
    prop.load(new FileInputStream("./Configuration/HA_Configuration.properties"));
    sAppURL = prop.getProperty("sAppURL");
    sSharedUIMapPath = prop.getProperty("SharedUIMap");
// Using the path we got above to to load the UI map
    prop.load(new FileInputStream(sSharedUIMapPath));
  }

  @Test
  public void testMyFirstWebDriver() throws Exception {
    //driver.get(baseUrl + "/HotelApp/");
	//HA_BF_Search (WebDriver driver, String location, String hotelName, String roomType, 
		//String numberRooms, String checkIn, String checkOut, String noAdults, String noChild)
	driver.get(sAppURL);
    HA_BF_Login(driver, "zaidtest", "zaidtest2018");
    HA_BF_Search(driver, "Sydney","Hotel Creek", "Deluxe", "2", "28/10/2019", "28/11/2019", "2", "2");
    HA_BF_book (driver, "Zaid", "Al Dhahi", "5000 Denton Hwy", "1001517676000000", "American Express", "December", "2018", "7676");
    driver.findElement(By.linkText(prop.getProperty("Lnk_BookingHotel_Logout"))).click();
    driver.findElement(By.linkText(prop.getProperty("Lnk_Logout_ClickHeretoLoginAgain"))).click();
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
