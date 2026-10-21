
package ExceptionHandling;

class InvalidAgeException extends Exception
{
    InvalidAgeException(String msg)
    {
        super(msg);
    }
}

public class CustomException
{
    public static void main(String[] args)
    {
        int age = 16;

        try
        {
            if(age < 18)
            {
                throw new InvalidAgeException("Under Age");
            }
            System.out.println("Eligible");
        }
        catch(InvalidAgeException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Bye");
        }
    }
}
