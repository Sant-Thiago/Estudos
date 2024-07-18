#include <iostream>
#include "stack.h"

using namespace std;

int main() {
    stack pilha1;
    StackType item;
    int opcao;
    
    cout << "Programa gerador de pilha:\n";

    do {
        cout << "Digite 0 para parar o programa!\n";
        cout << "Digite 1 para inserir um elemento!\n";
        cout << "Digite 2 para remover um elemento!\n";
        cout << "Digite 3 para imprimir a pilha!\n";
        cin >> opcao;

        if (opcao == 1 )
        {
            cout << "Digite o elemento a ser inserido:\n";
            cin >> item;
            pilha1.push(item);
        } 
        else if (opcao == 2) 
        {
            item = pilha1.pop();
            cout << "Elemento removido:: " << item << endl;
        } 
        else if (opcao == 3) 
        {
            pilha1.print();
        } 
    } while (opcao != 0);
}


// cout Fluxo de saída para enviar dados;
// cin Fluxo de entrada para receber dados;
// << Operador de inserção usado para enviar dados de um fluxo de saída;
// >> Operador de extração usado para receber dados de um fluxo de entrada;

// <iostream> Biblioteca padrão que fornece classes e objetos para entrada e saída de dados, por exemplo cin (fluxo de entrada padrão), cout (fluxo de saída padrão), cerr (fluxo de erro padrão)...

// using namespace std é uma 'diretiva' que permite o uso direto dos identificadores da biblioteca padrão 'std' sem precisar usar o prefixo std::