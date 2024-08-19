package cucumber.stepDefinition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.testng.AssertJUnit;

import e2e.TestComponents.Base;
import e2e.pageObjects.CartPage;
import e2e.pageObjects.ConfirmPage;
import e2e.pageObjects.LoginPage;
import e2e.pageObjects.PaymentPage;
import e2e.pageObjects.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition_e2e extends Base {
	public ProductPage productPage;
	public PaymentPage paymentPage;
	public ConfirmPage confirmPage;
	List<String> productList;
	List<String> orderIds;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		openWebpage();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productPage = loginPage.login(username, password);
	}

	@When("^I add the products (.+) to cart$")
	public void I_add_the_products_to_carts(String products) throws InterruptedException {
		productList = Arrays.asList(products.split(","));
		productPage.addProductToCart(productList);
	}

	@And("Checkout the products")
	public void Checkout_the_products() {
		cartPage.navigateToCart();
		AssertJUnit.assertTrue(cartPage.checkAddedProducts(productList));
		paymentPage = cartPage.checkout();
	}

	@And("^I fill in the country (.+) and place the order$")
	public void I_fill_in_the_country_and_place_the_orders(String country) {
		// Payment
		paymentPage.inputCountry(country);
		confirmPage = paymentPage.placeOrder();

		// Confirmation
		// Get order ID
		orderIds = confirmPage.getOrderIds();
	}

	@Then("I verify the orders in Order History page")
	public void I_verify_the_orders_in_Order_History_page() {
		// Go to Order Page
		orderPage.navigateOrderPage();

		// Check in orders whether order Id is present
		Assert.assertTrue(orderPage.checkOrderId(orderIds));
		driver.close();
	}
	
	@Then ("Toast message {string} is shown")
	public void Toast_message_is_shown(String toastMsg) {
		AssertJUnit.assertEquals(toastMsg, loginPage.getInvalidLoginMsg());
		driver.close();
	}

}
