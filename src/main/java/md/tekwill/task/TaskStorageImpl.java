package md.tekwill.task;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskStorageImpl implements TaskStorage {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private final BaseDataSource dataSource;

    public TaskStorageImpl() {
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setUrl(CONNECTION_URL);
        this.dataSource.setUser(USERNAME);
        this.dataSource.setPassword(PASSWORD);
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();

        String selectSQL = "SELECT id, title, description, targetdate, done FROM task";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("targetDate");
                LocalDate targetDate = date.toLocalDate();
                boolean done = resultSet.getBoolean("done");

                tasks.add(new Task(id, title, description, targetDate, done));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return tasks;
    }

    @Override
    public Task findById(int idToFind) {
        Task task = null;
        String selectSQL = "SELECT id, title, description, targetdate, done FROM task where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, idToFind);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("targetDate");
                LocalDate targetDate = date.toLocalDate();
                boolean done = resultSet.getBoolean("done");

                task = new Task(id, title, description, targetDate, done);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return task;
    }

    @Override
    public void removeById(int id) {
        String deleteSQL = "DELETE FROM task WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);

            int nrOfAffectedRows = preparedStatement.executeUpdate();

            if (nrOfAffectedRows > 0) {
                System.out.println("Successfully deleted " + nrOfAffectedRows + " row(s)!");
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void updateDone(int id, boolean done) {
        String updateSQL = "UPDATE task SET done = ? WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setBoolean(1, done);
            preparedStatement.setInt(2, id);

            int nrOfAffectedRows = preparedStatement.executeUpdate();

            if (nrOfAffectedRows > 0) {
                System.out.println("Successfully updated " + nrOfAffectedRows + " row(s)!");
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void create(Task task) {
        String insertSQL = "INSERT INTO task(title, description, targetdate, done) VALUES ( ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getTargetDate()));
            preparedStatement.setBoolean(4, task.isDone());

            int row = preparedStatement.executeUpdate();
            System.out.println("Inserted " + row + " row(s)!");

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("Created task with " + generatedKeys.getInt(1) + " id ");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
