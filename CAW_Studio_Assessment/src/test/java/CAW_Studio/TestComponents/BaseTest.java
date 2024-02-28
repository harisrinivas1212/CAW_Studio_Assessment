package CAW_Studio.TestComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import CAW_Studio.PageObjects.DynamicTablePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public DynamicTablePage dynamicTablePage;
	
	
	@BeforeMethod(alwaysRun = true)
	public void launchURL() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		dynamicTablePage = new DynamicTablePage(driver);
		dynamicTablePage.goTo();
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}

	// converting json data to String
	public String getJsonDataToString(String filepath) throws IOException {

		// read json and convert to string
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		return jsonContent;
	}
}
