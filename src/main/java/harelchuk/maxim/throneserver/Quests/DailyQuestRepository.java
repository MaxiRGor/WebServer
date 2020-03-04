package harelchuk.maxim.throneserver.Quests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuestRepository extends JpaRepository<DailyQuests, Integer> {
}
