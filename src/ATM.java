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
