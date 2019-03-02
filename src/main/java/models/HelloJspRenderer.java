package models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloJspRenderer {
    private static List<String> entries = new LinkedList<String>();
    private String code;

    public String getCode() {
        code = getTableCode();
        return code;
    }

    public void addItemEntry(String good_name, String good_price){
        entries.add("<tr><td>"+good_name+"</td>");
        entries.add("<td>"+good_price+"</td></tr>");
    }

    private String getTableCode(){
        return entries.stream().collect(Collectors.joining());
    }
}