#if !defined(HASH_TABLE_H) // guarda de inclusão
#define HASH_TABLE_H

#include "Queue.h"

typedef int HashTableType;

struct Knot
{
    Queue queues;
};

class HashTable {
    private:
    int maxItens;
    Knot* key;

    public:
    HashTable(); // contrutora
    HashTable(int max);
    ~HashTable(); // destrutora
    bool isFull(); // verifica se está cheio
    bool isEmpty(); // verifica se está vazia
    void insert(HashTableType item); // insere
    HashTableType remove(); // remove
    HashTableType find(HashTableType item); // remove
    void print(); // imprimir
    int length(); // tamanho
};

#endif
