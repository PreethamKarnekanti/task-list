package com.codurance.training.tasks;

import java.io.PrintWriter;

public class ErrorImpl implements  Error{

    private final PrintWriter out;

    public ErrorImpl(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void showError(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
