package Abstraction;

interface A1 {
    void show();

    static void display() {
        System.out.println("Static method");
    }
}

class B1 implements A1 {
    public void show() {
        System.out.println("Hello");
    }
}

public class Interface {
    public static void main(String[] args) {
        B1 b = new B1();
        b.show();
        A1.display();
    }
}