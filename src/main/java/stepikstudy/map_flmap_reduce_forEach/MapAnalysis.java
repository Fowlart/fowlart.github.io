package stepikstudy.map_flmap_reduce_forEach;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SomeObject {
    final int id = count++;
    static int count = 0;
    public String name = "Some name";

    @Override
    public String toString() {
        return "SomeObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + "\n";
    }
}

class DTOSomeObject {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DTOSomeObject{" +
                "id='" + id + '\'' +
                '}';
    }
}

public class MapAnalysis {
    public static void main(String[] strings) {
        //Actually, map is often used to get a properties from stream of objects.
        // Reason to use the Streams is lay on possibility that streams do not make influence on their source
        List<SomeObject> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) list.add(new SomeObject());
        String rez = list.stream().map((x) -> x.toString()).collect(Collectors.joining());
        System.out.println(rez);
        //Another common use is to create DTO (it's not always good) for passing an object
        // between modules or returning response from a service.
        List<DTOSomeObject> dtoSomeObjects = list.stream().map((x) -> {
            DTOSomeObject mapped = new DTOSomeObject();
            mapped.setId(String.valueOf(x.id));
            return mapped;
        }).collect(Collectors.toList());
        System.out.println(dtoSomeObjects);
    }
}