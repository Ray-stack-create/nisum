interface Greeting {
    default void sayHello() {
        System.out.println("Hello from interface");
    }
}

class Greeter implements Greeting {
    @Override
    public void sayHello() {
        Greeting.super.sayHello(); 
        System.out.println("Hello from Greeter class");
    }
}
