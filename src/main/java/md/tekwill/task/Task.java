package md.tekwill.task;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final String targetDate;
    private boolean done;


    public Task(int id, String title, String when, String targetDate) {
        this.id = id;
        this.title = title;
        this.description = when;
        this.targetDate = targetDate;
        this.done = false;
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

    public String getTargetDate() {
        return targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
