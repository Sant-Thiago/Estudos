#include <iostream>
#include "HashTable.h"

using namespace std;

int main() {
    HashTable hash1(10);
    int opcao;
    HashTableType item;
    cout << "Tabela espalhadora:\n";

    do {
        cout << "Digite 0 para para o programa!\n";
        cout << "Digite 1 para inserir um elemento!\n";
        cout << "Digite 2 para encontrar um elemento!\n";
        cout << "Digite 3 para imprimir a fila!\n";
        cin >> opcao;

        switch (opcao)
        {
        case 1:
            cout << "Digite o elemento a ser inserido na fila!\n";
            cin >> item;
            hash1.insert(item);
            break;
        case 2:
            cout << "Digite o item deseja procurar:\n";
            cin >> item;
            item = hash1.find(item);
            cout << "O item encontrado foi: " << item << endl; 
            break;
        case 3:
            hash1.print();
            break;
        }

    } while (opcao != 0);
    
}