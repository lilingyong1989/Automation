package e2e.pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import e2e.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	By orderIds= By.xpath("//tbody//tr//th[1]");

	public List<WebElement> getOrderId(){
		List<WebElement> orderCols=driver.findElements(orderIds);
		return orderCols;
	}

	public Boolean checkOrderId(List<String> orderIds) {
		List<String> actualOrderId=getOrderId().stream().map(s->s.getText()).toList();
		boolean orderPresent=orderIds.stream().allMatch(actualOrderId::contains);
		return orderPresent;
	}





}
