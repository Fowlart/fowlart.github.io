package models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TableWordsRender {

    private List<String> entries = new LinkedList<String>();

    private String tablecode;

    public String getTablecode() {
        tablecode = getTableCode();
        return tablecode;
    }

    public void addItemEntry(String ENG, String UKR, String RATIO){
        entries.add("<tr><td>"+ENG+"</td>");
        entries.add("<td>"+UKR+"</td>");
        entries.add("<td>"+RATIO+"</td></tr>");
    }

    private String getTableCode(){
        return entries.stream().collect(Collectors.joining());
    }

    public void clearTable(){
        this.entries.clear();
    }
}
