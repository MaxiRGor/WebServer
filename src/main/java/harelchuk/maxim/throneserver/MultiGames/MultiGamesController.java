package harelchuk.maxim.throneserver.MultiGames;

import harelchuk.maxim.throneserver.GameVariables.GameVariablesRepository;
import harelchuk.maxim.throneserver.MatchGameQuestions.MatchGameQuestions;
import harelchuk.maxim.throneserver.MatchGameQuestions.MatchGameQuestionsRepository;
import harelchuk.maxim.throneserver.Question.LocaledQuestion;
import harelchuk.maxim.throneserver.Question.Question;
import harelchuk.maxim.throneserver.Question.QuestionController;
import harelchuk.maxim.throneserver.Question.QuestionRepository;
import harelchuk.maxim.throneserver.User.User;
import harelchuk.maxim.throneserver.User.UserRepository;
import harelchuk.maxim.throneserver.User.UsersMultiRating;
import harelchuk.maxim.throneserver.User.UsersMultiRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.*;

import static harelchuk.maxim.throneserver.Question.QuestionController.switchLocale;

@RestController
@RequestMapping("/games/multi")
public class MultiGamesController {
    private UserRepository userRepository;
    private MultiGamesRepository multiGamesRepository;
    private QuestionRepository questionRepository;
    private MatchGameQuestionsRepository matchGameQuestionsRepository;
    private GameVariablesRepository gameVariablesRepository;
    private UsersMultiRatingRepository multiRatingRepository;

    @Autowired
    MultiGamesController(UserRepository userRepository, MultiGamesRepository multiGamesRepository,
                         QuestionRepository questionRepository, MatchGameQuestionsRepository matchGameQuestionsRepository,
                         GameVariablesRepository gameVariablesRepository, UsersMultiRatingRepository multiRatingRepository) {
        this.multiGamesRepository = multiGamesRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.matchGameQuestionsRepository = matchGameQuestionsRepository;
        this.gameVariablesRepository = gameVariablesRepository;
        this.multiRatingRepository = multiRatingRepository;
    }

