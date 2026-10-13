
package CollectionFramework;

import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();

        v.add(10);
        v.add(20);
        v.add(30);
        v.add(40);
        System.out.println("Vector: " + v);

        v.addElement(50);
        v.add(2, 25);
        System.out.println("After adding: " + v);

        v.set(1, 15);
        System.out.println("After updating: " + v);

        System.out.println("Element at index 2: " + v.get(2));
        System.out.println("First element: " + v.firstElement());
        System.out.println("Last element: " + v.lastElement());
        System.out.println("Size: " + v.size());
        System.out.println("Capacity: " + v.capacity());
        System.out.println("Contains 30: " + v.contains(30));
        System.out.println("Index of 40: " + v.indexOf(40));

        v.remove(2);
        v.removeElement(Integer.valueOf(50));
        System.out.println("After removing: " + v);

        System.out.println("Is empty: " + v.isEmpty());

        System.out.println("Elements:");
        for (int x : v) {
            System.out.println(x);
        }

        v.clear();
        System.out.println("After clear: " + v);
        System.out.println("Is empty: " + v.isEmpty());
    }
}
