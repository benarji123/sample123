package Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResuableFunctions {
	WebDriver driver;
	public ResuableFunctions(WebDriver dr) {
		driver=dr;
		
	}
	
	
	public void wait_For_Element(long time,WebElement el) {
		WebDriverWait w=new 	WebDriverWait(driver,Duration.ofSeconds(time));
		w.until(ExpectedConditions.visibilityOf(el));
	}

}
