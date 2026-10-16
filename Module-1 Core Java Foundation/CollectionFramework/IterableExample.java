
package CollectionFramework;

import java.util.*;

public class IterableExample
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Iterable<Integer> itr = list;

        for(int x : itr)
        {
            System.out.println(x);
        }
    }
}
