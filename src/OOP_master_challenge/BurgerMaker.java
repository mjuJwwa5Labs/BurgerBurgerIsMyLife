package OOP_master_challenge;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */

class BurgerMaker {

    private static volatile BurgerMaker burgerMaker;
    private Scanner sc;
    private Menu menu;

    private BurgerMaker(Menu menu) {
        if (burgerMaker != null){
            throw new RuntimeException("Singleton class, use getInstance().");
        }
        this.menu = menu;
    }

    static BurgerMaker getInstance(Menu menu) {
        if (burgerMaker == null) {
            synchronized (BurgerMaker.class) {
                if (burgerMaker == null) {
                    burgerMaker = new BurgerMaker(menu);
                }
            }
        }
        return burgerMaker;
    }

    Burger makeBurger() {
        sc = new Scanner(System.in);
        Burger burger = applySpecialRules(applyChosenAdditions(chooseBurger()));  // o, wyszedł mi Dekorator :D
        sc.close();
        return burger;
    }

    private Burger chooseBurger() {
        return (Burger) retrieveAskedItem(new Burger());
    }

    // apply Additions chosen by customer
    private Burger applyChosenAdditions(Burger burger) {
        return burger.applyAdditions(chooseAdditions(burger));
    }

    private LinkedList<Addition> chooseAdditions(Burger burger) {
        int additionLimit = burger.getAdditionLimit();
        LinkedList<Addition> chosenAdditions = new LinkedList<>();

        if (additionLimit > 0) {
            System.out.println("\nYou can choose up to " + additionLimit + " additions.");

            System.out.println();
            Addition addition;

            while (chosenAdditions.size() < additionLimit) {
                addition = (Addition) retrieveAskedItem(new Addition()); // new Addition() provides method with a reference for what to look for
                chosenAdditions.add(addition);
            }
        }
        return chosenAdditions;

        // TODO make this a for loop and extract the "asking" to print # of ask...
    }

    // apply additional business rules to burger
    // here we add chips & drink to Deluxe Hamburger
    // can be potentially abstracted if names of included additions are provided externally
    private Burger applySpecialRules(Burger burger) {
        if (burger.getItem().getItemName().equals("Deluxe Hamburger")) {
            burger.applyAddition((Addition) this.retrieveItem("chips"));  // WARNING: no null check! TODO.
            burger.applyAddition((Addition) this.retrieveItem("drink"));
            burger.applyDiscount(0.15);
        }
        return burger;
    }

    // Works for both Burger and Addition
    private Item retrieveAskedItem(Item item) {
        ItemType desiredItemType = item.getItemType();
        int choice;
        boolean invalidChoice = false;
        Item retrievedItem = null;

        System.out.print("\n>> Choose " + item.getClass().getSimpleName() + ": \n-----------------\n");
        menu.printItemsOfType(desiredItemType);
        System.out.print("\nChoice (enter number): ");

        do {

            if (invalidChoice) {
                System.out.print("Invalid choice, try again: ");  // this prints multiple times if I keep choosing wrong :/
            }

            invalidChoice = false;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 0) {
                    retrievedItem = retrieveItem(choice);
                }
            }  else {
                sc.nextLine();
                invalidChoice = true;
            }

            if (retrievedItem == null) {
                invalidChoice = true;
            }

            if (retrievedItem != null && !retrievedItem.getItemType().equals(desiredItemType)) {
                invalidChoice = true;
            }

        } while (invalidChoice);
        return retrievedItem;
    }

    // retrieve Item from menu (overloaded method)
    public Item retrieveItem(Integer key) {
        Optional<Item> optional = Optional.of(menu.getItemMap().get(key));
        return optional.orElse(null);
    }

    public Item retrieveItem(String name) {
        Optional<Item> optional = menu.getItemMap().values().stream()
                .filter(e -> e.getItemName().equals(name))
                .distinct()
                .findFirst();
        return optional.orElse(null);
    }
}