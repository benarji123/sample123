package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import Utils.ResuableFunctions;

public class Orange_Hrm_Login_Page {
private WebDriver driver;
private	ResuableFunctions w;

	public Orange_Hrm_Login_Page(WebDriver rd) {
		this.driver=rd;
		PageFactory.initElements(rd, this);
		 w=new ResuableFunctions(driver);
		
		
	}
	
@CacheLookup
@FindBy(name="username")
private WebElement txt_username;

@CacheLookup
@FindBy(xpath = "//input[@type='password']")
private WebElement txt_password;

@CacheLookup
@FindBy(xpath="//button[@type='submit']")
private WebElement login_btn;

@CacheLookup
@FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']")
private WebElement Pim;

@CacheLookup
@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
private WebElement usernamedropdown;

@CacheLookup
@FindBy(linkText = "Logout")
WebElement logout_Link;

public void set_username(String username) {
     w.wait_For_Element(10, txt_username);
     txt_username.clear();
     txt_username.sendKeys(username);
	
}
public void set_password(String password) {
    w.wait_For_Element(10, txt_password);
    txt_password.clear();
    txt_password.sendKeys(password);
	
}
public void click_login_btn() {
	w.wait_For_Element(10, login_btn);
	login_btn.click();
	
}

public boolean verify_Pim_Isdisplayed() {
	
		return Pim.isDisplayed();
	
}

public void click_On_Logout() {
	w.wait_For_Element(10, usernamedropdown);
	 usernamedropdown.click();
	 w.wait_For_Element(10, logout_Link);
	 
}
	
}
