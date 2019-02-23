package stepikstudy.practical_tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


class Accaunt {

    static public <T> List<T> filter(List<T> elems, Predicate<T> predicate) {
        return elems.stream().filter(predicate).collect(Collectors.toList());
    }

    String number;
    Long balance;
    boolean isLocked;
    List<Accaunt> list;

    public String getNumber() {
        return number;
    }

    public Long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public List<Accaunt> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Accaunt{" + "balance=" + balance + '}';
    }
}
public class Task29 {
    public static void main(String[] strings) {
        List<Accaunt> accounts = new ArrayList<>();
        Accaunt accaunt1 = new Accaunt();
        accaunt1.balance = (long) 1;
        Accaunt accaunt2 = new Accaunt();
        accaunt2.balance = (long) 2;
        Accaunt accaunt3 = new Accaunt();
        accaunt3.balance = (long) 3;
        Accaunt accaunt4 = new Accaunt();
        accaunt4.balance = (long) 100000001;

        accounts.add(accaunt1);
        accounts.add(accaunt2);
        accounts.add(accaunt3);
        accounts.add(accaunt4);

       System.out.println(  Accaunt.filter(accounts, (acc) ->
       {
           return ( (acc.getBalance() >= (long) 100000000) & (!acc.isLocked()) );
       }) );

       System.out.println( Accaunt.filter(accounts, (acc) ->acc.getBalance()>(long)0) );
    }
}