package com.company;

import java.io.*;
import java.util.*;

/**
 * Клас, в който ще се реализира игра, подобна на Монополи
 *
 * @author Donika Zlatanova
 */
public class Monopoly {
    public static Scanner input = new Scanner(System.in);

    public static String START = "|     S    |";
    public static String TRAP1 = "|   TRAP1  |";//7 бр
    public static String TRAP2 = "|   TRAP2  |";//7 бр
    public static String TRAP3 = "|   TRAP3  |";//7 бр
    public static String TRAP4 = "|   TRAP4  |";//7 бр
    public static String TRAP5 = "|   TRAP5  |";//7 бр
    public static String TRAP6 = "|   TRAP6  |";//7 бр
    public static String TRAP7 = "|   TRAP7  |";//7 бр
    public static String INVEST1 = "|  Invest  |";//3 бр
    public static String INVEST2 = "|  Invest  |";//3 бр
    public static String INVEST3 = "|  Invest  |";//3 бр
    public static String PARTY_HARD1 = "|Party hard|";//3 бр
    public static String PARTY_HARD2 = "|Party hard|";//3 бр
    public static String PARTY_HARD3 = "|Party hard|";//3 бр
    public static String Chance1 = "|  Chance  |";//3 бр
    public static String Chance2 = "|  Chance  |";//3 бр
    public static String Chance3 = "|  Chance  |";//3 бр
    public static String Steal1 = "|   Steal  |";//3 бр
    public static String Steal2 = "|   Steal  |";//3 бр
    public static String Steal3 = "|   Steal  |";//3 бр

    public static List<Integer> ListForCountTaxRevisionPlayerOne = new ArrayList<>();
    public static List<Integer> ListForCountTaxRevisionPlayerTwo = new ArrayList<>();

    public static List<Double> ListForSuccessInvestmentPlayerOne = new ArrayList<>();
    public static List<Double> ListForSuccessInvestmentPlayerTwo = new ArrayList<>();

    public static String[] companyArray = new String[6];

    public static int partyHardMoney = 0;
    public static String partyHardAnnouncement = null;
    public static List<Integer> CountLoops = new ArrayList<>();

    public static int playerStartGameNumber = 0;

