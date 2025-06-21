package Tech.Tech_flare;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

	WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public Homepage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		this.act=new Actions(driver);
	}

	public String  Allurl(String navButton) {
		List<WebElement>url=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//nav[@id='navmenu']//a")));
		for(WebElement e:url)
		{

			String urlText=e.getText();
			if(urlText.equals(navButton)) {
				e.click();
				String currentUrl=driver.getCurrentUrl();
				return currentUrl;
			}
		}
		return null;	
	}


	public void pagerefresh() {
		WebElement e=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='TECHFLARE INNOVATIONS']")));
		e.click();
	}

	public void registernowbutton() {
		WebElement register=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Register now' and @href='#contact']")));
		if(register.isDisplayed())
		{
			System.out.println("Register button is here");
			act.moveToElement(register).build().perform();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",register);
		}

	}
	public void register(String yourname,String youremail,String yourphone,String yourqualification,String yourcourse) {
		driver.findElement(By.xpath("//input[@placeholder='Your Name']")).sendKeys(yourname);
		driver.findElement(By.xpath("//input[@placeholder='Your email']")).sendKeys(youremail);
		driver.findElement(By.xpath("//input[@placeholder='Your Phone']")).sendKeys(yourphone);
		driver.findElement(By.xpath("//input[@placeholder='Your qualification']")).sendKeys(yourqualification);
		driver.findElement(By.xpath("//input[@placeholder='Your course']")).sendKeys(yourcourse);
		WebElement submit=driver.findElement(By.xpath("//button[text()='Send Message']"));
		submit.click();
	}

	public String error() {
		WebElement ermeg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error-message d-block']")));
		String message=ermeg.getText();
		return message;
	}

	public boolean validation() {
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// ✅ Use correct property: validationMessage
		String validationMsg = (String) js.executeScript("return arguments[0].validationMessage;", emailField);

		if (validationMsg != null && validationMsg.contains("@")) {
			System.out.println("Validation message is displayed: " + validationMsg);
			return true;
		} else 

			System.out.println("Validation message is not displayed");
		return false;
	}
	public WebElement ZoomableImage() {
		// Click on the first preview icon,just copy the xpath
		WebElement image = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='portfolio']//div[1]//a/i")));
		act.moveToElement(image).click().perform();

		// Click on the first zoomable image
		WebElement firstZoomImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='zoomable']")));
		act.moveToElement(firstZoomImage).click().perform();

		return firstZoomImage;






	}
	public void imageurl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void calltoaction()
	{
		WebElement callto=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class= 'cta-btn']")));
		callto.click();
	}
	public void clickservice(String serviceName) {
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

		WebElement servicelink = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//a[text() = 'Services']")));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", servicelink);

		// Click using Javascript to avoid intercept issues
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", servicelink);
	}
	public String callaction() {
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		WebElement ctaclick = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='cta-btn']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ctaclick);    
		wait.until(ExpectedConditions.elementToBeClickable(ctaclick));

		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ctaButton);
		ctaclick.click();
		wait.until(ExpectedConditions.urlContains("#contact"));
		return driver.getCurrentUrl();
	}


	public List<String> PORTFOLIOIMAGES() {
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

		// Scroll down to portfolio section
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");

		// Wait for presence of elements
		List<WebElement> imagelinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='portfolio-info']//a")));

		System.out.println(imagelinks.size());

		List<String> imageaddresses = new ArrayList<>();
		for (WebElement link : imagelinks) {
			String href = link.getAttribute("href");
			System.out.println(href);
			imageaddresses.add(href);
		}

		return imageaddresses;
	}
	public WebElement clickFirstZoomableImage() {

		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

		WebElement previewIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"portfolio\"]/div[2]/div/div/div[1]/div/div/a/i")));   
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", previewIcon);
		act.moveToElement(previewIcon).click().perform();

		List<WebElement> zoomImages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//img[@class='zoomable']")));

		WebElement firstZoomImage = zoomImages.get(0);
		act.moveToElement(firstZoomImage).click().perform();

		return firstZoomImage; 

	}
	public void closeZoomImage() {
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

		WebElement closeButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//button[contains(@class,'gclose')]")));

		closeButton.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("glightbox-body")));
	}







}



