package method_1;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class testSuite extends ReportBuilder {

	@Test
	public void Test1() {

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
