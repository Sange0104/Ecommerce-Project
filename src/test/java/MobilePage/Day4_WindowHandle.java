/*      
Test Steps:
1. Goto http://live.techpanda.org/
2. Click on MOBILE menu
3. In mobile products list , click on Add To Compare for 2 mobiles (Sony Xperia & Iphone)
4. Click on COMPARE button. A popup window opens
5. Verify the pop-up window and check that the products are reflected in it
   Heading "COMPARE PRODUCTS" with selected products in it.
6. Close the Popup Windows
*/

package MobilePage;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day4_WindowHandle {
	WebDriver driver;
  //@Test
  public void CompareProducts() throws InterruptedException {
	  driver.findElement(By.xpath("//*[contains(text(),'Mobile')]")).click();
	  driver.findElement(By.xpath("//html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
	  driver.findElement(By.xpath("//button[@title='Compare']/span")).click();
	  Set <String> handles = driver.getWindowHandles();
	  System.out.println("Title of the current window" +driver.getTitle());
	  
	  for(String newwindow : handles) {
		  driver.switchTo().window(newwindow);
	  }
		System.out.println("Number of windows opened :" + handles.size());
		System.out.println("Title of the current window : " + driver.getTitle());
		System.out.println("Name of the current window : " + driver.getWindowHandle());
		
		driver.findElement(By.xpath("//button[@title = 'Close Window']"));
		
  }
  
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.firefoxdriver().setup();
	  driver = new FirefoxDriver();
	  driver.get("http://live.techpanda.org");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }
  
  //Original Script
  @Test
  public void testTestCase4() throws Exception {
	
	  
    // 2. Click on Mobile menu
    driver.findElement(By.linkText("MOBILE")).click();
  
    
    // 3. In mobile products list , click on Add To Compare for 2 mobiles (Iphone & Sony Xperia)
    
    //note: store the title of the 2 mobiles for comparison for verification later when popup page comes up
    driver.findElement(By.xpath(" /html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
    String mainMobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();  // text captured - upperCase "IPHONE"
    System.out.println("mainMobile1 = "+mainMobile1);
   
    driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click(); 
    
   String mainMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured - upperCase "SONY XPERIA"
    System.out.println("mainMobile2 = "+mainMobile2);

    
    // 4. Click on COMPARE button. A popup window opens	   
    driver.findElement(By.xpath("//button[@title='Compare']")).click();	    
    Thread.sleep(1000);
    
    // switching to new window
    for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
    
    // 5. Verify the pop-up window and check that the products are reflected in it
    //    Heading "COMPARE PRODUCTS" with selected products in it.
    String strHead = ("COMPARE PRODUCTS");
    String compHead = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();	
    System.out.println("compHead = "+compHead);
    String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();  // text captured is "IPHONE" in uppercase
    String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured "SONY XPERIA" in uppercase
    System.out.println("popupMobile1 = "+popupMobile1);
    System.out.println("popupMobile2 = "+popupMobile2);
    Thread.sleep(1000);
    // to check the popup heading/title
    try {	    	
    	assertEquals(strHead, compHead);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }	
    // to check the 2 mobiles selected are the two in the popup - this is tp check the IPhone
    try {	    	
    	assertEquals(mainMobile1, popupMobile1);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }	
    // to check the 2 mobiles selected are the two in the popup - this is to check Sony X
    try {	    	
    	assertEquals(mainMobile2, popupMobile2);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }	
    	    
    // 6. Close the Popup Windows
    driver.findElement(By.xpath("//button[@title='Close Window']")).click();
    
    // switching to new window
    for (String handle : driver.getWindowHandles()) {
    driver.switchTo().window(handle);
    }	    
  }
}
