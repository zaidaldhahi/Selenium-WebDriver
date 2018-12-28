package functions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class HotelApp_BusinessFunctions {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public void HA_BF_Login (WebDriver driver, String sUserName, String sPassword){
	 // Provide user name.
	 driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
	 driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);
	 // Provide Password.
	 driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).clear();
	 driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);
	 // Click on login button.
	 driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
	 // We will put the verification of the welcome message in the JUnit test file instead of here.
	 }

	public void HA_BF_Search (WebDriver driver, String location, String hotelName, String roomType, 
			String numberRooms, String checkIn, String checkOut, String noAdults, String noChild){
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Location")))).selectByVisibleText(location);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Hotels")))).selectByVisibleText(hotelName);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Room_Type")))).selectByVisibleText(roomType);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Room_Nos")))).selectByValue(numberRooms);
		driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Check_In_Date"))).clear();
		driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Check_In_Date"))).sendKeys(checkIn);
		driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Check_Out_Date"))).clear();
		driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Check_Out_Date"))).sendKeys(checkOut);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Adult_Room")))).selectByValue(noAdults);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_SearchHotel_Child_Room")))).selectByValue(noChild);
		driver.findElement(By.id(prop.getProperty("Btn_SearchHotel_Search"))).click();
		
	}
	public void HA_BF_book (WebDriver driver, String first_name, String last_name, String address, String card_num, String card_type, String exp_month, String exp_year, String CV){
		driver.findElement(By.id(prop.getProperty("Rad_SelectHotel_RadioButton_0"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_SelectHotel_Continue"))).click();
		driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_FirstName"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_FirstName"))).sendKeys(first_name);
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_LastName"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_LastName"))).sendKeys(last_name);
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_Address"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_Address"))).sendKeys(address);
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCNumber"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCNumber"))).sendKeys(card_num);
	    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCType")))).selectByVisibleText(card_type);
	    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCExpMonth")))).selectByVisibleText(exp_month);
	    new Select(driver.findElement(By.id(prop.getProperty("Lst_BookingHotel_CCExpYear")))).selectByVisibleText(exp_year);
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCCvvNumber"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_BookingHotel_CCCvvNumber"))).sendKeys(CV);
	    driver.findElement(By.id(prop.getProperty("Btn_BookingHotel_BookNow"))).click();

	}
	
}
