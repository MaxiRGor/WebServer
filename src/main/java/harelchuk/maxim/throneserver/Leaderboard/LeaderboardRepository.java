package harelchuk.maxim.throneserver.Leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface LeaderboardRepository extends JpaRepository<LeaderboardUser, Integer> {

    //ArrayList<LeaderboardUser> findAll();

    @Query(value = "SELECT * from leaderboard_new limit 100", nativeQuery = true)
    ArrayList<LeaderboardUser> findOneHundred();

    LeaderboardUser findDistinctByUserId(int id);

}
