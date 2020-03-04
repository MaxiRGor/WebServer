package harelchuk.maxim.throneserver.GameVariables;

import javax.persistence.*;

@Entity
@Table(name = "game_variables")
public class GameVariables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "single_difficulty_costs")
    private int singleDifficultyCosts;

    @Column(name = "multi_rating_costs")
    private int multiRatingCost;

    @Column(name = "multi_rating_min")
    private int multiRatingMin;

    @Column(name = "single_difficulty_rewards")
    private int singleDifficultyRewards;

    @Column(name = "theme_costs")
    private int themeCosts;

    @Column(name = "icons_costs")
    private int iconsCosts;

    @Column(name = "energy_time_to_refill")
    private int energyTimeToRefill;

    @Column(name = "crystals_for_full")
    private int crystalsForFullEnergy;

    public Integer getId() {
        return id;
    }

    public int getSingleDifficultyCosts() {
        return singleDifficultyCosts;
    }

    public int getMultiRatingCost() {
        return multiRatingCost;
    }

    public int getMultiRatingMin() {
        return multiRatingMin;
    }

    public int getSingleDifficultyRewards() {
        return singleDifficultyRewards;
    }

    public int getThemeCosts() {
        return themeCosts;
    }

    public int getIconsCosts() {
        return iconsCosts;
    }

    public int getEnergyTimeToRefill() {
        return energyTimeToRefill;
    }

    public int getCrystalsForFullEnergy() {
        return crystalsForFullEnergy;
    }
}
