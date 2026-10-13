package LambdaExpression;

@java.lang.FunctionalInterface
interface Calculator {
    public int calculate(int a, int b);
}


public class Calculation {
    public static void main(String[] args) {
        Calculator sum = (a,b) -> a + b;
        System.out.println(sum.calculate(5, 6));

        Calculator diff = (a,b) -> a - b;
        System.out.println(diff.calculate(5, 6));

        Calculator mul = (a,b) -> a * b;
        System.out.println(mul.calculate(5, 6));

        Calculator div = (a,b) -> a / b;
        System.out.println(div.calculate(5, 6));

        Calculator mod = (a,b) -> a % b;
        System.out.println(mod.calculate(5, 6));
    }
}
