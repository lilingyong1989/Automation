package e2e.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='form-group']/input")
	WebElement countryInput;

	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeOrderBtn;

	By countries=By.xpath("//button[contains(@class,'ta-item')]//span");

	public List<WebElement> getCountryList() {
		waitForElementVisible(countries);
		List<WebElement> countryList=driver.findElements(countries);
		return countryList;
	}

	public void inputCountry(String country) {
		countryInput.sendKeys(country);
		getCountryList().stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().ifPresent(s ->s.click());
	}

	public ConfirmPage placeOrder() {
		placeOrderBtn.click();
		ConfirmPage confirmPage=new ConfirmPage(driver);
		return confirmPage;
	}



	/*driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(country);

	List<WebElement> countryEle=driver.findElements(By.xpath("//button[contains(@class,'ta-item')]//span"));
	countryEle.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().ifPresent(s ->s.click());

	driver.findElement(By.xpath("//a[text()='Place Order ']")).click();*/

}
