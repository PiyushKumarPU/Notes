package com.learning.patterns.behavioural.command;

/*
 * Client: Configures the commands and executes via Invoker
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000);

        TransactionInvoker invoker = new TransactionInvoker();

        // Deposit 500
        TransactionCommand deposit500 = new DepositCommand(account, 500);
        invoker.executeCommand(deposit500);

        // Withdraw 200
        TransactionCommand withdraw200 = new WithdrawCommand(account, 200);
        invoker.executeCommand(withdraw200);

        // Undo last command (Withdraw 200)
        invoker.undoLastCommand();

        // Undo last command (Deposit 500)
        invoker.undoLastCommand();
    }
}
