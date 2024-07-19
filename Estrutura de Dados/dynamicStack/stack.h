typedef int StackType;

struct Knot // basicamente tudo aqui é public
{
    StackType value;
    Knot* next;
};

class stack {
    private:
    Knot* topKnot;

    public:
    stack();
    ~stack();
    bool isEmpty();
    bool isFull();
    void push(StackType item);
    StackType pop();
    void print();
};
