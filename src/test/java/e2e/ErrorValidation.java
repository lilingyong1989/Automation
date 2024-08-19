package e2e;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import e2e.TestComponents.Base;


public class ErrorValidation extends Base {

	@Test(groups= {"ErrorHandling"})
	public void errorValid01() throws IOException, InterruptedException {

		//login
		loginPage.login("as@example.com", "Abcd@1234");
		AssertJUnit.assertEquals("Incorrect email or password.", loginPage.getInvalidLoginMsg());

	}

	@Test
	public void errorValid02() throws IOException, InterruptedException {
		//login
		loginPage.login("as@example.com", "Abcd@1234");
		AssertJUnit.assertEquals("Incorrect email or passwords.", loginPage.getInvalidLoginMsg());

	}
}
