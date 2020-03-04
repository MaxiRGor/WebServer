package harelchuk.maxim.throneserver.FriendGames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface FriendGamesRepository extends JpaRepository<FriendGames, Integer> {
    FriendGames findFriendGamesById(int id);

    FriendGames findFriendGamesByCreateTimeAndIdUserWho(long time, int who);

    ArrayList<FriendGames> findAllByIdUserWhoOrIdUserTo(int myId, int myId2);

    ArrayList<FriendGames> findAll();
}
