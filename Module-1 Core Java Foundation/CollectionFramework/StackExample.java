
package CollectionFramework;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);

        System.out.println("Stack: " + s);
        System.out.println("Peek: " + s.peek());

        System.out.println("Pop: " + s.pop());
        System.out.println("After Pop: " + s);

        System.out.println("Peek: " + s.peek());
    }
}
