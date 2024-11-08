public class QueueList<T> {
    private Node<T> front;
    private Node<T> rear;

    public QueueList() {
        front = null;
        rear = null;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (rear != null) {
            rear.setNext(newNode);
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public T dequeue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public T peek() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }
}