    public static void main(String[] args) {

        String[] board = new String[19];
        board[0] = TRAP7;
        board[1] = Steal1;
        board[2] = Steal3;
        board[3] = Steal2;
        board[4] = Chance1;
        board[5] = Chance2;
        board[6] = Chance3;
        board[7] = PARTY_HARD1;
        board[8] = PARTY_HARD2;
        board[9] = PARTY_HARD3;
        board[10] = INVEST1;
        board[11] = INVEST2;
        board[12] = INVEST3;
        board[13] = TRAP1;
        board[14] = TRAP2;
        board[15] = TRAP3;
        board[16] = TRAP4;
        board[17] = TRAP5;
        board[18] = TRAP6;


        Player playerOne = new Player("FirstPlayer", 1000, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player playerTwo = new Player("SecondPlayerBot", 1000, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        //СТАРТ НА ИГРАТА
        playerStartGameNumber = playerStartNumber();
        System.out.println("За да се определи кой играч стартира пръв, играта хвърля 2-стенен зар....И резултатът е.." + playerStartGameNumber + "!");

        while (true) {
            gameOn(playerOne, playerTwo, board);
        }
    }

    /**
     * Метод, който във фор цикъл вика метод, свързан с основните действия на играчите.
     * Цикълът фор съответства на цикъла на играта.
     *
     * @param board     масив за дъската
     * @param playerOne играч
     * @param playerTwo опонент
     * @param index     начален индекс
     */
    public static void playerActionLoops(String[] board, Player playerOne, Player playerTwo, int index) {
        loop:
        for (int i = index; i < board.length; i++) {
            playerActionInDetails(i, board, playerOne, playerTwo);

        }
    }

    /**
     * Метод, който връща 1 или 0: 1 ако сме в първи цикъл за да наместим впоследствие правилно
     * позицията на играча, тъй като при започване позицията СТАРТ е извън масива за дъската
     *
     * @param i номер на цикъла
     * @return 1 или 0
     */
    public static int seeDoWeNeedToSetPlayerPositionSubstractByOne(int i) {
        if (i == 0) {
            return 1; //ако индексът е равен на 0 значи сме в първия цикъл на играта,
            //в такъв случай си запазваме една променлива със стойност 1 за да може
            //малко по-надолу когато "нагласяме" позицията на фигурката на играча да извадим
            //1 тъй като в началото стои квадратче старт, което обаче не е част от масива
            //на дъската

        } else {
            return 0;
        }
    }

    /**
     * Детайлна последователност на действията на играч и на самата игра.
     *
     * @param i         номер цикъл
     * @param board     масив за дъската
     * @param playerOne текущ играч
     * @param playerTwo играч
     * @return
     */
    public static String playerActionInDetails(int i, String[] board, Player playerOne, Player playerTwo) {
        CountLoops.add(1);
        int x = seeDoWeNeedToSetPlayerPositionSubstractByOne(i);
        int resultFromRollingTheDice2 = resultFromRollingTheDice(2);
        System.out.println((i + 1) + "-и ход на " + playerOne.getId() + " Играта хвърля 2-стенен зар...Резулатът е " + resultFromRollingTheDice2);
        playerOne.setIndexPosition(playerOne.getIndexPosition() + resultFromRollingTheDice2 - x);//Изваждаме 1 защото реално
        //фигурката ни е на квадратче СТАРТ, което не е част от масива, а се намира точно преди него.
        if (playerOne.getIndexPosition() >= board.length) {
            ifRunIntoTrapTwo(playerOne);////////////
            checkExecutePunishmentsAndNullCharacteristics(playerOne);
            System.out.println("Приключихте този цикъл. Вашите пари са: " + playerOne.getMoney());
            moneyCheckToSeeWhoStarts(playerOne, playerTwo);
            if (CountLoops.size() == 1) {
                playerActionInDetails(i, board, playerTwo, playerOne);
            } else {
                moneyAndWinnerCheckInTheEndOfLoop(playerOne, playerTwo);
                moneyCheckToSeeWhoStarts(playerOne, playerTwo);
                CountLoops.clear();
                return "Край за Вас.";
            }
        } else {
            System.out.println("Попаднахте на квадратче " + board[playerOne.getIndexPosition()]);
            actionForEachSquare(board, playerOne.getIndexPosition(), playerOne, playerTwo);
            if (CountLoops.size() == 1) {
                playerActionInDetails(i, board, playerTwo, playerOne);
            } else {
                CountLoops.clear();
                return "";
            }
        }
        return "";
    }

    /**
     * Метод, който на края на всеки цикъл занулява всички характеристики и листове, които
     * трябва да са занулени за следващия цикъл(тоест за важат само за текущ) и проверява
     * дали играчът е минавал през данъчна ревизия за да му вземе от печалбата.
     *
     * @param playerOne текущ играч
     */
    public static void checkExecutePunishmentsAndNullCharacteristics(Player playerOne) {
        playerOne.setPunishmentTwo(0);
        if (playerOne.getId().equals("FirstPlayer")) {
            ListForSuccessInvestmentPlayerOne.clear();
        } else {
            ListForSuccessInvestmentPlayerTwo.clear();
        }
        playerOne.setNoTrapsAnymore(0);
        System.out.println("Вашите пари преди потенциална данъчна ревизия(ако сте попаднали): " + playerOne.getMoney());
        if (playerOne.getNumber() == 1) {
            playerOne.setMoney(playerOne.getMoney() - ListForCountTaxRevisionPlayerOne.size() * (playerOne.getMoney() * 0.1));
            ListForCountTaxRevisionPlayerOne.clear();
        } else {
            playerOne.setMoney(playerOne.getMoney() - ListForCountTaxRevisionPlayerTwo.size() * (playerOne.getMoney() * 0.1));
            ListForCountTaxRevisionPlayerTwo.clear();
        }
    }

    /**
     * Метод, в който последователно се изпълняват действия, свързани с квадратче Стийл
     *
     * @param player   текущ играч
     * @param player2  опонент
     * @param evilPlan номер зъл план
     */
    public static void stealSquare(Player player, Player player2, int evilPlan) {
        if (player2.getHasSteal() == 0 && player.getHasSteal() == 0) {
            if (player.getPunishmentFour() == 1) {
                System.out.println("Не може да реализирате зъл план, тъй като попаднахте преди на капан 4.");
                player.setPunishmentFour(0);
            } else {
                switch (evilPlan) {
                    case 1:
                        player.setHasFirstEvilPlan(1);
                        getInfoFromFile("resource/conquering_the_world", player);
                        player.setHasSteal(1);
                        break;
                    case 2:
                        player.setHasSecondEvilPlan(1);
                        getInfoFromFile("resource/hostages", player);
                        player.setHasSteal(1);
                        break;
                    case 3:
                        player.setHasThirdEvilPlan(1);
                        getInfoFromFile("resource/the_big_bank_robbery", player);
                        player.setHasSteal(1);
                        break;
                }

            }
        }
    }

    /**
     * Метод, който от вече създаден масив с информация всеки елемент съответно всяка фирма за инвестиране,
     * взима тази информация за фирмите във вид на елементи на масив и прави масив копие.
     * После на масивът копие се разбъркват елементите и се извеждат първите три елемента(фирми)
     * в менюто в конзолата за играча.
     *
     * @param player    текущ играч
     * @param copyArray масив копие
     */
    public static void investActions(Player player, String[] copyArray) {
        int counter = 1;
        loop:
        for (int k = 0; k < counter; k++) {
            if (player.getMoney() > 50) {
                shuffleElements(copyArray);
                System.out.println("1 ) " + copyArray[0]);
                System.out.println("2 ) " + copyArray[1]);
                System.out.println("3 ) " + copyArray[2]);
                System.out.println("0 ) " + "Не ми се инвестира (повече)");
                System.out.println("Изберете опция: ");
                int companyForInvest = 0;
                if (player.getId().equals("SecondPlayerBot")) {
                    companyForInvest = randomGenerate(0, 3);///??? bounda
                } else {
                    companyForInvest = input.nextInt();
                }
                if (companyForInvest == 1) {
                    actionForInvest(copyArray, 0, player);
                }
                if (companyForInvest == 2) {
                    actionForInvest(copyArray, 1, player);
                }
                if (companyForInvest == 3) {
                    actionForInvest(copyArray, 2, player);
                }
                if (player.getMoney() >= 50 && companyForInvest != 0) {
                    System.out.println("Можеш да инвестираш пак. ");
                    counter++;
                }
                if (companyForInvest == 0) {
                    System.out.println("Край на инвестициите за този ход. ");
                }
            } else {
                System.out.println("Нямате достатъчно пари за инвестиция. ");
                break loop;
            }
        }
    }

    public static void actionForInvest(String[] copyArray, int indexElement, Player player) {
        for (int i = 0; i < companyArray.length; i++) {
            if (copyArray[indexElement].equals(companyArray[i])) {
                seeWhichFirmAndExecute(player, companyArray, i);
            }
        }
    }

    public static void getInfoFromFile(String pathname, Player player) {
        File fileReference = new File(pathname);
        try {
            FileReader fileReferenceReader = new FileReader(fileReference);
            BufferedReader bufferedReader = new BufferedReader(fileReferenceReader);
            String lineReference;
            while ((lineReference = bufferedReader.readLine()) != null) {
                fileInformation(pathname, lineReference, player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод, който в зависимост от това кой шанс се е паднал, взима информацията за него и я принтира
     *
     * @param x      пари
     * @param y      + или - в зависимост от това дали шансът е положителен или отрицателн,
     *               събира или изважда пари от тези на играча, според шанса
     * @param player текущ играч
     */
    public static void printChancesAccordingToIntervals(int x, int y, Player player) {
        //положителни шансове
        if (x == 50 && y == 1) {
            getInfoFromFile("resource/the_big_hopes", player);
        }
        if (x == 100 && y == 1) {
            getInfoFromFile("resource/lolita", player);
        }
        if (x == 150 && y == 1) {
            getInfoFromFile("resource/pride_and_prejudice", player);
        }
        if (x == 200 && y == 1) {
            getInfoFromFile("resource/lord_of_the_flies", player);
        }
        if (x == 250 && y == 1) {
            getInfoFromFile("resource/the_hobbit", player);
        }
        //отрицателни шансове
        if (x == 50 && y == -1) {
            getInfoFromFile("resource/1001_nights", player);
        }
        if (x == 100 && y == -1) {
            getInfoFromFile("resource/the_dance_of_the_fairies", player);
        }
        if (x == 150 && y == -1) {
            getInfoFromFile("resource/war_and_peace", player);
        }
        if (x == 200 && y == -1) {
            getInfoFromFile("resource/crime_and_punishment", player);
        }
        if (x == 250 && y == -1) {
            getInfoFromFile("resource/the_fruits_of_anger", player);
        }
    }

    /**
     * Метод, който според шанса извършва съответните парични действия,
     * както и вика метод за да принтира информация за съответния шанс
     *
     * @param interval      число от случайно генериране
     * @param leftInterval  лява граница
     * @param rightInterval дясна граница
     * @param sumOfChance   сума на шанса
     * @param y             + или - в зависимост от това дали шансът е пол. или отр(събира или изважда сумата на шанса)
     * @param player        текущ играч
     */
    public static void actionAccordingToIntervals(int interval, int leftInterval, int rightInterval, int sumOfChance, int y, Player player) {
        if (leftInterval <= interval && rightInterval >= interval) {
            player.setMoney(player.getMoney() + sumOfChance * y);
            printChancesAccordingToIntervals(sumOfChance, y, player);
        }

    }


    /**
     * Метод, който генерира случайно число, за да определи какъв положителен или отрицателен шанс се е паднал
     *
     * @param y      + или -
     * @param player текущ играч
     */
    public static void chanceIntervals(int y, Player player) { //y=1
        int interval = resultFromRollingTheDice(100);
        actionAccordingToIntervals(interval, 1, 39, 50, y, player);

        actionAccordingToIntervals(interval, 40, 65, 100, y, player);

        actionAccordingToIntervals(interval, 65, 79, 150, y, player);

        actionAccordingToIntervals(interval, 80, 94, 200, y, player);

        actionAccordingToIntervals(interval, 95, 100, 250, y, player);
    }

    /**
     * Метод, който хвърлете 10-стенен зар, ако
     * числото е четно – шансът е положителен, а ако е нечетно – шансът е отрицателен.
     * След това се викат методи за съответните действия.
     *
     * @param player текуш играч
     */
    public static void chanceSquare(Player player) {
        int x = 0;
        if (player.getPunishmentFive() == 1) {
            System.out.println("Шансът е отрицателен, тъй като попанахте преди на капан 5.");
            chanceIntervals(-1, player);
            player.setPunishmentFive(0);
        } else {
            if (resultFromRollingTheDice(10) % 2 != 0) {
                System.out.println("Шансът е отрицателен. ");
                chanceIntervals(-1, player);
            } else {

                System.out.println("Шансът е положителен. ");
                chanceIntervals(1, player);

            }
        }
    }

    /**
     * Метод свързан с действията след избора на фирма за инвестиция.
     *
     * @param player            текую играч
     * @param minMoneyForInvest минималната сума за инвестиция за самата фирма
     * @param coefficient       коефициент
     * @param minN              долна граница за интервал за генериране на случайно число
     * @param maxN              горна граница за интервал за генериране на случайно число
     */
    public static void investFirm(Player player, double minMoneyForInvest, double coefficient, int minN, int maxN) {
        double yourMoneyForInvest = 0;
        System.out.println("Ти избра да направиш инвестиция, посочи сумата, която си склонен да отделиш:");
        int counterInvalidMoney = 1;
        for (int i = 0; i < counterInvalidMoney; i++) {
            System.out.println("Въведете сумата, която влагате:");
            yourMoneyForInvest = moneyForInvest(player, yourMoneyForInvest);
            if (yourMoneyForInvest > player.getMoney()) {
                System.out.println("Нямате толкова пари, за да вложите. ");
                counterInvalidMoney++;
            } else {
                if (yourMoneyForInvest >= minMoneyForInvest) {
                    System.out.println("Чудесно. Влагате " + yourMoneyForInvest + " парички.");
                    int randomm = randomGenerate(minN, maxN);
                    if (randomm >= 0) {
                        System.out.println("Супер, ти направи успешна инвестиция!");
                        seeInWhichListToAddInvestments(player, yourMoneyForInvest);
                    } else {
                        System.out.println("За съжаление инвестицията е неуспешна. Твоите пари са:" + player.getMoney());
                    }
                } else {
                    System.out.println("Минималната инвестиция за тази компания е " + minMoneyForInvest);
                    counterInvalidMoney++;
                }
            }
        }
    }

    /**
     * Метод, който събира в лист инвестициите на всеки играч, за да може накрая на цикъла,
     * ако играч е попаднал на капан в който губи пари от печалба от този цикъл, сумата от тези
     * инвестиции да се извади
     *
     * @param player             текущ играч
     * @param yourMoneyForInvest пари, които се инвестират
     */
    public static void seeInWhichListToAddInvestments(Player player, double yourMoneyForInvest) {
        if (player.getId().equals("FirstPlayer")) {
            ListForSuccessInvestmentPlayerOne.add(yourMoneyForInvest);
        } else {
            ListForSuccessInvestmentPlayerTwo.add(yourMoneyForInvest);
        }
    }

    /**
     * Извършват се действията с данни за фирмата, в която сме избрали да инвестираме
     *
     * @param player  текую играч
     * @param company масив с информация за всяка от компаниите
     * @param i       индекс на елемент на масива, всеки елемент е за всяка компания информация
     */
    public static void seeWhichFirmAndExecute(Player player, String[] company, int i) {

        if (company[i].equals(company[0])) {

            investFirm(player, 500, 0.2, -5, 100);
        }
        if (company[i].equals(company[1])) {
            investFirm(player, 400, 0.5, -10, 50);
        }
        if (company[i].equals(company[2])) {
            investFirm(player, 300, 1.5, -15, 35);
        }
        if (company[i].equals(company[3])) {
            investFirm(player, 200, 2, -18, 50);
        }
        if (company[i].equals(company[4])) {
            investFirm(player, 100, 2.5, -25, 100);
        }
        if (company[i].equals(company[5])) {
            investFirm(player, 50, 5, -20, 10);
        }


    }

    /**
     * Метод, който намира сумата на елементите в Лист
     *
     * @param List лист
     * @return сума
     */
    public static double sumOfList(List<Double> List) {
        int sum = 0;
        for (int i = 0; i < List.size(); i++) {
            sum += List.get(i);
        }
        return sum;
    }

    /**
     * Метод, който генерира случайни числа.
     *
     * @param min минимална граница
     * @param max максимална граница + 1
     * @return случайно число в посочен интервал
     */
    public static int randomGenerate(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min) + min;
        return (randomNumber);
    }

    /**
     * Метод, който служи за определяне на стартовия играч в началото на играта.
     *
     * @return рандъм генерирано число между 1 и 2
     */
    public static int playerStartNumber() {
        return randomGenerate(1, 3);
    }

    /**
     * Метод, който служи за генериране на случайно число между 1 и 3
     * за зъл план в началото на всеки цикъл.
     *
     * @return случайно число между 1 и 3
     */
    public static int evilPlanNumber() {
        return randomGenerate(1, 4);
    }

    /**
     * Метод, който генерира случайно число между 1 и зададена граница.
     * Изпълнява ролята на N стенно зарче
     *
     * @param n_wall граница на интервал за генериране/стени на зарчето
     * @return число между 1 и зададена граница
     */
    public static int resultFromRollingTheDice(int n_wall) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(n_wall);
        randomNumber += 1;
        return randomNumber;
    }

    /**
     * Метод, който проверява дали по време на цикъла сме попаднали на капан 2.
     * Вика се в края на всеки цикъл.
     *
     * @param player текущият играч
     */
    public static void ifRunIntoTrapTwo(Player player) {
        if (player.getPunishmentTwo() == 0) {
            if (player.getId().equals("FirstPlayer")) {
                player.setMoney(player.getMoney() + sumOfList(ListForSuccessInvestmentPlayerOne));
            } else {
                player.setMoney(player.getMoney() + sumOfList(ListForSuccessInvestmentPlayerTwo));

            }
        } else {
            if (resultFromRollingTheDice(10) != 2 || resultFromRollingTheDice(10) != 8) {
                if (player.getId().equals("FirstPlayer")) {
                    player.setMoney(player.getMoney() + sumOfList(ListForSuccessInvestmentPlayerOne));
                } else {
                    player.setMoney(player.getMoney() + sumOfList(ListForSuccessInvestmentPlayerTwo));

                }
            }
        }
    }

    /**
     * Метод, който занулира някои характеристики на играчите за нов цикъл
     *
     * @param playerOne играч 1
     * @param playerTwo играч 2
     */
    public static void setNewCharactersticForNewLoop(Player playerOne, Player playerTwo) {
        playerOne.setIndexPosition(0);
        playerTwo.setIndexPosition(0);
        playerOne.setHasSteal(0);
        playerTwo.setHasSteal(0);
        playerOne.setHasFirstEvilPlan(0);
        playerOne.setHasSecondEvilPlan(0);
        playerOne.setHasThirdEvilPlan(0);
        playerTwo.setHasFirstEvilPlan(0);
        playerTwo.setHasSecondEvilPlan(0);
        playerTwo.setHasThirdEvilPlan(0);
        playerOne.setNoTrapsAnymore(0);
        playerTwo.setNoTrapsAnymore(0);
        playerOne.setPunishmentFour(0);
        playerOne.setPunishmentFive(0);
        playerTwo.setPunishmentFour(0);
        playerTwo.setPunishmentFive(0);
    }

    /**
     * Метод, който се изпълнява когато играч попадне в собствения си капан
     *
     * @param trapSquareNumber номер квадратче "Капан"
     * @param player           играчът, който е на ход
     */
    public static void runIntoOwnTrap(int trapSquareNumber, Player player) {
        System.out.println("Попаднахте в собствения си капан. ");
        if (resultFromRollingTheDice(10) % 3 == 0) {
            System.out.println("Спасихте се!");
        } else {
            runIntoTrap(trapSquareNumber, player, player);
        }
    }

    /**
     * Метод, който проверява дали играчът, попаднал на съответното квадратче "Капан"
     * може да заложи капан или вече му е заложен.
     * И в двата варианта се викат допълнителни други методи.
     *
     * @param trapSquareNumber номер на квадратче "Капан"
     * @param player           играч, който е на ход
     * @param player2          другият играч
     */
    public static void trapSquare(int trapSquareNumber, Player player, Player player2) {
        int sumOfTrap = 0;
        if (getOwnTrapX(trapSquareNumber, player2) == 1) {
            runIntoTrap(trapSquareNumber, player, player2);
        }
        if (getOwnTrapX(trapSquareNumber, player) == 1) {
            runIntoOwnTrap(trapSquareNumber, player);
        } else {
            if (player.getNoTrapsAnymore() == 0) {
                trapAction(trapSquareNumber, sumOfTrap, player, player2);
            } else {
                System.out.println("Не може да поставяте повече капани за този цикъл. ");
            }
        }
    }

    /**
     * Метод, който изпечатва менюто с капани и предоставя възможност на играча
     * да избере кой капан да заложи.
     *
     * @param trapSquareNumber номер на квадратчето "Капан"
     * @param sumOfTrap        сумата на всеки капан
     * @param player           играчът, който е на ход
     * @param player2          противникът
     */
    public static void trapAction(int trapSquareNumber, int sumOfTrap, Player player, Player player2) {
        getInfoFromFile("resource/TrapMenu", player);
        int requestNumberforTrap = requestNumberForTrap(player, player2);
        switch (requestNumberforTrap) {
            case 1:
            case 3:
            case 5:
                sumOfTrap = 100;
                casesForTrap(sumOfTrap, trapSquareNumber, requestNumberforTrap, player, player2);
                break;
            case 2:
                sumOfTrap = 200;
                casesForTrap(sumOfTrap, trapSquareNumber, requestNumberforTrap, player, player2);
                break;
            case 4:
                sumOfTrap = 50;
                casesForTrap(sumOfTrap, trapSquareNumber, requestNumberforTrap, player, player2);
                break;
            case 0:
                printMoney(player, player2);
                break;
            default:
                System.out.println("Няма такава опция от предоставеното меню. ");
        }
    }

    /**
     * Метод, който връща стойност желаният капан за поставяне на противниковия играч.
     * Ако на ход е играч номер1, тази стойност се въвежда от конзолата,
     * ако на ход е играч бот - стойността се взима от метод за рандъм генериране на числа
     * в интервала на броя капани.
     *
     * @param player  играчът, който е на ход.
     * @param player2
     * @return желаният капан за поставяне
     */
    public static int requestNumberForTrap(Player player, Player player2) {

        if (player.getId().equals("FirstPlayer")) {
            System.out.println("Въведете опция: ");
            int requestNumberforTrap = input.nextInt();
            return requestNumberforTrap;
        } else {
            int requestNumberforTrap = randomGenerate(0, 7);
            return requestNumberforTrap;
        }
    }

    /**
     * Метод, който проверява дали играчът има достатъчно пари за да заложи капана,
     * който желае
     *
     * @param sumOfTrap            сума на капана
     * @param trapSquareNumber     номер на квадратче "Капан"
     * @param requestNumberforTrap номер желан капан
     * @param player               текущ играч
     * @param player2              противник
     */
    public static void casesForTrap(int sumOfTrap, int trapSquareNumber, int requestNumberforTrap, Player player, Player player2) {
        if (player.getMoney() >= sumOfTrap) {
            actionInCaseOptionsForEveryTrap(sumOfTrap, trapSquareNumber, requestNumberforTrap, player, player2);
        } else {
            System.out.println("Нямате достатъчно пари за този капан. ");
        }
    }

    /**
     * Метод, който се вика след всяка опция за поставяне(или непоставяне) на капан.
     * Принтира паричките на всеки играч до момента.
     *
     * @param player  играчът, който е на ход
     * @param player2 другият играч
     */
    public static void printMoney(Player player, Player player2) {
        System.out.println("Портфейл:\n" +
                "Играч " + player.getNumber() + ": " + player.getMoney() + "\n" +
                "Играч " + player2.getNumber() + ": " + player2.getMoney() + "\n");
    }

    /**
     * Метод, който таксува играча за желания от него капан и извършва съптветните действия.
     *
     * @param sumOfTrap        сума на капана
     * @param trapSquareNumber номер на квадратчето "Капан"
     * @param trapNumber       желаният капан
     * @param player           текущ играч
     * @param player2          противник
     */
    public static void actionInCaseOptionsForEveryTrap(int sumOfTrap, int trapSquareNumber, int trapNumber, Player player, Player player2) {
        removeTheMoneyForTheTrap(player, player2, sumOfTrap);
        setWishedTrapforTrapX(trapSquareNumber, player, trapNumber);
        setOwnTrapX(trapSquareNumber, player, 1);
    }

    /**
     * Метод, който променя характеристика на играча спрямо номера на капана и спрямо дали
     * притежава този капан(квадратче) или не.
     *
     * @param trapSquareNumber номер на квадратчето "Капан"
     * @param player           текущият играч
     * @param nullOrOne        параметър, който служи за маркировка дали играчът притежава
     *                         капана: 1 за да, 0 за не.
     */
    public static void setOwnTrapX(int trapSquareNumber, Player player, int nullOrOne) {
        switch (trapSquareNumber) {
            case 1:
                player.setOwnTrapOne(nullOrOne);
                break;
            case 2:
                player.setOwnTrapTwo(nullOrOne);
                break;
            case 3:
                player.setOwnTrapThree(nullOrOne);
                break;
            case 4:
                player.setOwnTrapFour(nullOrOne);
                break;
            case 5:
                player.setOwnTrapFive(nullOrOne);
                break;
            case 6:
                player.setOwnTrapSix(nullOrOne);
                break;
            case 7:
                player.setOwnTrapSeven(nullOrOne);
                break;
        }
    }

    /**
     * Метод, който променя характеристика на играча спрямо номера на капана и спрямо кой номер опция
     * капан е пожелал (и притежава/л). Маркировката е самото номерче на капана(опция), или 0 в
     * случай, че (вече) не я(капан-опцията) притежава.
     *
     * @param trapSquareNumber номер на квадратче "Капан"
     * @param player           текущият играч
     * @param trapNumber       номер на капан опция
     */
    public static void setWishedTrapforTrapX(int trapSquareNumber, Player player, int trapNumber) {
        switch (trapSquareNumber) {
            case 1:
                player.setWishedTrapForTrap1(trapNumber);
                break;
            case 2:
                player.setWishedTrapForTrap2(trapNumber);
                break;
            case 3:
                player.setWishedTrapForTrap3(trapNumber);
                break;
            case 4:
                player.setWishedTrapForTrap4(trapNumber);
                break;
            case 5:
                player.setWishedTrapForTrap5(trapNumber);
                break;
            case 6:
                player.setWishedTrapForTrap6(trapNumber);
                break;
            case 7:
                player.setWishedTrapForTrap7(trapNumber);
                break;
        }
    }

    /**
     * Метод, който според съответния номер на квадратчето "Капан", на което сме попаднали,
     * взима стойността на желания капан от противника.
     *
     * @param trapSquareNumber номер на квадратче "Капан"
     * @param player2          противника
     * @return капан опцията, чието наказание ни се пада.
     */
    public static int wishedTrapForTrapX(int trapSquareNumber, Player player2) {
        int wishedTrap = -1;
        switch (trapSquareNumber) {
            case 1:
                wishedTrap = player2.getWishedTrapForTrap1();
                break;
            case 2:
                wishedTrap = player2.getWishedTrapForTrap2();
                break;
            case 3:
                wishedTrap = player2.getWishedTrapForTrap3();
                break;
            case 4:
                wishedTrap = player2.getWishedTrapForTrap4();
                break;
            case 5:
                wishedTrap = player2.getWishedTrapForTrap5();
                break;
            case 6:
                wishedTrap = player2.getWishedTrapForTrap6();
                break;
            case 7:
                wishedTrap = player2.getWishedTrapForTrap7();
                break;
        }
        return wishedTrap;
    }

    /**
     * Метод, който връща стойност равна на 1 или 0, в зависимост дали играчът
     * притежава капан-квадратчето или не.
     *
     * @param trapSquareNumber номер на квадратчето "Капан"
     * @param player2          играч
     * @return 0 или 1
     */
    public static int getOwnTrapX(int trapSquareNumber, Player player2) {
        int ownTrap = -1;
        switch (trapSquareNumber) {
            case 1:
                ownTrap = player2.getOwnTrapOne();
                break;
            case 2:
                ownTrap = player2.getOwnTrapTwo();
                break;
            case 3:
                ownTrap = player2.getOwnTrapThree();
                break;
            case 4:
                ownTrap = player2.getOwnTrapFour();
                break;
            case 5:
                ownTrap = player2.getOwnTrapFive();
                break;
            case 6:
                ownTrap = player2.getOwnTrapSix();
                break;
            case 7:
                ownTrap = player2.getOwnTrapSeven();
                break;
        }
        return ownTrap;
    }

    /**
     * Метод, който отпечатва дъската в началото на всеки цикъл.
     *
     * @param board масив за дъската
     */
    public static void printBoard(String[] board) {
        System.out.println(board[9] + "  " + board[10] + "  " + board[11] + "  " + board[12] + "  " + board[13] + "  " + board[14] + "  " + board[15] + "  " + board[16]);
        System.out.println(board[8] + "                                                                                      " + board[17]);
        System.out.println(board[7] + "                                                                                      " + board[18]);
        System.out.println(board[6] + "  " + board[5] + "  " + board[4] + "  " + board[3] + "  " + board[2] + "  " + board[1] + "  " + board[0] + "  " + START);

    }

    /**
     * Метод, който прочита информация от файл и извършва съответните действия
     * при попадане на квадратче PartyHard
     *
     * @param player текущият играч
     */
    public static void partyHardSquare(Player player) {
        getInfoFromFile("resource/PartyHard", player);
    }

    /**
     * Метод, който разбърква елементите на масив.
     *
     * @param board масив
     */
    public static void shuffleElements(String[] board) {
        Random randomGenerator = new Random();

        for (int i = 0; i < board.length; i++) {
            int randomIndex = randomGenerator.nextInt(board.length);
            String temp = board[i];
            board[i] = board[randomIndex];
            board[randomIndex] = temp;
        }

    }

    /**
     * Метод, който се вика на края всеки цикъл, за да провери дали някой играч
     * е останал без пари. Ако има такъв, провъзгласява се другия играч за победител
     * и играда приключва. Ако и двамата играчи са без пари иградата приключва
     * без победител.
     *
     * @param playerOne един от двамата играчи
     * @param playerTwo другият играч
     */
    public static void moneyAndWinnerCheckInTheEndOfLoop(Player playerOne, Player playerTwo) {
        if (playerOne.getMoney() <= 0 && playerTwo.getMoney() > 0) {
            System.out.println("Играч " + playerTwo.getNumber() + "печели играта!");
            System.exit(0);
        }
        if (playerTwo.getMoney() <= 0 && playerOne.getMoney() > 0) {
            System.out.println("Играч " + playerOne.getNumber() + "печели играта!");
            System.exit(0);
        }
        if (playerTwo.getMoney() <= 0 && playerOne.getMoney() <= 0) {
            System.out.println("За съжаление няма победител. ");
        }
    }

    /**
     * Метод, който според квадратчето на което играча попада вика съответнят индивидуален
     * метод(и) с действия за всяко квадратче. След приключване на действията на квадратчето
     * обръща редовете и така идва ред на другия играч.
     *
     * @param board   дъската с квадратчетата
     * @param i       индексът на квадратчето
     * @param player  играчът, който е на ход
     * @param player2 другият играч
     */
    public static void actionForEachSquare(String[] board, int i, Player player, Player player2) {
        if (board[i].equals("|Party hard|")) {
            partyHardSquare(player);
        }
        if (board[i].equals("|   Steal  |")) {
            seeIfHasEvilPlanForSteal(player);
            stealSquare(player, player2, evilPlanNumber());
        }
        if (board[i].equals(TRAP1)) {
            trapSquare(1, player, player2);
        }
        if (board[i].equals(TRAP2)) {
            trapSquare(2, player, player2);
        }
        if (board[i].equals(TRAP3)) {
            trapSquare(3, player, player2);
        }
        if (board[i].equals(TRAP4)) {
            trapSquare(4, player, player2);
        }
        if (board[i].equals(TRAP5)) {
            trapSquare(5, player, player2);
        }
        if (board[i].equals(TRAP6)) {
            trapSquare(6, player, player2);
        }
        if (board[i].equals(TRAP7)) {
            trapSquare(7, player, player2);
        }
        if (board[i].equals("|  Chance  |")) {
            seeIfHasEvilPlanForChance(player);
            chanceSquare(player);
        }
        if (board[i].equals("|  Invest  |")) {
            investSquare(player);
        }
        player.setTurnNow(1);
        player2.setTurnNow(0);
    }

    /**
     * Този метод проверява на края на цикъла кой играч е с повече пари за да
     * се определи кой започва пръв следващия цикъл.
     *
     * @param playerOne играч
     * @param playerTwo играч
     */
    public static void moneyCheckToSeeWhoStarts(Player playerOne, Player playerTwo) {
        if (playerOne.getMoney() > playerTwo.getMoney()) {
            playerStartGameNumber = 1;
        } else {
            playerStartGameNumber = 2;
        }
    }

    /**
     * Този метод прави проверка дали играчът е реализирал зъл план 1.
     * Вика се при попадане на квадратче шанс.
     *
     * @param player играч
     */
    public static void seeIfHasEvilPlanForChance(Player player) { //за Chance
        if (player.getHasFirstEvilPlan() == 1) {
            player.setMoney(player.getMoney() + 100);
            System.out.println("Получавате допълнителни 100 шп защото имате активиран зъл план. ");
        }
    }

    /**
     * Този метод прави проверка дали играчът е реализирал зъл план 2.
     * Вика се при попадане на квадратче инвест.
     *
     * @param player играч
     */
    public static void seeIfHasEvilPlanForInvest(Player player) { // за Invest
        if (player.getHasSecondEvilPlan() == 1) {
            player.setMoney(player.getMoney() + 100);
            System.out.println("Получавате допълнителни 100 шп защото имате активиран зъл план. ");

        }
    }

    /**
     * Този метод прави проверка дали играчът е реализирал зъл план 3.
     * Вика се при попадане на квадратче стийл.
     *
     * @param player играч
     */
    public static void seeIfHasEvilPlanForSteal(Player player) { //за Steal
        if (player.getHasThirdEvilPlan() == 1) {
            player.setMoney(player.getMoney() + 100);
            System.out.println("Получавате допълнителни 100 шп защото имате активиран зъл план. ");

        }
    }

    /**
     * Този метод съдържа всички главни методи за осъществяването на играта.
     * В началото проверява кой играч ще започне пръв, след това разбърква елементите
     * на дъската(квадратчетата), принтира я и се извършват ходовете на всеки един от играчите.
     * Накрая се занулират някои (ползвани) характеристики на играчите за да са чисти
     * за следващия цикъл.
     *
     * @param playerOne играч
     * @param playerTwo играч
     * @param board     масив за дъската
     */
    public static void gameOn(Player playerOne, Player playerTwo, String[] board) {
        if (playerOne.getMoney() > playerTwo.getMoney()) {
            playerStartGameNumber = 1;
        } else {
            playerStartGameNumber = 2;
        }
        if (playerStartGameNumber == 1) {
            System.out.println("Започва играч номер 1. Успех!");
            shuffleElements(board);
            printBoard(board);
            playerActionLoops(board, playerOne, playerTwo, 0);
            setNewCharactersticForNewLoop(playerOne, playerTwo);

        } else {
            System.out.println("Започва играч номер 2. Успех!");
            shuffleElements(board);
            printBoard(board);
            playerActionLoops(board, playerTwo, playerOne, 0);
            setNewCharactersticForNewLoop(playerOne, playerTwo);
        }

    }

    /**
     * Този метод проверява на кого играч трябва да добави в лист брой пъти попадане на данъчна ревизия.
     *
     * @param player  играч
     * @param player2 играч
     */
    public static void inWhichListToAdd(Player player, Player player2) {
        if (player.getNumber() == 1) {
            ListForCountTaxRevisionPlayerOne.add(1);
        } else {
            ListForCountTaxRevisionPlayerTwo.add(1);
        }
    }

    /**
     * Метод, който извършва съответните действия при попадане на играч в свой(ако не се е
     * спасил в метод runIntoOwnTrap ) или противников играч
     *
     * @param trapSquareNumber номер крадратче "Капан"
     * @param player           текущ играч
     * @param player2          противник/или този, който е заложил капана/
     */
    public static void runIntoTrap(int trapSquareNumber, Player player, Player player2) {
        System.out.println("Попаднахте в капан " + wishedTrapForTrapX(trapSquareNumber, player2));
        if (wishedTrapForTrapX(trapSquareNumber, player2) == 1) {
            inWhichListToAdd(player, player2);
            setWishedTrapforTrapX(trapSquareNumber, player2, 0);
        }
        if (wishedTrapForTrapX(trapSquareNumber, player2) == 2) {
            player.setPunishmentTwo(1);
            setWishedTrapforTrapX(trapSquareNumber, player2, 0);
        }
        if (wishedTrapForTrapX(trapSquareNumber, player2) == 3) {
            player.setNoTrapsAnymore(1);
            setWishedTrapforTrapX(trapSquareNumber, player2, 0);
        }
        if (wishedTrapForTrapX(trapSquareNumber, player2) == 4) {
            player.setPunishmentFour(1);
            setWishedTrapforTrapX(trapSquareNumber, player2, 0);
        }
        if (wishedTrapForTrapX(trapSquareNumber, player2) == 5) {
            player.setPunishmentFive(1);
            setWishedTrapforTrapX(trapSquareNumber, player2, 0);
        }
        setOwnTrapX(trapSquareNumber, player2, 0);
    }

    /**
     * Метод, който след всеки "закупен" капан взима цената на капана от парите на играча
     * и извежда паричния баланс и на двамата играчи.
     *
     * @param player    текущ играч
     * @param player2   противник
     * @param sumOfTrap цена на капана
     */
    public static void removeTheMoneyForTheTrap(Player player, Player player2, int sumOfTrap) {

        player.setPutTrap(1);
        player.setMoney(player.getMoney() - sumOfTrap);
        System.out.println("Портфейл:\n" +
                "Играч " + player.getNumber() + ": " + player.getMoney() + "\n" +
                "Играч " + player2.getNumber() + ": " + player2.getMoney() + "\n");

    }

    /**
     * Метод, който взима и обработва информация от файлове по конкретен начин, в зависимост от файла
     *
     * @param pathname      къде се намира файла, име
     * @param lineReference ред от файла
     * @param player        играч
     */
    public static void fileInformation(String pathname, String lineReference, Player player) {
        if (pathname.equals("resource/PartyHard")) {
            String[] partyHardDataArray = lineReference.split(":");
            partyHardAnnouncement = partyHardDataArray[0];
            partyHardMoney = Integer.parseInt(partyHardDataArray[1]);
            player.setMoney(player.getMoney() - partyHardMoney);
        }
        if (pathname.equals("resource/InvestCompany")) {
            String[] investComapnies = lineReference.split("@");
            companyArray[0] = investComapnies[0];
            companyArray[1] = investComapnies[1];
            companyArray[2] = investComapnies[2];
            companyArray[3] = investComapnies[3];
            companyArray[4] = investComapnies[4];
            companyArray[5] = investComapnies[5];
            String[] copyArray = new String[6];
            for (int i = 0; i < copyArray.length; i++) {
                copyArray[i] = companyArray[i];
            }
            investActions(player, copyArray);
        } else {
            System.out.println(lineReference);
        }

    }

    /**
     * Метод, който вика други методи свързани с действията Иневст. Проверява дали играчът
     * преди това не е реализирал план, от който печели 100 парички при попадане на
     * квадрватче Инвест и взима обработена вече информация от файл.
     *
     * @param player текущ играч
     */
    public static void investSquare(Player player) {
        seeIfHasEvilPlanForInvest(player);
        getInfoFromFile("resource/InvestCompany", player);

    }

    /**
     * Метод, който връща стойност пари, които желаем да инвестираме.
     * Ако играчът е бот, генерира рандом число, ако не - следва въвеждане от конзолата.
     *
     * @param player             текущ играч
     * @param yourMoneyForInvest променлива за пари за инвестиране
     * @return пари за инвестиране посочени от играча
     */
    public static double moneyForInvest(Player player, double yourMoneyForInvest) {
        if (player.getId().equals("SecondPlayerBot")) {
            return randomGenerate(0, 1000);
        } else {
            yourMoneyForInvest = Double.parseDouble(input.next());
            return yourMoneyForInvest;
        }
    }


}
