interface MathUtils {
    static int square(int x) {
        return x * x;
    }
}

public class TestStatic14 {
    public static void main(String[] args) {
        int result = MathUtils.square(5);
        System.out.println("Square: " + result); 
    }
}
