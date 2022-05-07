/*     Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.  
Test Steps:
1. Go to http://live.techpanda.org/
2. Click on my account link
3. Click Create an Account link and fill New User information except Email ID
4. Click Register
5. Verify Registration is done. Expected account registration done.
6. Go to TV menu
7. Add product in your wish list - use product - LG LCD
8. Click SHARE WISHLIST 
9. In next page enter Email and a message and click SHARE WISHLIST
10.Check wishlist is shared. Expected wishlist shared successfully. */

package MobilePage;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day5_CreateAccount {
	WebDriver driver;
	String lastname = "USER3";
	  String firstname = "TEST3";
  @Test
  public void CreateAccount() {
	  driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/a")).click();
	  driver.findElement(By.linkText("My Account")).click();
	  String ExpectedTitle ="LOGIN OR CREATE AN ACCOUNT";
	  WebElement Title1 = driver.findElement(By.xpath("//div[@class='page-title']/h1"));
	  String ActualTitle= Title1.getText();
	  System.out.println("Verify the title page " +ActualTitle);
	  try {
		assertEquals(ExpectedTitle,ActualTitle);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  driver.findElement(By.xpath("//a[@title='Create an Account']/span")).click();
	  driver.findElement(By.id("firstname")).sendKeys(firstname);
	  driver.findElement(By.id("lastname")).sendKeys(lastname);
	  driver.findElement(By.id("password")).sendKeys("testuser3");
	  driver.findElement(By.id("confirmation")).sendKeys("testuser3");
	
	  /*String Expectmsg ="This is a required field.";
	  WebElement msg = driver.findElement(By.xpath("//*[@id=\'advice-required-entry-email_address\']"));
	  System.out.println(msg);
	  String verifymsg=msg.getText();
	  System.out.println("Verify the message :" +verifymsg);
	  try {
		assertEquals(Expectmsg,verifymsg);
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}*/
	  driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Testuser2@email.com");
	  driver.findElement(By.xpath("//button[@title = 'Register']")).click();
	  for(String handle:driver.getWindowHandles()) {
		  driver.switchTo().window(handle);
		  
	  }
	  
	  //Verify the registration done
	  
	  String vWelcome = ("WELCOME, "+firstname+" "+lastname+"!");
	  String tWelcome= driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
	  System.out.println("Welcome user " + tWelcome);
	  
	  try {
		assertEquals(vWelcome,tWelcome);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  driver.findElement(By.linkText("TV")).click();
	  driver.findElement(By.xpath("//a[@class='link-wishlist']")).click();
	  driver.findElement(By.xpath("//button[@title='Share Wishlist']/span")).click();
	  driver.findElement(By.id("email_address")).clear();
	  driver.findElement(By.id("email_address")).sendKeys("Testuser3@email.com");
	  driver.findElement(By.xpath("//button[@title='Share Wishlist']/span")).click();
	  
	  String vWishList = "Your Wishlist has been shared.";
	  String wishList=driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
	  System.out.println("Message displayed :" + wishList);
	  
	  try {	    	
	    	assertEquals(vWishList, wishList);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
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
