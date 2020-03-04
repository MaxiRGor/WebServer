package harelchuk.maxim.throneserver.Leaderboard;

import javax.persistence.*;

@Entity
@Table(name = "leaderboard_new")
public class LeaderboardUser {

    public LeaderboardUser() {
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPlace() {
        return place;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getRating() {
        return rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "user_id")
    public int userId;


    @Column(name = "place")
    public int place;

    @Column(name = "icon")
    public int icon;

    @Column(name = "name")
    public String name;

    @Column(name = "rank")
    public int rank;

    @Column(name = "rating")
    public int rating;


    public LeaderboardUser(int userId, int place, int icon, String name, int rank, int rating) {
        this.userId = userId;
        this.place = place;
        this.icon = icon;
        this.name = name;
        this.rank = rank;
        this.rating = rating;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}

    /*
    @Column(name = "unique_number")
    public String uniqueNumber;

    @Column(name = "money")
    public int money;

   @Column(name = "crystals")
    public int crystals;

    @Column(name = "easy_percent")
    public int easyPercent;

    @Column(name = "medium_percent")
    public int mediumPercent;

    @Column(name = "hard_percent")
    public int hardPercent;

    @Column(name = "rating_percent")
    public int ratingPercent;*/


/*
    public LeaderboardUser(int userId, *//*String uniqueNumber, *//*int icon, String name,
                           int rank, int money, int crystals, int easyPercent,
                           int mediumPercent, int hardPercent, int ratingPercent) {
        this.userId = userId;
        //this.uniqueNumber = uniqueNumber;
        this.icon = icon;
        this.name = name;
        this.rank = rank;
        this.money = money;
        this.crystals = crystals;
        this.easyPercent = easyPercent;
        this.mediumPercent = mediumPercent;
        this.hardPercent = hardPercent;
        this.ratingPercent = ratingPercent;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getMoney() {
        return money;
    }

    public int getCrystals() {
        return crystals;
    }

    public int getEasyPercent() {
        return easyPercent;
    }

    public int getMediumPercent() {
        return mediumPercent;
    }

    public int getHardPercent() {
        return hardPercent;
    }

    public int getRatingPercent() {
        return ratingPercent;
    }*/