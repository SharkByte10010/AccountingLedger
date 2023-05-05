package com.example;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.time.format.DateTimeFormatter;

public class Ledger {
    // initializing an array list which holds objects and calling it transactionsList
    // and we are inheriting the transactions array list from the getTransactions
    public static ArrayList<Transactions> transactionsList = getTransactions();

    public static ArrayList<Transactions> getTransactions() { // Declaring a method called getTransactions
        // of the type array list that hold transactions objects
        ArrayList<Transactions> transactions = new ArrayList<>();// making a array list of transaction objects named transactions
        try { // Intializing the file reader and buffered reader and passing the transactions.csv file into it.
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;// Declaring a variable named input of type String.
            while ((input = bufferedReader.readLine()) != null) { // looping through the csv file
                // we're taking the current line from the csv file and storing it inside the input variable
                // and this loop will keep reapeating until the next line is null()
                String[] details = input.split("\\|"); // splitting the input with "|" and storing all the pieces
                // into the String array variable called details
                LocalDate date = LocalDate.parse(details[0]); // we're taking the string at index 0 from the details array and
                // converting it to LocalDate using the LocalDate class and store it in the date variable of the type LocalDate
                LocalTime time = LocalTime.parse(details[1]); // were taking the string at index 1 from the details array
                // and converting it to the LocalTime and storing it to the time variable of type Local time.
                String description = details[2]; // were taking the the strin
                String vendor = details[3];
                double amount = Double.parseDouble(details[4]);

                Transactions transaction = new Transactions(date, time, description, vendor, amount);
                // We're using the constructor from the transaction clasd and we're passing it (date , time, description)
                // into the constructor to make a new object called transaction of type Transactions
                transactions.add(transaction); //  we're adding the transcation object we just created into the transactions array list.
                // we will repeat all the steps above for each iteration of the loop (for each line in the csv file)
            }
        } catch (FileNotFoundException e) { // if the csv file can't be found , throw and error
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Comparator<Transactions> compareByDate = Comparator.comparing(Transactions::getDate).reversed();
        Comparator<Transactions> compareByTime = Comparator.comparing(Transactions::getTime).reversed();
        Comparator<Transactions> compareByDateTime = compareByDate.thenComparing(compareByTime);
        transactions.sort(compareByDateTime);

        return transactions; // returning the transactions array list to our method , so when we call on our method we get an array list returned to us.
    }

    public static void showLedger() { // creating method called showLedger() to display ledger screen
        Scanner scanner = new Scanner(System.in);
        System.out.println("================LEDGER================\n");
        System.out.println("Welcome to your ledger ");
        System.out.println(("Main Menu: "));
        System.out.println("[A] - View All Entries");
        System.out.println("[D] - View Deposits");
        System.out.println("[P] - View Payments");
        System.out.println("[R] - View Reports");
        System.out.println("[X] - Go back to Home page");
        //using switch method instead of if-else statement to run the corresponding method based on users input

        String input = scanner.nextLine();
        switch (input.toUpperCase()){
            case "A":
                allEntries();
                break;
            case "D":
                showDepositedEntries();
                break;
            case "P":
                showPaymentEntries();
                break;
            case "R":
                showReports();
                break;
            case "X":
                Main.homescreen();
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }

    //show reports
    public static void showReports() { // creating a method called showReports() to display the reprots menu
        Scanner scanner = new Scanner(System.in);
        System.out.println("================REPORTS================\n");
        System.out.println("Welcome to Reports\n");
        System.out.println(("Reports Menu: "));
        System.out.println("[1] - Month to Date");
        System.out.println("[2] - Previous months");
        System.out.println("[3] - Year to Date");
        System.out.println("[4] - Previous Year");
        System.out.println("[5] - Search by Vendor");
        System.out.println("[6] - Return to previous screen");
        //using switch method instead of if/else statement to run the corresponding method based on user's input
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "1":
                monthtoDate();
                break;

            case "2":
                previousMonths();
                break;

            case "3":
             yeartoDate();
             break;

            case "4":
            previousYears();
             break;

            case "5":
            searchbyVendor();
             break;

            case "6":
             showLedger();
             break;

        }
    }

    private static void monthtoDate() { // prints the 1st of the current month to the current date(today)
        System.out.println("Here is your month to date report: ");
        LocalDate currentDate = LocalDate.now(); // this method gets the current date using 'LocalDate.now()
        LocalDate startOfTheCurrentMonth = currentDate.withDayOfMonth(1); // this method gets the first day of the month
        //using the 'withDayOfMonth(1) method
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy"); // using the DateTimeFormatter
        //class to format the dates in month to date format
        System.out.println("From" + " " + startOfTheCurrentMonth + " to " + currentDate);

        for (Transactions item : transactionsList) {
            //if we don't subtract 1 , the first day of the month will be excluded since we are using 'isAfter' method
            if (item.getDate().isAfter(startOfTheCurrentMonth.minusDays(1)) || item.getDate().isEqual(currentDate)) {
                System.out.println(item.getDate() + " | " + item.getTime() + " | " + item.getDescription() + " | " +
                        item.getVendor() + " | " + item.getAmount());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to Reports screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            monthtoDate();
        }else {
            showReports();
        }

    }
    private static void previousMonths() {
        LocalDate today = LocalDate.now(); // this method gets the current date
        int previousMonthsValue = today.minusMonths(1).getMonthValue(); // this method gets the previous month
        System.out.println("Previous months");
        for (Transactions transaction : transactionsList) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.getMonthValue() == previousMonthsValue && transactionDate.getYear() == today.getYear()) {
                System.out.println(transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                        transaction.getAmount() + "|" + transaction.getDate());

            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to Reports screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            previousMonths();
        }else {
            showReports();
        }
    }

    private static void yeartoDate() { // prints the 1st of the current month to the current date(today)
        System.out.println("Here is your year to date report: ");
        LocalDate currentDate = LocalDate.now(); // this method gets the current date using 'LocalDate.now()
        LocalDate startOfTheCurrentYear = currentDate.withDayOfYear(1); // this method gets the first day of the month
        //using the 'withDayOfMonth(1) method
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy"); // using the DateTimeFormatter
        //class to format the dates in month to date format
        System.out.println("From" + " " + startOfTheCurrentYear.format(formatter) + " to " + currentDate.format(formatter));

        for (Transactions transaction : transactionsList) {
            //if we don't subtract 1 , the first day of the month will be excluded since we are using 'isAfter' method
            if (transaction.getDate().isAfter(startOfTheCurrentYear.minusDays(1)) || transaction.getDate().isEqual(currentDate)) {
                System.out.println(transaction.getDate() + " | " + transaction.getTime() + " | " + transaction.getDescription() + " | " +
                        transaction.getVendor() + " | " + transaction.getAmount());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to Ledger screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            yeartoDate();
        }else {
            showReports();
        }
    }

    private static void previousYears() { // prints prev
        LocalDate today = LocalDate.now();
        int previousYearValue = today.minusYears(1).getYear(); // changed to get previous year
        System.out.println("Previous Year");
        for (Transactions item : transactionsList) {
            LocalDate transactionDate = item.getDate();
            if (transactionDate.getYear() == previousYearValue && transactionDate.getYear() != today.getYear()) {
                // changed condition to check if transaction is in previous year
                System.out.println(item.getDate() + " | " + item.getTime() + " | " + item.getDescription() + " | " +
                        item.getVendor() + " | " + item.getAmount());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to Reports screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            previousYears();
        }else {
            showReports();
        }
    }
    private static void searchbyVendor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Please enter Vendor's name: ");
        String vendorName = scanner.nextLine();

        for (Transactions item : transactionsList) {
            if (item.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(item.getDate() + " | " + item.getTime() + " | " + item.getDescription() + " | " +
                        item.getVendor() + " | " + item.getAmount());
            }
        }
        Scanner choice2 = new Scanner(System.in);
        System.out.println("Search again?(R).....Or return to Reports screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            searchbyVendor();
        }else {
            showReports();
        }
    }


    private static void showPaymentEntries() {
        System.out.println("Payment");
        for (Transactions item : transactionsList) { // loop through each transaction object(item) in the transactionslist
            // array list and check if the price is negative(payment)
            if (item.getAmount() < 0) {
                // printing out it's private variable using the getter methods
            System.out.println(item.getDate() + " " + item.getTime() + " " + item.getDescription() + " " +
                    item.getVendor() + " " +  item.getAmount());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to previous screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            showPaymentEntries();
        }else {
            showLedger();
        }
    }

    private static void showDepositedEntries() {
        System.out.println("Deposits");
        for (Transactions item : transactionsList) { // loop through each transaction object(item) in the transactionslist
            // array list and check if the price is positive(deposit)
            if (item.getAmount() > 0) {
                //printing out it's private variable using the getter methods
            System.out.println(item.getDate() + " " + item.getTime() + " " + item.getDescription() + " " +
                    item.getVendor() + " " +  item.getAmount());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to previous screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            showDepositedEntries();
        }else {
            showLedger();
        }
    }

    private static void allEntries() { // we're declaring the method called allEntries
        System.out.println(" All Entries");
        for (Transactions item : transactionsList) { // loop through each transaction object(item) in the transactionslist
            // array list and printing out its private variables using the getter method
            System.out.println(item.getDate() + " " + item.getTime() + " " + item.getDescription() + " " +
                    item.getVendor() + " " +  item.getAmount());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run again?(R).....Or return to Previous screen?(L)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("R")){
            allEntries();
        }else {
            showLedger();
        }
    }

}
