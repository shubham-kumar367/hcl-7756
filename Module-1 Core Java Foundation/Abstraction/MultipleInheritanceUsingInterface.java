package Abstraction;

interface A2 {
    void show();
}

interface B2 {
    void display();
}

class C implements A2, B2 {
    public void show() {
        System.out.println("Interface A2");
    }

    public void display() {
        System.out.println("Interface B2");
    }
}

public class MultipleInheritanceUsingInterface {
    public static void main(String[] args) {
        C c = new C();
        c.show();
        c.display();
    }
}