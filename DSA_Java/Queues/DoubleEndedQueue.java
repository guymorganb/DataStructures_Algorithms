package Queues;

public class DoubleEndedQueue {
	int[] dq;
    int front;
    int rear;
    int size;

    public DoubleEndedQueue(int n) {
        dq = new int[n];
        front = -1;
        rear = 0;
        size = n;
    }

    void insertFront(int key) {
        if (isFull()) {
            System.out.println("-1");
            return;
        }
        
        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (front == 0)
            front = size - 1 ;
        else
            front = front - 1;

        dq[front] = key ;
    }

    void insertRear(int key) {
        if (isFull()) {
            System.out.println("-1");
            return;
        }

        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (rear == size-1)
            rear = 0;
        else
            rear = rear+1;

        dq[rear] = key;
    }

    void deleteFront() {
        if (isEmpty()) {
            System.out.println("-1");
            return;
        }

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size -1)
            front = 0;
        else
            front = front+1;
    }

    void deleteRear() {
        if (isEmpty()) {
            System.out.println("-1");
            return;
        }

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0)
            rear = size - 1;
        else
            rear = rear-1;
    }

    int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return dq[front];
    }

    int getRear() {
        if(isEmpty() || rear < 0) {
            return -1;
        }

        return dq[rear];
    }

    boolean isFull() {
        return ((front == 0 && rear == size - 1) || front == rear + 1);
    }

    boolean isEmpty() {
        return (front == -1);
    }
}
