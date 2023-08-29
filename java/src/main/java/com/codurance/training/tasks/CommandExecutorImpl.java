package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CommandExecutorImpl implements CommandExecutor {
    private final PrintWriter out;
    private Map<String, List<Task>> tasks;

    private Error errors;

    private TaskAdder addToTask;
    private ToggleTask toggleTask;

    private Helper helper;

    private TaskViewer taskViewer;

    public CommandExecutorImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void executeTask(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskViewer = new TaskViewerImpl(out, tasks);
                taskViewer.show();
                break;
            case "add":
                addToTask = new TaskAdderImpl(out, tasks);
                addToTask.add(commandRest[1]);
                break;
            case "check":
                toggleTask = new ToggleTaskImpl(out, tasks);
                toggleTask.toggle(commandRest[1], true);
                break;
            case "uncheck":
                toggleTask = new ToggleTaskImpl(out, tasks);
                toggleTask.toggle(commandRest[1], false);
                break;
            case "help":
                helper = new HelperImpl(out);
                helper.help();
                break;
            default:
                errors = new ErrorImpl(out);
                errors.showError(command);
                break;
        }
    }
}
