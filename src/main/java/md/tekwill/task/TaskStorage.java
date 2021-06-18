package md.tekwill.task;

import java.util.List;

interface TaskStorage {

    List<Task> findAll();

    Task findById(int id);

    void create(Task task);

    void removeById(int id);

    void updateDone(int id, boolean done);
}
