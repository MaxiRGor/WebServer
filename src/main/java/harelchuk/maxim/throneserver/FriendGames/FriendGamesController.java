package harelchuk.maxim.throneserver.FriendGames;

import harelchuk.maxim.throneserver.MatchGameQuestions.MatchGameQuestions;
import harelchuk.maxim.throneserver.MatchGameQuestions.MatchGameQuestionsRepository;
import harelchuk.maxim.throneserver.Question.LocaledQuestion;
import harelchuk.maxim.throneserver.Question.Question;
import harelchuk.maxim.throneserver.Question.QuestionController;
import harelchuk.maxim.throneserver.Question.QuestionRepository;
import harelchuk.maxim.throneserver.User.User;
import harelchuk.maxim.throneserver.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.*;

import static harelchuk.maxim.throneserver.Question.QuestionController.switchLocale;

@RestController
@RequestMapping("/games/friend")
public class FriendGamesController {
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private FriendGamesRepository friendGamesRepository;
    private MatchGameQuestionsRepository matchGameQuestionsRepository;

    @Autowired
    FriendGamesController(UserRepository userRepository,
                          QuestionRepository questionRepository,
                          FriendGamesRepository friendGamesRepository,
                          MatchGameQuestionsRepository matchGameQuestionsRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.friendGamesRepository = friendGamesRepository;
        this.matchGameQuestionsRepository = matchGameQuestionsRepository;
    }


    @GetMapping("/create/{uuid}/{friend_id}/{difficulty}/{cost}")
    public boolean createFriendGame(@PathVariable String uuid,
                                    @PathVariable int friend_id,
                                    @PathVariable int difficulty,
                                    @PathVariable long cost) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        long time = new Date().getTime();
        FriendGames game = new FriendGames(myId, friend_id, difficulty, cost, time);
        friendGamesRepository.save(game);

        boolean inBook = userRepository.getById(friend_id).isBooks() & userRepository.getById(myId).isBooks();
        boolean inSerial = userRepository.getById(friend_id).isFilms() & userRepository.getById(myId).isFilms();

        ArrayList<Integer> questionIds;
        if (inBook && inSerial) {
            questionIds = questionRepository.findManyByDifficulty(difficulty);
        } else {
            if (inBook) {
                questionIds = questionRepository.findManyByDifficultyBook(difficulty, true);
            } else {
                questionIds = questionRepository.findManyByDifficultySerial(difficulty, true);
            }
        }
        int[] ids = getSevenQuestionIds(questionIds);
        int gameId = friendGamesRepository.findFriendGamesByCreateTimeAndIdUserWho(time, myId).getId();

