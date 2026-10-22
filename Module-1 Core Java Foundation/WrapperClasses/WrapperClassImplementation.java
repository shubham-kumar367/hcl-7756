
package WrapperClasses;

public class WrapperClassImplementation
{
    public static void main(String[] args)
    {
        int a = 10;
        Integer b = a; // Autoboxing (primitive to wrapper)

        System.out.println("Primitive value: " + a);
        System.out.println("Wrapper object: " + b);

        Integer x = 20;
        int y = x; // Unboxing (wrapper to primitive)

        System.out.println("Wrapper object: " + x);
        System.out.println("Primitive value: " + y);

        String s = "100";
        int n = Integer.parseInt(s);

        System.out.println("String to int: " + n);

        Integer obj = Integer.valueOf(50);
        System.out.println("Integer object: " + obj);
    }
}
