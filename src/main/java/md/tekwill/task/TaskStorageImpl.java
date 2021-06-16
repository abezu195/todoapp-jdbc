package md.tekwill.task;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class TaskStorageImpl implements TaskStorage {
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private int idCounter = 1;

    private final BaseDataSource dataSource;

    public TaskStorageImpl() {
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setUrl(CONNECTION_URL);
        this.dataSource.setUser(USERNAME);
        this.dataSource.setPassword(PASSWORD);
    }

    @Override
    public Task[] findAll() {
        return new Task[0];
    }

    @Override
    public Task findById(int id) {
        return null;
    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public void create(Task task) {
        final String insertSQL = "INSERT INTO task(id, title, description, targetdate, done) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, idCounter++);
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getTargetDate());
            preparedStatement.setBoolean(5, task.isDone());
            int row = preparedStatement.executeUpdate();
            System.out.println("Created task with " + (idCounter-1) + " id ");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
