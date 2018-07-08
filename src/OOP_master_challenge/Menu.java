package OOP_master_challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */

class Menu {

    private static volatile Menu menu;
    private HashMap<Integer, Item> itemMap;

    private Menu() {
        if (menu != null) {
            throw new RuntimeException("Singleton class, use getInstance().");
        }
    }

    static Menu getMenu(File burgerFile, File additionFile) {
        if (menu == null) {
            synchronized (Menu.class) {
                if (menu == null) {
                    menu = new Menu();
                }
                LinkedList<Item> mainItemList = buildMainItemList(burgerFile, additionFile);
                HashMap<Integer, Item> itemIndex = new HashMap<>();
                for (int i = 0; i < mainItemList.size(); i++) {
                    itemIndex.put(i, mainItemList.get(i));
                }
                menu.setItemMap(itemIndex);
                return menu;
            }
        }
        return menu;
    }

    void show() {
        System.out.println("-============= || MENU ||=============-\n");

        System.out.println("BURGERS:\n--------");
        printItemsOfType(ItemType.BURGER);

        System.out.println("\nADDITIONS:\n----------");
        printItemsOfType(ItemType.ADDITION);
    }

    void printItemsOfType(ItemType itemType) {
        itemMap.entrySet().stream()
                .filter(i -> i.getValue().getItemType() == itemType)
                .forEach(Menu::printMenuLine);
    }  // ugly stream?

    private static void printMenuLine(Map.Entry<Integer, Item> entry) {
        int index = entry.getKey();
        String name = entry.getValue().getItemName();
        double price = entry.getValue().getItemPrice();
        String menuLine = index + ". " + name + " " + price + " zł.";
        System.out.println(menuLine);
    }

    private static LinkedList<Item> buildMainItemList(File burgerFile, File additionFile) {
        LinkedList<Item> mainItemList = new LinkedList<>();
        mainItemList.addAll(buildBurgerList(loadList(burgerFile)));
        mainItemList.addAll(buildAdditionList(loadList(additionFile)));
        return mainItemList;
    }

    private static LinkedList<Item> buildBurgerList(LinkedList<String[]> list) {
        LinkedList<Item> burgerList = new LinkedList<>();
        list.forEach(e -> {
            String name = String.valueOf(e[0]);
            Double basePrice = Double.valueOf(e[1]);
            BreadRollType breadRollType = BreadRollType.valueOf(e[2]);
            MeatType meatType = MeatType.valueOf(e[3]);
            Integer additionLimit = Integer.valueOf(e[4]);
            burgerList.add(new Burger(name, basePrice, breadRollType, meatType, additionLimit));
        });  // is this a proper way? No side effects?
        return burgerList;
    }

    private static LinkedList<Item> buildAdditionList(LinkedList<String[]> list) {
        LinkedList<Item> additionList = new LinkedList<>();
        list.forEach(e -> {
            String name = e[0];
            Double price = Double.valueOf(e[1]);
            additionList.add(new Addition(name, price));
        });
        return additionList;
    }

    private static LinkedList<String[]> loadList(File file) {
        LinkedList<String[]> list = new LinkedList<>();
        String separator = ",";
        String line; // skip header
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(separator);
                list.add(splitLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void setItemMap(HashMap<Integer, Item> itemMap) {
        this.itemMap = itemMap;
    }

    HashMap<Integer, Item> getItemMap() {
        return itemMap;
    }
}
