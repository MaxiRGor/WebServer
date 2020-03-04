package harelchuk.maxim.throneserver.Friends;

public class Friend {

    public Friend() {
    }

    private int id;
    private int icon;
    private String name;
    private long money;
    private int easyGames;

    private int easyWins;
    private int mediumGames;
    private int mediumWins;
    private int hardGames;
    private int hardWins;
    private int rank;
    private int crystals;
    private int ratingGames;
    private int ratingWins;

    public Friend(int id, int icon, String name, long money,
                  int easyGames, int easyWinnings, int mediumGames, int mediumWinnings, int hardGames, int hardWinnings,
                  int rank, int crystals, int ratingGames, int ratingWins) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.money = money;
        this.easyGames = easyGames;
        this.easyWins = easyWinnings;
        this.mediumGames = mediumGames;
        this.mediumWins = mediumWinnings;
        this.hardGames = hardGames;
        this.hardWins = hardWinnings;
        this.rank = rank;
        this.crystals = crystals;
        this.ratingGames = ratingGames;
        this.ratingWins = ratingWins;
    }

    public int getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public long getMoney() {
        return money;
    }

    public int getEasyGames() {
        return easyGames;
    }

    public int getEasyWins() {
        return easyWins;
    }

    public int getMediumGames() {
        return mediumGames;
    }

    public int getMediumWins() {
        return mediumWins;
    }

    public int getHardGames() {
        return hardGames;
    }

    public int getHardWins() {
        return hardWins;
    }

    public int getRank() {
        return rank;
    }

    public int getCrystals() {
        return crystals;
    }

    public int getRatingGames() {
        return ratingGames;
    }

    public int getRatingWins() {
        return ratingWins;
    }

}
