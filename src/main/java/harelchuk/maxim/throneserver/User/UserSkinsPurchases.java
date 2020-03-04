package harelchuk.maxim.throneserver.User;

import javax.persistence.*;

@Entity
@Table(name = "users_skins_purchases")
public class UserSkinsPurchases {
    public UserSkinsPurchases() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "skin_0_t")
    private boolean purchasedSkinTarg;

    @Column(name = "skin_1_s")
    private boolean purchasedSkinStark;

    @Column(name = "skin_2_l")
    private boolean purchasedSkinLann;

    @Column(name = "skin_3_n")
    private boolean purchasedSkinNight;

    public UserSkinsPurchases(int userId, int firstChosenTheme) {
        this.userId = userId;
        this.purchasedSkinTarg = false;
        this.purchasedSkinStark = false;
        this.purchasedSkinLann = false;
        this.purchasedSkinNight = false;
        switch (firstChosenTheme) {
            case 0:
                this.purchasedSkinTarg = true;
                break;
            case 1:
                this.purchasedSkinStark = true;
                break;
            case 2:
                this.purchasedSkinLann = true;
                break;
            case 3:
                this.purchasedSkinNight = true;
                break;
        }
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPurchasedSkinTarg() {
        return purchasedSkinTarg;
    }


    public boolean isPurchasedSkinStark() {
        return purchasedSkinStark;
    }


    public boolean isPurchasedSkinLann() {
        return purchasedSkinLann;
    }


    public boolean isPurchasedSkinNight() {
        return purchasedSkinNight;
    }

    void setSkinPurchased(int number) {
        switch (number) {
            case 0:
                this.purchasedSkinTarg = true;
                break;
            case 1:
                this.purchasedSkinStark = true;
                break;
            case 2:
                this.purchasedSkinLann = true;
                break;
            case 3:
                this.purchasedSkinNight = true;
                break;
        }
    }


}
