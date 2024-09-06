from test_app import test_app as ta
from selenium.webdriver.common.by import By

test_app = None

def perform_login(username, password):
    # Instância o futuro objeto manager  
    global test_app
    test_app = ta()

    # Vai para o link indicado
    test_app.go_to("https://galmoambientesf24--uat.sandbox.lightning.force.com/")

    # Insere valores no objeto com o id "username"
    test_app.insert_value(id="username", message=username)

    # Insere valores no objeto com o id "password"
    test_app.insert_value(id="password", message=password)

    # Entrar
    test_app.enter()
    return test_app

def close():
    global test_app
    if test_app:
        # Pausar até o usuário pressionar Enter
        input("Pressione Enter para fechar o navegador...")
        # Fecha o webdriver
        test_app.close()
    else:
        print("test_app não está inicializado")


# leadsPage = test_app.get_item(By.LINK_TEXT, "Leads")

