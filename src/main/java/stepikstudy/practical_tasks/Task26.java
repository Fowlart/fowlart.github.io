package stepikstudy.practical_tasks;

import java.util.function.Function;

public class Task26 {
    public static void main(String[] args){
        int a=1;
        int b=2;
        int c=3;
        Function<Integer, Double> function = (x)-> {return (double)(a*x*x)+(b*x)+c;};
        System.out.println(function.apply(2));
    }
}
