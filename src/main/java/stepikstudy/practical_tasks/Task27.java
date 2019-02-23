package stepikstudy.practical_tasks;

import javax.swing.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task27 {
    public static void main(String[] strings){
        String sometext =" some text ";
        String prefix=" [";
        String suffix="] ";
        Stream<String> stream = Stream.<String>builder().add(sometext).build();
        System.out.println(stream.map( (s)->prefix.concat(s.trim()).concat(suffix) ).collect(Collectors.joining()));
        /**true task 27**/
        Thread thread = new Thread(()->{System.out.println("Hello from thread");});
        thread.start();
        Comparator<String> comparator = (s1,s2)->{return s1.compareTo(s2);};
        JButton button = new JButton("Click me");
        JFrame jFrame = new JFrame();
        jFrame.add(button);
        jFrame.setVisible(true);
        button.addActionListener((e)->{System.out.println("Button clicked!");});
    }
}
