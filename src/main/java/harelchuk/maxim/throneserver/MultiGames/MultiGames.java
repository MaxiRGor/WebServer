package harelchuk.maxim.throneserver.MultiGames;

import javax.persistence.*;


@Entity
@Table(name = "multi_games")
public class MultiGames {

    public MultiGames() {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_range")
    private int gameRank;

    @Column(name = "create_time")
    private long createTime;

    @Column(name = "user_first_id")
    private int userFirstId;

    @Column(name = "user_first_unique")
    private String userFirstUnique;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_fisrt_status")
    private int userFirstStatus;

    @Column(name = "user_first_count")
    private int userFirstCount;

    @Column(name = "user_second_id")
    private int userSecondId;

    @Column(name = "user_second_unique")
    private String userSecondUnique;

    @Column(name = "user_second_name")
    private String userSecondName;

    @Column(name = "user_second_status")
    private int userSecondStatus;

    @Column(name = "user_second_count")
    private int userSecondCount;

    public MultiGames(int gameRank, long createTime, int userFirstId, String userFirstUnique, String userFirstName) {
        this.gameRank = gameRank;
        this.createTime = createTime;
        this.userFirstId = userFirstId;
        this.userFirstUnique = userFirstUnique;
        this.userFirstName = userFirstName;
        this.userFirstStatus = 1;   //in play
        this.userFirstCount = 0;
        this.userSecondId = 0;
        this.userSecondStatus = 0;  //not played
        this.userSecondCount = 0;
        this.userSecondUnique = "";
        this.userSecondName = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGameRank() {
        return gameRank;
    }

    public void setGameRank(int gameRank) {
        this.gameRank = gameRank;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(int userFirstId) {
        this.userFirstId = userFirstId;
    }

    public String getUserFirstUnique() {
        return userFirstUnique;
    }

    public void setUserFirstUnique(String userFirstUnique) {
        this.userFirstUnique = userFirstUnique;
    }

    public int getUserFirstStatus() {
        return userFirstStatus;
    }

    public void setUserFirstStatus(int userFirstStatus) {
        this.userFirstStatus = userFirstStatus;
    }

    public int getUserFirstCount() {
        return userFirstCount;
    }

    public void setUserFirstCount(int userFirstCount) {
        this.userFirstCount = userFirstCount;
    }

    public int getUserSecondId() {
        return userSecondId;
    }

    public void setUserSecondId(int userSecondId) {
        this.userSecondId = userSecondId;
    }

    public String getUserSecondUnique() {
        return userSecondUnique;
    }

    public void setUserSecondUnique(String userSecondUnique) {
        this.userSecondUnique = userSecondUnique;
    }

    public int getUserSecondStatus() {
        return userSecondStatus;
    }

    public void setUserSecondStatus(int userSecondStatus) {
        this.userSecondStatus = userSecondStatus;
    }

    public int getUserSecondCount() {
        return userSecondCount;
    }

    public void setUserSecondCount(int userSecondCount) {
        this.userSecondCount = userSecondCount;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }
}

