/* Data Delete by MongoDB */

use('ProjetoTest')
// deleteOne delete somente um valor, com base na condição passada.
db.filmes.deleteOne({titulo: "The Exorcist"})

use('ProjetoTest')
// deleteMany deleta varios registro, também com base na condição.
db.filmes.deleteMany({diretor: "George Lucas"})

use('ProjetoTest')
// deleteMany sem paramtros deleta todos registros.
db.filmes.deleteMany({})

// explica o metodoo que desejar
db.filmes.explain
