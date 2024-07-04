/* Data Update by MongoDB */

use('ProjetoTest')
// updateOne atualiza somente um valor, com base na condição passada no primeiro parametro
// $set setta o novo valor que eu quero para aquele registro 
db.filmes.updateOne(
    {titulo: "Guerra nas Estrelas"},
    {
        $set: {
            ano: 1977,
            genero: "Ficção"
        }
    }
)

use('ProjetoTest')
// updateMany atualiza varios registro, também com base na condição passada no primeiro parametro
db.filmes.updateMany(
    {diretor: "George Lucas"},
    {
        $set: {
            duracao: 100_312_013 // duracao não existe mas ele cria
        }
    }
)

use('ProjetoTest')
// replaceOne atualiza um único registro, e substitui todos atributos de um objeto pelos valores colocados no segundo parametro.
// nesse caso só vai existir o id, title e gender onde o ano do filme é igual a 1983
db.filmes.replaceOne(
    {ano: 1973},
    {
        title: "The Exorcist",
        gender: "Terror"
    }
)