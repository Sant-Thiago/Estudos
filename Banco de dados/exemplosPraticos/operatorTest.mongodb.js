/* Opeartors by MongoDB */


use('ProjetoTest')
// Seleciona dados onde os anos que são maiores ou iguais a 1978 e menores ou iguais a 1980
db.filmes.find({
    "ano": {$gte: 1978, $lte: 1980} 
})

use('ProjetoTest')
// Seleciona dados onde os anos são maiores ou iguais a 1978 e menores ou iguais a 1980, e onde o diretor é o 'Seu Cuca'
db.filmes.find({
    $and: [
        {"ano": {$gte: 1978, $lte: 1980}},
        {"diretor": {$eq: 'Seu Cuca'}} 
    ]
})
