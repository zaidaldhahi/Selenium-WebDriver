package tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.io.FileInputStream;
import functions.HotelApp_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
// It's important to add  the 2 above and the 1 below codes for parametric test
@RunWith(JUnitParamsRunner.class)

public class DynamicUIObjectTest extends HotelApp_BusinessFunctions {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sAppURL, sSharedUIMapPath;

  @Before
  public void setUp() throws Exception {
// Magic code goes here
	System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
    driver = new FirefoxDriver();
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
  @FileParameters("src/Excel/Search Hotel.csv")
  
  public void testMyFirstWebDriver(int testCaseNo, String location, String hotelName, String roomType, String numberRooms, String checkIn,
		  String checkOut, String noAdults, String noChild, String ExpPriceNight, String totalPrice) throws Exception {
	driver.get(sAppURL);
    HA_BF_Login(driver, "zaidtest", "zaidtest2018");
    HA_BF_Search(driver, location, hotelName, roomType, numberRooms, checkIn, checkOut, noAdults, noChild);
    
    String actualPerNight = driver.findElement(By.id("price_night_0")).getAttribute("value");
    String TotalPrice = driver.findElement(By.id("total_price_0")).getAttribute("value");
    assertEquals(ExpPriceNight, actualPerNight);
    assertEquals(totalPrice, TotalPrice);
    
    HA_BF_book (driver, "Zaid", "Al Dhahi", "5000 Denton Hwy", "1001517676000000", "American Express", "December", "2018", "7676");
// Synchronization Explicit Wait Code
    WebDriverWait myWaitDuration = new WebDriverWait(driver, 10);
    myWaitDuration.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("Btn_BookingHotel_Logout"))));
    
    String orderNum = driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_OrderNo"))).getAttribute("value");
    
    driver.findElement(By.id(prop.getProperty("Btn_BookingHotel_MyItinerary"))).click();
    driver.findElement(By.id(prop.getProperty("Txt_BookedItinerary_SearchOrderid"))).sendKeys(orderNum);
    driver.findElement(By.id(prop.getProperty("Btn_BookedItinerary_Go"))).click();
    
    Thread.sleep(10_000);
    
    driver.findElement(By.xpath(".//*[@value='Cancel "+orderNum+"']")).click();
    //driver.findElement(By.xpath(".//*(@value= 'Cancel "+orderNum+"']")).click();
    //.//*[@id='btn_id_272781']
    
    Alert javascriptAlert = driver.switchTo().alert();
    System.out.println(javascriptAlert.getText());
    javascriptAlert.accept();
    
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
