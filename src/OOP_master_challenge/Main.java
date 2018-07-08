package OOP_master_challenge;

import java.io.File;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 *
 * Focused so hard on avoiding inheritance and serializing objects,
 * that I ended up with a botched, patchworky BurgerMaker class for business logic :/
 * Redesign needed.
 *
 */
public class Main {
    public static void main(String[] args) {

        File burgerFile = new FileLoader().loadFile("OOP_master_challenge/res/burgerfile.csv");
        File additionFile = new FileLoader().loadFile("OOP_master_challenge/res/additionfile.csv");

        Menu menu = Menu.getMenu(burgerFile, additionFile);
        BurgerShop burgerShop = BurgerShop.getInstance(menu);

        burgerShop.showMenu();
        Burger burger = burgerShop.orderBurger();
        burgerShop.showTotalPrice(burger);


    }
}

// TODO (probably never): defensive for input + error handling + better encapsulation


// BLOCK all .apply.. methods in burger
//
//
//        menu.show(); // BLOCK? HOW? I'd like for it to be only accessible form burgerShop.
//                menu.getItemMap(); // BLOCK?
//
//
//                // Is this too much access? Or ist that OK?
//                burger.applyAddition(new Addition());  // BLOCK?
//                burger.applyAdditions(new LinkedList<>());  // BLOCK?
//        burger.applyDiscount(50); // BLOCK?
