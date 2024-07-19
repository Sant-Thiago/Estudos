#include <iostream>
#include "queue.h"

using namespace std;

    queue::queue() 
    {
        first = 0;
        last = 0;
        structure = new QueueType[maxItens];
    }

    queue::~queue() 
    {
        delete[] structure;
    }

    void queue::enqueue(QueueType item) 
    {
        if (isFull()) {
            cout << "A fila está chei\n";
            cout << "Esse elemnto nao pode ser inserido!";
        } else {
            structure[last % maxItens] = item;
            last++;
        }
    }

    QueueType queue::dequeue() 
    {
        if (isEmpty()) {
            cout << "A fila está vazia!\n";
            cout << "Nenhum elemento foi removido";
            return 0;
        } else {
            return structure[first++ % maxItens];
        }
    }

    bool queue::isEmpty()
    {
        return (first == last);
    }

    bool queue::isFull()
    {
        return (last-first == maxItens);
    }

    void queue::print()
    {
        cout << "Queue [ ";
        for (int i = first; i < last; i++) {
            cout << structure[i % maxItens] << " ";
        }
        cout << "]\n";
    }