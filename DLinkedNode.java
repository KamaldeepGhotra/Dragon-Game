public class DLinkedNode <T> {

    private T dataItem; // A reference to the data item stored in this node
    private double priority; // This is the priority of the data item stored in this node.
    private DLinkedNode<T> next; // A reference to the next node in the linked list.
    private DLinkedNode<T> prev; // A reference to the previous node in the linked list.

    // Method that will Create a Node and Store the Data Item and Priority
    public DLinkedNode (T data, double prio) {
        this.dataItem = data;
        this.priority = prio;
        this.next = null;
        this.prev = null;
    }

    // Method to Create an Empty Node with No Data Items and No Priorities
    public DLinkedNode() {
        this.dataItem = null;
        this.priority = 0;
        this.next = null;
        this.prev = null;
    }

    // Method to Return the Priority
    public double getPriority() {
        return priority;
    }

    // Method to Return the Data Item
    public T getDataItem() {
        return dataItem;
    }

    // Method to Return the Next Data Item
    public DLinkedNode<T> getNext() {
        return next;
    }

    // Method to Return the Previous Data Item
    public DLinkedNode<T> getPrev() {
        return prev;
    }

    // Method to Change th Data Item
    public void setDataItem(T data) {
        this.dataItem = data;
    }

    // Method to Change the Next Data Item
    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    // Method to Change the Previous Data Item
    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }

    // Method to Change the Priority
    public void setPriority(double prio) {
        this.priority = prio;
    }

}
