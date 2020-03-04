package harelchuk.maxim.throneserver.User;

import javax.persistence.*;

@Entity
@Table(name = "users_multi_rating")
public class UsersMultiRating {
    public UsersMultiRating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "rating")
    private int rating;

    @Column(name = "rank")
    private int rank;

    @Column(name = "crystals")
    private int crystals;

    @Column(name = "games")
    private int games;

    @Column(name = "winnings")
    private int winnings;

    public UsersMultiRating(int idUser) {
        this.idUser = idUser;
        this.rating = 1;
        this.rank = 1;
        this.crystals = 10;
        this.games = 0;
        this.winnings = 0;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getRating() {
        return rating;
    }

    public int getRank() {
        return rank;
    }

    public int getCrystals() {
        return crystals;
    }

    public void subtractCrystals(int cost) {
        this.crystals -= cost;
    }

    public int getGames() {
        return games;
    }

    public int getWinnings() {
        return winnings;
    }

    public void addRating(int add) {
        this.rating += add;
    }

    public void subtractRating(int sub) {
        this.rating -= sub;
    }

    public void incrementRank() {
        ++this.rank;
    }

    public void decrementRank() {
        --this.rank;
    }

    public void addCrystals(int add) {
        this.crystals += add;
    }

    public void incrementGames() {
        ++this.games;
    }

    public void incrementWinningsAndGames() {
        ++this.winnings;
        ++this.games;
    }

    public Integer getId() {
        return id;
    }

/*    public void setId(Integer id) {
        this.id = id;
    }*/
}
