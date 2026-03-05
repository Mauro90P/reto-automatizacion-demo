package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            webDriver.set(createWebDriver());
        }
        return webDriver.get();
    }

    public static Browser getPlaywrightBrowser() {
        if (playwright.get() == null || browser.get() == null) {
            Playwright pw = Playwright.create();
            playwright.set(pw);
            browser.set(createPlaywrightBrowser(pw));
        }
        return browser.get();
    }

    private static WebDriver createWebDriver() {
        String browserName = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = System.getProperty("headless", "false").equals("true") || System.getenv("CI") != null;

        switch (browserName) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                return new EdgeDriver(edgeOptions);
            default: // chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                // Soluciona un problema de timeouts en contenedores Docker/CI
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                return new ChromeDriver(chromeOptions);
        }
    }

    private static Browser createPlaywrightBrowser(Playwright playwright) {
        String browserName = System.getProperty("browser", "chromium").toLowerCase();
        boolean headless = System.getProperty("headless", "false").equals("true") || System.getenv("CI") != null;
        
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName) {
            case "firefox":
                return playwright.firefox().launch(launchOptions);
            case "webkit":
                return playwright.webkit().launch(launchOptions);
            default: // chromium
                return playwright.chromium().launch(launchOptions);
        }
    }

    public static void quitWebDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

    public static void closePlaywright() {
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }
}
