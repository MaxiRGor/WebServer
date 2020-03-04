package harelchuk.maxim.throneserver.Quests;

import javax.persistence.*;

@Entity
@Table(name = "users_daily_quests")
public class UsersDailyQuests {
    public UsersDailyQuests() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "quest_id")
    private int questId;

    @Column(name = "progress")
    private int progress;

    @Column(name = "is_reward_taken")
    private boolean isRewardTaken;

    public UsersDailyQuests(int userId, int questId, int progress, boolean isRewardTaken) {
        this.userId = userId;
        this.questId = questId;
        this.progress = progress;
        this.isRewardTaken = isRewardTaken;
    }


    public int getProgress() {
        return progress;
    }

    public boolean isRewardTaken() {
        return isRewardTaken;
    }

    public void setRewardTaken(boolean rewardTaken) {
        isRewardTaken = rewardTaken;
    }

    public void incrementProgress() {
        ++this.progress;
    }
}
