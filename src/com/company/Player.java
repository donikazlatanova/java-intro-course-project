package com.company;

/**
 * Клас с атрибути за всеки играч.
 */
public class Player {
    String id;
    double money;
    int number;
    int indexPosition;
    int turnNow;

    //квадратчета "Капан"
    int ownTrapOne;
    int ownTrapTwo;
    int ownTrapThree;
    int ownTrapFour;
    int ownTrapFive;
    int ownTrapSix;
    int ownTrapSeven;

    //опции капани за всяко квадратче "Капан"
    int wishedTrapForTrap1;
    int wishedTrapForTrap2;
    int wishedTrapForTrap3;
    int wishedTrapForTrap4;
    int wishedTrapForTrap5;
    int wishedTrapForTrap6;
    int wishedTrapForTrap7;

    //наказания от капани
    int punishmentTwo;
    int punishmentFive;
    int punishmentFour;
    int noTrapsAnymore; // наказание 3


    int hasTrap;
    int putTrap;
    int hasSteal;

    //зли планове
    int hasFirstEvilPlan;
    int hasSecondEvilPlan;
    int hasThirdEvilPlan;

    public Player(String id, double money, int number, int indexPosition, int turnNow, int ownTrapOne, int ownTrapTwo, int ownTrapThree, int ownTrapFour, int ownTrapFive, int ownTrapSix, int ownTrapSeven, int wishedTrapForTrap1, int wishedTrapForTrap2, int wishedTrapForTrap3, int wishedTrapForTrap4, int wishedTrapForTrap5, int wishedTrapForTrap6, int wishedTrapForTrap7, int punishmentTwo, int punishmentFive, int punishmentFour, int noTrapsAnymore, int hasTrap, int putTrap, int hasSteal, int hasFirstEvilPlan, int hasSecondEvilPlan, int hasThirdEvilPlan) {
        this.id = id;
        this.money = money;
        this.number = number;
        this.indexPosition = indexPosition;
        this.turnNow = turnNow;
        this.ownTrapOne = ownTrapOne;
        this.ownTrapTwo = ownTrapTwo;
        this.ownTrapThree = ownTrapThree;
        this.ownTrapFour = ownTrapFour;
        this.ownTrapFive = ownTrapFive;
        this.ownTrapSix = ownTrapSix;
        this.ownTrapSeven = ownTrapSeven;
        this.wishedTrapForTrap1 = wishedTrapForTrap1;
        this.wishedTrapForTrap2 = wishedTrapForTrap2;
        this.wishedTrapForTrap3 = wishedTrapForTrap3;
        this.wishedTrapForTrap4 = wishedTrapForTrap4;
        this.wishedTrapForTrap5 = wishedTrapForTrap5;
        this.wishedTrapForTrap6 = wishedTrapForTrap6;
        this.wishedTrapForTrap7 = wishedTrapForTrap7;
        this.punishmentTwo = punishmentTwo;
        this.punishmentFive = punishmentFive;
        this.punishmentFour = punishmentFour;
        this.noTrapsAnymore = noTrapsAnymore;
        this.hasTrap = hasTrap;
        this.putTrap = putTrap;
        this.hasSteal = hasSteal;
        this.hasFirstEvilPlan = hasFirstEvilPlan;
        this.hasSecondEvilPlan = hasSecondEvilPlan;
        this.hasThirdEvilPlan = hasThirdEvilPlan;
    }

    public int getPunishmentFour() {
        return punishmentFour;
    }

    public void setPunishmentFour(int punishmentFour) {
        this.punishmentFour = punishmentFour;
    }

    public int getPunishmentFive() {
        return punishmentFive;
    }

    public void setPunishmentFive(int punishmentFive) {
        this.punishmentFive = punishmentFive;
    }

    public int getWishedTrapForTrap7() {
        return wishedTrapForTrap7;
    }

    public void setWishedTrapForTrap7(int wishedTrapForTrap7) {
        this.wishedTrapForTrap7 = wishedTrapForTrap7;
    }

    public int getWishedTrapForTrap6() {
        return wishedTrapForTrap6;
    }

    public void setWishedTrapForTrap6(int wishedTrapForTrap6) {
        this.wishedTrapForTrap6 = wishedTrapForTrap6;
    }


    public int getNumber() {
        return number;
    }

    public int getOwnTrapOne() {
        return ownTrapOne;
    }

