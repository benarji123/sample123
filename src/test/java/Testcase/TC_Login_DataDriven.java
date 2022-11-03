package Testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObject.Orange_Hrm_Login_Page;

public class TC_Login_DataDriven extends BaseClass{
	
	
	@Test(dataProvider = "login")
	public void loginHrm(String username,String password) {
		
		
		Orange_Hrm_Login_Page l=new Orange_Hrm_Login_Page(driver);
		test.log(Status.INFO, "username enter");
		l.set_username(username);
		test.log(Status.INFO, "password enter");
		l.set_password(password);
		test.log(Status.INFO, "click on login button");
		l.click_login_btn();
		
		try {
		if(l.verify_Pim_Isdisplayed()) {
			l.click_On_Logout();
			test.log(Status.INFO, "click on logout btn");
			Assert.assertTrue(true);
		}
		}catch (Exception e) {
			System.out.println("login failed");
			test.log(Status.INFO, " enter wrong creditials");
			
			Assert.assertTrue(false);
			
		} 
		
		
	}
@DataProvider(name="login")
public Object[][] dataprovider() {
	Object[][]obj= {{"Admin","admin123"},{"Admin","admin13"},{"Admin","admin13"},{"Admin","admin123"}};
	    return obj;
    
	
}

}
