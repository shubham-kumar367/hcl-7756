
package Practice;

public class Example {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.makeInstance();
        Singleton obj2 = Singleton.makeInstance();

        String msg = obj1.getMsg();

        System.out.println(msg);
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj1 == obj2);
    }
}
