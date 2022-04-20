/*  Verify you are able to change or reorder previously added product
 *  Test Data = QTY = 10
Test Steps:
1. Go to http://live.techpanda.org/
2. Click on my account link
3. Login in application using previously created credential
4. Click on 'REORDER' link , change QTY & click Update
5. Verify Grand Total is changed
6. Complete Billing & Shipping Information
7. Verify order is generated and note the order number

Expected outcomes:
1) Grand Total is Changed
2) Order number is generated
*/

package MobilePage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day8_ReOrder {
	WebDriver driver;
  @Test
  public void Reorder() {
	  driver.findElement(By.linkText("MY ACCOUNT")).click();
	  for(String handle: driver.getWindowHandles()) {
		  driver.switchTo().window(handle);
	  }
	  driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("Testuser@email.com");
	  driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("testuser");
	  driver.findElement(By.id("send2")).click();
	  
	  driver.findElement(By.linkText("REORDER")).click();
	  String GTBefore = driver.findElement(By.xpath(" //*[@id=\'shopping-cart-totals-table\']/tfoot/tr/td[2]/strong/span")).getText();
	  System.out.println("The Grand total before change :" +GTBefore);
	  driver.findElement(By.xpath("//input[@title='Qty']")).clear();
	  driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("10");
	  driver.findElement(By.xpath("//button[@title='Update']")).click();
	  String GTAfter = driver.findElement(By.xpath(" //*[@id=\'shopping-cart-totals-table\']/tfoot/tr/td[2]/strong/span")).getText();
	  System.out.println("The Grand total after change :" +GTAfter);
	  
	  try {
		Assert.assertNotEquals(GTBefore, GTAfter);
	} catch (Exception e) {
		System.out.println("The Test fails as the values are not updated");
	}
	  System.out.println("The Test passes as the values are updated");
	  
	  driver.findElement(By.linkText("PROCEED TO CHECKOUT")).click();
	  
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

