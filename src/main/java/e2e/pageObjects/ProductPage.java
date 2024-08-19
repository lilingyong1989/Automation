package e2e.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getAddToCartBtn(String product) {
		return driver
				.findElement(By.xpath("//b[text()='" + product + "']//ancestor::h5//following-sibling::button[2]"));
	}

	By toastMsgOutput=By.xpath("//div[@id='toast-container']");
	By spinningIcon=By.cssSelector(".ngx-spinner-overlay");

	public void addProductToCart(List<String> products) throws InterruptedException {
		for (String product : products) {
			getAddToCartBtn(product).click();
			waitForElementVisible(toastMsgOutput);
			waitForElementInvisible(spinningIcon);
			Thread.sleep(500);

		}
	}

}
