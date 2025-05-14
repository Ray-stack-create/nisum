package SPRINT_1_DAY_2;
import java.util.ArrayDeque;

public class SimpleStack_11 {
    private ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(int value) {
        stack.push(value);
    }

    public int pop() {
        return stack.pop(); 
    }

    public int peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SimpleStack_11 s = new SimpleStack_11();
        s.push(10);
        s.push(20);
        System.out.println("Top element: " + s.peek());
        System.out.println("Popped: " + s.pop());
        System.out.println("Is empty: " + s.isEmpty());
    }
}
