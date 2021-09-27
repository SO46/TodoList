package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private static int currentId = 1;
    private static final Map<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTask() {
        return new ArrayList<Task>(tasks.values());
    }

    public static Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public static int addTask(Task task) {
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Task update(Task task){
        return tasks.put(task.getId(), task);
    }

    public static Task delete(int id){
        return tasks.remove(id);
    }

    public static void deleteAll(){
        tasks.clear();
        currentId = 1;
    }

}