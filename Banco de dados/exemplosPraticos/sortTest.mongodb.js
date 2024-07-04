/* Datas Sort by MongoDB */

use('ProjetoTest')
db.filmes.find({
    "ano": {$gte: 1978, $lte: 1980} 
}).sort({"diretor":1})

// ordenação Ascendente (A-Z) 1
// ordenação Descendente (Z-A) -1
