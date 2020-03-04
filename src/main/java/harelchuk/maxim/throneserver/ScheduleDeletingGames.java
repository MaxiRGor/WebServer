package harelchuk.maxim.throneserver;

import harelchuk.maxim.throneserver.FriendGames.FriendGames;
import harelchuk.maxim.throneserver.FriendGames.FriendGamesRepository;
import harelchuk.maxim.throneserver.MultiGames.MultiGames;
import harelchuk.maxim.throneserver.MultiGames.MultiGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class ScheduleDeletingGames {

    private MultiGamesRepository multiGamesRepository;
    private FriendGamesRepository friendGamesRepository;

    @Autowired
    ScheduleDeletingGames(MultiGamesRepository multiGamesRepository, FriendGamesRepository friendGamesRepository) {
        this.multiGamesRepository = multiGamesRepository;
        this.friendGamesRepository = friendGamesRepository;
    }

    @Scheduled(fixedRate = 1200000)       //1200000 20 min
    public void deletingGames() {
        long yesterday = new Date().getTime() - 86400000;
        ArrayList<MultiGames> multiGames = multiGamesRepository.findAll();
        ArrayList<Integer> multiIds = new ArrayList<>();
        for (MultiGames multiGame : multiGames) {
            if (multiGame.getCreateTime() < yesterday) {
                multiIds.add(multiGame.getId());
            }
        }
        for (Integer id : multiIds) {
            multiGamesRepository.deleteById(id);
        }

        ArrayList<FriendGames> friendGames = friendGamesRepository.findAll();
        ArrayList<Integer> friendIds = new ArrayList<>();
        for (FriendGames friendGame : friendGames) {
            if (friendGame.getCreateTime() < yesterday) {
                friendIds.add(friendGame.getId());
            }
        }
        for (Integer id : friendIds) {
            friendGamesRepository.deleteById(id);
        }
    }
}
