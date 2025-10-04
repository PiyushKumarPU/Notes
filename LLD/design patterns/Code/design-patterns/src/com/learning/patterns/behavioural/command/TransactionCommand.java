package com.learning.patterns.behavioural.command;

/*
 * Command interface
 * - Declares execute() and undo() methods.
 */
public interface TransactionCommand {
    void execute();
    void undo();
}
