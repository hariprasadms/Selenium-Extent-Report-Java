package method_1;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

private static ExtentReports extent;
private static ExtentSparkReporter sparkReporter;
    
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("test-output/extent.html");
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(fileName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        return extent;
    }
	
}
