package com.learning.patterns.behavioural.command;

import java.util.Stack;

/*
 * Invoker: Executes and manages command history
 */
public class TransactionInvoker {
    private Stack<TransactionCommand> history = new Stack<>();

    public void executeCommand(TransactionCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            TransactionCommand lastCommand = history.pop();
            lastCommand.undo();
            System.out.println("Undo last command");
        } else {
            System.out.println("No commands to undo");
        }
    }
}
