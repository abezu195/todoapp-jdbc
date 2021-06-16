package md.tekwill.task;

import java.util.Arrays;

interface TaskStorage {

    Task[] findAll();
    Task findById(int id);
    void create(Task task);
    void removeById(int id);
}
