package cucumber.feature;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/feature",glue="cucumber.stepDefinition",monochrome=true,tags="@Regression",plugin={"html:targer/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	

}
