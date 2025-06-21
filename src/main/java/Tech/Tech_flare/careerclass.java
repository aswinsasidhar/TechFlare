package Tech.Tech_flare;





import java.util.List;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class careerclass {
	WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public careerclass(WebDriver driver) 
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	public void geturl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public String careerurl() {
		 //driver.get("https://www.techflareinnovations.in/careers.html#courses");
		 //ebElement abouttext =driver.findElement(By.xpath("//h2[text()='About Techflare Careers']"));
		// if(abouttext.isDisplayed()) {
			 //System.out.println("text is displayed");
		 //}
		 //else {
			// System.out.println("test is not displayed");
		// }
		
	 WebElement abouttext =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='About Techflare Careers']")));
	 String s=abouttext.getText();
	 return s;
	
}
	public List<String> Corsenameslist() {
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		   List<WebElement> courselist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card']//h3")));
		   List<String> list = new ArrayList<String>();

		    for (WebElement course : courselist) {
		    	String name = course.getText();
		        list.add(name);
		        System.out.println("Course: " + name); 
		    }
			return list;
	}
	
	
	
}
	
