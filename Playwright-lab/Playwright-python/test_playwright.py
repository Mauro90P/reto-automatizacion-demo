import pytest
from playwright.sync_api import sync_playwright, expect

# ---------- FIXTURES (equivalente a @BeforeAll / @AfterAll) ----------

@pytest.fixture(scope="session")
def playwright_instance():
    with sync_playwright() as p:
        yield p


@pytest.fixture(scope="session")
def browser(playwright_instance):
    browser = playwright_instance.chromium.launch(headless=False)
    yield browser
    browser.close()


# ---------- FIXTURES (equivalente a @BeforeEach / @AfterEach) ----------

@pytest.fixture
def context(browser):
    context = browser.new_context()
    yield context
    context.close()


@pytest.fixture
def page(context):
    page = context.new_page()
    page.goto("https://the-internet.herokuapp.com/login")
    return page


# ---------- TEST ----------

def test_validar_formulario(page):
    # Ingresar usuario y contraseña
    page.fill("#username", "tomsmith")
    page.fill("#password", "SuperSecretPassword!")

    # Hacer clic en el botón Login
    page.click("button:has-text('Login')")

    # Espera automática: Playwright espera a que el elemento esté listo
    result_element = page.locator("#flash")

    # Validar que el mensaje contenga el texto esperado
    expect(result_element).to_contain_text(
        "You logged into a secure area!"
    )

    # Pausa de 5 segundos para ver el resultado (opcional)
    page.wait_for_timeout(5000)
