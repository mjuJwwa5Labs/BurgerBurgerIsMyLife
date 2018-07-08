package OOP_master_challenge;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
public class Burger implements Item {

    private String name;
    private double basePrice;
    private BreadRollType breadRollType;
    private MeatType meatType;
    private int additionLimit;
    private ItemType itemType;
    private ArrayList<Addition> additionList;
    private double discount;

    public Burger() {
        this.itemType = ItemType.BURGER;
        this.additionList = new ArrayList<>();
        this.discount = 0;
    }

    Burger(String name, double basePrice, BreadRollType breadRollType, MeatType meatType, int additionLimit) {
        this();
        this.name = name;
        this.basePrice = basePrice;
        this.breadRollType = breadRollType;
        this.meatType = meatType;
        this.additionLimit = additionLimit;
    }

    public String getName() {
        return name;
    }

    public int getAdditionLimit() {
        return additionLimit;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Burger getItem() {
        return this;
    }

    public String getItemName() {
        return name;
    }

    public double getItemPrice() {
        return basePrice;
    }

    private void setDiscount(double discount) {
        this.discount = discount;
    }

    private double getTotalPrice() {
        double totalPrice = basePrice;
        for (Addition addition : additionList) {
            totalPrice+=addition.getItemPrice();
        }
        return (totalPrice-=(totalPrice*discount));
    }

    void showTotalPrice() {
        System.out.println();
        System.out.println(String.format(getItemName() + ", price %s zł", getItemPrice()));
        System.out.println("Additions: ");
        for (Addition addition : additionList) {
            System.out.println(String.format(addition.getItemName()+ " %s zł",addition.getItemPrice()));
        }
        System.out.println("-------------");
        System.out.println("DISCOUNT: " + ((int) (discount*100)) + "%");
        System.out.println(String.format("TOTAL: %s zł\n",getTotalPrice()));
    }

    void applyAddition(Addition addition) {
        this.additionList.add(addition);
    }

    Burger applyAdditions(LinkedList<Addition> chosenAdditions) {
        this.additionList.addAll(chosenAdditions);
        return this;
    }

    void applyDiscount(double discount) {
        if (discount >= 0 && discount <= 100) {
            setDiscount(discount);
        }
    }
}
