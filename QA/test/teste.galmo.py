from test_app import test_app as ta
from selenium.webdriver.common.by import By


def close():
    # Pausar até o usuário pressionar Enter
    input("Pressione Enter para fechar o navegador...")
    # Fecha o webdriver
    test_app.close()

# Instância o futuro objeto manager  
test_app = ta()
# Vai para o link indicado
test_app.go_to("https://galmoambientesf24--uat.sandbox.lightning.force.com/")
# Insere valores no objeto com o id "username"
test_app.insert_value(id="username", message="thiago.santos@galmo.uat")
# Insere valores no objeto com o id "password"
test_app.insert_value(id="password", message="Mari1234")
# Entrar
test_app.enter()
existLink = test_app.wait_presence(link="Leads")


if not existLink:
    print("Link não encontrado!")
    close()

leadsPage = test_app.get_item(By.LINK_TEXT, "Leads")


close()



