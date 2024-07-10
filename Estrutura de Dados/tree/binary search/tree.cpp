#include <iostream>
#include "tree.h"

using namespace std;

    tree::tree()
    {
        size = 0;
        structure = new TreeType[maxItens];
    }

    tree::~tree()
    {
        delete [] structure;
    }

    int tree::search(TreeType item) 
    {
        if (isEmpty) 
        {
            cout << "Não é possível procurar em uma árvore vazia!\n";
        } 
        else 
        {
            sort();

            int start = 0;
            int end = size;
            int middle = divide(start, end);
            while (structure[middle] != item) 
            {
                if (structure[middle] > item) 
                {
                    end = middle;
                } 
                else if (structure[middle] < item) 
                {
                    start = middle;
                } 
                else if (structure[middle] == item) 
                {
                    return structure[item];
                } 
                else 
                {
                    cout << "Não exite o valor:: " << item << " na árvore\n";
                    return 0;
                }
                middle = divide(start, end);
            }
            
        }
    }

    int tree::divide(int start, int end) {
        int size = start - end;
        return start + (size % 2 == 0) ? size / 2 : (size + 1) / 2;
    }

    void tree::sort() 
    {

        int oldNum = structure[0];
        // bool trigger = false;
        for (int i = 0; i < size; i++)
        {
            // Lógica onde volta o index para 0 toda vez que hover troca de posições
            if (oldNum > structure[i]) {
                structure[i-1] = structure[i];
                structure[i] = oldNum;
                i = -1;
                oldNum = structure[0];
                continue;
            }
            oldNum = structure[i];

            /*
            * Lógica onde pega o maior número e percore com ele até o que o número seguinte dele seja maior, 
            *
            {if (oldNum > structure[i]) {
                structure[i-1] = structure[i];
                structure[i] = oldNum;
                trigger = true;
            }
            oldNum = structure[i];

            if (++i == size && trigger) {
                i = -1;
                oldNum = structure[0];
                continue;
            }}
            */
        }
        // [ 4, 5, 62, 34, 6, 7, 3, 2, 46, 2]
        // [ 4, 5, 62, 34, 76, 7, 43, 4, 46, 2]
    }

    bool tree::isEmpty()
    {
        return (size == 0);
    }

    int tree::length()
    {
        return size;
    }
