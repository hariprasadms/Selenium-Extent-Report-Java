package method_1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Base {

    private static Base instance;

    protected Base() {

    }

    public static Base getInstance() {
        if (instance == null) {
            synchronized (Base.class) {
                if (instance == null) {
                    instance = new Base();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

}
