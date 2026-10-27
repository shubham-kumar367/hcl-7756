//package Stream;
//
//import java.util.*;
//
//public class StreamImplementation
//{
//    public static void main(String[] args)
//    {
//        List<Integer> l = new ArrayList<>();
//        l.add(1);
//        l.add(2);
//        l.add(3);
//        l.add(4);
//        l.add(5);

//        l.stream()
//                .filter((num) -> num % 2 == 0)
//                .forEach((num) -> System.out.println(num)); // or forEach(System.out::println) (ClassName::MethodName)--> Method Reference
//        List<Integer> result = l.stream()
//                .filter(num -> num % 2 == 0)
//                .toList();
//
//        System.out.println(result);
//    }
//}


//package Stream;
//
//import java.util.*;
//
//public class StreamImplementation
//{
//    public static void main(String[] args)
//    {
//        List<String> l = new ArrayList<>();
//        l.add("Shubham");
//        l.add("Rohit");
//        l.add("Abhishek");
//        l.add("Neha");
//        l.add("Sachin");
//        l.add("Shubham");
//
//        l.stream()
//                .filter(s -> s.toLowerCase().startsWith("s"))
//                .distinct()
//                .map(String::toUpperCase)
//                .sorted()
//                .forEach(System.out::println);
//    }
//}
//
//package Stream;
//
//import java.util.*;
//
//public class StreamImplementation
//{
//    public static void main(String[] args)
//    {
//        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
//
//        int sum = l.stream()
//                .reduce(0, (a, b) -> a + b);
//
//        System.out.println(sum);
//    }
//}

package Stream;

import java.util.*;

class Employee
{
    String name;
    String gender;

    Employee(String name, String gender)
    {
        this.name = name;
        this.gender = gender;
    }
}

public class StreamImplementation
{
    public static void main(String[] args)
    {
        List<Employee> l = Arrays.asList(
                new Employee("Rahul", "Male"),
                new Employee("Priya", "Female"),
                new Employee("Aman", "Male"),
                new Employee("Neha", "Female")
        );

        l.stream()
                .map(e -> (e.gender.equalsIgnoreCase("Male") ? "Mr. " : "Mrs. ") + e.name)
                .forEach(System.out::println);
    }
}
