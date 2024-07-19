#include <iostream>
#include "Queue.h"

using namespace std;

    Queue::Queue() 
    {
        first = 0;
        last = 0;
        structure = new QueueType[maxItens];
    }

    Queue::~Queue() 
    {
        delete[] structure;
    }

    void Queue::enqueue(QueueType item) 
    {
        if (isFull()) {
            cout << "A fila está chei\n";
            cout << "Esse elemnto nao pode ser inserido!";
        } else {
            structure[last % maxItens] = item;
            last++;
        }
    }

    QueueType Queue::dequeue() 
    {
        if (isEmpty()) {
            cout << "A fila está vazia!\n";
            cout << "Nenhum elemento foi removido";
            return 0;
        } else {
            return structure[first++ % maxItens];
        }
    }

    bool Queue::isEmpty()
    {
        return (first == last);
    }

    bool Queue::isFull()
    {
        return (last-first == maxItens);
    }

    void Queue::print()
    {
        cout << "Queue [ ";
        for (int i = first; i < last; i++) {
            cout << structure[i % maxItens] << " ";
        }
        cout << "]\n";
    }
    
    int Queue::getLast()
    {
        return last;
    }

    int* Queue::getStructure()
    {
        return structure;
    }


