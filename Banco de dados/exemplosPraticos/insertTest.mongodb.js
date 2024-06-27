/* Data Insert by MongoDB */

// Selecionar o database a ser utilizado
use('ProjetoTest')

// Exemplo de insert com o insertOne na tabela filmes
db.filmes.insertOne({
    "titulo": "Guerra nas Estrelas",
    "ano": 1978,
    "diretor": 'George Lucas'
})

use('ProjetoTest')
// Exemplo de insert com insertMany 
db.filmes.insertMany([
    {
        "titulo": "O exorcista",
        "ano": 1973,
        "diretor": "Willian Friedkin"
    }, {
        "titulo": "007 Sem tempo para morrer",
        "ano": 2020,
        "diretor": "Cary Fukunaga",
        "genero": "ação"
    }
])
