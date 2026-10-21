package Practice;
public class Singleton {
    static final Singleton obj = new Singleton();

    //    rule1: make constructor private
    private Singleton(){

    }
    // return same object every time
    public static Singleton makeInstance(){
        return obj;
    }
    public String getMsg(){
        return "Heyy...I am using Java";
    }
}