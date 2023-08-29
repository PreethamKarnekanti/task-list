package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ToggleTaskImpl implements ToggleTask{

    private  final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public ToggleTaskImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void toggle(String idString, boolean value) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(value);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
