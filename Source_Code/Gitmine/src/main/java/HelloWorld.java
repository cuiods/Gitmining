/**
 * Created by Harry on 2016/5/4.
 */
public class HelloWorld {
    public static void main(String [] args) {
        HelloWorld h = new HelloWorld();
        h.printHello();
    }

    public String printHello(){
        String hello = "Hello World!";
        System.out.println(hello);
        return hello;
    }
}
