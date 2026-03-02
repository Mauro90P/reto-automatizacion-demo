package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    WebDriver driver;

    @Given("el usuario navega a la página de login")
    public void navegarLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("ingresa usuario {string}")
    public void ingresarUsuario(String usuario) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
    }

    @And("ingresa password {string}")
    public void ingresarPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("hace clic en Login")
    public void hacerClickLogin() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("debería visualizar el inventario de productos")
    public void validarInventario() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"));
        driver.quit();
    }

    @Then("debería visualizar mensaje de error")
    public void validarError() {
        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assertions.assertTrue(error.isDisplayed());
        driver.quit();
    }
}