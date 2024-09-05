from behave import given, when, then
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from cookies_manager import save_cookies as save


@given("que o usuário está na página de Login")
def step_given_user_is_on_login_page(context):
    # Inicializa o navegador Chrome usando o WebDriver Manager
    # chromeDriver = webdriver.Chrome(ChromeDriverManager().install())
    chromeDriver = webdriver.Chrome()
    context.driver = chromeDriver
    context.driver.get("https://planejador-financeiro.onrender.com/index.html")

@when("o usuário insere o ra e senha válidos")
def step_when_user_enters_valid_credentials(context):
    # Usa o WebDriver armazenado no context para interagir com a página
    username_ra_input = context.driver.find_element(By.ID, "ra")
    password_input = context.driver.find_element(By.ID, "password")
    username_ra_input.send_keys("1232001") 
    password_input.send_keys("senha123")
    password_input.send_keys(Keys.RETURN)

@then("o usuário deve ser redirecionado para a página do planejador")
def step_then_user_should_br_redirected(context):
    # Aguarda até que a URL seja a esperada ou um timeout de 10 segundos ocorra
    WebDriverWait(context.driver, 10).until(
        EC.url_to_be("https://planejador-financeiro.onrender.com/planejador.html")
    )
    # Salva o estado da página para futuras automações
    save(context.driver, "cookies.pkl")
    # Verifica a URL usando o WebDriver armazenado no context
    assert context.driver.current_url == "https://planejador-financeiro.onrender.com/planejador.html"
    # Fecha o navegador
    context.driver.quit()