package harelchuk.maxim.throneserver.Quests;

import harelchuk.maxim.throneserver.User.UserRepository;
import harelchuk.maxim.throneserver.User.UsersMultiRating;
import harelchuk.maxim.throneserver.User.UsersMultiRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quests")
public class QuestController {
    private final DailyQuestRepository dailyQuestRepository;
    private final UsersDailyQuestsRepository usersDailyQuestsRepository;
    private final UserRepository userRepository;
    private final UsersMultiRatingRepository usersMultiRatingRepository;

    @Autowired
    public QuestController(DailyQuestRepository dailyQuestRepository,
                           UsersDailyQuestsRepository usersDailyQuestsRepository,
                           UserRepository userRepository,
                           UsersMultiRatingRepository usersMultiRatingRepository) {
        this.dailyQuestRepository = dailyQuestRepository;
        this.usersDailyQuestsRepository = usersDailyQuestsRepository;
        this.userRepository = userRepository;
        this.usersMultiRatingRepository = usersMultiRatingRepository;
    }

    @GetMapping(path = "/daily/{uuid}")
    public @ResponseBody
    ArrayList<ContainerDailyQuest> getDailyQuests(@PathVariable("uuid") String uuid) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        List<DailyQuests> dailyQuests = dailyQuestRepository.findAll();
        List<UsersDailyQuests> usersDailyQuests = usersDailyQuestsRepository.findAllByUserIdOrderByQuestId(myId);
        ArrayList<ContainerDailyQuest> containerDailyQuests = new ArrayList<>();
        if (usersDailyQuests.size() == 0) {
            // usersDailyQuests = (List<UsersDailyQuests>) new UsersDailyQuests();
            for (DailyQuests quests : dailyQuests) {
                UsersDailyQuests usersQuest = new UsersDailyQuests(myId, quests.getId(), 0, false);
                ContainerDailyQuest containerDailyQuest = new ContainerDailyQuest(quests.getDescription(), quests.getGoal(),
                        quests.getRewardAmount(), quests.getRewardImageUrl(), quests.getRewardType(), 0, false);
                usersDailyQuests.add(usersQuest);
                containerDailyQuests.add(containerDailyQuest);
            }
            usersDailyQuestsRepository.saveAll(usersDailyQuests);
        } else {
            int questSize = dailyQuests.size();
            for (int i = 0; i < questSize; i++) {
                ContainerDailyQuest usersQuest = new ContainerDailyQuest(dailyQuests.get(i).getDescription(),
                        dailyQuests.get(i).getGoal(), dailyQuests.get(i).getRewardAmount(),
                        dailyQuests.get(i).getRewardImageUrl(), dailyQuests.get(i).getRewardType(),
                        usersDailyQuests.get(i).getProgress(), usersDailyQuests.get(i).isRewardTaken());
                containerDailyQuests.add(usersQuest);
            }
        }
        return containerDailyQuests;
    }

    @GetMapping(path = "daily/{uuid}/increment_progress/{quest_id}")
    public @ResponseBody
    boolean incrementDailyQuestProgress(@PathVariable("uuid") String uuid, @PathVariable("quest_id") int questId) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UsersDailyQuests usersDailyQuest = usersDailyQuestsRepository.findDistinctByUserIdAndQuestId(myId, questId);
        usersDailyQuest.incrementProgress();
        usersDailyQuestsRepository.save(usersDailyQuest);
        return true;
    }

    @GetMapping(path = "daily/{uuid}/set_checked/{quest_id}")
    public @ResponseBody
    boolean setDailyQuestChecked(@PathVariable("uuid") String uuid, @PathVariable("quest_id") int questId) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UsersDailyQuests usersDailyQuest = usersDailyQuestsRepository.findDistinctByUserIdAndQuestId(myId, questId);
        usersDailyQuest.setRewardTaken(true);
        int reward = dailyQuestRepository.findAll().get(questId).getRewardAmount();
        UsersMultiRating rating = usersMultiRatingRepository.findDistinctByIdUser(myId);
        rating.addCrystals(reward);
        usersDailyQuestsRepository.save(usersDailyQuest);
        usersMultiRatingRepository.save(rating);
        return true;
    }

    private byte[] getBytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

}
