# Accounting Ledger
## Description
This is an application that allows the user to make deposits, payments, as well view the accounting transactions.
# Abilities
- Make Deposits
- Make Payments
- View Ledger transactions
- Make Reports

## Intersesting Piece Of Code
> while (true) { // using else/if statement to correspond based on users input
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