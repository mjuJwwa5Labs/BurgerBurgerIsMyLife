package OOP_master_challenge;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
public interface Item<T> {

    T getItem();

    String getItemName();

    ItemType getItemType();

    double getItemPrice();
}
