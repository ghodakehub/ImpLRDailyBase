package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import ConfigurationPath.PathFile;
import ExtentReportBasic.ExtentReportManager;
import PomClass.LRLOGIN;
import PomClass.Login;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class LoginPage {
	
	Login log;
	BaseLib base;
	UtilityClass utility;
	String tcid;
	
	
	  public static ExtentReports extent;
		
		public static ExtentSparkReporter spark;
		
		public static ExtentTest test1;
		
		
	
	@BeforeClass
	public void launchbrowser()
	{
		ExtentReportManager.initializeExtentReports();

        // Create a new test for this class
        test1 = ExtentReportManager.extent.createTest("LR_Login Test");

		base= new BaseLib();
		base.initailizbrowser();
		base.implicitwait(10);
		log= new Login(BaseLib.driver, test1);
		utility= new UtilityClass();
		
		
	}
	
	
	@Test
	public void VerifyLoginPageOFLR() throws InterruptedException, IOException
	
	{
		BaseLib.driver.get(PathFile.LRURL);
		Thread.sleep(2000);
		tcid="1: verify Login test case of LR Login page";
		log.login("pratiksha.damodar@legitquest.com","lq@123");
		//test1.pass("Login TestCase is passed successfully.");
		
	}

	
	@AfterMethod()
	public void aftermethod(ITestResult result) throws  IOException, MessagingException
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			test1.log(Status.FAIL, "test case is failed"+result.getName());
			test1.log(Status.FAIL, "test case is failed"+result.getThrowable());
			String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,result.getName() );
		
			test1.log(Status.FAIL,"test"+ test1.addScreenCaptureFromPath(screenshot));
			
			
			 String testUrl = BaseLib.driver.getCurrentUrl();  // Get the URL where the failure occurred
			 ForMultiplemailReceipent.sendEmail(
	            	    BaseLib.driver, new String[]{"ghodake6896@gmail.com","mamta.Kashyap@legitquest.com"},
	            	    "LR LOGIN PAGE ",
	            	    "Please check issue coming on LR LOGIN page, please find the attached screenshot for details." ,
	            	    screenshot , testUrl
	            	   
	            	);
		
		
		}
		
		else if(result.getStatus()== ITestResult.SKIP){
			
			
			test1.log(Status.SKIP, "test case is skipped"+result.getName());
			

		}ExtentReportManager.flushReports(); // Flush the report
    
		BaseLib.driver.quit();	
		}

	
	
	
	
	
	
	
	
	


	
	
	
	
	
}
