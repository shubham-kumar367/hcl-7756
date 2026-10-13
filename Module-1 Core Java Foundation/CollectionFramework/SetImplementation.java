
package CollectionFramework;

//import java.util.HashSet;
//import java.util.Set;

import java.util.*;

public class SetImplementation
{
    public static void main(String[] args)
    {
         Set<Integer> set = new HashSet<>();
         //Set<Integer> set = new TreeSet<>();

        set.add(10);
        set.add(20);
        set.add(30);
        set.add(20);

        System.out.println(set);
        System.out.println(set.contains(10));

        set.remove(30);
        System.out.println(set);
    }
}
