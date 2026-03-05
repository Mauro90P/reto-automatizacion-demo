package steps;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import support.DriverManager;

public class PlaywrightSteps {

    private Page page;

    public PlaywrightSteps() {
        Browser browser = DriverManager.getPlaywrightBrowser();
        this.page = browser.newPage();
    }

    private void loginAndAddToCart(String productName) {
        page.navigate("https://www.saucedemo.com/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        Assert.assertTrue(page.url().contains("inventory.html"));
        
        String addButtonSelector = "#add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        page.click(addButtonSelector);
        Assert.assertEquals("1", page.locator(".shopping_cart_badge").textContent());
    }

    @Given("the user has added {string} to the shopping cart")
    public void userHasAddedToCart(String productName) {
        loginAndAddToCart(productName);
    }

    @When("the user navigates to the cart page")
    public void userNavigatesToCartPage() {
        page.click(".shopping_cart_link");
        Assert.assertTrue(page.url().contains("cart.html"));
    }

    @And("removes the product from the cart")
    public void removesProductFromCart() {
        page.click("button.cart_button");
    }

    @Then("the shopping cart is empty")
    public void shoppingCartIsEmpty() {
        Assert.assertFalse("Shopping cart icon should not be visible", page.locator(".shopping_cart_badge").isVisible());
    }

    @Given("the user has added {string} to the cart and is on the cart page")
    public void userHasAddedToCartAndIsOnCartPage(String productName) {
        loginAndAddToCart(productName);
        page.click(".shopping_cart_link");
        Assert.assertTrue(page.url().contains("cart.html"));
    }

    @When("the user proceeds to checkout")
    public void userProceedsToCheckout() {
        page.click("#checkout");
        Assert.assertTrue(page.url().contains("checkout-step-one.html"));
    }

    @And("fills the form with {string}, {string} and {string}")
    public void fillsTheForm(String firstName, String lastName, String postalCode) {
        page.fill("#first-name", firstName);
        page.fill("#last-name", lastName);
        page.fill("#postal-code", postalCode);
        page.click("#continue");
        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    @And("finishes the purchase")
    public void finishesThePurchase() {
        page.click("#finish");
    }

    @Then("the user sees the confirmation message {string}")
    public void userSeesConfirmationMessage(String message) {
        String confirmationMessage = page.locator("h2.complete-header").textContent();
        Assert.assertEquals(message, confirmationMessage);
    }
}

