#include <iostream>
#include "stack.h"
#include <cstddef> //null

using namespace std;

    stack::stack()
    {
        topKnot = NULL;
    }

    stack::~stack()
    {
        Knot* tempKnot;
        while (topKnot != NULL) {
            tempKnot = topKnot;
            topKnot = topKnot -> next;
            delete tempKnot;
        }
    }

    bool stack::isEmpty()
    {
        return (topKnot == NULL);
    }

    bool stack::isFull()
    {
        Knot* newKnot;
        try {
            newKnot = new Knot;
            delete newKnot;
            return false;
        } catch(bad_alloc e) { // bad_alloc = sem espaço para alocar
            return true;
        }
    }

    void stack::push(StackType item)
    {
        if (isFull()) {
            cout << "A Pilha esta cheia!\n";
            cout << "Nao foi possivel inserir este elemento!";
        } else {
            Knot* newKnot = new Knot;
            newKnot -> value = item;
            newKnot -> next = topKnot;
            topKnot = newKnot;
        }
    }

    StackType stack::pop()
    {
        if (isEmpty()) {
            cout << "A Pilhae esta vazia!\n";
            cout << "Nao e possivel remover nenhum elemento";
            return 0;
        } else {
            Knot* tempKnot;
            tempKnot = topKnot;
            StackType item = topKnot -> value;
            topKnot = topKnot -> next;
            delete tempKnot;
            return item;
        }
    }

    void stack::print()
    {
        Knot* tempKnot = topKnot;
        cout << "Pilha: [ ";
        while (tempKnot != NULL) {
            cout << tempKnot -> value << " ";
            tempKnot = tempKnot -> next;
        }
        cout << "]\n";
    }