        ArrayList<MatchGameQuestions> openGames = new ArrayList<>();
        for (int id : ids) {
            openGames.add(new MatchGameQuestions(gameId, true, id));
        }
        matchGameQuestionsRepository.saveAll(openGames);
        return true;
    }


    @GetMapping("/get/{uuid}")
    public @ResponseBody
    ArrayList<FriendGames> getFriendGames(@PathVariable String uuid) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<FriendGames> friendGames = friendGamesRepository.findAllByIdUserWhoOrIdUserTo(myId, myId);
        ArrayList<FriendGames> suitable = new ArrayList<>();
        for (FriendGames friendGame : friendGames) {
            if (friendGame.getIdUserWho() == myId) {                  // IM WHO
                if (friendGame.getStatusWho() != 2 && friendGame.getStatusWho() != 6) {
                    suitable.add(friendGame);                       // I DIDN'T HIDE OR DECLINED
                }
            } else {                                                          // IM TO
                if (friendGame.getStatusTo() != 2 && friendGame.getStatusTo() != 6) {
                    suitable.add(friendGame);                       // I DIDN'T HIDE OR DECLINED
                }
            }
        }
        return suitable;
    }

    @GetMapping("/enter/{uuid}/{id_game}/{is_russian}")
    public @ResponseBody
    List<LocaledQuestion> enterFriendGame(@PathVariable String uuid,
                                          @PathVariable int id_game,
                                          @PathVariable boolean is_russian) {
        User user = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        int myId = user.getId();
        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        user.subtractUser_money(friendGame.getCost());
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return null;
        ArrayList<MatchGameQuestions> match = matchGameQuestionsRepository.findAllByIdGameAndGameType(id_game, true);
        int[] ids = new int[match.size()];
        for (int i = 0; i < match.size(); i++) {
            ids[i] = match.get(i).getIdQuestion();
        }
        userRepository.save(user);
        return getLocaledQuestions(questionRepository.findAllByIdIn(ids), is_russian);
    }

    @GetMapping("/update/{uuid}/{id_game}/{number}")
    public boolean updateFriendGameAnswersCountAndSelectWinner(@PathVariable String uuid,
                                                               @PathVariable int id_game,
                                                               @PathVariable int number) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return false;

        if (myId == friendGame.getIdUserTo()) {
            friendGame.setStatusTo(1);
            friendGame.setNumberTo(number);
            if (friendGame.getStatusWho() != 0) {
                friendGame.setGameStatus(false);
            }
        } else {
            friendGame.setStatusWho(1);
            friendGame.setNumberWho(number);
            if (friendGame.getStatusTo() != 0) {
                friendGame.setGameStatus(false);
            }
        }

        if (!friendGame.getGameStatus()) {
            if (friendGame.getStatusWho() == 2) {
                friendGame.setStatusTo(5);
            } else {
                if (friendGame.getStatusTo() == 2) {
                    friendGame.setStatusWho(5);
                } else {
                    if (friendGame.getNumberTo() > friendGame.getNumberWho()) {
                        friendGame.setStatusTo(3);  //win
                        friendGame.setStatusWho(4); //lose
                    } else {
                        if (friendGame.getNumberTo() < friendGame.getNumberWho()) {
                            friendGame.setStatusTo(4);
                            friendGame.setStatusWho(3);
                        } else {                //debt
                            friendGame.setStatusTo(5);
                            friendGame.setStatusWho(5);
                        }
                    }
                }
            }
        }

        friendGamesRepository.save(friendGame);
        return true;
    }


    @GetMapping("/update/{uuid}/{id_game}/reward")
    public long getRewardAH(@PathVariable String uuid,
                            @PathVariable int id_game) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        User user = userRepository.getById(myId);
        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return 0;

        int difficulty = friendGame.getDifficulty();
        long reward = 2 * friendGame.getCost();
        if (myId == friendGame.getIdUserTo()) {     //I AM TO
            if (friendGame.getStatusTo() == 3) {    //AND I WIN
                win(user, difficulty, reward);        //UPDATE STATISTICS
            }
            friendGame.setStatusTo(6);              //SET MY STATUS TO HIDE

        } else {                                   //I AM TO
            if (friendGame.getStatusWho() == 3) {   //AND I WIN
                win(user, difficulty, reward);        //UPDATE STATISTICS
            }
            friendGame.setStatusWho(6);             //SET MY STATUS TO HIDE
        }

        checkDeletingGame(id_game, friendGame);
        userRepository.save(user);
        return reward;
    }

    //++++++++++
    @GetMapping("/update/{uuid}/{id_game}/get_bet")
    public long getBetBackAH(@PathVariable String uuid,
                             @PathVariable int id_game) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        User user = userRepository.getById(myId);
        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return 0;
        long cost = friendGame.getCost();

        if (myId == friendGame.getIdUserTo()) {
            int myAnswers = friendGame.getNumberTo();
            if (myAnswers != 0)
                user.addUser_money(cost);           //RETURN MY BET IF I ANSWERED ANYTHING
            friendGame.setStatusTo(6);              //UPDATE MY STATUS IF IM TO
        } else {
            int myAnswers = friendGame.getNumberWho();
            if (myAnswers != 0)
                user.addUser_money(cost);           //RETURN MY BET IF I ANSWERED ANYTHING
            friendGame.setStatusWho(6);             //UPDATE MY STATUS  IF IM WHO
        }

        checkDeletingGame(id_game, friendGame);
        userRepository.save(user);
        return cost;
    }


    @GetMapping("/update/{uuid}/{id_game}/lose")
    public boolean getLoseAH(@PathVariable String uuid,
                             @PathVariable int id_game) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        User user = userRepository.getById(myId);
        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return false;

        int difficulty = friendGame.getDifficulty();

        if (myId == friendGame.getIdUserTo()) {     //IM TO
            if (friendGame.getStatusTo() == 4) {    //AND I LOSE
                lose(user, difficulty);             //UPDATE STATISTICS
            }
            friendGame.setStatusTo(6);              //UPDATE STATUS

        } else {                                    //IM TO
            if (friendGame.getStatusWho() == 4) {   //AND I LOSE
                lose(user, difficulty);             //UPDATE STATISTICS
            }
            friendGame.setStatusWho(6);             //UPDATE STATUS
        }

        checkDeletingGame(id_game, friendGame);

        userRepository.save(user);
        return true;
    }


    //++++
    @GetMapping("/update/{uuid}/{id_game}/decline")
    public boolean declineFriendGame(@PathVariable String uuid,
                                     @PathVariable int id_game) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();

        FriendGames friendGame = friendGamesRepository.findFriendGamesById(id_game);
        if (friendGame.getIdUserTo() != myId && friendGame.getIdUserWho() != myId) return false;

        if (myId == friendGame.getIdUserTo()) {
            friendGame.setStatusTo(2);              //SET MY STATUS AS DECLINED
            friendGame.setStatusWho(5);         //SET FRIEND STATUS AS  DEBT
        } else {
            friendGame.setStatusWho(2);
            friendGame.setStatusTo(5);
        }
        checkDeletingGame(id_game, friendGame);
        friendGamesRepository.save(friendGame);
        return true;
    }

    private void checkDeletingGame(@PathVariable int id_game, FriendGames friendGame) {
        if ((friendGame.getStatusTo() == 6 && friendGame.getStatusWho() == 6)
                || (friendGame.getStatusTo() == 2 && friendGame.getStatusWho() == 2)
                || (friendGame.getStatusTo() == 2 && friendGame.getStatusWho() == 6)
                || (friendGame.getStatusTo() == 6 && friendGame.getStatusWho() == 2)) {
            friendGame.setGameStatus(false);        //SET GAME STATUS TO FALSE
            friendGamesRepository.delete(friendGame);   //DELETE
            ArrayList<MatchGameQuestions> match = matchGameQuestionsRepository.findAllByIdGameAndGameType(id_game, true);
            matchGameQuestionsRepository.deleteAll(match);
        } else {
            friendGamesRepository.save(friendGame);     //OR UPDATE
        }
    }

    private void lose(User user, int difficulty) {
        switch (difficulty) {
            case 0:
                user.updateEasy_loses();
                break;
            case 1:
                user.updateMedium_loses();
                break;
            case 2:
                user.updateHard_loses();
                break;
        }
    }

    private void win(User user, int difficulty, long reward) {
        switch (difficulty) {
            case 0:
                user.updateEasy_winnings(reward);
                break;
            case 1:
                user.updateMedium_winnings(reward);
                break;
            case 2:
                user.updateHard_winnings(reward);
                break;
        }
    }

    private byte[] getBytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private int[] getSevenQuestionIds(ArrayList<Integer> selectedQuestionsID) {
        int[] ids = new int[7];
        Random rand = new Random();
        QuestionController.getSevenQuestionIds(selectedQuestionsID, ids, rand);
        return ids;
    }

    private List<LocaledQuestion> getLocaledQuestions(List<Question> questions, boolean isRussian) {
        ArrayList<LocaledQuestion> localedQuestions = new ArrayList<>();
        switchLocale(questions, isRussian, localedQuestions);
        return localedQuestions;
    }

}
