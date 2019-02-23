package stepikstudy.stream_intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WhatBetter {
    public static Random rundom = new Random(47);

    /**Використовуємо класичний підхід, для додавання певних знчень */
    public static List<Integer> classicPacking(List<Integer> values) {
        List<Integer> filteredValues = new ArrayList<>();
        for (Integer val : values) { if (val <= 10) {  filteredValues.add(val); } }
        return filteredValues;
    }

    /**Використовуємо лямбду. Stream API*/
    public static List<Integer> functionalPacking(List<Integer> values) {
        List<Integer> filteredValues = values.stream().filter(val -> val <= 10).collect(Collectors.toList());
        return filteredValues;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) list.add(WhatBetter.rundom.nextInt(100));
        System.out.println(list);
        System.out.println(functionalPacking(list));
        System.out.println(classicPacking(list));
    }


}
