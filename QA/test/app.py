from test_app import test_app as ta

# Instância o futuro objeto manager  
test_app = ta()
# Vai para o link indicado
test_app.go_to("https://youinc--staging.sandbox.my.salesforce.com/")
# Insere valores no objeto com o id "username"
test_app.insert_value(id="username", message="secretaria.vendas@elera.io")
# Insere valores no objeto com o id "password"
test_app.insert_value(id="password", message="secretaria2024")
# Entrar
test_app.enter()
# Pausar até o usuário pressionar Enter
input("Pressione Enter para fechar o navegador...")
# Fecha o webdriver
test_app.close()