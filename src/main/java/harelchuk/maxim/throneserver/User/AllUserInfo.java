package harelchuk.maxim.throneserver.User;

public class AllUserInfo {
    public AllUserInfo() {
    }

    private User user;
    private UsersMultiRating usersMultiRating;
    private UserSinglePoints userSinglePoints;
    private UserSkinsPurchases userSkinsPurchases;
    private UserIconsPurchases userIconsPurchases;

    public AllUserInfo(User user, UsersMultiRating usersMultiRating,
                       UserSinglePoints userSinglePoints, UserSkinsPurchases userSkinsPurchases,
                       UserIconsPurchases userIconsPurchases) {
        this.user = user;
        this.usersMultiRating = usersMultiRating;
        this.userSinglePoints = userSinglePoints;
        this.userSkinsPurchases = userSkinsPurchases;
        this.userIconsPurchases = userIconsPurchases;
    }

    public User getUser() {
        return user;
    }

    public UsersMultiRating getUsersMultiRating() {
        return usersMultiRating;
    }

    public UserSinglePoints getUserSinglePoints() {
        return userSinglePoints;
    }

    public UserSkinsPurchases getUserSkinsPurchases() {
        return userSkinsPurchases;
    }

    public UserIconsPurchases getUserIconsPurchases() {
        return userIconsPurchases;
    }

}
