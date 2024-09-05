Feature: Login de Usuário

    Scenario: Login bem-sucedido com credenciais válidas
        Given que o usuário está na página de Login
        When o usuário insere o ra e senha válidos
        Then o usuário deve ser redirecionado para a página do planejador
