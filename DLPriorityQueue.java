public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

    private DLinkedNode<T> front; // a reference to the first node of the doubly linked list
    private DLinkedNode<T> rear; // a reference to the last node of the doubly linked list
    private int count; // the number of data items in the priority queue

    // Method to Create an Empty Priority Queue
    public DLPriorityQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    // Method that Adds dataItem and its Priority to the Priority Queue
    public void add(T dataItem, double priority) {
        // Creating new Instance of the Doubly Linked Node and setting it equal to 'newNode'
        DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);

        // If the Priority Queue is Empty
        if (isEmpty()) {
            front = newNode;
            rear = newNode;

        } else if (priority < front.getPriority()) { // else if the priority is less than the priority in the front
            newNode.setNext(front); // set the next node as front
            front.setPrev(newNode); // set the previous node as newNode
            front = newNode;
        } else { // if none of the previous conditions meet
            // Doubly Linked Node called 'current' is equal to front
            DLinkedNode<T> current = front;
            // while the node does not equal null and the next nodes priority is less than or equal to 'priority'
            while (current.getNext() != null && current.getNext().getPriority() <= priority) {
                current = current.getNext(); // current is equal to the next node
            }

            if (current.getNext() == null) {// if the node is null
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;

            } else { // otherwise
                newNode.setNext(current.getNext());
                newNode.setPrev(current);
                current.getNext().setPrev(newNode);
                current.setNext(newNode);
            }
        }
        count++; // add one to count
    }

    // Method that changes the Priority of the Given Data Item to the New Value
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> current = front;
        // while the current node isn't null and the current and the current nodes data item is equal to the data item
        while (current != null && !current.getDataItem().equals(dataItem)) {
            current = current.getNext(); // get the next node
        }
        // if the current is null
        if (current == null) {
            throw new InvalidElementException("The data item " + dataItem + " was not found in the priority queue.");
        }
        // Set the Current's Priority to the newPriority
        current.setPriority(newPriority);
        // while the previous node isn't null and the current priority is less than the previous priority
        while (current.getPrev() != null && current.getPriority() < current.getPrev().getPriority()) {
            DLinkedNode<T> p = current.getPrev();
            DLinkedNode<T> next = current.getNext();
            DLinkedNode<T> previous = p.getPrev();

            // if the previous node is not null
            if (previous != null) {
                previous.setNext(current);
            } else {
                front = current;
            }
            current.setPrev(previous);
            current.setNext(p);
            p.setPrev(current);
            p.setNext(next);
            // if next is not null
            if (next != null) {
                next.setPrev(p); // set the previous from next to p
            } else {
                rear = p;
            }
        }
        // while the next node is not null and the current's priority is greater than the next's priority
        while (current.getNext() != null && current.getPriority() > current.getNext().getPriority()) {
            DLinkedNode<T> p = current.getPrev();
            DLinkedNode<T> next = current.getNext();
            DLinkedNode<T> nextNext = next.getNext();
            // if p is not null
            if (p != null) {
                p.setNext(next); // set the one after p to next
            } else {
                front = next;
            }
            next.setPrev(p);
            next.setNext(current);
            current.setPrev(next);
            current.setNext(nextNext);
            // if next next is not null
            if (nextNext != null) {
                nextNext.setPrev(current);// set the previous one from the next next to current
            } else {
                rear = current;
            }
        }
    }

    // Method to Remove and Return Data Item from the Smallest Priority to the Greatest Priority
    public T removeMin() throws EmptyPriorityQueueException {
        // if it is empty
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Cannot remove from an empty priority queue.");
        }
        T removed = front.getDataItem();
        if (front == rear) { // if only one node in the list
            front = rear = null;
        } else {
            front = front.getNext(); // set front equal to the next node
            front.setPrev(null); // set the previous node to null
        }
        count--; // subtract one from the count
        return removed; // return the removed nodes (which will start from lowest priority)
    }

    // Method to Return True or False Based on Whether the Priority Queue is Empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Method to Return the Size of the Priority Queue
    public int size() {
        return count;
    }

    // Method to String the Priority Queue
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DLinkedNode<T> current = front;
        while (current != null) {
            sb.append(current.getDataItem().toString());
            current = current.getNext();
        }
        return sb.toString();
    }

    // Method to Return Rear
    public DLinkedNode<T> getRear() {
        return rear;
    }





}

