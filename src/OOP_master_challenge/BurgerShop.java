package OOP_master_challenge;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
class BurgerShop {

    private static volatile BurgerShop burgerShop;
    private BurgerMaker burgerMaker;
    private Menu menu;

    private BurgerShop(Menu menu) {
        if (burgerShop != null){
            throw new RuntimeException("Singleton class, use getInstance().");
        }
        this.burgerMaker = BurgerMaker.getInstance(menu);
        this.menu = menu;
    }

    static BurgerShop getInstance(Menu menu) {
        if (burgerShop == null) {
            synchronized (BurgerShop.class) {
                if (burgerShop == null) {
                    burgerShop = new BurgerShop(menu);
                }
            }
        }
        return burgerShop;
    }

    void showMenu() {
        menu.show();
    }

    Burger orderBurger() {
        return burgerMaker.makeBurger();
    }

    void showTotalPrice(Burger burger) {
        burger.showTotalPrice();
    }

}

