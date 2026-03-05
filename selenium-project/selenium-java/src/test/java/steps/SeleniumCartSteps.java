package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import support.DriverManager;

public class SeleniumCartSteps {

    private WebDriver driver;

    public SeleniumCartSteps() {
        this.driver = DriverManager.getWebDriver();
    }

    @Given("the user is logged in and on the inventory page")
    public void userIsLoggedIn() {
        // This step assumes the login scenario ran before.
        // For safety, we ensure we are on the correct page.
        if (!driver.getCurrentUrl().contains("inventory.html")) {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }
        Assert.assertTrue("User is not on the inventory page.", driver.getCurrentUrl().contains("inventory.html"));
    }

    @When("the user adds the product {string} to the cart")
    public void userAddsProductToCart(String productName) {
        String addButtonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(addButtonId)).click();
    }

    @Then("the shopping cart icon shows {string}")
    public void shoppingCartIconShows(String expectedCount) {
        String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(expectedCount, actualCount);
    }
}

