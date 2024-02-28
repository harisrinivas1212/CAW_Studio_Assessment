package CAW_Studio.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicTablePage {

	WebDriver driver;
	public DynamicTablePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='jsondata']")
	WebElement edit_textbox;
	@FindBy(xpath = "//*[@id='refreshtable']")
	WebElement btn_refreshtable;
	@FindBy(xpath = "//summary")
	WebElement tableContent;
	@FindBy(xpath = "//*[@id='dynamictable']")
	WebElement dynamicTable;
	
	
	// lauching the link
	public void goTo() {
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
	}
	
	// performing the actions in DynamicTablePage
	public void enterJSONData(String jsondata) throws InterruptedException {
		tableContent.click();
		edit_textbox.clear();
		edit_textbox.sendKeys(jsondata);
		btn_refreshtable.click();
	}
	
	// taking the data from WebTable appending to StringBuilder
	public StringBuilder getDatafromTable() {
		List<WebElement> rows = dynamicTable.findElements(By.tagName("tr"));
        StringBuilder webTableData = new StringBuilder();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                webTableData.append(cell.getText()).append(",");
            }
            webTableData.append("\n");
        }
        return webTableData;
	}
}
