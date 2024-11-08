public class StackList<T> {
    private Node<T> top;

    public StackList() {
        top = null;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }
}