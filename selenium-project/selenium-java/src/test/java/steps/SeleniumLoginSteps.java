package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import support.DriverManager;

public class SeleniumLoginSteps {

    private WebDriver driver;

    public SeleniumLoginSteps() {
        this.driver = DriverManager.getWebDriver();
    }

    @Given("the user navigates to the SauceDemo login page")
    public void userNavigatesToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user enters credentials {string} and {string}")
    public void userEntersCredentials(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("clicks the Login button")
    public void clicksLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("the user is redirected to the inventory page")
    public void userIsRedirectedToInventory() {
        Assert.assertTrue("User was not redirected to the inventory page.", driver.getCurrentUrl().contains("inventory.html"));
    }
}

