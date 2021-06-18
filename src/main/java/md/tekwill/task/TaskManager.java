package md.tekwill.task;

import java.util.List;

public class TaskManager {

    private final TaskStorage taskStorage;

    public TaskManager() {
        this.taskStorage = new TaskStorageImpl();
    }

    public List<Task> getAll() {
        return taskStorage.findAll();
    }

    public Task getById(int id) {
        return taskStorage.findById(id);
    }

    public void add(Task task) {
        Task existingTask = taskStorage.findById(task.getId());
        if (existingTask == null) {
            taskStorage.create(task);
        } else {
            System.out.println("Another md.tekwill.task found with id " + task.getId() + "! Unable to add new.");
        }
    }

    public void delete(int id) {
        Task task = taskStorage.findById(id);
        if (task != null) {
            taskStorage.removeById(id);
        } else {
            System.out.println("No md.tekwill.task found with id " + id + "! Unable to delete.");
        }
    }

    public void update(int id, boolean done) {
        taskStorage.updateDone(id, done);
    }
}
