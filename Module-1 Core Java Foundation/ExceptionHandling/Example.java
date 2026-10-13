//package ExceptionHandling;
//
//public class Example
//{
//    public static void main(String[] args)
//    {
//        try
//        {
//            int a = 10;
//            int b = 0;
//            System.out.println(a / b);
//        }
//        catch(ArithmeticException e)
//        {
//            System.out.println("Cannot divide by zero");
//        }
//        finally
//        {
//            System.out.println("Finally block executed");
//        }
//
//        System.out.println("Program continues");
//    }
//}

//package ExceptionHandling;
//
//public class Example
//{
//    static int test()
//    {
//        try
//        {
//            System.out.println("Inside try");
//            return 10;
//        }
//        catch(Exception e)
//        {
//            System.out.println("Inside catch");
//            return 20;
//        }
//        finally
//        {
//            System.out.println("Inside finally");
//        }
//    }
//
//    public static void main(String[] args)
//    {
//        System.out.println("Return value: " + test());
//    }
//}

//package ExceptionHandling;
//
//public class Example
//{
//    public static void main(String[] args)
//    {
//        try
//        {
//            System.out.println("Inside try");
//            System.exit(0);
//        }
//        catch(Exception e)
//        {
//            System.out.println("Inside catch");
//        }
//        finally
//        {
//            System.out.println("Inside finally");
//        }
//
//        System.out.println("Program ended");
//    }
//}

package ExceptionHandling;

import java.sql.SQLOutput;

public class Example
{
    public static void main(String[] args)
    {
        try
        {
            int arr[] = {10, 20, 30};
            System.out.println(arr[5]);
            int a = 10 / 0;
        }
        catch(ArithmeticException e)
        {
            System.out.println("Arithmetic Exception");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array Index Exception");
            return;
        }
        catch(Exception e)
        {
            System.out.println("General Exception");
        }
        finally {
            System.out.println("In finally");
        }

        System.out.println("Program continues");
    }
}