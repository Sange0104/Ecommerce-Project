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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day8_ReOrder {
	WebDriver driver;
	public String firstName = "BERRY";    // These testdata stuff will be placed    
	  public String lastName = "BERRYTEN";  // in a TestData EXCEL file at some stage
	  public String vEmail = "Berry.Berrysix@gmail.com";
	  public String vPW = "123456";
  @Test
  public void Reorder() throws InterruptedException {
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
	  
	 // driver.findElement(By.linkText("Proceed to Checkout")).click();
	  
	  driver.findElement(By.xpath("//button[@type='button']")).click();  // this could be the Proceed to Checkout button 
	  
	  // switching to new window                                                                                  
	    for (String handle : driver.getWindowHandles()) {                                             
	    	driver.switchTo().window(handle);
	    	} 
	    
	    //
	    //*  BILLING ADDRESS
	    try {
	    	Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
	    	int bAddrSize = bAddr.getOptions().size();
	    	bAddr.selectByIndex(bAddrSize-1); 
		    } catch (Exception e) {
		    	//e.printStackTrace();
		    	System.out.println("No dropdown element present");
		    }
	  
	    driver.findElement(By.id("billing:firstname")).clear();
	    driver.findElement(By.id("billing:firstname")).clear();
	    driver.findElement(By.id("billing:firstname")).sendKeys(firstName); 
	    driver.findElement(By.id("billing:lastname")).clear();
	    driver.findElement(By.id("billing:lastname")).sendKeys(lastName); 
	    driver.findElement(By.id("billing:company")).clear(); 
	    
	    driver.findElement(By.id("billing:street1")).clear();
	    driver.findElement(By.id("billing:street1")).sendKeys("148 Crown Street"); 
	    new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']"))).selectByIndex(14);
	    Thread.sleep(5000);	    
	    driver.findElement(By.id("billing:city")).clear();	
	    driver.findElement(By.id("billing:city")).sendKeys("Sydney"); 
	    driver.findElement(By.id("billing:region")).clear();
	    driver.findElement(By.id("billing:region")).sendKeys("New South Wales");
	    driver.findElement(By.id("billing:postcode")).clear();
	    driver.findElement(By.id("billing:postcode")).sendKeys("2000");
	    driver.findElement(By.id("billing:telephone")).clear();
	    driver.findElement(By.id("billing:telephone")).sendKeys("8850 6789"); 
	    
	    // check radio button to "Ship to different address" 
      driver.findElement(By.id("billing:use_for_shipping_no")).click();
	    
      // click the CONTINUE button 
	   
	    // after the click above, it is still on same web page: live.guru99.com/index.php/checkout/onepage/
	    driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();
	    
	    
	    // switching to new window                                                                                
	    for (String handle : driver.getWindowHandles()) {  
	    	driver.switchTo().window(handle);
	    	}  
	    Thread.sleep(2000); 
	    
	 
	    //*  SHIPPING ADDRESS
	    try {
	    	Select sAddr = new Select(driver.findElement(By.name("shipping_address_id")));
	    	int sAddrSize = sAddr.getOptions().size();
	    	sAddr.selectByIndex(sAddrSize-1); 
		    } catch (Exception e) {
		    	//e.printStackTrace();
		    	System.out.println("No dropdown element present");
		    }
	    
	  
	    Thread.sleep(2000);   
	   
	    driver.findElement(By.id("shipping:firstname")).clear();
	    driver.findElement(By.id("shipping:firstname")).clear();
	    driver.findElement(By.id("shipping:firstname")).sendKeys(firstName); 
	    driver.findElement(By.id("shipping:lastname")).clear();
	    driver.findElement(By.id("shipping:lastname")).sendKeys(lastName); 
	    driver.findElement(By.id("shipping:company")).clear(); 
	    
	    driver.findElement(By.id("shipping:street1")).clear();
	    driver.findElement(By.id("shipping:street1")).sendKeys("50 Berry Street"); 
	    driver.findElement(By.id("shipping:street2")).clear();
	    // shipping country must be selected first from dropdown
	    new Select(driver.findElement(By.xpath("//select[@id='shipping:country_id']"))).selectByIndex(14); 
	    // once Australia is selected, then the region and city becomes simply a text input, instead of dropdown
	    driver.findElement(By.id("shipping:region")).clear();
	    driver.findElement(By.id("shipping:region")).sendKeys("New Sounth Wales"); 
	    driver.findElement(By.id("shipping:city")).clear();
	    driver.findElement(By.id("shipping:city")).sendKeys("Sydney"); 
	    driver.findElement(By.id("shipping:postcode")).clear();
	    driver.findElement(By.id("shipping:postcode")).sendKeys("2000"); 
	    driver.findElement(By.id("shipping:telephone")).clear();
	    driver.findElement(By.id("shipping:telephone")).sendKeys("8034 1234");
	        
	    Thread.sleep(3000);	    
	    
	    driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click(); 
	    			    
	    // switching to new window                                                                                
	    for (String handle : driver.getWindowHandles()) {  
	    	driver.switchTo().window(handle);
	    	}  
	    Thread.sleep(3000); 
	    
	 // In Shipping Method, Click Continue 			    
	    driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click(); 
	   	 
	    Thread.sleep(2000);
	    
	    // In Payment Information select 'Check/Money Order' radio button. Click Continue			    
	    driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
	    
	    
	    Thread.sleep(2000);				   
	    driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click(); 
	   
	    Thread.sleep(2000);
	    
	    // Click 'PLACE ORDER' button 
	    driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();
	    
	    Thread.sleep(2000);
	    
	    // Verify Order is generated. Note the order number 
	    String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();	
	    System.out.println("*** Your order number for your record = " + orderNum);
	  
	  
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

