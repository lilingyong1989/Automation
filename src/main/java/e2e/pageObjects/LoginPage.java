package e2e.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
	}

	@FindBy(id="userEmail")
	WebElement userEmailInput;

	@FindBy(css="input[formcontrolname='userPassword']")
	WebElement passwordInput;

	@FindBy(css=".toast-message")
	WebElement invalidLoginMsg;

	@FindBy(css=".login-btn")
	WebElement loginBtn;

	public void launchLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductPage login(String email,String pwd){
		userEmailInput.sendKeys(email);
		passwordInput.sendKeys(pwd);
		loginBtn.click();
		ProductPage productPage=new ProductPage(driver);
		return productPage;
	}

	public String getInvalidLoginMsg() {
		waitForWebElementVisible(invalidLoginMsg);
		return invalidLoginMsg.getText();
	}



}
