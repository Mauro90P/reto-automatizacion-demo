package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import support.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        // La inicialización se hará de forma perezosa en los steps
    }

    @After("@Selenium")
    public void tearDownSelenium(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                WebDriver driver = DriverManager.getWebDriver();
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (Exception e) {
                System.err.println("Error al tomar screenshot: " + e.getMessage());
            }
        }
        DriverManager.quitWebDriver();
    }
    
    @After("@Playwright")
    public void tearDownPlaywright() {
        // No se necesita screenshot para Playwright, ya que genera su propio reporte con trazas
        DriverManager.closePlaywright();
    }
}
