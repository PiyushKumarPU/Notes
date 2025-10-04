package com.learning.patterns.behavioural.command;

/*
 * Concrete Command: Withdraw
 */
public class WithdrawCommand implements TransactionCommand {
    private BankAccount account;
    private double amount;

    public WithdrawCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }

    @Override
    public void undo() {
        account.deposit(amount); // undo withdraw
    }
}
