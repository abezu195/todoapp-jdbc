package md.tekwill.task;

import java.time.LocalDate;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final LocalDate targetDate;
    private boolean done;


    public Task(int id, String title, String when, LocalDate targetDate, boolean done) {
        this.id = id;
        this.title = title;
        this.description = when;
        this.targetDate = targetDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
