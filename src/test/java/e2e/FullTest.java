package e2e;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import e2e.TestComponents.Base;
import e2e.TestComponents.Retry;
import e2e.pageObjects.ConfirmPage;
import e2e.pageObjects.PaymentPage;
import e2e.pageObjects.ProductPage;

public class FullTest extends Base {
	List<String> orderIds;
	//new codes

	@Test(dataProvider="getTestData",groups= {"Purchase"},retryAnalyzer=Retry.class)
	public void submitOrder01(HashMap<Object,Object>input) throws IOException, InterruptedException {

        // Cast input data to appropriate types
        String email = (String) input.get("email");
        String password = (String) input.get("password");
        @SuppressWarnings("unchecked")
		List<String> products = (List<String>) input.get("products");
        String country = (String) input.get("country");


		// login
		ProductPage productPage = loginPage.login(email, password);

		productPage.addProductToCart(products);

		cartPage.navigateToCart();

		AssertJUnit.assertTrue(cartPage.checkAddedProducts(products));
		PaymentPage paymentPage = cartPage.checkout();

		// Payment
		paymentPage.inputCountry(country);
		ConfirmPage confirmPage = paymentPage.placeOrder();

		// Confirmation
		// Get order ID
		orderIds = confirmPage.getOrderIds();

		// Go to Order Page
		orderPage.navigateOrderPage();

		// Check in orders whether order Id is present
		Assert.assertTrue(orderPage.checkOrderId(orderIds));

	}

	@Test(dependsOnMethods = { "submitOrder01" },enabled=false)
	public void orderHistoryTest() {

		// Go to Order Page
		orderPage.navigateOrderPage();

		// Check in orders whether order Id is present
		Assert.assertTrue(orderPage.checkOrderId(orderIds));

	}




	@DataProvider
	public Object[][] getTestData() throws IOException {

		List<HashMap<Object,Object>> data=getJsonDataToMap("//src//test//java//e2e//testData//orderTestData.json");

		Object[][] testData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            testData[i] = new Object[]{data.get(i)};
        }
        return testData;

	}
}
