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


public class ConfigurationFileTest extends HotelApp_BusinessFunctions {
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
	driver.get(sAppURL);
    HA_BF_Login(driver, "zaidtest", "zaidtest2018");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Location")))).selectByVisibleText("Sydney");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Room_Nos")))).selectByVisibleText("2 - Two");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Adult_Room")))).selectByVisibleText("2 - Two");
    driver.findElement(By.id(prop.getProperty("Btn_SearchHotel_Search"))).click();
    driver.findElement(By.id(prop.getProperty("Rad_SelectHotel_RadioButton_1"))).click();
    driver.findElement(By.id(prop.getProperty("Btn_SelectHotel_Continue"))).click();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_FirstName"))).clear();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_FirstName"))).sendKeys("Zaid");
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_LastName"))).clear();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_LastName"))).sendKeys("Al Dhahi");
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_Address"))).clear();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_Address"))).sendKeys("5000 Denton Hwy");
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCNumber"))).clear();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCNumber"))).sendKeys("1001517676000000");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCType")))).selectByVisibleText("American Express");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCExpMonth")))).selectByVisibleText("December");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCExpYear")))).selectByVisibleText("2018");
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCCvvNumber"))).clear();
    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCCvvNumber"))).sendKeys("7676");
    driver.findElement(By.id(prop.getProperty("Btn_BookingHotel_BookNow"))).click();
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
