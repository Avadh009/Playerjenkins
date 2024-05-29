package Gamer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class AB1 {

//	private WebDriver driver;
	private String url = "https://tutorialsninja.com/demo/index.php?route=account/login";
//	EdgeDriver driver;
	WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {

//		WebDriverManager.edgedriver().setup();
//		Thread.sleep(2000);
//		 driver = new EdgeDriver();

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get(url);
//		Thread.sleep(1000);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void invalidLogin() throws InterruptedException {
		Thread.sleep(2000);
		WebElement UserName = driver.findElement(By.id("input-email"));
		WebElement password = driver.findElement(By.id("input-password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@class= 'btn btn-primary']"));

//		Thread.sleep(1000);
		UserName.sendKeys("avadhbhalani@gmail.com");
		password.sendKeys("4321");
		loginButton.click();
//		Thread.sleep(1500);
		WebElement errormsg = driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]"));
		Assert.assertTrue(errormsg.isDisplayed());

		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void validLogin() throws InterruptedException {
		WebElement UserName = driver.findElement(By.id("input-email"));
		WebElement password = driver.findElement(By.id("input-password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@class= 'btn btn-primary']"));

		UserName.clear();
		password.clear();
		Thread.sleep(5000);
		UserName.sendKeys("avadhbhalani123@gmail.com");
		password.sendKeys("1234");
		loginButton.click();

		String ExpectedText = "My Account";
		String ActucalText = driver.getTitle();
		Assert.assertEquals(ExpectedText, ActucalText);
	}

	@Test(priority = 3)
	public void searchProduct() {

		WebElement SearchProduct = driver.findElement(By.name("search"));
		SearchProduct.sendKeys("Mobile");
		// click on search button
		driver.findElement(By.xpath("/html[1]/body[1]/header[1]/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]/i[1]"))
				.click();
		// CheckBox
		driver.findElement(By.id("description")).click();
		// Search button
		driver.findElement(By.id("button-search")).click();
		String CurrentUrl = driver.getCurrentUrl();
		String ActualUrl = "https://tutorialsninja.com/demo/index.php?route=product/search&search=Mobile";

		if (CurrentUrl != ActualUrl) {
			System.out.println("Product is Display Correctly");
		} else {
			System.out.println("Product is not available");
		}
	}

	@Test(priority = 4)
	public void SelectToCart() throws InterruptedException {

		// Select the product
		WebElement ddown = driver.findElement(By.id("input-sort"));
		ddown.click();
		Select de = new Select(ddown);
		Thread.sleep(5000);
//		de.selectByValue("Model (A - Z)");
		de.selectByIndex(7);

		driver.findElement(By.linkText("iPhone")).click();
		driver.findElement(
				By.xpath("//body/div[@id='product-product']/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/img[1]"))
				.click();
		int i = 0;

		// View the Product
		while (i < 5) {

			driver.findElement(By.xpath("//body/div[2]/div[1]/button[2]")).click();
			Thread.sleep(1000);
			i++;
		}
		Thread.sleep(2000);
		driver.findElement(By.className("mfp-close")).click();

//		Product Quantity
		WebElement quantity = driver.findElement(By.id("input-quantity"));
		quantity.clear();
		quantity.sendKeys("2");
		Thread.sleep(2000);
		WebElement AddCart = driver.findElement(By.xpath("//button[@id='button-cart']"));
		AddCart.click();

		if (driver.findElement(By.xpath("//body/div[@id='product-product']/div[1]")).isDisplayed()) {
			System.out.println("Your product successfull added in your cart");
		} else {
			System.out.println("Your product not added in cart");
		}
	}

	@Test(priority = 5)
	public void shopping() throws InterruptedException {

		Thread.sleep(3000);
		// Shopping cart
//		driver.findElement(By.id("button-cart")).click();
		driver.findElement(By.linkText("shopping cart")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]")).click();
		Thread.sleep(2000);
		WebElement quantity = driver.findElement(By.xpath("//input[@class='form-control']"));
		quantity.clear();
		Thread.sleep(2000);
		quantity.sendKeys("2");

		boolean display1 = driver.findElement(By.xpath("//body/div[@id='checkout-cart']/div[1]")).isDisplayed();

		if (display1 == true) {

			System.out.println("Quantity is not available as of you can select other same product");
			driver.findElement(By.xpath("//body/div[@id='checkout-cart']/ul[1]/li[1]/a[1]/i[1]")).click();
		}

	}

}
