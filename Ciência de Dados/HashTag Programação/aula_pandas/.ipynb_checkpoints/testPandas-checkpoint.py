# Pandas: facil e alta performance
# DataFrame usado em várias outras bibliotecas de Ciência de Dados

import pandas as pd

base = pd.read_csv('C:\\Users\\Thiago Santos\\Documents\\coisasMinhas\\Estudos\\Ciência de Dados\\HashTag Programação\\MOCK_DATA.csv')

base.head(10) # retorna no min as 5 linhas superiores, depende do valor do argumento 
base.tail(10) # retorna no min as 5 linhas últimas, depende do valor do argumento 
base.shape[0|1] # retorna a quantidade de linhas e colunas ou somente um em especifico 
baseObjeto = base.to_json()

# Exibe primeiras e últimas linhas e tamanho da base
display(base)
