package Testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObject.Orange_Hrm_Login_Page;

public class Tc_Login_Hrm extends BaseClass{
	@Test
	public void login_hrm_website() {

		Orange_Hrm_Login_Page l=new Orange_Hrm_Login_Page(driver);
		test.log(Status.INFO, "username enter");
		l.set_username("Admin");
		test.log(Status.INFO, "password enter");
		l.set_password("admin123");
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
			Assert.assertFalse(true);
		} 
		
	}

}