    @GetMapping("/get/{uuid}")
    public @ResponseBody
    ArrayList<MultiGames> getMultiGames(@PathVariable String uuid) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<MultiGames> games = multiGamesRepository.findAllByUserFirstIdOrUserSecondIdOrderByCreateTimeDesc(myId, myId);
        ArrayList<MultiGames> suitable = new ArrayList<>();
        int myStatus;
        for (MultiGames game : games) {
            if (game.getUserFirstId() == myId) {                  // IM FIRST
                myStatus = game.getUserFirstStatus();
                if (myStatus != 0 && myStatus != 1 && myStatus != 6) {
                    suitable.add(game);                       // I DIDN'T HIDE OR DECLINE OR NOT PLAYED OR IN GAME
                }
            } else {                                                          // IM SECOND
                myStatus = game.getUserSecondStatus();
                if (myStatus != 0 && myStatus != 1 && myStatus != 6) {
                    suitable.add(game);                       // I DIDN'T HIDE OR DECLINE OR NOT PLAYED OR IN GAME
                }
            }
        }
        return suitable;
    }

    @GetMapping("/play/{uuid}")
    public int multiGameId(@PathVariable String uuid) {
        User me = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        int myId = me.getId();
        String myUnique = me.getUniqueNumber();
        String myName = me.getName();

        int[] ranks = new int[]{1, 1};
        try {
            ranks[0] = multiRatingRepository.findDistinctByIdUser(myId).getRank();
        } catch (Exception e) {
            System.out.println("mistake");

        }
        if (ranks[0] != 1) ranks[1] = ranks[0] - 1;

        int gameId;
        //int finalRank;

        ArrayList<MultiGames> games = multiGamesRepository
                .findAllByGameRankInAndUserFirstIdNotAndUserSecondStatusOrderByCreateTimeDesc
                        (ranks, myId, 0);

        if (games.size() > 0) { //if game was created previously
            MultiGames game = games.get(games.size() - 1);
            game.setUserSecondId(myId);
            game.setUserSecondUnique(myUnique);
            game.setUserSecondName(myName);
            game.setUserSecondStatus(1);    // in play
            multiGamesRepository.save(game);
            gameId = game.getId();
        } else {
            MultiGames game = new MultiGames(ranks[0], new Date().getTime(), myId, myUnique, myName);
            multiGamesRepository.save(game);
            gameId = game.getId();
            ArrayList<Integer> questionIds;
            questionIds = questionRepository.findByLevelSerial(ranks[0], true);
            int[] ids = getSevenQuestionIds(questionIds);
            ArrayList<MatchGameQuestions> openGames = new ArrayList<>();
            for (int id : ids) {
                openGames.add(new MatchGameQuestions(gameId, false, id));
            }
            matchGameQuestionsRepository.saveAll(openGames);
        }
        return gameId;
    }

    @GetMapping("/enter/{uuid}/{id_game}/{is_russian}")
    public @ResponseBody
    List<LocaledQuestion> enterMultiGame(@PathVariable String uuid,
                                         @PathVariable int id_game,
                                         @PathVariable boolean is_russian) {

        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        MultiGames multiGame = multiGamesRepository.findDistinctById(id_game);

        if (multiGame.getUserFirstId() != myId && multiGame.getUserSecondId() != myId) return null;
        ArrayList<MatchGameQuestions> match = matchGameQuestionsRepository.findAllByIdGameAndGameType(id_game, false);
        int[] ids = new int[match.size()];
        for (int i = 0; i < match.size(); i++) {
            ids[i] = match.get(i).getIdQuestion();
        }
        return getLocaledQuestions(questionRepository.findAllByIdIn(ids), is_russian);
    }

    private List<LocaledQuestion> getLocaledQuestions(List<Question> questions, boolean isRussian) {
        ArrayList<LocaledQuestion> localedQuestions = new ArrayList<>();
        switchLocale(questions, isRussian, localedQuestions);
        return localedQuestions;
    }

    @GetMapping("/update/{uuid}/{id_game}/{number}")
    public boolean updateMultiGameAnswersCountAndSelectWinner(@PathVariable String uuid,
                                                              @PathVariable int id_game,
                                                              @PathVariable int number) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        MultiGames game = multiGamesRepository.findDistinctById(id_game);
        if (game.getUserFirstId() != myId && game.getUserSecondId() != myId) return false;

        if (myId == game.getUserFirstId()) {
            game.setUserFirstStatus(2);
            game.setUserFirstCount(number);
        } else {
            game.setUserSecondStatus(2);
            game.setUserSecondCount(number);
        }

        if (game.getUserFirstStatus() == 2 && game.getUserSecondStatus() == 2) {
            int comparable = game.getUserFirstCount() - game.getUserSecondCount();
            int gameRank = game.getGameRank();
            int cost = gameVariablesRepository.findAllByMultiRatingCostNot(0).get(gameRank - 1).getMultiRatingCost();
            if (comparable > 0) {
                UsersMultiRating userRatingFirst = multiRatingRepository.findDistinctByIdUser(game.getUserFirstId());
                userRatingFirst.addRating(cost * 2);
                if (userRatingFirst.getRank() != 10) {
                    if (userRatingFirst.getRating() >=
                            gameVariablesRepository.findAllByMultiRatingMinNot(0).get(userRatingFirst.getRank()/* - 1*/).getMultiRatingMin()) {
                        userRatingFirst.incrementRank();
                    }
                }
                userRatingFirst.addCrystals(gameRank * gameRank * 2);
                userRatingFirst.incrementWinningsAndGames();
                UsersMultiRating ratingSecond = multiRatingRepository.findDistinctByIdUser(game.getUserSecondId());
                ratingSecond.incrementGames();
                multiRatingRepository.save(userRatingFirst);
                multiRatingRepository.save(ratingSecond);
                game.setUserFirstStatus(3);
                game.setUserSecondStatus(4);
            } else {
                if (comparable < 0) {
                    UsersMultiRating ratingFirst = multiRatingRepository.findDistinctByIdUser(game.getUserFirstId());
                    ratingFirst.incrementGames();
                    UsersMultiRating userRatingSecond = multiRatingRepository.findDistinctByIdUser(game.getUserSecondId());
                    userRatingSecond.addRating(cost * 2);
                    if (userRatingSecond.getRank() != 10) {
                        if (userRatingSecond.getRating() >=
                                gameVariablesRepository.findAllByMultiRatingMinNot(0).get(userRatingSecond.getRank()/* - 1*/).getMultiRatingMin()) {
                            userRatingSecond.incrementRank();
                        }
                    }
                    userRatingSecond.addCrystals(gameRank * gameRank * 2);
                    userRatingSecond.incrementWinningsAndGames();
                    multiRatingRepository.save(ratingFirst);
                    multiRatingRepository.save(userRatingSecond);
                    game.setUserFirstStatus(4);
                    game.setUserSecondStatus(3);
                } else {/*comparable==0*/
                    UsersMultiRating userRatingFirst = multiRatingRepository.findDistinctByIdUser(game.getUserFirstId());
                    UsersMultiRating userRatingSecond = multiRatingRepository.findDistinctByIdUser(game.getUserSecondId());
                    userRatingFirst.incrementWinningsAndGames();
                    userRatingFirst.addRating(cost);
                    if (userRatingFirst.getRank() != 10) {
                        if (userRatingFirst.getRating() >=
                                gameVariablesRepository.findAllByMultiRatingMinNot(0).get(userRatingFirst.getRank()/* - 1*/).getMultiRatingMin()) {
                            userRatingFirst.incrementRank();
                        }
                    }
                    userRatingFirst.addCrystals(gameRank * gameRank);
                    userRatingSecond.incrementWinningsAndGames();
                    userRatingSecond.addRating(cost);
                    if (userRatingSecond.getRank() != 10) {
                        if (userRatingSecond.getRating() >=
                                gameVariablesRepository.findAllByMultiRatingMinNot(0).get(userRatingSecond.getRank()/* - 1*/).getMultiRatingMin()) {
                            userRatingSecond.incrementRank();
                        }
                    }
                    userRatingSecond.addCrystals(gameRank * gameRank);
                    multiRatingRepository.save(userRatingFirst);
                    multiRatingRepository.save(userRatingSecond);
                    game.setUserFirstStatus(5);
                    game.setUserSecondStatus(5);

                }
            }
        }
        multiGamesRepository.save(game);
        return true;
    }

    @GetMapping("/update/{uuid}/{id_game}/hide")
    public boolean hideMultiGame(@PathVariable String uuid,
                                 @PathVariable int id_game) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        MultiGames game = multiGamesRepository.findDistinctById(id_game);
        if (game.getUserFirstId() != myId && game.getUserSecondId() != myId) return false;

        if (myId == game.getUserFirstId()) {
            game.setUserFirstStatus(6);
        } else {
            game.setUserSecondStatus(6);
        }

        if (game.getUserFirstStatus() == 6 && game.getUserSecondStatus() == 6) {
            multiGamesRepository.delete(game);
            ArrayList<MatchGameQuestions> match = matchGameQuestionsRepository.findAllByIdGameAndGameType(id_game, false);
            matchGameQuestionsRepository.deleteAll(match);
        } else {
            multiGamesRepository.save(game);
        }
        return true;
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


}


