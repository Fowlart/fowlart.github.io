package project.entities.item_implementations.goods;

import project.entities.Item;
import project.entities.ItemProperty;

public class GoodsItem extends Item {

    public GoodsItem(ItemProperty name, ItemProperty price){
        this.addPropertyToList(name);
        this.addPropertyToList(price);
    }
}