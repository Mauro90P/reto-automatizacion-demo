import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.*;

public class TestPagePlaywright {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setUpAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false) // Cambia a true si quieres modo headless
        );
    }

    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/login");
    }

    @Test
    void validarFormulario() {
        // Ingresar usuario y contraseña
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword");

        // Hacer clic en el botón Login
        page.click("button:has-text('Login')");

        // Espera automática: Playwright espera a que el elemento esté visible
        Locator resultElement = page.locator("#flash");

        // Validar que el mensaje contenga el texto esperado
        PlaywrightAssertions.assertThat(resultElement)
            .containsText("You logged into a secure area!");

        // Pausa de 5 segundos para ver el resultado (opcional)
        page.waitForTimeout(5000);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
    }
}