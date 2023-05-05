package com.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        homescreen();
    }

    public static void homescreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Hello! Welcome To Your Accounting Ledger: ======\n");
        System.out.println(("Main Menu"));
        System.out.println("[D] - Add a Deposit");
        System.out.println("[P] - Make a Payment");
        System.out.println("[L] - View Ledger");
        System.out.println("[X] - Exit Application");
// use switch statement
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "D":
                addDeposit();
                break;
            case "P":
                makePayment();
                break;
            case "L":
                Ledger.showLedger();
                break;
            case "X":
                System.out.println("Goodbye For Now ;) ");
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a valid option \n");
                homescreen();
                break;

        }


    }


    private static void makePayment() {// Use scanner to get users input and storing in the corresponding variables.
        System.out.println("=================PAYMENT=================\n");
        System.out.println("Okay!....Lets make a payment.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Date:(yyyy-MM-dd)");
        String date = scanner.nextLine();
        System.out.println("Please enter Time: (HH:mm:SS)");
        String time = scanner.nextLine();
        System.out.println("Please enter Description");
        String description = scanner.nextLine();
        System.out.println("Please enter vendor");
        String vendor = scanner.nextLine();
        System.out.println("Please enter amount");
        double amount = scanner.nextDouble();// storing the amount as a double.

        try { // Using the filewriter to add  the collected data into csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // Store all information with a -ve sign before the amount variable
            fileWriter.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "|");
            System.out.println("WooHoo! Your payment was made successfully");
            fileWriter.close();
        } catch (IOException e) { //prints out error message when input is wrong
            System.out.println("Oh no! There was an error inputting data!");
        }
        System.out.println("Would you like to return To Homescreen(H)?...Or make another Payment?(P)?  ");
        String choice = scanner.next();

        while (true) { // using else/if statement to correspond based on users input
            if (choice.equalsIgnoreCase("H")) {
                Main.homescreen();
                break;
            } else if (choice.equalsIgnoreCase("P")) {
                addDeposit();
                break;
            } else {
                System.out.println("Please enter a valid option ('H' or 'P'):");
                choice = scanner.next();
            }
        }
    }


    private static void addDeposit() { // using scanner to get users input and storing it into a variable
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================DEPOSIT=================\n");
        System.out.println("Okay!....Lets add a deposit.");
        System.out.println("Please enter Date:(yyyy-MM-dd)");
        String date = scanner.nextLine();
        System.out.println("Please enter Time: (HH:mm:SS)");
        String time = scanner.nextLine();
        System.out.println("Please enter Description");
        String description = scanner.nextLine();
        System.out.println("Please enter vendor");
        String vendor = scanner.nextLine();
        System.out.println("Please enter amount");
        double amount = scanner.nextDouble();

        try {// writing the information from the variables to the csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // add | in between the variables
            fileWriter.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "|");
            System.out.println("Great your deposit was added successfully!");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Oh no! There was an error inputting data!");
        }
        System.out.println("Return To Homescreen(H)?...Or add another deposit(D)?  ");
        String choice = scanner.next();

        while (true) { // using if/else statement based on users input to return to homescreen or add another deposit.
            if (choice.equalsIgnoreCase("H")) {
                Main.homescreen();
                break;
            } else if (choice.equalsIgnoreCase("D")) {
                addDeposit();
                break;
            } else {
                System.out.println("Please enter a valid option ('H' or 'D'):");
                choice = scanner.next();
            }
        }
    }
}

