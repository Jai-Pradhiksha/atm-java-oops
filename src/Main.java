import java.util.Scanner;

import static java.lang.System.exit;
abstract class ATM {
    private int accountBalance;

    public int getAccountBalance() {
        return accountBalance;
    }
    public abstract void depositAmount(int amountToBeDeposited) throws InvalidAmountException;
    public abstract void withdrawAmount(int amountToBeWithdrawn) throws InvalidAmountException, InsufficientBalanceException;
    public abstract void checkBalance();
    public abstract void addMoney(int amountToBeDeposited);
    public abstract void takeMoney(int amountToBeWithdrawn);

    protected void updateBalance(int amount) {
        accountBalance += amount;
    }

    public void printLine(){
        System.out.println("________________________________");
    }
}
class BankOfIndia extends ATM {

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        int userChoice, inputAmount;

        do {
            printLine();
            System.out.println("1 - Deposit Amount");
            System.out.println("2 - Withdraw Amount");
            System.out.println("3 - Check Balance");
            System.out.println("4 - Exit");
            printLine();
            System.out.print("Enter your preferred option ... ");

            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                switch (userChoice) {
                    case 1 -> {
                        System.out.print("Enter the amount to be deposited ... Rs.");
                        inputAmount = Integer.parseInt(scanner.nextLine());
                        if (isValidAmount(inputAmount)) {
                            depositAmount(inputAmount);
                        }
                    }

                    case 2 -> {
                        System.out.print("Enter the amount to be withdrawn ... Rs.");
                        inputAmount = Integer.parseInt(scanner.nextLine());
                        if (isValidAmount(inputAmount)) {
                            if (getAccountBalance() < inputAmount) {
                                throw new InsufficientBalanceException("Insufficient balance in your account :(");
                            }
                            withdrawAmount(inputAmount, "Withdrawal successful!");
                        }
                    }

                    case 3 -> checkBalance();

                    case 4 -> {
                        System.out.println("Thank you, Have a nice day :)");
                        exit(0);
                    }

                    default -> {
                        printLine();
                        System.out.println("Please retry with a valid option.");
                    }
                }
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("Please enter a valid number.");
            } catch (InvalidAmountException | InsufficientBalanceException e) {
                printLine();
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    @Override
    public void addMoney(int amountToBeDeposited) {
        updateBalance(amountToBeDeposited);
        printLine();
        System.out.println("Deposited Rs. " + amountToBeDeposited + " successfully");
    }

    @Override
    public void takeMoney(int amountToBeWithdrawn) {
        updateBalance(-amountToBeWithdrawn);
    }

    @Override
    public void depositAmount(int amountToBeDeposited) throws InvalidAmountException {
        addMoney(amountToBeDeposited);
    }

    @Override
    public void withdrawAmount(int amountToBeWithdrawn) throws InvalidAmountException, InsufficientBalanceException {
        takeMoney(amountToBeWithdrawn);
    }

    public void withdrawAmount(int amountToBeWithdrawn, String note) throws InvalidAmountException, InsufficientBalanceException {
        printLine();
        System.out.println("Rs. "+ amountToBeWithdrawn + " " + note);
        takeMoney(amountToBeWithdrawn);
    }

    @Override
    public void checkBalance() {
        printLine();
        System.out.println("Your balance is Rs. " + getAccountBalance());
    }

    private boolean isValidAmount(int inputAmount) throws InvalidAmountException {
        if (inputAmount > 0) {
            return true;
        }
        throw new InvalidAmountException("Amount must be greater than 0.");
    }
}

public class Main {
    public static void main(String[] args) {
        BankOfIndia centralATM = new BankOfIndia();
        centralATM.initialize();
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
