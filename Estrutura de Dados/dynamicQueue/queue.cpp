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
            Knot* tempKnot = first;
            QueueType item = first -> value;
            first = first -> next;
            if (first == NULL) {
                last = NULL;
            }
            delete tempKnot;
            return item;
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
        Knot* tempLast = first;
        cout << "Queue [ ";
        while (tempLast != NULL) {
            string point = ", ";
            if (tempLast -> next == NULL) {
                point = " ";
            }
            cout << tempLast -> value << point;
            tempLast = tempLast -> next;
        }
        cout << "]\n";
    }