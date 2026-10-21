// Generic Class
//package Generics;
//
//class Demo<T>
//{
//    T data;
//
//    Demo(T data)
//    {
//        this.data = data;
//    }
//
//    void display()
//    {
//        System.out.println(data);
//    }
//}
//
//public class GenericsExample
//{
//    public static void main(String[] args)
//    {
//        Demo<Integer> d1 = new Demo<>(100);
//        Demo<String> d2 = new Demo<>("Hello Java");
//        Demo<Double> d3 = new Demo<>(10.5);
//
//        d1.display();
//        d2.display();
//        d3.display();
//    }
//}

// Generics Method

//package Generics;
//
//public class GenericsExample
//{
//    public <T> void display(T data)
//    {
//        System.out.println(data);
//    }
//
//    public static void main(String[] args)
//    {
//        GenericsExample obj = new GenericsExample();
//
//        obj.display(100);
//        obj.display("Hello Java");
//        obj.display(10.5);
//        obj.display('A');
//    }
//}

// Bounded Generic Class
//
//package Generics;
//
//class Demo<T extends Number>
//{
//    T data;
//
//    Demo(T data)
//    {
//        this.data = data;
//    }
//
//    void display()
//    {
//        System.out.println(data);
//    }
//}
//
//public class GenericsExample
//{
//    public static void main(String[] args)
//    {
//        Demo<Integer> d1 = new Demo<>(100);
//        Demo<Double> d2 = new Demo<>(10.5);
//
//        d1.display();
//        d2.display();
//    }
//}

// Bounded Generic Method

package Generics;

public class GenericsExample
{
    public <T extends Number> void display(T data)
    {
        System.out.println(data);
    }

    public static void main(String[] args)
    {
        GenericsExample obj = new GenericsExample();

        obj.display(100);
        obj.display(10.5);
        obj.display(25.5f);
    }
}



