package e2e.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class ConfirmPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	By orderIdEles=By.xpath("//td[@class='em-spacer-1']//label[@class='ng-star-inserted']");

	public List<WebElement> getOrderList(){
		waitForElementVisible(orderIdEles);
		List<WebElement> orderIds=driver.findElements(orderIdEles);
		return orderIds;
	}

	public List<String> getOrderIds(){
		List<String> orderIds = getOrderList().stream().map(s->s.getText().split("\\| ")[1].split(" \\|")[0]).toList();
		return orderIds;
	}

}
