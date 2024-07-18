typedef int QueueType;

// Knot tem dois valores que são o objeto e o próximo objeto
struct Knot // basicamente tudo aqui é public
{
    QueueType value;
    Knot* next;
};


class queue {
    private:
    Knot* first;
    Knot* last;

    public:
    queue();
    ~queue();
    void enqueue(QueueType item);
    QueueType dequeue();
    bool isEmpty();
    bool isFull();
    void print();
};