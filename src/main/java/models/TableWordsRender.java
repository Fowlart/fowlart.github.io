package models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TableWordsRender {

    private List<String> entries = new LinkedList<String>();

    private String header = "<table><tr><th>ENG</th><th>UKR</th><th>RATIO</th></tr>";
    private String tablecode;
    private String footer = "</table>";

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
        if (entries.isEmpty()) return "";
        return header+entries.stream().collect(Collectors.joining())+footer;
    }

    public void clearTable(){
        this.entries.clear();
    }
}
