package md.tekwill.main;

import md.tekwill.console.ConsoleMenu;
import md.tekwill.task.TaskManager;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        ConsoleMenu menu = new ConsoleMenu(taskManager, scanner);

        menu.run();
    }
}
