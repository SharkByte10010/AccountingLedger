package com.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        homescreen();
    }

       public static void homescreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your accounting Ledger: ");
        System.out.println(("Main Menu"));
        System.out.println("[D] - Add Deposit");
        System.out.println("[P] - Make Payment");
        System.out.println("[L] - Ledger");
        System.out.println("[X] - Exit");
// use switch statement
        String input = scanner.nextLine();
        switch (input.toUpperCase()){
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
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }


    }





    private static void makePayment() { // Use scanner to get users input and storing in the corresponding variables.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date:(yyyy-MM-dd)");
        String date = scanner.nextLine();
        System.out.println("Enter Time: ( HH:mm:SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description =scanner.nextLine();
        System.out.println("Enter vendor");
        String vendor = scanner.next();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();// storing the amount as a double.

        try{ // Using the filewriter to add  the collected data into csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // Store all information with a -ve sign before the amount variable
            fileWriter.write( "\n" + date + "|" + time + "|" + vendor + "|" + "-"  + amount + "|");
            System.out.println("Payment made successfully");
            fileWriter.close();
        } catch (IOException e){ //prints out error message when input is wrong
            System.out.println("Error inputting data!");
        }
    }

    private static void addDeposit() { // using scanner to get users input and storing it into a variable
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date:(yyyy-MM-dd)");
        String date = scanner.nextLine();
        System.out.println("Enter Time: ( HH:mm:SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description =scanner.nextLine();
        System.out.println("Enter vendor");
        String vendor = scanner.next();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();

        try{// writing the information from the variables to the csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // add | in between the variables
            fileWriter.write( "\n" + date + "|" + time + "|" + vendor + "|" + amount + "|");
            System.out.println("Deposit added successfully");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Error inputting data!");
        }

    }
}
