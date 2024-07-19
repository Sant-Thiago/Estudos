typedef int QueueType;

const int maxItens = 100;

class queue {
    private:
    int first, last;
    int* structure;

    public:
    queue();
    ~queue();
    void enqueue(QueueType item);
    QueueType dequeue();
    bool isEmpty();
    bool isFull();
    void print();
};