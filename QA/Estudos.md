# Quality Assurance - QA

Conjunto de processos e atividades sistemáticas que visam garantir que os produtos ou serviços sejam desenvolvidos de acordo com padrões de qualidade estabelecidos.


## Casos de Testes

Um conjunto de valores de entrada, pré-condições de execução, resultados esperados e pós-condições de execução, desenvolvido para um objetivo particular ou condição de teste.


## Casos de Testes Derivados

- User Story
    - Como
    - Quero
    - Para

- Regras de Negócio
    - Se isso
    - Faça Aquilo

- Critérios de Aceitação
    - Dado
    - Quando
    - Então

Com uso de técnicas de design de casos de teste e com base em regras de negócio como **Partição de Equivalência, Análise do Valor Limite, Tabela de Decisão, etc.**. A derivação também pode ocorrer de maneira ad-hoc, **com base na interpretação do texto que descreve a regra.**


## Exemplo de Derivação com Base em Tabela de Decisão

Clientes que possuem saldo positivo podem fazer transferências a seus favorecidos. Ao realizar uma transferência, o saldo deve ser subtraído da conta origem e adicionado a conta destino.

|   Variável    |  Partição  | CT1 | CT2 | CT3 | CT4 | 
|---------------|------------|-----|-----|-----|-----|
|     Saldo     |  Positivo  | 👍  | 👍  |     |     |
|               |  Negativo  |     |     | 👍  | 👍  |
|  Favorecido   |     Sim    | 👍  |     | 👍  |     |
|               |     Não    |     | 👍  |     | 👍  |
| Saldo Origem  | Subtraído  | ✔  |      |     |     |
|               |  Intacto   |     |  ✔  | ✔  |  ✔  |
| Saldo Destino | Adicionado | ✔  |      |     |     |
|               |  Intacto   |     |  ✔  | ✔  |  ✔  |


## Visão da ISTQB

Documento
**CT1**
**Transferência de valor com saldo positivo**

- Pré-condições de execução
    - O cliente precisa ter saldo de R$ 500,00 
    - A favorecida precisa ter saldo de R$ 0,00

- Valores de entrada
    - Bebel como Favorecida
    - R$ 500,00 como Valor da Transferência

- Resultados esperados
    - Sucesso ao transferir os R$ 500,00

- Pós-condições de execução
    - Novo saldo do cliente será R$ 0,00
    - Novo saldo da Bebel será R$ 500,00


## A Adaptação das Ferramentas de Gestão de Testes

Inspiradas em normas como a ISO-29119-3

Documento
|**Projeto: Banco 2.0**|**ID: CT-1**| |
|----------------------|------------|-|
|**Ambiente:** Web, Chrome 99, Mobile|
|**Autor:** Thiago Santos|
|**Titulo:** Transferência de valor com saldo positivo|
|**Pré-condições:**|
|O cliente precisa ter saldo de R$ 500,00|
|A favorecida precisa ter saldo de R$ 0,00|
|**Passos**|**Descrição**|**Resultado esperado**|
|    1     |Faça login com os dados do cliente|Tela secreta apresentada|
|    2     |Inicie uma transferência de valores|Foco no campo favorecido|
|    3     |Informe R$ 500 para a Bebel|Mensagem de confirmação é apresentada|
|    4     |Confirme a transferência|Mensagem de sucesso na transferência é apresentada|
|    5     |Faça logoff do sistema|Tela de login é apresentada|
|**Pós-condições:**|
|O cliente precisa ter saldo de R$ 0,00|
|A favorecida precisa ter saldo de R$ 500,00|


## Uma Adaptação do Mercado em Times Ágeis

Com base em estruturas de escrita **usando a sintaxe Gherkin e especificação por exemplos.** Muitos chamam esse template de Caso de Teste em BBD, mas está errado.

**Cenário: TransferÊncia de valor com saldo positivo**
**Dado que** o usuário tem saldo de R$ 500,00
**Quando** ele seleciona Bebel como Favorecida
**E** R$ 500,00 como Valor da Transferência
**Então** o usuário é informado do sucesso da Transferência
**E** o novo saldo do usuário de Lima passa a ser R$ 0,00
**E** o novo saldo da Bebel passa a ser R$ 500,00


## Boas Práticas

- Não crie casos de teste que dependem de outros casos de teste
- Crie casos de teste que sejam claros e diretos ao ponto
- Siga, ao menos, o template incial com pré-condições, entradas, resultado esperado e pós-condições
- Não escreva casos de teste que se baseiam em suposições
- Crie casos de teste com base em técnicas de design


## Behavior-Driven Development - BDD

Desenvolvimento Orientado a Comportamento é uma prátiva que visa melhorar a comunicação entre as partes interessadas no desenvolvimento de software, como desenvolvedores, testadores e clientes.

**Conceitos de BDD**

1. BDD se concentra no comportamento do software, procura garantir que o software se comporte de uma maneira que seja útil e compreensível para o usuário final.

2. No BDD as especificações são escritas em uma linguagem natural que é facil de entender. Normalmente são descritas em termos de "dado-quando-então" (given-when-then)

3. As especificações são usadas para criar testes automatizados que verificam se o software se comporta como esperado. Ferramentas populares para BDD incluem Cucumber, SpecFlow e JBehanve.