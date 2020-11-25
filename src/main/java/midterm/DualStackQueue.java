package midterm;

import java.util.Stack;

public class DualStackQueue {

    private final Stack<Integer> s1 = new Stack<>();
    private final Stack<Integer> s2 = new Stack<>();

    public int take() {
        if (s1.isEmpty())
            throw new IllegalStateException("Queue is Empty.");

        return s1.pop();
    }

    public void pushBack(int e) {
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        s1.push(e);

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public int head() {
        return s1.peek();
    }

    public static void main(String[] args) {
        DualStackQueue q = new DualStackQueue();

        q.pushBack(1);
        q.pushBack(2);
        q.pushBack(3);
        q.pushBack(4);

        System.out.println(q.head());
    }
}
