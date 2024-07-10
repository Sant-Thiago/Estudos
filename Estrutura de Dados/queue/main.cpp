#include <iostream>
#include "queue.h"

using namespace std;

int main() {
    queue fila1;
    int opcao;
    QueueType item;
    cout << "Programa gerador de filas:\n";

    do {
        cout << "Digite 0 para para o programa!\n";
        cout << "Digite 1 para inserir um elemento!\n";
        cout << "Digite 2 para remover um elemento!\n";
        cout << "Digite 3 para imprimir a fila!\n";
        cin >> opcao;

        switch (opcao)
        {
        case 1:
            cout << "Digite o elemento a ser inserido na fila!\n";
            cin >> item;
            fila1.enqueue(item);
            break;
        case 2:
            item = fila1.dequeue();
            cout << "O elemento removido e: " << item << endl; 
            break;
        case 3:
            fila1.print();
            break;
        }

    } while (opcao != 0);
    
}