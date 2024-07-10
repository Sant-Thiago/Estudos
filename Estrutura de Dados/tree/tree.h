typedef int TreeType;

const int maxItens = 100;

class tree {
    private:
    int size;
    int* structure;

    public:
    tree();
    ~tree();
    void sort();
    int divide(int start, int end);
    // retorna o index
    int search(TreeType item);
    int length();
    bool isEmpty();
};
