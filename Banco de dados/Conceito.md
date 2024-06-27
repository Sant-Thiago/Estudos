> # Banco de dados
> ****
> ## Modelos de Database
>
> **NoSQL** 
>   No only Structed Query Language, é um tipo de banco de dados não relacional, normalmente são orientados a documentos, por exemplo um JSON chave-valor;
>
> Equivalencia de modelos:
> <br>
> | Mongo/NoSQL Termos | Trandicional SQL Termos |
> |--------------------|-------------------------|
> |      Database      |         Database        |
> |     Collection     |          Table          |
> |      Document      |           Row           |
> |       Field        |         Column          |
> 
> No mongoDB para criar uma **Database** execute USE <Nome_Database> ao invés de CREATE DATABASE...
> Cada documento tem um atributo identificado **(_id)**, o id tem a quantidade de 24 caracteres;
> - BSON Types - Tipos de Dados
>   - Timestamp: Timestamp equivalente ao do SQL tradicional, útil para a gravação de data quando um documento for modificado ou acrescentado;
>   - Object: Este tipo de dado se equipara ao foreign key, usado para incorporar documentos;
>   - Date: Como o nome diz, usado para armazenar data ou a hora atual no formato de UNIX;
>   - Object ID: Este tipo de dado é usado para armazenar os identificadores (_id) dos documentos;
>   - Binary data: Este tipo de dado é usado para armazenar um dado binário;
>   - Regular expression: Este tipo de dado é usado para armazenar expressões regulares.
>
> - Comandos
>   - CREATE DATABASE
>       - use(): Cria um database.
>   - INSERT:
>       - insertOne(): Recebe como parâmetro um único {} documento;
>       - insertMany(): Recebe como parâmetro um array[] de documentos. 
>   - SELECT:
>       - find(): Retorna todos documentos que atendem aos critérios especificados;
>       - findOne(): Retorna um único documento que atende ao os critérios, caso exista mais de um documento com o valor especificado retorna o primeiro.
>
> - Operadores
>   - Comparção
>      - $eq: Exibe valores que são **iguais** ao valor especificado.(=)
>      - $gt: Exibe valores que são **maiores** ao especificado. (>)
>      - $gte: Exibe valores que são **maiores ou iguais** aoespecificado. (>=)
>      - $in: Exibe valores **iguais** aos especificados no array. (in)
>      - $it: Exibe valores que são **menores** ao especificado. (<)
>      - $ite: Exibe valores que são **menores ou iguais** aoespecificado. (<=)
>      - $ne: Exibe valores **diferentes** ao especificado. (<>)
>      - $nin: Exibe valores **diferentes** aos especificados no array. (not in)
>   - Lógico
>       - $and: Retorna todos os documentos que são verdadeiros em ambas as cláusulas. (and)
>       - $or: Retorna todos os documentos que são verdadeiros em pelo menos uma das cláusulas. (or)
>       - $not: Retorna todos os documentos diferentes da cláusula solicitada. (not)
>       - $nor: Retorna todos os documentos que são falsos em ambas as cláusulas. (nor)
>   - Elemento
>       - 
>
> // aula 7, professor alguma coisa lima - operadores





