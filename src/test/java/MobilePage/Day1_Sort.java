package MobilePage;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Day1_Sort {
	WebDriver driver;
  @Test
  public void Sort() throws IOException {
	  Reporter.log("The current Url is:" + driver.getCurrentUrl());
	  String valueTitle = driver.getTitle();
	  
	  System.out.println("Verify the page title : " +valueTitle );
	  Reporter.log(valueTitle);
	  Assert.assertTrue(true, valueTitle);
	  
	  driver.findElement(By.xpath("//*[contains(text(),'Mobile')]")).click();
	  String Title2 = driver.getTitle();
	  Reporter.log("The current page title is : " +Title2);
	  Assert.assertTrue(true, Title2);
	  
	 new Select (driver.findElement(By.xpath("//select[@title = 'Sort By']"))).selectByVisibleText("Name");
	
	 Reporter.log("The mobile is sorted by Name");
	 
	 TakesScreenshot TS = (TakesScreenshot)driver;
	 File Sourcepath = TS.getScreenshotAs(OutputType.FILE);
	 File Targetpath =new File(".\\screenshots\\page01.png");
	 FileUtils.copyFile(Sourcepath, Targetpath);
	 Reporter.log("The screenshot is taken" + driver.getPageSource());
	 
	
	  
	  
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

/*
 package eCommerceLive;

//import static org.junit.Assert.fail;

import java.io.File;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;


public class TestCase1 {
	  private WebDriver driver;
	  private String baseUrl;
	  public int scc = 0;
	  
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeMethod
	@BeforeTest
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
		// Step 1 Goto http://live.techpanda.org/
	    baseUrl = "http://live.techpanda.org/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  @Test
	  public void testDay1TestCase1() throws Exception {
		
	    driver.get(baseUrl); 
		//Step 2. Verify Title of the page
	    String demoSite  = driver.findElement(By.cssSelector("h2")).getText();
	    System.out.println(demoSite);
	    try {
	      AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }	    
	    

	    // Step 3. Click on MOBILEmenu
	    driver.findElement(By.linkText("MOBILE")).click();	
        // Step 5. In the list of all mobile , select SORT BY dropdown as name    
         new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
	    
	    // Step 6. Verify all products are sorted by name
		// this will take a screen shot of the manager's page after a successful login
	    scc = (scc+1);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String png = ("C:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\Mobile Products are sorted" + scc + ".png");
		FileUtils.copyFile(scrFile, new File(png));
	
	    }	
	  
	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	   
	  }
	
	  
	}
*/
 
