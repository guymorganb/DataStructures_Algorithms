package Queues;
import java.util.Queue;
import java.util.LinkedList;

// implement a stack data structure using 2 queues
public class stackUsingTwoQueues {
	//Define the data members
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public stackUsingTwoQueues() {
        //Implement the Constructor
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /*----------------- Public Functions of Stack -----------------*/

    public int getSize() { 
        //Implement the getSize() function
        return queue1.size();
    }

    public boolean isEmpty() {
        //Implement the isEmpty() function
         return queue1.isEmpty();
    }

    public void push(int element) {
        //Implement the push(element) function
         queue1.add(element);
    }

    public int pop() {
        //Implement the pop() function
         if(queue1.isEmpty()){
            return -1;
        }
        while(queue1.size() > 1){
            queue2.add(queue1.poll());
        }
        int popVal = queue1.poll();

        // Swap names of queues
        Queue<Integer> temp = queue1; 
        queue1 = queue2;
        queue2 = temp;

        return popVal;
    }

    public int top() {
        //Implement the top() function
        if(queue1.isEmpty()){
            return -1;
        }
        while(queue1.size() > 1){
            queue2.add(queue1.poll());
        }
        int topVal = queue1.peek();
        queue2.add(queue1.poll());

        // Swap names of queues
        Queue<Integer> temp = queue1; 
        queue1 = queue2;
        queue2 = temp;

        return topVal;
    }
}
