package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CommandExecutorImpl implements CommandExecutor {
    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    private Error errors;

    private TaskAdder taskAdder;
    private ToggleTask toggleTask;

    private Helper helper;

    private TaskViewer taskViewer;

    public CommandExecutorImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
        this.errors = new ErrorImpl(out);
        this.taskAdder = new TaskAdderImpl(out, tasks);
        this.toggleTask = new ToggleTaskImpl(out, tasks);
        this.helper = new HelperImpl(out);
        this.taskViewer = new TaskViewerImpl(out, tasks);
    }

    @Override
    public void executeTask(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskViewer.show();
                break;
            case "add":
                taskAdder.add(commandRest[1]);
                break;
            case "check":
                toggleTask.toggle(commandRest[1], true);
                break;
            case "uncheck":
                toggleTask.toggle(commandRest[1], false);
                break;
            case "help":
                helper.help();
                break;
            default:
                errors.showError(command);
                break;
        }
    }
}
