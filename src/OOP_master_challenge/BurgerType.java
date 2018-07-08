package OOP_master_challenge;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
public enum BurgerType {

    // this class creates duplication :/
    HAMBURGER(4), HEALTHY_BURGER(6), DELUXE_HAMBURGER(0), VEGGIE_BURGER(4), FISH_BURGER(4);

    private final int additionLimit;

    BurgerType(int additionLimit) {
        this.additionLimit = additionLimit;
    }

    public int getAdditionLimit() {
        return additionLimit;
    }
}
