package project.entities;

public class GoodsItem extends Item {
    @Override
    public String toString() {
        return this.getName() + " " + this.getPrice();
    }
}