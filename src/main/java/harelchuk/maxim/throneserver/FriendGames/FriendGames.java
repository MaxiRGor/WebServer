package harelchuk.maxim.throneserver.FriendGames;

import javax.persistence.*;

@Entity
@Table(name = "friend_games")
public class FriendGames {

    public FriendGames() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_user_who")
    private int idUserWho;

    @Column(name = "status_who")
    private int statusWho;

    @Column(name = "number_who")
    private int numberWho;

    @Column(name = "id_user_to")
    private int idUserTo;

    @Column(name = "status_to")
    private int statusTo;

    @Column(name = "number_to")
    private int numberTo;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "cost")
    private long cost;

    @Column(name = "create_time")
    private long createTime;

    @Column(name = "game_status")
    private boolean gameStatus;

    public FriendGames(int idUserWho, int idUserTo, int difficulty, long cost, long createTime) {
        this.idUserWho = idUserWho;
        this.statusWho = 0;
        this.numberWho = 0;
        this.idUserTo = idUserTo;
        this.statusTo = 0;
        this.numberTo = 0;
        this.difficulty = difficulty;
        this.cost = cost;
        this.createTime = createTime;
        this.gameStatus = true;
    }

    public Integer getId() {
        return id;
    }

    public int getIdUserWho() {
        return idUserWho;
    }

    public int getStatusWho() {
        return statusWho;
    }

    public int getNumberWho() {
        return numberWho;
    }

    public void setNumberWho(int numberWho) {
        this.numberWho = numberWho;
    }

    public int getIdUserTo() {
        return idUserTo;
    }

    public int getStatusTo() {
        return statusTo;
    }

    public int getNumberTo() {
        return numberTo;
    }

    public void setNumberTo(int numberTo) {
        this.numberTo = numberTo;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public long getCost() {
        return cost;
    }

    public long getCreateTime() {
        return createTime;
    }


    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setStatusTo(int statusTo) {
        this.statusTo = statusTo;
    }

    public void setStatusWho(int statusWho) {
        this.statusWho = statusWho;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }
}
