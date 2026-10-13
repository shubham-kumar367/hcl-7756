
package CollectionFramework;

import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println("LinkedList: " + list);
        list.add(3, 60);
        System.out.println("LinkedList: " + list);
        list.addFirst(5);
        list.addLast(50);
        System.out.println("After adding: " + list);

        list.set(2, 25);
        System.out.println("After updating: " + list);

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Element at index 2: " + list.get(2));

        list.removeFirst();
        list.removeLast();
        list.remove(Integer.valueOf(25));
        System.out.println("After removing: " + list);

        System.out.println("Size: " + list.size());
        System.out.println("Contains 20: " + list.contains(20));

        System.out.println("Peek: " + list.peek());
        System.out.println("Poll: " + list.poll());
        System.out.println("After poll: " + list);

        System.out.println("Is empty: " + list.isEmpty());

        list.clear();
        System.out.println("After clear: " + list);
    }
}
