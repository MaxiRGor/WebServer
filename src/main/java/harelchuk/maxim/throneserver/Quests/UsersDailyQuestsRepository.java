package harelchuk.maxim.throneserver.Quests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDailyQuestsRepository extends JpaRepository<UsersDailyQuests, Integer> {

    List<UsersDailyQuests> findAllByUserIdOrderByQuestId(int userId);

    UsersDailyQuests findDistinctByUserIdAndQuestId(int userId, int questId);
}
