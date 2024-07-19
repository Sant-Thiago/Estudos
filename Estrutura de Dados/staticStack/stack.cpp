#include <iostream>
#include "stack.h"

using namespace std;

    stack::stack() 
    {
        tamanho = 0;
        estrutura = new StackType[maxItens];
    }

    stack::~stack() 
    {
        delete [] estrutura;
    }
    
    bool stack::isFull() 
    {
        return (tamanho == maxItens);
    }
    
    bool stack::isEmpty() 
    {
        return (tamanho == 0);
    }
    
    void stack::push(StackType item) 
    {
        if (isFull()) {
            cout << "A pilha está cheia!\n";
            cout << "Não é possível inserir este elemento!\n";
        } else {
            estrutura[tamanho] = item;
            tamanho++;
        }
    }
    
    StackType stack::pop() 
    {
        if (isEmpty()) {
            cout << "A pilha está vazia!\n";
            cout << "Não tem elemento para ser removido!\n";
            return 0;
        } else {
            tamanho--;
            return estrutura[tamanho];
        }
    }
    
    void stack::print() 
    {
        cout << "Pilha: [ ";
        for (int i = 0; i < tamanho; i++)
        {
            cout << estrutura[i] << " ";
        }

        cout << "]\n";
    }
    
    int stack::length() 
    {
        return tamanho;
    }
