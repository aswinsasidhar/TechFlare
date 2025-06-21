package Tech.Tech_flare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import com.sun.org.apache.xerces.internal.util.Status;

import java.time.Duration;
@Listeners(ExtentTestngITestListener.class)

public class HomeTest {

	WebDriver driver;
	WebDriverWait wait;
	Footerclass f;
	Homepage h;
	careerclass C;
	ExtentTest test;

	


	@Test(priority=0)
	public void Urlverification()
	{
	    ExtentTest test = ExtentTestngITestListener.testThread.get();

		String homeurl=h.Allurl("Home");
		Assert.assertTrue(homeurl.contains("#hero"),"hero is not contain in the home page");
		System.out.println("Home button is working");
	    test.log(Status.INFO, "check Home button is working");




		String abouturl=h.Allurl("About");
		Assert.assertTrue(abouturl.contains("#about"),"about is not contain in the about page");
		System.out.println("About button is working");
	    test.log(Status.INFO, "check About button is working");


		String servicesurl=h.Allurl("Services");
		Assert.assertTrue(servicesurl.contains("#services"),"services is not contain in the services page");
		System.out.println("Services button is working");
	    test.log(Status.INFO, "check Services button is working");


		String portfoliourl=h.Allurl("Portfolio");
		Assert.assertTrue(portfoliourl.contains("#portfolio"),"portfolio is not contain in the portfolio page");
		System.out.println("Portfolio  button is working");
	    test.log(Status.INFO, "check Portfolio  button is working");

		

		String careersurl=h.Allurl("Careers");
		Assert.assertTrue(careersurl.contains("careers"),"careers is not contain in the careers page");
		System.out.println("Careers  button is working");
	    test.log(Status.INFO, "check Careers  button is working");


		String teamurl=h.Allurl("Team");
		Assert.assertTrue(teamurl.contains("#team"),"team is not contain in the team page");
		System.out.println("Team button is working");
	    test.log(Status.INFO, "check Team button is working");

		

		String contacturl=h.Allurl("Contact");
		Assert.assertTrue(contacturl.contains("#contact"),"Contact is not contain in the Contact page");
		System.out.println("Contact button is working");
	    test.log(Status.INFO, "check Contact button is working");


	}
	@Test(priority=1)
	public void FooterLinks() throws InterruptedException {
	    ExtentTest test = ExtentTestngITestListener.testThread.get();

		

		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");//scrollTo  end scroll
		Thread.sleep(2000);
		String instagramurl=f.socialLinks("instagram");
		System.out.println("CapturedUrl:"+instagramurl);
		Assert.assertTrue(instagramurl.contains("instagram"),"instagram link is not working");
		System.out.println("Instagram link is working");
	    test.log(Status.INFO, "check Instagram link is working");



		String linkedinmurl=f.socialLinks("linkedin");
		System.out.println("CapturedUrl:"+linkedinmurl);
		Assert.assertTrue(linkedinmurl.contains("linkedin"),"linkedin link is not working");
		System.out.println("linkedin link is working");
	    test.log(Status.INFO, "check linkedin link is working");



	}

	@Test(priority=2)
	public void verifypagerefresh() throws InterruptedException {
	    ExtentTest test = ExtentTestngITestListener.testThread.get();


		Thread.sleep(2000);
		h.pagerefresh();
		String currentUrl = driver.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("index.html"), "Home page did not refresh"); 
	    System.out.println("Home page refreshed successfully " + currentUrl);
	    test.log(Status.INFO,"Home page refreshed successfully ");
	}

	@Test(priority=3)
	public void verifyregisternowbutton() throws InterruptedException {
	    ExtentTest test = ExtentTestngITestListener.testThread.get();


		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		h.registernowbutton();
		//Assert.assertTrue(driver.getCurrentUrl().contains("#contact"),"url does not contain contact");
		//System.out.println("url contain contact");
		WebElement contactSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='forms/save_data.php']"))) ; // or any other valid locator)
		//parent of all contact field
		  Assert.assertTrue(contactSection.isDisplayed(), "Registration form is not displayed");
	      System.out.println("Registration form is displayed, Register now button is working");
		    test.log(Status.INFO, "check Registration form is displayed, Register now button is working");

	      
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");//scrollTo  end scroll
		h.register("Aswin","aaswinsasidhar@gmail.com","9207475244","BSc computerscience","Software Testing");

		Assert.assertTrue(h.error().equals("Your message has been sent. Thank you!"),"wrong error message");
		System.out.println("Correct error message");
	    test.log(Status.INFO, "check Correct error message");



	}

	@Test(priority=4)
	public void invalidcase() throws InterruptedException { 
	    ExtentTest test = ExtentTestngITestListener.testThread.get();



		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));


		h.registernowbutton();
		WebElement contactSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='forms/save_data.php']"))) ; // or any other valid locator)
		//parent of all contact field
		Assert.assertTrue(contactSection.isDisplayed(), "Registration form is not displayed");
		System.out.println("Registration form is displayed, Register now button is working");
	    test.log(Status.INFO, "check Registration form is displayed, Register now button is working");


		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");//scrollTo  end scroll
		h.register("23646","aswingmail.com","#@$gfd73748","@Q345wv132","dfgda13!$$");

		Assert.assertTrue(h.validation(), "Wrong validation error message");
		System.out.println("Correct validation error message");
	    test.log(Status.INFO, "check Correct validation error message");


		Assert.assertTrue(h.error().equals("Error: Data successfully saved!"),"Wrong error meggage displayed");
		System.out.println("correct Wrong error meggage displayed");
	    test.log(Status.INFO, "check correct Wrong error meggage displayed");



	}


	@Test(priority=5)
	public void pagerefresh() throws InterruptedException 
	{
	    ExtentTest test = ExtentTestngITestListener.testThread.get();


		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");//scrollTo  end scroll
		Thread.sleep(2000);
		f.refresh();
		Thread.sleep(2000);
		 String currentUrl = driver.getCurrentUrl();
		    Assert.assertTrue(currentUrl.contains("https://www.techflareinnovations.in/"), "can't navigate back to home page");
		    System.out.println("navigate back to home page");
		    test.log(Status.INFO,"can't navigate back to home page");

		//upward arrow
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");//scrollTo  end scroll,scrollby perticular size
		Thread.sleep(2000);
		f.refresh();
	}
	

	
