package ShieldClass;

public sealed class ShieldClassExample permits ChildClass {
    public static void main(String[] args) {
        ShieldClassExample obj = new ChildClass("Active");

        if (obj instanceof ChildClass child) {
            System.out.println("Status: " + child.status);
        }
    }
}

final class ChildClass extends ShieldClassExample {
    public String status;

    public ChildClass(String status) {
        this.status = status;
    }
}
