
package CollectionFramework;
import java.util.*;
//import java.util.Queue;
//import java.util.LinkedList;

public class QueueImplementation
{
    public static void main(String[] args)
    {
//        Queue<Integer> q = new LinkedList<>();
//
//        q.add(10);
//        q.add(20);
//        q.add(30); // Not added return exception
//        q.offer(40); // Not added return null
//
//        System.out.println(q);
//        System.out.println(q.peek()); // return top element
//
//        q.remove();
//        q.poll();
//        System.out.println(q);

//        Queue<Integer> q = new ArrayDeque<>();
          PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(10);
        q.add(20);
        q.add(30); // Not added return exception
        q.offer(40); // Not added return null

        System.out.println(q);
        System.out.println(q.peek()); // return top element

        q.remove();
        q.poll();
        System.out.println(q);
    }
}
