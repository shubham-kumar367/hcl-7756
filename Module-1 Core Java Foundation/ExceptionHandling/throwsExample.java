package ExceptionHandling;

import java.io.*;

public class throwsExample
{
    static void readFile() throws IOException
    {
        FileReader f = new FileReader("test.txt");
    }

    public static void main(String[] args) throws IOException
    {
        readFile();
        System.out.println("Program ended");
    }
}