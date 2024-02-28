package CAW_Studio.Tests;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CAW_Studio.PageObjects.DynamicTablePage;
import CAW_Studio.TestComponents.BaseTest;

public class DynamicTableDataTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void DynamicTableDataValidation(String input) throws InterruptedException, IOException {


		DynamicTablePage dynamictablepage = new DynamicTablePage(driver);
		dynamictablepage.enterJSONData(input);
		StringBuilder webTableData = dynamictablepage.getDatafromTable();
		
		// converting the input(json data) to array and appending to StringBuilder
		JSONArray jsonArray = new JSONArray(input);
		StringBuilder jsonData = new StringBuilder();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject item = jsonArray.getJSONObject(i);
			jsonData.append(item.getString("name")).append(",").append(item.getInt("age")).append(",").append(item.getString("gender")).append(",").append("\n");
		}

		System.out.println(webTableData.toString());
		System.out.println(jsonData.toString());
		//Validate the json data and Dynamic WebTableData
		
		 Assert.assertEquals(webTableData.toString().trim(), jsonData.toString().trim());
		 
	}


	//getting the data from the json file
	@DataProvider
	public Object[] getData() throws IOException {

		String data = getJsonDataToString(System.getProperty("user.dir")+"\\src\\test\\java\\CAW_Studio\\Data\\TestData.json");
		return new Object [] {data};

	}
}
