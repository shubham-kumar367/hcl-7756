//
//package CollectionFramework;
//
//import java.util.ArrayList;
//
//public class List {
//    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//
//        for (int i = 1; i <= 10; i++) {
//            list.add(i);
//        }
//
//        System.out.println("Original ArrayList: " + list);
//
//        list.set(2, 30);
//        list.set(5, 60);
//
//        System.out.println("Updated ArrayList: " + list);
//    }
//}

package CollectionFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class List {
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>();

        c.add(10);
        c.add(20);
        c.add(30);
        c.add(40);
        c.add(50);
        System.out.println("Collection: " + c);

        c.addAll(Arrays.asList(60, 70));
        System.out.println("After addAll: " + c);

        System.out.println("Size: " + c.size());
        System.out.println("Is empty: " + c.isEmpty());
        System.out.println("Contains 30: " + c.contains(30));
        System.out.println("Contains all: " + c.containsAll(Arrays.asList(10, 20)));

        c.remove(20);
        System.out.println("After remove: " + c);

        c.removeAll(Arrays.asList(40, 50));
        System.out.println("After removeAll: " + c);

        c.retainAll(Arrays.asList(10, 30, 60));
        System.out.println("After retainAll: " + c);

        c.addAll(Arrays.asList(70, 80, 90));
        c.removeIf(n -> n > 70);
        System.out.println("After removeIf: " + c);

        Object[] arr = c.toArray();
        System.out.println("Array: " + Arrays.toString(arr));

        Integer[] arr2 = c.toArray(new Integer[0]);
        System.out.println("Typed Array: " + Arrays.toString(arr2));

        System.out.print("Iterator: ");
        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        System.out.print("forEach: ");
        c.forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println("Stream count: " + c.stream().count());
        System.out.println("Parallel stream count: " + c.parallelStream().count());

        c.clear();
        System.out.println("After clear: " + c);
        System.out.println("Is empty: " + c.isEmpty());
    }
}

