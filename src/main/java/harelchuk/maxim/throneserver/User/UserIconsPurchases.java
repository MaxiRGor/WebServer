package harelchuk.maxim.throneserver.User;

import javax.persistence.*;

@Entity
@Table(name = "users_icons_purchases")
public class UserIconsPurchases {
    public UserIconsPurchases() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ic_0")
    private boolean ic0;

    @Column(name = "ic_1")
    private boolean ic1;

    @Column(name = "ic_2")
    private boolean ic2;

    @Column(name = "ic_3")
    private boolean ic3;

    @Column(name = "ic_4")
    private boolean ic4;

    @Column(name = "ic_5")
    private boolean ic5;

    @Column(name = "ic_6")
    private boolean ic6;

    @Column(name = "ic_7")
    private boolean ic7;

    @Column(name = "ic_8")
    private boolean ic8;

    @Column(name = "ic_9")
    private boolean ic9;

    @Column(name = "ic_10")
    private boolean ic10;

    @Column(name = "ic_11")
    private boolean ic11;

    @Column(name = "ic_12")
    private boolean ic12;

    @Column(name = "ic_13")
    private boolean ic13;

    @Column(name = "ic_14")
    private boolean ic14;

    @Column(name = "ic_15")
    private boolean ic15;

    @Column(name = "ic_16")
    private boolean ic16;

    public UserIconsPurchases(int userId) {
        this.userId = userId;
        this.ic0 = true;
        this.ic1 = false;
        this.ic2 = false;
        this.ic3 = false;
        this.ic4 = false;
        this.ic5 = false;
        this.ic6 = false;
        this.ic7 = false;
        this.ic8 = false;
        this.ic9 = false;
        this.ic10 = false;
        this.ic11 = false;
        this.ic12 = false;
        this.ic13 = false;
        this.ic14 = false;
        this.ic15 = false;
        this.ic16 = false;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isIc0() {
        return ic0;
    }

    public boolean isIc1() {
        return ic1;
    }

    public boolean isIc2() {
        return ic2;
    }

    public boolean isIc3() {
        return ic3;
    }

    public boolean isIc4() {
        return ic4;
    }

    public boolean isIc5() {
        return ic5;
    }

    public boolean isIc6() {
        return ic6;
    }

    public boolean isIc7() {
        return ic7;
    }

    public boolean isIc8() {
        return ic8;
    }

    public boolean isIc9() {
        return ic9;
    }

    public boolean isIc10() {
        return ic10;
    }

    public boolean isIc11() {
        return ic11;
    }

    public boolean isIc12() {
        return ic12;
    }

    public boolean isIc13() {
        return ic13;
    }

    public boolean isIc14() {
        return ic14;
    }

    public boolean isIc15() {
        return ic15;
    }

    public boolean isIc16() {
        return ic16;
    }


    public void setIconPurchased(int number) {
        switch (number) {
            case 0:
                this.ic0 = true;
                break;
            case 1:
                this.ic1 = true;
                break;
            case 2:
                this.ic2 = true;
                break;
            case 3:
                this.ic3 = true;
                break;
            case 4:
                this.ic4 = true;
                break;
            case 5:
                this.ic5 = true;
                break;
            case 6:
                this.ic6 = true;
                break;
            case 7:
                this.ic7 = true;
                break;
            case 8:
                this.ic8 = true;
                break;
            case 9:
                this.ic9 = true;
                break;
            case 10:
                this.ic10 = true;
                break;
            case 11:
                this.ic11 = true;
                break;
            case 12:
                this.ic12 = true;
                break;
            case 13:
                this.ic13 = true;
                break;
            case 14:
                this.ic14 = true;
                break;
            case 15:
                this.ic15 = true;
                break;
            case 16:
                this.ic16 = true;
                break;
        }
    }
}
