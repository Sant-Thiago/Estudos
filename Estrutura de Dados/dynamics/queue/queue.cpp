#include <iostream>
#include "queue.h"
#include <cstddef> //null

using namespace std;

    queue::queue() 
    {
        first = NULL;
        last = NULL;
    }

    queue::~queue() 
    {
        Knot* tempFirst;
        while (first != NULL)
        {
            tempFirst = first;
            first = first -> next;
            delete tempFirst;
        }
        last = NULL;
    }

    void queue::enqueue(QueueType item) 
    {
        if (isFull()) {
            cout << "A fila está cheia\n";
            cout << "Esse elemento nao pode ser inserido!";
        } else {
            Knot* newKnot = new Knot;
            newKnot -> value = item;
            newKnot -> next = NULL;
            if (first == NULL) {
                first = newKnot;
            } else {
                last -> next = newKnot;
            }
            last = newKnot;
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
        return (first == NULL);
    }

    bool queue::isFull()
    {
        try
        {
            Knot* newKnot = new Knot;
            delete newKnot;
            return false;
        }
        catch(bad_alloc e)
        {
            return true;
        }
    }

    void queue::print()
    {
        Knot* tempFirst = first;
        cout << "Queue [ ";
        while (tempFirst != NULL) {
            string point = ", ";
            if (tempFirst -> next == NULL) {
                point = " ";
            }
            cout << tempFirst -> value << point;
            tempFirst = tempFirst -> next;
        }
        cout << "]\n";
    }