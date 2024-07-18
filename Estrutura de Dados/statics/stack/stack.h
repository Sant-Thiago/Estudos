typedef int StackType; // isso serve para quando eu quiser alterar o tipo da classe, por exemplo int significa que a classe stack é do tipo int
const int maxItens = 100;

class stack {
    private:
    int tamanho;
    int* estrutura; // * serve para definir um novo vetor


    public:
    stack(); // contrutora
    ~stack(); // destrutora
    bool isFull(); // verifica se está cheio
    bool isEmpty(); // verifica se está vazia
    void push(StackType item); // insere
    StackType pop(); // remove
    void print(); // imprimir
    int length(); // tamanho
};
