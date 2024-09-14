from test_app import test_app as ta
from selenium.webdriver.common.by import By
from login_galmo import perform_login, close


test_app = perform_login("thiago.santos@galmo.uat", "Mari1234")


xpath = '//button[@title="Iniciador de aplicativos"]'
element = test_app.wait_presence(xpath=xpath, time=30)

if element is None:
    print("Elemento não encontrado!")


element.click()


test_app.driver.send_keys("Leads")
# element.send_keys("Leads")

# xpath = '//input[contains(@placeholder, "itens...")]'
# element = test_app.wait_presence(xpath=xpath, time=0)
# test_app.insert_value(xpath=xpath, message="Leads")

if element is None:
    print("Elemento 2 não encontrado!")

# input = test_app.get_item(By.XPATH, xpath)

# test_app.insert_value()

close()



