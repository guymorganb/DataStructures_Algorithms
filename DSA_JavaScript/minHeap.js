class MinHeap {
    constructor() {
      this.heap = [];
    }
  
    // Get index of parent, left child, and right child
    getParentIndex(index) { return Math.floor((index - 1) / 2); }
    getLeftChildIndex(parentIndex) { return 2 * parentIndex + 1; }
    getRightChildIndex(parentIndex) { return 2 * parentIndex + 2; }
  
    // Helper method to swap nodes
    swap(indexOne, indexTwo) {
      [this.heap[indexOne], this.heap[indexTwo]] = [this.heap[indexTwo], this.heap[indexOne]];
    }
  
    // Insert method
    insert(value) {
      this.heap.push(value);
      this.heapifyUp();
    }
  
    // Heapify up for maintaining heap properties after insertion
    heapifyUp() {
      let currentIndex = this.heap.length - 1;
  
      while (currentIndex > 0) {
        let parentIndex = this.getParentIndex(currentIndex);
  
        if (this.heap[parentIndex] > this.heap[currentIndex]) {
          this.swap(currentIndex, parentIndex);
          currentIndex = parentIndex;
        } else {
          break;
        }
      }
    }
  
    // Extract the minimum element
    extractMin() {
      if (this.heap.length === 0) {
        return null;
      }
      const min = this.heap[0];
      this.heap[0] = this.heap.pop();
      this.heapifyDown();
      return min;
    }
  
    // Heapify down for maintaining heap properties after extraction
    heapifyDown() {
      let currentIndex = 0;
  
      while (this.getLeftChildIndex(currentIndex) < this.heap.length) {
        let smallerChildIndex = this.getLeftChildIndex(currentIndex);
        if (this.getRightChildIndex(currentIndex) < this.heap.length && this.heap[this.getRightChildIndex(currentIndex)] < this.heap[smallerChildIndex]) {
          smallerChildIndex = this.getRightChildIndex(currentIndex);
        }
  
        if (this.heap[currentIndex] > this.heap[smallerChildIndex]) {
          this.swap(currentIndex, smallerChildIndex);
        } else {
          break;
        }
  
        currentIndex = smallerChildIndex;
      }
    }
  }
  
const minHeap = new MinHeap();
minHeap.insert(20);
minHeap.insert(15);
minHeap.insert(30);
minHeap.insert(10);

console.log("Extracted Min:", minHeap.extractMin()); // 10
console.log("Extracted Min:", minHeap.extractMin()); // 15
