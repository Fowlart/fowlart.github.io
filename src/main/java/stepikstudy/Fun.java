package stepikstudy;

@FunctionalInterface
public interface Fun<T, R> {

    /**Головна функція функціонального інтерфейсу*/
    R apply(T t);

    static void doNothingStatic() {
    }

    default void doNothingByDefault() {
    }

    public static void main(String[] args) {

        /**Переопреділення головної функції функціонального інтерфейсу, використовуючи lambda*/
        Fun<Double, Double> fun = (x) -> x * 2 + 1;
        // 3d - означає автоконвертацію int в double
        System.out.println(fun.apply(3d));
    }
}