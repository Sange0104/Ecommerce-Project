/*Test Steps:
1. Goto http://live.techpanda.org/
2. Click on MOBILE menu
3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
4. Change QTY value to 1000 and click UPDATE button. Expected that an error is displayed 
   "The requested quantity for "Sony Xperia" is not available.
5. Verify the error message
6. Then click on EMPTY CART link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
7. Verify cart is empty
*/

package MobilePage;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Day3_VerifyCart {
	WebDriver driver;
	String message ="* The maximum quantity allowed for purchase is 500.";
  @Test
  public void VerifyCart() throws IOException {
	  driver.findElement(By.xpath("//*[contains(text(),'Mobile')]")).click();
	  driver.findElement(By.xpath("//button[@title='Add to Cart']/span/span")).click();
	 driver.findElement(By.xpath("//input[@title='Qty']")).isEnabled();
	 driver.findElement(By.xpath("//input[@title='Qty']")).clear();
	 driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("1000");
	 driver.findElement(By.xpath("//*[@id=\'shopping-cart-table\']/tbody/tr/td[4]/button")).click();
	 Assert.assertTrue(true, message);
	 driver.findElement(By.id("empty_cart_button")).click();
	  // 7. Verify cart is empty
	    String noItemsStg = ("You have no items in your shopping cart.");
	    String noItemsMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
	    System.out.println("You have no items msg = " + noItemsMsg);
	    
	    try {	    	
 	assertEquals(noItemsStg, noItemsMsg);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }	
	 
	 
	 //capture screen to verify the cart is empty
	 TakesScreenshot TS = (TakesScreenshot)driver;
	 File SourcePath = TS.getScreenshotAs(OutputType.FILE);
	 File TargetPath = new File(".\\screenshots\\Emptycart.png");
	 FileUtils.copyFile(SourcePath, TargetPath);
	 	  
  }
  
  
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.firefoxdriver().setup();
	  driver = new FirefoxDriver();
	  driver.get("http://live.techpanda.org");
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }


}




/* original Script
 public class TestCase3 {
	  private WebDriver driver;
	  private String baseUrl;	
	  
	@BeforeTest
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://live.techpanda.org/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void testTestCase3() throws Exception {
		
		// 1. Go to http://live.techpanda.org
	    driver.get(baseUrl); 
	    
	    // 2. Click on Mobile menu
	    driver.findElement(By.linkText("MOBILE")).click();
	    	  
	    // 3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
	    driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")).click();	
	    
	    // 4. Change QTY value to 1000 and click UPDATE button. 
	    //    Expected "The requested quantity for "Sony Xperia" is not available." error message is displayed. 
	    
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();	    
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");	
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
	   	    
	    // 5. Verify the error message
	    String reqQty = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();	    
	    String msgQty = ("* The requested quantity for \"Sony Xperia\" is not available.");
	    try {	    	
	    	assertEquals(reqQty, msgQty);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    
	    // 6. Click on EMPTY CART link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
	    
	    driver.findElement(By.xpath(".//*[@id='empty_cart_button']")).click();
	    	    
	    // 7. Verify cart is empty
	    String noItemsStg = ("You have no items in your shopping cart.");
	    String noItemsMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
	    System.out.println("You have no items msg = " + noItemsMsg);
	    
	    try {	    	
    	assertEquals(noItemsStg, noItemsMsg);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }	
	      	   
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	  }
	
	}
*/
