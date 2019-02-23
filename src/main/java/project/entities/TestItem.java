package project.entities;
public class TestItem extends Item {
    @Override
    public String toString() {
        return this.getName() + ": " + this.getPrice() + this.getList();
    }
}