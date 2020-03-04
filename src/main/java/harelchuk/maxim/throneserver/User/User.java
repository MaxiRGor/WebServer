package harelchuk.maxim.throneserver.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;


    @Column(name = "uuid_bytes")
    private byte[] uuidBytes;

    @Column(name = "unique_number")
    private String uniqueNumber;

    @Column(name = "uuid")
    private String uuidString;

    @Column(name = "currentIcon")
    private int currentIcon;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_money")
    private long money;

    @Column(name = "easy_games")
    private int easyGames;

    @Column(name = "medium_games")
    private int mediumGames;

    @Column(name = "hard_games")
    private int hardGames;

    @Column(name = "easy_winnings")
    private int easyWinnings;

    @Column(name = "medium_winnings")
    private int mediumWinnings;

    @Column(name = "hard_winnings")
    private int hardWinnings;

    @Column(name = "is_adv")
    private boolean adv;

    @Column(name = "is_books")
    private boolean books;

    @Column(name = "is_films")
    private boolean films;

    @Column(name = "current_theme")
    private int currentTheme;

    @Column(name = "is_credit")
    private boolean credit;

    @Column(name = "credit_time")
    private long creditTime;

    @Column(name = "credit_sum")
    private long creditSum;

    @Column(name = "is_debit")
    private boolean debit;

    @Column(name = "debit_time")
    private long debitTime;

    @Column(name = "debit_sum")
    private long debitSum;

    User(String uniqueNumber, byte[] uuid_bytes, String uuid, int currentTheme) {

        //id auto

        this.uuidBytes = uuid_bytes;

        this.uuidString = uuid;

        this.uniqueNumber = uniqueNumber;

        this.currentIcon = 0;

        this.name = "";

        this.money = 10;

        this.easyGames = 0;

        this.mediumGames = 0;

        this.hardGames = 0;

        this.easyWinnings = 0;

        this.mediumWinnings = 0;

        this.hardWinnings = 0;

        this.adv = true;

        this.books = false;

        this.films = true;

        this.currentTheme = currentTheme;

        this.credit = false;

        this.creditTime = 0;

        this.creditSum = 0;

        this.debit = false;

        this.debitTime = 0;

        this.debitSum = 0;
    }


    //getters

    public Integer getId() {
        return id;
    }

    public byte[] getUuidBytes() {
        return uuidBytes;
    }

    public String getUuidString() {
        return this.uuidString;
    }

    public String getUniqueNumber() {
        return this.uniqueNumber;
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

    public int getMediumGames() {
        return mediumGames;
    }

    public int getHardGames() {
        return hardGames;
    }

    public int getEasyWinnings() {
        return easyWinnings;
    }

    public int getMediumWinnings() {
        return mediumWinnings;
    }

    public int getHardWinnings() {
        return hardWinnings;
    }

    public boolean isAdv() {
        return adv;
    }

    public boolean isBooks() {
        return books;
    }

    public boolean isFilms() {
        return films;
    }

    public int getCurrentTheme() {
        return currentTheme;
    }

    public boolean isCredit() {
        return credit;
    }

    public long getCreditTime() {
        return creditTime;
    }

    public long getCreditSum() {
        if (!this.credit) return creditSum;
        else return updatedCreditSum(creditSum);
    }

    public boolean isDebit() {
        return debit;
    }

    public long getDebitTime() {
        return debitTime;
    }

    public long getDebitSum() {
        if (!this.debit) return debitSum;
        else return updatedDebitSum(debitSum);
    }

    //setters

    void setName(String name) {
        this.name = name;
    }

    public void addUser_money(long add) {
        this.money += add;
    }

    public void subtractUser_money(long subtract) {
        this.money -= subtract;
    }

    public void updateEasy_loses() {
        ++this.easyGames;
    }

    public void updateEasy_winnings(long add) {
        ++this.easyWinnings;
        ++this.easyGames;
        this.money += add;
    }

    public void updateMedium_loses() {
        ++this.mediumGames;
    }

    public void updateMedium_winnings(long add) {
        ++this.mediumWinnings;
        ++this.mediumGames;
        this.money += add;
    }

    public void updateHard_loses() {
        ++this.hardGames;
    }

    public void updateHard_winnings(long add) {
        ++this.hardWinnings;
        ++this.hardGames;
        this.money += add;
    }


    void removeAdv() {
        this.adv = false;
    }


    void setBooks(boolean books) {
        this.books = books;
    }


    void setFilms(boolean films) {
        this.films = films;
    }

    void setCurrentTheme(int currentTheme) {
        this.currentTheme = currentTheme;
    }

    void getCredit(long get) {
        this.money += get;
        this.credit = true;
        this.creditTime = new Date().getTime();
        this.creditSum = (long) (get * 1.07);
    }

    void returnCredit() {
        if (this.credit) {
            this.creditSum = this.updatedCreditSum(this.creditSum);
            if (this.money >= this.creditSum) {
                this.money -= this.creditSum;
                this.credit = false;
                this.creditSum = 0;
                this.creditTime = 0;
            }
        }
    }

    private long updatedCreditSum(long credit_sum) {
        long creditSum = credit_sum;
        if (this.credit) {
            long time_now = new Date().getTime();
            if ((time_now - this.creditTime) > (21600000)) {
                while ((time_now - this.creditTime) > (21600000)) {
                    creditSum *= 1.07;
                    this.creditTime += 21600000;
                }
            }
        }
        return creditSum;
    }

    void addDebit(long add) {
        this.money -= add;
        if (this.debit) this.debitSum = updatedDebitSum(this.debitSum);
        this.debitSum += add;
        this.debit = true;
        this.debitTime = new Date().getTime();
    }

    void returnDebit() {
        this.money += updatedDebitSum(this.debitSum);
        this.debitSum = 0;
        this.debit = false;
        this.debitTime = 0;
    }

    private long updatedDebitSum(long debit_sum) {
        long debitSum = debit_sum;
        if (this.debit) {
            long time_now = new Date().getTime();
            if ((time_now - this.debitTime) > (21600000)) {
                while ((time_now - this.debitTime) > (21600000)) {
                    debitSum *= 1.05;
                    this.debitTime += 21600000;
                }
            }
        }
        return debitSum;
    }

    public int getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(int currentIcon) {
        this.currentIcon = currentIcon;
    }
}
