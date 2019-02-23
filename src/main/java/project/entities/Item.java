package project.entities;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class Item {
    private BigDecimal price;
    private String name;
    private List<ItemProperty> list = new LinkedList<>();

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemProperty> getList() {
        if (list!=null) return list;
        list = new LinkedList<>();
        return list;
    }

    public void addPropertyToList(ItemProperty prop) {
        this.list.add(prop);
    }
}
