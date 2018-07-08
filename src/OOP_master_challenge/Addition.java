package OOP_master_challenge;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
public class Addition implements Item {

    private String name;
    private double price;
    private ItemType itemType;

    Addition() {
        this.itemType = ItemType.ADDITION;
    }

    Addition(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public String getItemName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public double getItemPrice() {
        return price;
    }

    public Addition getItem() {
        return this;
    }

}
