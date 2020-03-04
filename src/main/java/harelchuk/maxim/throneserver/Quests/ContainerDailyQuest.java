package harelchuk.maxim.throneserver.Quests;

public class ContainerDailyQuest {

    //private Integer id;
    private String description;
    private int goal;
    private int rewardAmount;
    private String rewardImageUrl;
    private String rewardType;
    private int progress;
    private boolean isRewardTaken;

    public ContainerDailyQuest(String description, int goal, int rewardAmount, String rewardImageUrl, String rewardType, int progress, boolean isRewardTaken) {
        this.description = description;
        this.goal = goal;
        this.rewardAmount = rewardAmount;
        this.rewardImageUrl = rewardImageUrl;
        this.rewardType = rewardType;
        this.progress = progress;
        this.isRewardTaken = isRewardTaken;
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

    public int getProgress() {
        return progress;
    }

    public boolean isRewardTaken() {
        return isRewardTaken;
    }

}
