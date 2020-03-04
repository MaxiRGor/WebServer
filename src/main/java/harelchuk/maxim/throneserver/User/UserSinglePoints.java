package harelchuk.maxim.throneserver.User;

import javax.persistence.*;

@Entity
@Table(name = "users_single_points")
public class UserSinglePoints {
    public UserSinglePoints() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "max_points")
    private int maxPoints;

    @Column(name = "points")
    private int points;

    @Column(name = "time_when_full")
    private long timeWhenFull;

    @Column(name = "time_to_full")
    private int timeToFullInSeconds;

    public UserSinglePoints(int userId) {
        this.userId = userId;
        this.maxPoints = 10;
        this.points = 10;
        this.timeWhenFull = 0;
        this.timeToFullInSeconds = 0;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public int getPoints() {
        return points;
    }

    public long getTimeWhenFull() {
        return timeWhenFull;
    }

    void setPoints(int points) {
        this.points = points;
    }

    void setTimeWhenFull(long timeWhenFull) {
        this.timeWhenFull = timeWhenFull;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getTimeToFullInSeconds() {
        return timeToFullInSeconds;
    }

    public void setTimeToFullInSeconds(int timeToFullInSeconds) {
        this.timeToFullInSeconds = timeToFullInSeconds;
    }
}
