package models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloJspRenderer {

    private List<String> entries = new LinkedList<String>();

    private String tablecode;

    public String getTablecode() {
        tablecode = getTableCode();
        return tablecode;
    }

    public void addItemEntry(String first_entity, String second_entity){
        entries.add("<tr><td>"+first_entity+"</td>");
        entries.add("<td>"+second_entity+"</td></tr>");
    }

    private String getTableCode(){
        return entries.stream().collect(Collectors.joining());
    }
}