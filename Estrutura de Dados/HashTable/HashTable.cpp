#include <iostream>
#include "HashTable.h"
#include <cstddef> //null

using namespace std;

    HashTable::HashTable()
    {
        maxItens = 100;
        key = new Knot[maxItens];
    }

    HashTable::HashTable(int max)
    {
        maxItens = max;
        key = new Knot[maxItens];
    }

    HashTable::~HashTable()
    {
        delete[] key;
    }

    bool HashTable::isEmpty()
    {
        return (key == NULL);
    }

    bool HashTable::isFull()
    {
        for (int i = 0; i < maxItens; i++) 
            if (!key[i].queues.isFull()) 
                return false;
        return true;
    }

    void HashTable::insert(HashTableType item)
    {
        if (isFull()) {
            cout << "A tabela hash esta cheia!\n";
            cout << "Nao foi possivel inserir este item!";
        } else {
            HashTableType pos = item % maxItens;
            key[pos].queues.enqueue(item);
        }
    }

    // HashTableType HashTable::remove()
    // {
    //     if (isEmpty()) {
    //         cout << "A tabela hash esta vazia!\n";
    //         cout << "Nao e possivel remover nenhum item";
    //         return 0;
    //     } else {
    //         for (int i = maxItens-1; i < maxItens; i++) {
    //             if (!key[i].queues.isEmpty()) {
    //                 return key[i];
    //                 while (!key[i].queues.isEmpty()) {
    //                     key[i].queues.dequeue();
    //                 }
    //                 return key[i];
    //             }
    //         }
    //     }
    // }

    HashTableType HashTable::find(HashTableType item)
    {
        if (isEmpty()) {
            cout << "A tabela hash esta vazia!\n";
            cout << "Nao e possivel procurar nenhum item";
        } else {
            HashTableType pos = item % maxItens;
            for (int i = 0; i < key[pos].queues.getLast(); i++)
            {
                if (key[pos].queues.getStructure()[i] == item) 
                    return i << pos;
            }
        }
        return 0;
    }

    void HashTable::print()
    {
        Knot* newKey = key;
        cout << "Table Hash: [ \n";
        for (int i = 0; i < maxItens; i++) {
            if (!key[i].queues.isEmpty()) {
                cout << "Posicao " << i << ": ";
                key[i].queues.print();
            }
        }
        cout << "]\n";
    }
