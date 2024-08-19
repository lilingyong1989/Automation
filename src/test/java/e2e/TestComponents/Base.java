package e2e.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import e2e.pageObjects.CartPage;
import e2e.pageObjects.LoginPage;
import e2e.pageObjects.OrderPage;

public class Base {
	public WebDriver driver;
	public LoginPage loginPage;
	public CartPage cartPage;
	public OrderPage orderPage;

	@Test
	public WebDriver initDriver() throws IOException {
		//properties class
		Properties prop=new Properties();

		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//e2e//Resources//GlobalData.properties");
		prop.load(file);
		String browser=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		switch(browser) {
		case "Chrome":
			ChromeOptions options=new ChromeOptions();
			options.addArguments("headless");
			driver=new ChromeDriver(options);
			break;

		case "Edge":
			driver=new EdgeDriver();
			break;

		case "Firefox":
			driver=new FirefoxDriver();
			break;

		default:
			System.out.println("Browser is not defined properly");
			break;

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		return driver;
	}

	public List<HashMap<Object,Object>> getJsonDataToMap(String filePath) throws IOException {
		 String jsonContent = FileUtils.readFileToString(
	                new File(System.getProperty("user.dir") + filePath),
	                StandardCharsets.UTF_8
	        );
		//string to hashmap using jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<Object,Object>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<Object, Object>>>() {});

		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File sc=ts.getScreenshotAs(OutputType.FILE);
		File ds=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(sc,ds);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}


	@BeforeMethod(alwaysRun=true)
	public void openWebpage() throws IOException {
		driver=initDriver();
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
		//open web page
		loginPage.launchLoginPage();
	}


	@AfterMethod(alwaysRun=true)
	public void cleanup(){
		driver.quit();
	}


}
