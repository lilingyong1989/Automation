package e2e.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}



	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutBtn;

	By productAdded=By.cssSelector(".cartWrap");

	List<WebElement >productAddedList;

	public List<WebElement> getAddedProductList() {
		waitForElementVisible(productAdded);
		List<WebElement> productAddedList=driver.findElements(productAdded);
		return productAddedList;
	}


	public boolean checkAddedProducts(List<String> products) {
		List<String> actualProductName=getAddedProductList().stream().map(s->s.findElement(By.cssSelector("h3")).getText()).collect(Collectors.toList());
		boolean present=products.stream().allMatch(actualProductName::contains);
		return present;
	}

	public PaymentPage checkout() {
		checkoutBtn.click();
		PaymentPage paymentPage=new PaymentPage(driver);
		return paymentPage;
	}




}
