package project.entities.item_implementations.goods;
import project.entities.ItemProperty;
import java.math.BigDecimal;
public class GoodsPrice implements ItemProperty<BigDecimal> {
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String getPropertieName() {
        return this.getClass().getName().toLowerCase();
    }

    @Override
    public BigDecimal getPropertieValue() {
        return price;
    }
}
