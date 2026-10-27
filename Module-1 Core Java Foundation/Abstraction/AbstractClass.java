package Abstraction;

abstract class A {
    abstract void fun1();
    abstract int fun2(int a, int b);
}

class B extends A {
    void fun1() {
        System.out.println("Hello");
    }

    int fun2(int a, int b) {
        return a + b;
    }
}

public class AbstractClass {
    public static void main(String[] args) {
        B b = new B();
        b.fun1();
        System.out.println(b.fun2(10, 20));
    }
}