    public void setOwnTrapOne(int ownTrapOne) {
        this.ownTrapOne = ownTrapOne;
    }

    public int getOwnTrapTwo() {
        return ownTrapTwo;
    }

    public void setOwnTrapTwo(int ownTrapTwo) {
        this.ownTrapTwo = ownTrapTwo;
    }

    public int getOwnTrapThree() {
        return ownTrapThree;
    }

    public void setOwnTrapThree(int ownTrapThree) {
        this.ownTrapThree = ownTrapThree;
    }

    public int getOwnTrapFour() {
        return ownTrapFour;
    }

    public void setOwnTrapFour(int ownTrapFour) {
        this.ownTrapFour = ownTrapFour;
    }

    public int getOwnTrapFive() {
        return ownTrapFive;
    }

    public void setOwnTrapFive(int ownTrapFive) {
        this.ownTrapFive = ownTrapFive;
    }

    public int getOwnTrapSix() {
        return ownTrapSix;
    }

    public void setOwnTrapSix(int ownTrapSix) {
        this.ownTrapSix = ownTrapSix;
    }

    public int getOwnTrapSeven() {
        return ownTrapSeven;
    }

    public void setOwnTrapSeven(int ownTrapSeven) {
        this.ownTrapSeven = ownTrapSeven;
    }

    public int getNoTrapsAnymore() {
        return noTrapsAnymore;
    }

    public void setNoTrapsAnymore(int noTrapsAnymore) {
        this.noTrapsAnymore = noTrapsAnymore;
    }

    public int getPunishmentTwo() {
        return punishmentTwo;
    }

    public void setPunishmentTwo(int punishmentTwo) {
        this.punishmentTwo = punishmentTwo;
    }

    public int getWishedTrapForTrap1() {
        return wishedTrapForTrap1;
    }

    public void setWishedTrapForTrap1(int wishedTrapForTrap1) {
        this.wishedTrapForTrap1 = wishedTrapForTrap1;
    }

    public int getWishedTrapForTrap2() {
        return wishedTrapForTrap2;
    }

    public void setWishedTrapForTrap2(int wishedTrapForTrap2) {
        this.wishedTrapForTrap2 = wishedTrapForTrap2;
    }

    public int getWishedTrapForTrap3() {
        return wishedTrapForTrap3;
    }

    public void setWishedTrapForTrap3(int wishedTrapForTrap3) {
        this.wishedTrapForTrap3 = wishedTrapForTrap3;
    }

    public int getWishedTrapForTrap4() {
        return wishedTrapForTrap4;
    }

    public void setWishedTrapForTrap4(int wishedTrapForTrap4) {
        this.wishedTrapForTrap4 = wishedTrapForTrap4;
    }

    public int getWishedTrapForTrap5() {
        return wishedTrapForTrap5;
    }

    public void setWishedTrapForTrap5(int wishedTrapForTrap5) {
        this.wishedTrapForTrap5 = wishedTrapForTrap5;
    }

    public void setTurnNow(int turnNow) {
        this.turnNow = turnNow;
    }

    public int getHasFirstEvilPlan() {
        return hasFirstEvilPlan;
    }

    public void setHasFirstEvilPlan(int hasFirstEvilPlan) {
        this.hasFirstEvilPlan = hasFirstEvilPlan;
    }

    public int getHasSecondEvilPlan() {
        return hasSecondEvilPlan;
    }

    public void setHasSecondEvilPlan(int hasSecondEvilPlan) {
        this.hasSecondEvilPlan = hasSecondEvilPlan;
    }

    public int getHasThirdEvilPlan() {
        return hasThirdEvilPlan;
    }

    public void setHasThirdEvilPlan(int hasThirdEvilPlan) {
        this.hasThirdEvilPlan = hasThirdEvilPlan;
    }

    public int getHasSteal() {
        return hasSteal;
    }

    public void setHasSteal(int hasSteal) {
        this.hasSteal = hasSteal;
    }

    public int getPutTrap() {
        return putTrap;
    }

    public void setPutTrap(int putTrap) {
        this.putTrap = putTrap;
    }

    public int getHasTrap() {
        return hasTrap;
    }

    public void setHasTrap(int hasTrap) {
        this.hasTrap = hasTrap;
    }


    public int getIndexPosition() {
        return indexPosition;
    }

    public void setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
    }

    public Player(String id, int number) {
        this.number = number;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
