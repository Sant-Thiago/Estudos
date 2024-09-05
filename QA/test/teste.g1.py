from login.test_app import test_app as ta
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains


def close():
    # Pausar até o usuário pressionar Enter
    input("Pressione Enter para fechar o navegador...")
    # Fecha o webdriver
    test_app.close()

# Instância o futuro objeto manager  
test_app = ta()
# Vai para o link indicado
test_app.go_to("https://pt.wikipedia.org/wiki/Wikip%C3%A9dia:P%C3%A1gina_principal")

href_value = "https://www.globo.com/"
xpath = f'//*[@title="Enciclopédia"]'

# existLink = test_app.wait_presence(xpath=xpath)
elementLink = test_app.wait_presence(xpath=xpath, time=60)

print(f"elementLink ==> {elementLink}")

if elementLink is None:
    print("Link não encontrado!")
    close()


elementLink.click()

test_app.go_to("https://github.com/")

xpath = '//button[contains(text(), "Product")]'
elementLink = test_app.wait_presence(xpath=xpath, time=40)

print(f"elementLink ==> {elementLink}")
# Estudar XPATH

# elementLink.click()

# Move o cursor para o botão para acionar o hover
actions = ActionChains(test_app.driver)
actions.move_to_element(elementLink).perform()


# leadsPage = test_app.get_item(By.LINK_TEXT, "Leads")

close()




