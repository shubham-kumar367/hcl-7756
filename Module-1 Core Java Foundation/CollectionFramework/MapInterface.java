package CollectionFramework;


import java.util.*;

public class MapInterface {
    public static void main(String[] args) {
        //Map<Integer, String> mpp = new HashMap<>();
        //Map<Integer, String> mpp = new LinkedHashMap<>();
        //Map<Integer, String> mpp = new TreeMap<>();
        Map<Integer, String> mpp = new Hashtable<>();
        mpp.put(1,"Shubham");
        mpp.put(3,"Sachin");
        mpp.put(2,"Rohit");
        mpp.put(4,"Rohit");
        System.out.println(mpp);
        System.out.println(mpp.containsKey(1));
        System.out.println(mpp.containsValue("Shubham"));
        System.out.println(mpp.get(1));
        System.out.println(mpp.isEmpty());
        mpp.forEach((k,v)-> System.out.println(k + ":" + v));

    }
}
