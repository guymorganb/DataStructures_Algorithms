class PriorityQueueNode {
    constructor(value, priority) {
        this.value = value;
        this.priority = priority;
    }
}
class PriorityQueue {
    constructor() {
        this.heap = [];
    }
    // Swap elements in the heap
    swap(index1, index2) {
        [this.heap[index1], this.heap[index2]] = [this.heap[index2], this.heap[index1]];
    }
    // Get index of parent, left child, and right child
    getParentIndex(index) { return Math.floor((index - 1) / 2); }
    getLeftChildIndex(parentIndex) { return 2 * parentIndex + 1; }
    getRightChildIndex(parentIndex) { return 2 * parentIndex + 2; }

    // Insert a new element into the queue
    enqueue(value, priority) {
        const node = new PriorityQueueNode(value, priority);
        this.heap.push(node);
        this.heapifyUp();
    }
    // Move the last element up until it's in the correct position
    heapifyUp() {
        let index = this.heap.length - 1;

        while (index > 0) {
            let parentIndex = this.getParentIndex(index);

            if (this.heap[parentIndex].priority > this.heap[index].priority) {
                this.swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }
    // Remove the element with the highest priority
    dequeue() {
        if (this.heap.length === 0) return null;
        if (this.heap.length === 1) return this.heap.pop().value;

        const top = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.heapifyDown();
        return top.value;
    }
    // Move the first element down until it's in the correct position
    heapifyDown() {
        let index = 0;
        while (this.getLeftChildIndex(index) < this.heap.length) {
            let smallerChildIndex = this.getLeftChildIndex(index);
            if (this.getRightChildIndex(index) < this.heap.length && this.heap[this.getRightChildIndex(index)].priority < this.heap[smallerChildIndex].priority) {
                smallerChildIndex = this.getRightChildIndex(index);
            }

            if (this.heap[index].priority > this.heap[smallerChildIndex].priority) {
                this.swap(index, smallerChildIndex);
            } else {
                break;
            }

            index = smallerChildIndex;
        }
    }
    // Check if the queue is empty
    isEmpty() {
        return this.heap.length === 0;
    }
}
