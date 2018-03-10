package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.Assert;
import org.testng.Reporter;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewTest {
	
  public AppiumDriver<MobileElement> driver;
  WebDriverWait wait;

  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

    @BeforeClass
	public void setCapabilities() { // Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "LGLS755PBCAE6MNOBHQ"); // Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");

        // setting what app to be tested.
		// x40240.yusen.lin.a5/x40240.yusen.lin.a5.MainActivity
		caps.setCapability("appPackage", "x40240.yusen.lin.a5");
		caps.setCapability("appActivity", "x40240.yusen.lin.a5.MainActivity");

		caps.setCapability("noReset", "true");

		// Instantiate Appium Driver
		try {
			Reporter.log("Instantiate Appium Driver @local: http://0.0.0.0:4723/wd/hub", true);
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		// wait for 5 seconds then start the tests
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

  
  	@AfterClass
	public void TearDown() throws Exception {
		Reporter.log("Closing Appium Session.", true);
		Thread.sleep(5000); // App needs to fully load before closing
		driver.closeApp();
		Thread.sleep(2000);
		driver.quit();
	}
  	
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	  
  }

  
  @Test
	public void test1() throws Exception {
		Reporter.log("test 1 starting", true);
		try {
			boolean found=false;
			List<MobileElement> elements = driver.findElements(By.xpath("//android.widget.TextView"));
			for(MobileElement element : elements) {
				//Reporter.log("test 1=>text:"+element.getAttribute("text"), true);
				if(element.getAttribute("text").equals("A5-Yusen.Lin")) {
					found=true;
					break;
				}
			}
			
			if (found) {
				Reporter.log("  TEST RESULT: SUCESS, A5-Yusen.Lin IS displayed", true);
				Assert.assertEquals(true, true);
				
			} else {
				Reporter.log("  TEST RESULT: FAILED, A5-Yusen.Lin is NOT displayed", true);
				Assert.assertEquals(true, false);
			}
		} catch (Exception e) {
			Reporter.log("  TEST RESULT: FAILED, throw exception: " + e.toString(), true);

			Assert.assertEquals(false, true);
		}
		Reporter.log("test 1 ended\n", true);

	}
  
  
  @Test
  public void test2() {
		Reporter.log("test 2 starting", true);
		try {
			//x40240.yusen.lin.a5:id/location_button
			driver.findElementById("x40240.yusen.lin.a5:id/location_button").click();
			//wait 2 seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String latText = driver.findElementById("x40240.yusen.lin.a5:id/latitude_edit").getText();
			
			if (latText.isEmpty()) {
				Reporter.log("  TEST RESULT: FAILED, NO location data", true);
				Assert.assertEquals(true, false);
			} else {
				Reporter.log("  TEST RESULT: SUCESS, get location data", true);
				Assert.assertEquals(true, true);				
			}
			

		} catch (Exception e) {
			Reporter.log("  TEST RESULT: FAILED, throw exception: " + e.toString(), true);

			Assert.assertEquals(false, true);
		}
		Reporter.log("test 2 ended\n", true);
	  
  }
  
  @Test
  public void test3() {
		Reporter.log("test 3 starting", true);
		try {
			//x40240.yusen.lin.a5:id/location_button
			driver.findElementById("x40240.yusen.lin.a5:id/clear_button").click();
			//wait 2 seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String latText = driver.findElementById("x40240.yusen.lin.a5:id/latitude_edit").getText();
			
			if (latText.isEmpty()) {
				Reporter.log("  TEST RESULT: SUCESS, location data cleared", true);
				Assert.assertEquals(true, true);
			} else {
				Reporter.log("  TEST RESULT: FAIL, location data NOT cleared", true);
				Assert.assertEquals(true, false);				
			}
			
		} catch (Exception e) {
			Reporter.log("  TEST RESULT: FAILED, throw exception: " + e.toString(), true);

			Assert.assertEquals(false, true);
		}
		Reporter.log("test 3 ended\n", true);
  }
  
  
  @Test
  public void test4() {
		Reporter.log("test 4 starting", true);
		try {
			//x40240.yusen.lin.a5:id/location_button
			driver.findElementById("x40240.yusen.lin.a5:id/list_button").click();
			
			//wait 8 seconds
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("x40240.yusen.lin.a5:id/map_button")));
			List<MobileElement> elements = driver.findElements(By.xpath("//android.widget.Button"));
			for(MobileElement element : elements) {
				if(element.getAttribute("text").equals("Map/Log")) {
					element.click();
					break;
				}
			}

			//Reporter.log("Click map button, sleep for 15 seconds to wait google map", true);
			
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			boolean found=false;
			elements = driver.findElements(By.xpath("//android.widget.Button"));
			for(MobileElement element : elements) {
				if(element.getAttribute("text").equals("DIRECTIONS")) {
					found=true;
					break;
				}
			}
			
			if (found) {
				Reporter.log("  TEST RESULT: SUCESS, google map IS displayed", true);
				Assert.assertEquals(true, true);
				
			} else {
				Reporter.log("  TEST RESULT: FAILED, google map is NOT displayed", true);
				Assert.assertEquals(true, false);
			}
			
		} catch (Exception e) {
			Reporter.log("  TEST RESULT: FAILED, throw exception: " + e.toString(), true);

			Assert.assertEquals(false, true);
		}
		Reporter.log("test 4 ended\n", true);
  }
  

}
