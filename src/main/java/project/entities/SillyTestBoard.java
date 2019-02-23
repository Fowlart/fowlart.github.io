package project.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class SillyTestBoard {
    public static List<Item> getTestData() {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            TestItem item = new TestItem();
            item.setName("Item number " + i);
            item.setPrice(BigDecimal.valueOf((double) i / 2 / 2.44));
            if (i % 2 == 0) {
                ItemProperty<String> elegance = new ItemProperty<String>() {
                    @Override
                    public String getPropertieName() {
                        return "elegance";
                    }
                    @Override
                    public String getPropertieValue() {
                        return "very elegant";
                    }
                    @Override
                    public String toString() {
                        return this.getPropertieName() + ": " + this.getPropertieValue();
                    }
                };
                item.addPropertyToList(elegance);
            }
            list.add(item);
        }
        return list;
    }
}