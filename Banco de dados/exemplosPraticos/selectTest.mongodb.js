/* Data Select by MongoDB */

use('ProjetoTest')
// Seleciona todos valores
db.filmes.find()

use('ProjetoTest')
// Seleciona UM valor do ano 1978 e retira o _id da consulta
db.filmes.findOne({"ano": 1978}, {"_id": 0})

use('ProjetoTest')
// Seleciona UM valor do ano 1978 e retira o _id da consulta e especifica o diretor na consulta
db.filmes.findOne({"ano": 1978}, {"_id": 0, "diretor": 1})

use('ProjetoTest')
// Seleciona todos valores que contêm "estrela" no field titulo, excluindo os fields _id e ano
// /estrela/ = faz consulta sensitiva
// /estrela/i = faz consulta insensitiva 
db.filmes.find({"titulo": /estrela/i}, {"_id": 0, "ano": 0})