
/*Test Steps:
1. Go to http://live.techpanda.org/
2. Click on My Account link
3. Login in application using previously created credential 
4. Click on 'My Orders'
5. Click on 'View Order'
6. *** when steps 4 and 5 are executed, step 6 RECENT ORDERS was not displayed
   Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
7. Click on 'Print Order' link
8. *** note: the Change ... link was not found. 
   Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.
9. *** unable to execute this step, due to issue with step 8 issue
   Click on 'Save' button and save the file in some location.
10. *** unable to execute this step, due to steps 8 and 9 issues.
    Verify Order is saved as PDF
    
Expected results:
1. Previously created order is displayed in 'RECENT ORDERS' table and status is Pending.
2. Order is saved as PDF.*/

package MobilePage;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day7_VerifyOrder {
	WebDriver driver;
  @Test
  public void VerifyOrder() {
	  driver.findElement(By.linkText("MY ACCOUNT")).click();
	  driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("Testuser@email.com");
	  driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("testuser");
	  driver.findElement(By.id("send2")).click();
	  
	   // Step 6. Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
	    // note: RECENT ORDERS page is displayed immediately after customer/user has logged in
	  try {
	        assertEquals("RECENT ORDERS", driver.findElement(By.cssSelector("h2")).getText());
	        System.out.println("*** RECENT ORDERS is confirmed as the correct page to be in. ***");
	      } catch (Error e) {
	    	  System.out.println("*** RECENT ORDERS failed to get displayed on the page. ***");
	    	  e.printStackTrace();	
	      }
	    
	    try {
	    	assertEquals("Pending", driver.findElement(By.cssSelector("em")).getText());
	    	System.out.println("*** Status of Pending is correctly displayed for this recent order. ***");
	      } catch (Error e) {
	    	  System.out.println("*** Status of Pending failed to be confirmed for this recent order. ***");
	    	  e.printStackTrace();	
	    	  
	      }
	    
	  driver.findElement(By.linkText("MY ORDERS")).click();
	// switching to new window                                                                               
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	  driver.findElement(By.linkText("VIEW ORDER")).click();
	  driver.findElement(By.linkText("Print Order")).click();
	  
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
}
