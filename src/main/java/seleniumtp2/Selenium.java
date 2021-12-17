package seleniumtp2;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Selenium {

	public static void takeScreenshot(WebElement element, String fileWithPath) throws Exception {

		File SrcFile = element.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}

	public static WebDriver setUpTheDriver(String webDriverName, String webDrivePath) throws Exception {
		System.setProperty(webDriverName, webDrivePath);
		WebDriver driver = new EdgeDriver();
		Thread.sleep(1000);
		return driver;
	}

	public static void connectToAccount(WebDriver driver) throws Exception {
		driver.get("https://www.tunisianet.com.tn");
		System.out.println("tunisianet loaded");
		Thread.sleep(1000);
		driver.get("https://www.tunisianet.com.tn/mon-compte");
		System.out.println("reload to login to account");
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys("helloselenium@gmail.com");
		System.out.println("enter login");
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("helloselenium");
		System.out.println("enter password");
		Thread.sleep(1000);
		driver.findElement(By.id("submit-login")).click();
		System.out.println("submit login");
		Thread.sleep(1000);

	}

	public static void searchTheItem(WebDriver driver, String Item) throws Exception {
		driver.findElement(By.id("search_query_top")).sendKeys(Item);
		System.out.println("search for ther item");
		Thread.sleep(1000);
		driver.findElement(By.name("submit_search")).click();
		System.out.println("click on search");
		Thread.sleep(1000);
		driver.findElement(By.className("product-title")).click();

		System.out.println("click on the item");
		Thread.sleep(1000);
	}

	public static void orderTheItem(WebDriver driver) throws Exception {
		driver.findElement(By.className("add-to-cart")).click();
		System.out.println("add item to the cart ");
		Thread.sleep(1000);
		driver.navigate().to("https://www.tunisianet.com.tn/panier?action=show");
		System.out.println("navigate to the cart  ");
		Thread.sleep(1000);
		driver.navigate().to("https://www.tunisianet.com.tn/commande");
		System.out.println("navigate to the commande  ");
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.id("js-checkout-summary"));
		takeScreenshot(element, "C:\\Users\\asus\\eclipse-workspace\\seleniumtp2\\Screenshots\\screenshot.png");
		Thread.sleep(1000);
	}

	public static void confirmTheOrder(WebDriver driver) throws Exception {
		try {
			System.out.println("if adress wasn't introduced ");
			driver.findElement(By.name("address1")).sendKeys("chargeya centre urbain nord");

			driver.findElement(By.name("postcode")).sendKeys("4812");

			driver.findElement(By.name("city")).sendKeys("ariana");

			driver.findElement(By.name("phone")).sendKeys("71489632");

			driver.findElement(By.name("confirm-addresses")).click();
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("adress already present");

		}
		driver.findElement(By.name("confirm-addresses")).click();
		System.out.println("confirm adress");
		Thread.sleep(1000);

	}

	public static void main(String[] args) throws Exception {

		WebDriver driver = setUpTheDriver("webdriver.edge.driver",
				"C:\\Users\\asus\\Desktop\\tp sel driver\\msedgedriver.exe");
		connectToAccount(driver);
		searchTheItem(driver, "TISSOT CARSON PREMIUM");
		orderTheItem(driver);
		confirmTheOrder(driver);
		System.out.println("End of program");

	}
}
