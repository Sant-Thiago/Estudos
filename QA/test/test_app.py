from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException, ElementNotInteractableException, InvalidSelectorException, TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options as ChromeOptions
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.common.exceptions import WebDriverException
from webdriver_manager.microsoft import EdgeChromiumDriverManager
from selenium.webdriver.support import expected_conditions as EC 
from test import info, error, debug, warning, critical

class test_app:
    
    def __init__(self):
        try:
            self.driver = self.chrome_driver()
            if self.driver is None:
                self.driver = self.edge_driver()
            if self.driver is None:
                self.driver = self.firefox_driver()
            if self.driver is None:
                raise Exception("Nenhum WebDriver disponível. Instale o Chrome, Edge ou Firefox.")
        except WebDriverException as e:
            error(f"Erro ao inicializar o WebDriver: {e}")

    def go_to(self, url):
        info(f"Navegando para: {url}")
        self.driver.get(url)

    def insert_value(self, message):
        self.element.send_keys(message)
        info(f"Inserindo valor no campo com o id: {id}")

    def insert_value(self, id = None, class_name = None, name = None, xpath = None, link = None, message = ""):
        try: 
            self.wait_presence(id, class_name, name, xpath, link)

            by, value = self.getWebElement(id, class_name, name, xpath, link)

            self.element = self.driver.find_element(by, value)
            self.element.send_keys(message)
            info(f"Inserindo valor no campo com o id: {id}")
        except NoSuchElementException as e :
            debug(f"O elemento não foi econtrado: {e}")
        except ElementNotInteractableException as e:
            debug(f"O elemento não pode ser interagido: {e}")

    def enter(self):
        # try:
            self.element.send_keys(Keys.RETURN)
        #   info(f"Avançando de página")    
        #   return True
        # except:
        #   debug(f"Houve algo ao tentar avançar a página")    
        #   return False

    def get_item(self, type, item):
        return self.driver.find_element(type, item)
    
    def click(self, time = 14, value = None):
        element = WebDriverWait(self.driver, time).until(
            EC.presence_of_element_located(value)
        )     

        element.click()

    def esc(self):
        element = self.driver.find_element(By.TAG_NAME, "body")
        element.send_keys(Keys.ESCAPE)

    # Ativa o campo em focus e retorna o elemento
    def active(self):
        return self.driver.switch_to.active_element

    def wait_presence(self, id = None, class_name = None, name = None, xpath = None, link = None, time = 14):
        try:
            value = self.getWebElement(id, class_name, name, xpath, link)            

            return WebDriverWait(self.driver, time).until(
                EC.presence_of_element_located(value)
            )
        except InvalidSelectorException as e:
            debug(f"O elemento não encontrado: {e}")
            return None
        except TimeoutException as e:
            debug(f"O elemento não encontrado: {e}")
            return None

    def getWebElement(self, id = None, class_name = None, name = None, xpath = None, link = None):
        if id is not None:
            value = (By.ID, id)
        elif class_name is not None:
            value = (By.CLASS_NAME, class_name)
        elif name is not None:
            value = (By.NAME, name)
        elif xpath is not None:
            value = (By.XPATH, xpath)
        elif link is not None:
            value = (By.LINK_TEXT, link)
        return value


    def chrome_driver(self):
        try:
            # Configura as opções do Chrome
            chrome_options = ChromeOptions()
            chrome_options.add_argument("--disable-notifications")

            driver = webdriver.Chrome(options=chrome_options)
            info(f"Chrome WebDriver incializado com sucesso")
            return driver
        except WebDriverException as e:
            debug(f"Falha ao inicializar o Chrome: {e}")
            return None
        
    def firefox_driver(self):
        try:
            # Configura as opções do Firefox
            firefox_options = FirefoxOptions()
            # Configura o perfil do Firefox
            firefox_profile = webdriver.FirefoxProfile()

            # Configura as permissões d
            firefox_profile.set_preference("dom.webnotifications.enabled", False)
            firefox_options.profile = firefox_profile

            driver = webdriver.Firefox(options=firefox_options)
            info(f"Firefox WebDriver incializado com sucesso")
            return driver
        except WebDriverException as e:
            debug(f"Falha ao inicializar o Firefox: {e}")
            return None
        
    def edge_driver(self):
        try:
            driver = webdriver.Edge()
            info(f"Edge WebDriver incializado com sucesso")
            return driver
        except WebDriverException as e:
            debug(f"Falha ao inicializar o Edge: {e}")
            return None
        
    def close(self):
        self.driver.quit()