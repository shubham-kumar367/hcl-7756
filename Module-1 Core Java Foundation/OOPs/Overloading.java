package OOPs;

public class Overloading {
    String name;
    int rollno;
    Overloading(String name, int rollno){
        this.name = name;
        this.rollno = rollno;
    }
    public static void main(String[] args) {
        Overloading obj = new Overloading("Shubham",21);
        System.out.println(obj.name);
        System.out.println(obj.rollno);
    }
}
