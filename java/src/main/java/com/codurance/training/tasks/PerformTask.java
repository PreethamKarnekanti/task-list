package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PerformTask {
    private  Map<String, List<Task>> tasks;
    private final PrintWriter out;


    private final CommandExecutor commandExecutor;

    public PerformTask(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
        this.commandExecutor = new CommandExecutorImpl(out, tasks);
    }

    public void executeTask(String commandLine) {
        commandExecutor.executeTask(commandLine);
    }
}

