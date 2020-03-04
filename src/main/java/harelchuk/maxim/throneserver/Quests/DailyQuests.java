package harelchuk.maxim.throneserver.Quests;

import javax.persistence.*;

@Entity
@Table(name = "daily_quests")
public class DailyQuests {

    public DailyQuests() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "goal")
    private int goal;

    @Column(name = "reward_amount")
    private int rewardAmount;

    @Column(name = "reward_image_type")
    private String rewardImageUrl;

    @Column(name = "reward_type")
    private String rewardType;


    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getGoal() {
        return goal;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public String getRewardImageUrl() {
        return rewardImageUrl;
    }

    public String getRewardType() {
        return rewardType;
    }
}
