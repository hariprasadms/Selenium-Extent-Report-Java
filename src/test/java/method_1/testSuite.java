package method_1;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testSuite extends ReportBuilder {

	@Test
	public void Test1() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		System.out.println("Pass");

	}

	@Test
	public void Test2() {

		System.out.println("Pass");
	}

	@Test
	public void Test3() {

         Assert.assertEquals(true, false);
	}

	@Test
	public void Test4() {

		System.out.println("Pass");
	}

	@Test
	public void Test5() {

		System.out.println("Pass");
	}

	@Test
	public void Test6() {

		 Assert.assertEquals(true, false);
	}

}
