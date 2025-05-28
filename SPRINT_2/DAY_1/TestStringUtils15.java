interface StringUtils {
    static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
public class TestStringUtils15 {
    public static void main(String[] args) {
        System.out.println(StringUtils.isNullOrEmpty("  "));  
        System.out.println(StringUtils.capitalize("hello"));  
    }
}
