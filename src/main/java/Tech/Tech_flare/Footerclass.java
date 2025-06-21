package Tech.Tech_flare;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footerclass {
	WebDriver driver;
	WebDriverWait wait;
	Action act;

	public Footerclass(WebDriver driver) 
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));


	}
	public void getURL(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	public String socialLinks(String socialmeadia) throws InterruptedException 
	{

		List<WebElement>slinks=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//footer//a[@target='_blank']")));
		for(WebElement slink:slinks) 
		{
			String href=slink.getAttribute("href");
			if(href !=null && href.contains(socialmeadia))
			{
				String parentwindow =driver.getWindowHandle();
				//Thread.sleep(2000);

				wait.until(ExpectedConditions.elementToBeClickable(slink)).click();
				//Thread.sleep(2000);
				wait.until(driver ->driver.getWindowHandles().size()>1);
				//Thread.sleep(2000);

				Set<String> allWindows = driver.getWindowHandles();
				for(String window :allWindows)
				{
					if(!window.equals(parentwindow))
					{
						driver.switchTo().window(window);
						String socialurl= driver.getCurrentUrl();
						driver.close();
						driver.switchTo().window(parentwindow);
						return socialurl;
					}
				}
			}
		}
		return null;
	}
	public void refresh() 
	{
		//driver.findElement(By.xpath("//a[text()='TECHFLARE INNOVATIONS']")).click();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='TECHFLARE INNOVATIONS']")));
		element.click();

		
	}
	public void upwordarrow() {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='scroll-top']")));
		el.click();

		
	}
	
	
}
