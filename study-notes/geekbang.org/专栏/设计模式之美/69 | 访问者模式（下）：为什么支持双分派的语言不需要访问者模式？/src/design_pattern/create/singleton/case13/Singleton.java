package design_pattern.create.singleton.case13;

public class Singleton {
    private static Singleton instance = null;
    private final int paramA;
    private final int paramB;

    private Singleton(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public synchronized static Singleton getInstance(int paramA, int paramB) {
        if (instance == null) {
            instance = new Singleton(paramA, paramB);
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance(10, 50);
    }
}