# Accounting Ledger
## Description
This is a Java application that allows the user to make deposits, payments, as well view the accounting transactions. These transactions will be stored in a file names transactions.csv.
# Abilities
- Make Deposits
- Make Payments
- View Ledger transactions
- Make Reports

## Intersesting Piece Of Code
> while (true) { 
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

## Pictures
## Main Menu
![Capstone Project1.png](src%2Fmain%2Fresources%2FCapstone%20Project1.png)
## Ledger Menu
![Capstone Project2.png](src%2Fmain%2Fresources%2FCapstone%20Project2.png)
## Report Menu
![Capstone Project3.png](src%2Fmain%2Fresources%2FCapstone%20Project3.png)
## Example Of "MonthToDate" Report
![CapstoneProject4.png](src%2Fmain%2Fresources%2FCapstoneProject4.png)