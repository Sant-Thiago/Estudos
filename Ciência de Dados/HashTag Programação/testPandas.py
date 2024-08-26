# Pandas: facil e alta performance
# DataFrame usado em várias outras bibliotecas de Ciência de Dados

import pandas as pd
import matplotlib.pyplot as plt

base = pd.read_csv('C:\\Users\\Thiago Santos\\Documents\\coisasMinhas\\Estudos\\Ciência de Dados\\HashTag Programação\\MOCK_DATA.csv')

# retorna no min as 5 linhas superiores, depende do valor do argumento 
base.head(10) 

# retorna no min as 5 linhas últimas, depende do valor do argumento 
base.tail(10) 

# retorna a quantidade de linhas e colunas ou somente um em especifico 
base.shape[0|1] 

baseObjeto = base.to_json()

# Exibe primeiras e últimas linhas e tamanho da base
display(base)

# retorna somente o tipo dos dados
base.dtypes

# mostra o tipo de dados e os valores vazios
base.info()

# mostra os valores vazios por coluna
base.isnull()

#-
# Pode se somar Boolean
#-
base.isnull().sum()

#- 
# Cria Objeto e 
#-
dados = {
    'x': [2, 4, 7]
}

print(dados)

# Trata os dados para chave: index, valor: objetos
dados = pd.DataFrame(dados)

print(dados)
# Mostra a média
dados.mean()

# Mostra a quantidade de registros
dados.count()

# Mostra a mediana
dados.median()

# Desvio entre os números padrão
dados.std()

# Mostra a descrição de metódos relacionado a essa variavel
dados.describe()

# Mostra os dados da lista menores que 3
dados[dados['x'] < 3]

# Mostra dados que são iguais a 3 OU a 2
dados[dados['x'] == 3 | dados['x'] == 2]


# Mostra dados que são iguais a 3 OU a 2
dados.loc[(dados['x'] == 3) | (dados['x'] == 2)]

# Permite filtrar colunas de forma prática
dados.loc[(dados['x'] < 3) | (dados['x'] > 2), ['x']]

# Permite buscar valores especificas
dados.iloc[1:10, 3:5]

# É possível fazer um histograma simples
base.x.hist()