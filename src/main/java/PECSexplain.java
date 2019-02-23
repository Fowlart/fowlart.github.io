import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SuperPerson {
    public String superPerson = "superPerson";

    public String getSuperPerson() {
        return superPerson;
    }
}

class Person extends SuperPerson {
    String person = "Person";
}

class Artur extends Person {
    String Artur = "Artur";
}

class Olena extends Person {
    String Olena = "Olena";
}


class Melania extends Person {
    String Melania = "Melania";
}

public class PECSexplain {

    public static void main(String[] args) {
        /**Створити producer можливо*/
        List<? extends Person> producer = Arrays.asList(new Artur(), new Olena(), new Melania(), new Person());
        /**Запхати щось - неможливо*/
        //producer.add(new Person());
        //producer.add(new Artur());
        /**Дістати можемо тільки Person*/
        System.out.println(producer.get(0));
        /**В consumer ми можемо напхати, похідні від Person*/
        List<? super Person> consumer = new ArrayList<>();
        consumer.add(new Artur());
        consumer.add(new Melania());
        consumer.add(new Olena());
        consumer.add(new Person());
        /**Але дістати не можемо НІФІГА крім OBJECT*/
        System.out.println(consumer.get(0).getClass().getName());

    }
}
