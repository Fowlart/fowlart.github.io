package project.entities.item_implementations.goods;

import project.entities.ItemProperty;

public class GoodsName implements ItemProperty<String> {

    private String goodsname;

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    @Override
    public String getPropertieName() {
        return this.getClass().getName().toLowerCase();
    }

    @Override
    public String getPropertieValue() {
        return goodsname;
    }
}
