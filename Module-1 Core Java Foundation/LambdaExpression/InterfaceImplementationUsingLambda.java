
package LambdaExpression;

interface Demo
{
    void show();
}

public class InterfaceImplementationUsingLambda
{
    public static void main(String[] args)
    {
        Demo d = () -> System.out.println("Hello Java");

        d.show();
    }
}
