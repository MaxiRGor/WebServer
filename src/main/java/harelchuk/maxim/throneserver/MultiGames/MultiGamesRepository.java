package harelchuk.maxim.throneserver.MultiGames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MultiGamesRepository extends JpaRepository<MultiGames, Integer> {
    ArrayList<MultiGames> findAllByGameRankInAndUserFirstIdNotAndUserSecondStatusOrderByCreateTimeDesc(
            int[] ranges, int idNot, int secondStatus);

    MultiGames findDistinctById(int id);

    ArrayList<MultiGames> findAllByUserFirstIdOrUserSecondIdOrderByCreateTimeDesc(int myId, int myId2);

    ArrayList<MultiGames> findAll();
}
