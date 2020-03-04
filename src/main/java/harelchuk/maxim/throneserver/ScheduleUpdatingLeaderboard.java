package harelchuk.maxim.throneserver;

import harelchuk.maxim.throneserver.Leaderboard.LeaderboardRepository;
import harelchuk.maxim.throneserver.Leaderboard.LeaderboardUser;
import harelchuk.maxim.throneserver.User.User;
import harelchuk.maxim.throneserver.User.UserRepository;
import harelchuk.maxim.throneserver.User.UsersMultiRating;
import harelchuk.maxim.throneserver.User.UsersMultiRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ScheduleUpdatingLeaderboard {

    private UserRepository userRepository;
    private UsersMultiRatingRepository multiRatingRepository;
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    ScheduleUpdatingLeaderboard(UserRepository userRepository,
                                LeaderboardRepository leaderboardRepository,
                                UsersMultiRatingRepository multiRatingRepository) {
        this.userRepository = userRepository;
        this.leaderboardRepository = leaderboardRepository;
        this.multiRatingRepository = multiRatingRepository;
    }

    @Scheduled(fixedRate = 60000)       //3600000 60 min // 60000 1 min
    public void updateLeaderboard() {
        List<User> users = userRepository.findAll();
        ArrayList<UsersMultiRating> ratings = multiRatingRepository.findAll();
        List<LeaderboardUser> leaderboardUsers = new ArrayList<>();

        users.sort(Comparator.comparing(User::getId));
        ratings.sort(Comparator.comparing(UsersMultiRating::getIdUser));

        leaderboardRepository.deleteAll();

        for (int i = 0; i < ratings.size(); i++) {
            if (users.get(i).getId() == ratings.get(i).getIdUser()) {
                if (ratings.get(i).getRating() > 1) {
                    LeaderboardUser leaderboardUser = new LeaderboardUser(users.get(i).getId(), 0, users.get(i).getCurrentIcon(),
                            users.get(i).getName(), ratings.get(i).getRank(), ratings.get(i).getRating());
                    leaderboardUsers.add(leaderboardUser);
                }
            } else System.out.println("shit happened in ScheduleUpdatingLeaderboard");
        }
        leaderboardUsers.sort(Comparator.comparing(LeaderboardUser::getRating).reversed());
        for (int i = 0; i < leaderboardUsers.size(); i++) {
            leaderboardUsers.get(i).setPlace(i + 1);
        }
        leaderboardRepository.saveAll(leaderboardUsers);
    }
}


/*        int numberOfLeaders = 100;
        if (numberOfLeaders > ratings.size())
            numberOfLeaders = ratings.size();*/

/*            int easyPercent = 0;
            int mediumPercent = 0;
            int hardPercent = 0;
            int ratingPercent = 0;

            if (user.getEasyGames() != 0) easyPercent = 100 * user.getEasyWinnings() / user.getEasyGames();
            if (user.getMediumGames() != 0) mediumPercent = 100 * user.getMediumWinnings() / user.getMediumGames();
            if (user.getHardGames() != 0) hardPercent = 100 * user.getHardWinnings() / user.getHardGames();
            if (rating.getGames() != 0) ratingPercent = 100 * rating.getWinnings() / rating.getGames();*/

//old version using only coins by difficult formula
/*
    private UserRepository userRepository;

    private LeaderboardRepository leaderboardRepository;

    @Autowired
    ScheduleUpdatingLeaderboard(UserRepository userRepository,
                                LeaderboardRepository leaderboardRepository) {
        this.userRepository = userRepository;
        this.leaderboardRepository = leaderboardRepository;
    }

    @Scheduled(fixedRate = 21600000)       //1200000 20 min //6 hours
    public void updateLeaderboard() {

        ArrayList<User> users = userRepository
                .findUsersByCreditFalseAndEasyWinningsGreaterThanAndMediumWinningsGreaterThanAndHardWinningsGreaterThanAndMoneyGreaterThan
                        (0, 0, 0, 0);
        List<UserRating> userRatings = new ArrayList<>();

        if (users.size() > 0) {
            for (User user : users) {
                UserRating userRating = new UserRating(user.getId(), user.getMoney(),
                        user.getHardWinnings(),
                        user.getHardGames(),
                        user.getMediumWinnings(),
                        user.getMediumGames(),
                        user.getEasyWinnings(),
                        user.getEasyGames());
                userRatings.add(userRating);
            }

            Collections.sort(userRatings);

            int numberOfLeaders = 1000;
            if (numberOfLeaders > userRatings.size())
                numberOfLeaders = userRatings.size();

            leaderboardRepository.deleteAll();

            //leaderboardRepository.truncate;

            for (int i = 0; i < numberOfLeaders; i++) {
                LeaderboardUser leaderboardIds = new LeaderboardUser(userRatings.get(i).getId());
                leaderboardRepository.save(leaderboardIds);
            }
        }
    }

    public class UserRating implements Comparable<UserRating> {
        int id;
        double rating;

        UserRating(int id, long money, int hw, int hg, int mw, int mg, int ew, int eg) {
            this.id = id;
            this.rating = money * ((double) hw / (double) hg * 0.6
                    + (double) mw / (double) mg * 0.3
                    + (double) ew / (double) eg * 0.1) / 100;
        }

        double getRating() {
            return rating;
        }

        public int getId() {
            return id;
        }

        @Override
        public int compareTo(UserRating o) {
            return (int) (o.getRating() - this.getRating());
        }



        }


        for (LeaderboardUser leaderboardUser : leaderboardUsers) {
            System.out.println(leaderboardUser.getRating());
        }

        /*        for (int i = 0; i < ratings.size(); i++) {
            User user = userRepository.getById(ratings.get(i).getIdUser());
            LeaderboardUser leaderboardUser = new LeaderboardUser(user.getId(), i + 1, user.getCurrentIcon(),
                    user.getName(), ratings.get(i).getRank(), ratings.get(i).getRating());
            leaderboardRepository.save(leaderboardUser);
        }
    */

