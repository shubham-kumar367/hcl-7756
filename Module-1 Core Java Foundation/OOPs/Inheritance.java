
package OOPs;
// Method Overriding
class Animal {
    void sound() {
        System.out.println("Animal");
    }
}

public class Inheritance extends Animal {

    void sound() {
        System.out.println("Dog");
    }

    public static void main(String[] args) {
        Inheritance obj = new Inheritance();
        Animal a = new Animal();

        obj.sound();
        a.sound();
    }
}
