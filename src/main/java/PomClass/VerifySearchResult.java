package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import generic.BaseLib;

public class VerifySearchResult extends BaseLib{
	
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath = "//*[@id=\"search\"]") // Main Search Box
	private WebElement searchbox;

	@FindBy(xpath = "//*[@id=\"btnSearch\"]") // Main Search Icon (Button)
	private WebElement SearchButton;

	@FindBy(xpath = "//*[@id=\"court\"]/label/text()") // All Button result
	private WebElement court;
	
	
	// there ===========================
    public  VerifySearchResult(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }   

	
	
// Actions 	
	public void SearhResultverify(WebDriver driver) throws InterruptedException {
	
		Library.threadSleep(1000);
		
		Library.sendKeys(driver,searchbox , "Enter the keyword in searchbar", "the");
		test.info("Enter THE keyword in searchbar");
		Library.threadSleep(3000);
		Library.click(driver,SearchButton , "click on searchbutton");
		test.info("click on search Button");
		Library.threadSleep(6000);


		WebElement Notification = driver.findElement(By.xpath("//*[@id=\"material4\"]"));
        if (Notification.isDisplayed()) {
        	Library.threadSleep(2000);
               System.out.println("Search Result working fine");
            test.pass("Search Result working properly");
        } else {
            throw new AssertionError("Search result not working properly.");
            
            
        }
	
}
	
}


