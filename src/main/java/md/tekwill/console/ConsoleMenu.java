package md.tekwill.console;

import md.tekwill.task.Task;
import md.tekwill.task.TaskManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final TaskManager taskManager;
    private final Scanner scanner;

    public ConsoleMenu(TaskManager taskManager, Scanner scanner) {
        this.taskManager = taskManager;
        this.scanner = scanner;
    }

    public void run() {
        boolean exitOptionNotSelected;
        do {
            System.out.println("\n\tWelcome to TODO List App");
            System.out.println("Available options:" +
                                       "\n[1] View all tasks" +
                                       "\n[2] Add new task" +
                                       "\n[3] Delete a task" +
                                       "\n[4] Update a task" +
                                       "\n[5] Show single task" +
                                       "\n[0] Exit");
            System.out.print(">> ");
            exitOptionNotSelected = handleUserChoice(scanner.nextInt());
        } while (exitOptionNotSelected);
    }

    private boolean handleUserChoice(int userChoice) {
        scanner.nextLine();
        switch (userChoice) {
            case 1:
                showAllTasks();
                return true;
            case 2:
                addNewTask();
                return true;
            case 3:
                deleteTask();
                return true;
            case 4:
                updateTask();
                return true;
            case 5:
                showSingleTask();
                return true;
            case 0:
                System.out.println("KTHXBYE");
                return false;
            default:
                System.out.println("Unknown option selected!");
                return true;
        }
    }

    private void showAllTasks() {
        List<Task> tasks = taskManager.getAll();
        if (!tasks.isEmpty()) {
            System.out.println("All tasks: ");
            for (Task task : tasks) {
                System.out.println("\t[" + task.getId() + "] " + task.getTitle() + " " +
                                           "\n\tDescription: " + task.getDescription() +
                                           "\n\tTarget date: " + task.getTargetDate() +
                                           "\n\tDone: " + task.isDone() +
                                           "\n");
            }
        } else {
            System.out.println("No tasks yet!");
        }
    }

    private void showSingleTask() {
        System.out.println("Input ID of task to show: ");
        int id = scanner.nextInt(); //5
        scanner.nextLine();

        Task task = taskManager.getById(id);
        if (task == null) {
            System.out.println("No task is found with id " + id);
        } else {
            System.out.println("\t[" + task.getId() + "] " + task.getTitle() + " " +
                                       "\n\tDescription: " + task.getDescription() +
                                       "\n\tTarget date: " + task.getTargetDate() +
                                       "\n\tDone: " + task.isDone() +
                                       "\n");
        }

    }

    private void addNewTask() {
        System.out.println("Input unique ID (integer) for this task: ");
        int uniqueId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insert a title for your task: ");
        String title = scanner.nextLine();
        System.out.println("Provide a description of your task: ");
        String description = scanner.nextLine();
        System.out.println("Insert the target date for your task (yyyy-MM-dd): ");
        LocalDate targetDate = LocalDate.parse(scanner.nextLine());
        taskManager.add(new Task(uniqueId, title, description, targetDate, false));
    }

    private void deleteTask() {
        showAllTasks();
        System.out.println("Input the ID of task to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskManager.delete(id);
    }

    private void updateTask() {
        showAllTasks();
        System.out.println("Input the ID of task to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Is the task done? (accepted values are: true | false)");
        boolean done = scanner.nextBoolean();
        taskManager.update(id, done);
    }
}
