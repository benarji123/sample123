package Testcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ReusableMethods.PropertiesConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public ExtentSparkReporter htmlrepo;
	public ExtentReports extent;
	public ExtentTest test ;
	
	@BeforeMethod
	@Parameters({"Br"})
	public void launchBrowser(String BrowserType ) throws FileNotFoundException, IOException {
	
		PropertiesConfig.loadPropertyFile();
		//String BrowserType=PropertiesConfig.p.getProperty("Browser");
		String url=PropertiesConfig.p.getProperty("Url");
		test=extent.createTest("Login");
		
		if(BrowserType.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
			test.log(Status.INFO, "Chrome Browser open");
			driver.manage().window().maximize();
		}else if(BrowserType.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();

			test.log(Status.INFO, "Edge Browser open");
			
		}else if(BrowserType.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

			test.log(Status.INFO, "firefox browser open");
		}

		test.log(Status.INFO, "enter url");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@BeforeTest
	public  void setExtent() {
		htmlrepo=new ExtentSparkReporter(System.getProperty("user.dir")+"/Extentsreport/"+getdate()+".html");
		htmlrepo.config().setDocumentTitle("Automation Report");//title of the report
		htmlrepo.config().setReportName("Functional Report");//name of the report
		htmlrepo.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlrepo);
		extent.setSystemInfo("Hostname","LocalHost");
		extent.setSystemInfo("Os","windows10");
		
		
		
	}
	
	@AfterMethod
	public void tearUp(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getName());//to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());// to add error /exception
			String screenshotpath=getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotpath); //add screenshots			
			
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS" + result.getName());
			
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE SUCCESSED IS" + result.getName());
		}
		
		driver.quit();
		
	}
	public  String getdate() {
		String date=new SimpleDateFormat("YYYY-MM-dd hh-mm-ss").format(new Date());
		return date;
	}
	@AfterTest
	public void endRoport() {
		extent.flush();
		
	}

	
	public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
				String dateName=new SimpleDateFormat("YYYY-MM-dd hh-mm-ss").format(new Date());
				File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String destination=System.getProperty("user.dir")+"/ScreenShots/"+ScreenshotName + dateName + ".png";
				File finaldestination = new File(destination);
				FileUtils.copyFile(source, finaldestination);
				return destination;
				
				
				
			}
}
