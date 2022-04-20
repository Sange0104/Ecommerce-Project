/*      
Test Steps:
1. Goto http://live.techpanda.org/
2. Click on MOBILE menu
3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
4. Click on Sony Xperia mobile
5. Read the Sony Xperia mobile from detail page. Product value in list and details page should be equal ($100). 
*/

package MobilePage;
//import static org.testng.AssertJUnit.assertAll;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Day2_VerifyEqual {
	//private static final String Soft_Assert = null;
	WebDriver driver;
	SoftAssert Soft_Assert = new SoftAssert(); 
  @Test
  public void VerifyEqual() {
	  
	  driver.findElement(By.xpath("//*[contains(text(),'Mobile')]")).click();
		
		String actualval1String=  driver.findElement(By.xpath("//a[@title ='Sony Xperia']")).getText();
		System.out.println(actualval1String);
		//int actualVal1 = Integer.parseInt(actualval1String);
		//System.out.println(actualVal1);
		boolean price1 = driver.findElement(By.id("product-price-1")).isDisplayed();
		System.out.println("The price1 value is:" +price1 );
		String actualP1 = driver.findElement(By.id("product-price-1")).getText();
		System.out.println(actualP1);
		//int actualV1 = Integer.parseInt(actualP1);
		
		driver.findElement(By.xpath("//a[@title ='Sony Xperia']")).click();
		boolean price2 = driver.findElement(By.className("price")).isDisplayed();
		System.out.println("The price2 value is:" +price2 );
		String actualP2 = driver.findElement(By.className("price")).getText();
		System.out.println(actualP2);
		//int actualV2 = Integer.parseInt(actualP2);
		
		if(compareStringVals(actualP1,actualP2)) {
			 System.out.println("Both Values match.");
		} else {
			 System.out.println("Both Values not match.");
		  }   
		Soft_Assert.assertAll();
  }
				 
		private boolean compareStringVals(String actualP1, String actualP2) {
			try{
				   //If this assertion will fail, It will throw exception and catch block will be executed.
				   Assert.assertEquals(actualP1, actualP2);
				   }catch(Throwable t){
					   Soft_Assert.fail("Actual Value '"+actualP1+"' And Expected Value '"+actualP2+"' Do Not Match.");   
	return false;
				   }
			  //If  Integer values match, return true.
			  return true;
}

		//@Test  //from the original script	 
		 public void testTestCase2() throws Exception {
		 driver.findElement(By.linkText("MOBILE")).click();	
		  
	    // 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100) 	    	      
	    String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
	   
	    // 4. Click on Sony Xperia mobile 	   
	    driver.findElement(By.id("product-collection-image-1")).click();
	    
	    // 5. Read the XPeria mobile price from details page
	    String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
	    	    
	    //  Product price in list and details page should be equal ($100)
	    try {
	        assertEquals(XPeriaPrice, detailPrice); 
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
		}
  
 
  
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.firefoxdriver().setup();
	  driver = new FirefoxDriver();
	  driver.get("http://live.techpanda.org");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
  }
  

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
