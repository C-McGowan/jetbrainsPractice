package machine;
import java.util.Scanner;

public class CoffeeMachine {

    private static Scanner scanner;

    enum STATES {
        buy, fill, take, remaining, exit;
    }

    private int amountWater;
    private int amountMilk;
    private int amountCoffee;
    private int amountCups;
    private int amountMoney;

    public CoffeeMachine(int amountWater, int amountMilk, int amountCoffee, int amountCups, int amountMoney) {
        this.amountWater = amountWater;
        this.amountMilk = amountMilk;
        this.amountCoffee = amountCoffee;
        this.amountCups = amountCups;
        this.amountMoney = amountMoney;
    }

    public void setAmountWater(int amountWater) {
        this.amountWater = amountWater;
    }

    public void setAmountMilk(int amountMilk) {
        this.amountMilk = amountMilk;
    }

    public void setAmountCoffee(int amountCoffee) {
        this.amountCoffee = amountCoffee;
    }

    public void setAmountCups(int amountCups) {
        this.amountCups = amountCups;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public int getAmountWater() {
        return this.amountWater;
    }

    public int getAmountMilk() {
        return this.amountMilk;
    }

    public int getAmountCoffee() {
        return this.amountCoffee;
    }

    public int getAmountCups() {
        return this.amountCups;
    }

    public int getAmountMoney() {
        return this.amountMoney;
    }

    public static void buy(CoffeeMachine coffeeMachine, String option) {
        int waterChange = 0;
        int milkChange = 0;
        int coffeeChange = 0;
        int moneyChange = 0;
        switch (option) {
            case "1":
                waterChange = 250;
                coffeeChange = 16;
                moneyChange = 4;
                break;
            case "2":
                waterChange = 350;
                milkChange= 75;
                coffeeChange = 20;
                moneyChange = 7;
                break;
            case "3":
                waterChange = 200;
                milkChange = 100;
                coffeeChange = 12;
                moneyChange = 6;
                break;
            case "back":
                break;
            default:
                System.out.println("Unknown command, returning to main menu.");
        }

        String errorMessage = "Sorry, not enough ";
        if (option.equals("1") || option.equals("2") || option.equals("3")) {
            if (coffeeMachine.getAmountCups() == 0) {
                System.out.println(errorMessage + "disposable cups!");
            } else if ((coffeeMachine.getAmountWater() - waterChange) < 0 ) {
                System.out.println(errorMessage + "water!");
            } else if ((coffeeMachine.getAmountMilk() - milkChange) < 0) {
                System.out.println(errorMessage + "milk!");
            } else if ((coffeeMachine.getAmountCoffee() - coffeeChange) < 0) {
                System.out.println(errorMessage + "coffee!");
            } else {
                System.out.println("I have enough resources, making you a coffee!");

                coffeeMachine.setAmountWater(coffeeMachine.getAmountWater() - waterChange);
                coffeeMachine.setAmountMilk(coffeeMachine.getAmountMilk() - milkChange);
                coffeeMachine.setAmountCoffee(coffeeMachine.getAmountCoffee() - coffeeChange);
                coffeeMachine.setAmountCups(coffeeMachine.getAmountCups() - 1);
                coffeeMachine.setAmountMoney(coffeeMachine.getAmountMoney() + moneyChange);
            }
        }

        System.out.println();
    }

    public static void fill(CoffeeMachine coffeeMachine) {
        System.out.println("Write how many ml of water do you want to add:");
        coffeeMachine.setAmountWater(coffeeMachine.getAmountWater() + scanner.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        coffeeMachine.setAmountMilk(coffeeMachine.getAmountMilk() + scanner.nextInt());

        System.out.println("Write how many ml of coffee do you want to add:");
        coffeeMachine.setAmountCoffee(coffeeMachine.getAmountCoffee() + scanner.nextInt());

        System.out.println("Write how many ml of cups do you want to add:");
        coffeeMachine.setAmountCups(coffeeMachine.getAmountCups() + scanner.nextInt());
    }

    public static void take(CoffeeMachine coffeeMachine) {
        System.out.println("I gave you $" + coffeeMachine.getAmountMoney());
        coffeeMachine.setAmountMoney(0);
    }

    public static void remaining(CoffeeMachine coffeeMachine) {
        System.out.println("The coffee machine has:");
        System.out.println(coffeeMachine.getAmountWater() + " of water");
        System.out.println(coffeeMachine.getAmountMilk() + " of milk");
        System.out.println(coffeeMachine.getAmountCoffee() + " of coffee beans");
        System.out.println(coffeeMachine.getAmountCups() + " of disposable cups");
        System.out.println("$" + coffeeMachine.getAmountMoney() + " of money");
    }


    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        boolean moreActions = true;
        while (moreActions) {

            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String action = scanner.next();
            STATES state = STATES.valueOf(action);
            switch (state) {
                case buy:
                    System.out.println();
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    buy(coffeeMachine, scanner.next());
                    break;

                case fill:
                    System.out.println();
                    fill(coffeeMachine);
                    System.out.println();
                    break;

                case take:
                    System.out.println();
                    take(coffeeMachine);
                    System.out.println();
                    break;

                case remaining:
                    System.out.println();
                    remaining(coffeeMachine);
                    System.out.println();
                    break;

                case exit:
                    moreActions = false;
                    break;

                default:
                    System.out.println("Unknown action.");
                    break;
            }
        }
    }
}