@Test(priority = 6)
public void verifycareers() throws InterruptedException {
	wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	C.geturl("https://www.techflareinnovations.in/careers.html#courses");

	// Assert.assertTrue(c.abouttext().equals("About Techflare Careers"), "text is NOT displayed!");
	//System.out.println("The test is displayed");

	String aboutText = C.careerurl();
	Assert.assertEquals(aboutText, "About Techflare Careers", "text is not displayed!");
	System.out.println("text is displayed");


	Thread.sleep(2000);
	((JavascriptExecutor)driver).executeScript("window.scrollBy(100,1700);");
	Thread.sleep(2000);
	List<String> allCourses = C.Corsenameslist();
	Thread.sleep(2000);
	Assert.assertEquals(allCourses.size(), 9, "No courses found!");
	System.out.println(" Courses are listed successfully:");

}


	

	
	@Test(priority = 7)
	public void checkservice() {
	    ExtentTest test = ExtentTestngITestListener.testThread.get();


		
	    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

	    h.Allurl("https://www.techflareinnovations.in/index.html");

	    SoftAssert softAssert = new SoftAssert();

	  
	    h.clickservice("Website Development");
	    String url1 = driver.getCurrentUrl();
	    softAssert.assertTrue(url1.contains("#Website Development"), "urlL does not contain #Website Development");
	    System.out.println("url contain #Website Development ");
	    test.log(Status.INFO, "check url contain #Website Development ");

	    
	
	 
	    h.clickservice("Digital Marketing");
	    String url2 = driver.getCurrentUrl();
	    softAssert.assertTrue(url2.contains("#Digital Marketing"), "url does not contain #Digital Marketing");
	    System.out.println("url contain #Digital Marketing");
	    test.log(Status.INFO, "check url contain #Digital Marketing");


	    h.clickservice("Graphic designing");
	    String url3 = driver.getCurrentUrl();
	    softAssert.assertTrue(url3.contains("#Graphic designing"), "url does not contain #Graphic designing");
	    System.out.println("url contain #Graphic designing ");
	    test.log(Status.INFO, "check url contain #Graphic designing");


	    h.clickservice("Mobile app development");
	    String url4 = driver.getCurrentUrl();
	    softAssert.assertTrue(url4.contains("#Mobile app development"), "url does not contain #Mobile app development");
	    System.out.println("url contain #Mobile app development");
	    test.log(Status.INFO, "check url contain #Mobile app development");

	    

	    softAssert.assertAll();
	}
	
	@Test(priority = 8)
	public void CALLACTIONBUTTON() {
	    ExtentTest test = ExtentTestngITestListener.testThread.get();


		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		C.geturl("https://www.techflareinnovations.in/index.html#services");

		String currentUrl = h.callaction();
		System.out.println(currentUrl);

		Assert.assertTrue(currentUrl.contains("#contact"), "contact page is not displayed");
		System.out.println("contact page is displayed");
	    test.log(Status.INFO, "check contact page is displayed");

	}
	@Test(priority = 9)
	public void IMAGES() {
		ExtentTest test=ExtentTestngITestListener.testThread.get();

		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		C.geturl("https://www.techflareinnovations.in/index.html");

		List<String> imageaddresses = h.PORTFOLIOIMAGES();
		Assert.assertFalse(imageaddresses.isEmpty(), "portfolio images are not found");
		System.out.println("portfolio images are found");
	    test.log(Status.INFO, "check portfolio images are found");

	}


	@Test(priority = 10)
	public void verifyImageZoomOutByCloseButton() {
		ExtentTest test=ExtentTestngITestListener.testThread.get();

	    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

	    h.Allurl("https://www.techflareinnovations.in/index.html");

	    WebElement lightbox = h.clickFirstZoomableImage();
	    Assert.assertTrue(lightbox.isDisplayed(), "image is not zoomed");
	    System.out.println("image zoomed successfully.");
	    test.log(Status.INFO, "check image zoomed successfully.");


	    h.closeZoomImage();

	    boolean isPresent = driver.findElements(By.id("glightbox-body")).size() > 0;
	    Assert.assertFalse(isPresent, "aria-hidden attribute is false");
	    System.out.println("aria-hidden attribute is true");
	    test.log(Status.INFO, "check aria-hidden attribute is true");

	}






	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		driver=new ChromeDriver();
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		h=new Homepage(driver);
		f=new Footerclass(driver);
		C=new careerclass(driver);
		f.getURL("https://www.techflareinnovations.in/index.html");;
		
	}
	@AfterMethod
    public void tearDown() {
        driver.close();
    }

}


