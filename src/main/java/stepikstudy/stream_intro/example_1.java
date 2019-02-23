package stepikstudy.stream_intro;

import java.util.Arrays;

/**
 Example 1. Let's assume we have a list of words and we'd like to construct a long word from the short words (length < 4).
 The long word should be in upper case, each source word must be in the result only once.
 **/

public class example_1 {
    public static void main(String[] args) {

        String wo = "Ma ma ma ma pa pa pa pa za za za za";

        String longWord = Arrays.stream(wo.split(" "))
                .filter(w -> w.length() < 4)
                .map(String::toUpperCase)
                .distinct()
                .reduce("", String::concat);

        System.out.println(longWord);

    }
}
