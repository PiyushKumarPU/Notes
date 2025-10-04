package com.learning.patterns.behavioural.command;

/*
 * Concrete Command: Deposit
 */
public class DepositCommand implements TransactionCommand {
    private BankAccount account;
    private double amount;

    public DepositCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }

    @Override
    public void undo() {
        account.withdraw(amount); // undo deposit
    }
}
