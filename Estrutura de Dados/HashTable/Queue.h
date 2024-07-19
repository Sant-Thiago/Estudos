#ifndef QUEUE_H // guarda de inclusão
#define QUEUE_H

typedef int QueueType;

const int maxItens = 100;

class Queue {
    private:
    int first, last;
    int* structure;

    public:
    Queue();
    ~Queue();
    void enqueue(QueueType item);
    QueueType dequeue();
    bool isEmpty();
    bool isFull();
    void print();
    int getLast();
    int* getStructure();
};

#endif
