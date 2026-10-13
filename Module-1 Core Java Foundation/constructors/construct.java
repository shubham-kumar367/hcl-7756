//package constructors;
//
//public class construct {
//     construct(){
//        System.out.println("Hello, This is constructor");
//    }
//    void show() {
//        System.out.println("Hii");
//    }
//
//    public static void main(String[] args) {
//        construct obj = new construct();
//        obj.show();
//    }
//}

package constructors;

class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
}

public class construct extends Parent {

    construct() {
        super();
        System.out.println("Child constructor");
    }

    void show() {
        System.out.println("Hii");
    }

    public static void main(String[] args) {
        construct obj = new construct();
        obj.show();
    }
}


