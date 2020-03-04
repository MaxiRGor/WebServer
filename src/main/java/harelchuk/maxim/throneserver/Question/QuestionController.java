package harelchuk.maxim.throneserver.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

/*    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }*/

    @GetMapping(path = "/difficulty/{difficulty}/{in_book}/{in_serial}/{is_russian}")
    public @ResponseBody
    List<LocaledQuestion> getDifficultyQuestions(@PathVariable("difficulty") int difficulty,
                                                 @PathVariable("in_book") boolean in_book,
                                                 @PathVariable("in_serial") boolean in_serial,
                                                 @PathVariable("is_russian") boolean is_russian) {
        if (in_book && in_serial) {
            return getSevenQuestions(questionRepository.findManyByDifficulty(difficulty), is_russian);
        } else {
            if (in_book) {
                return getSevenQuestions(questionRepository.findManyByDifficultyBook(difficulty, true), is_russian);
            } else {
                return getSevenQuestions(questionRepository.findManyByDifficultySerial(difficulty, true), is_russian);
            }
        }
    }

    public static void getSevenQuestionIds(ArrayList<Integer> selectedQuestionsID, int[] ids, Random rand) {
        for (int i = 0; i < 7; i++) {
            int randomIndex;
            randomIndex = rand.nextInt(selectedQuestionsID.size());
            ids[i] = selectedQuestionsID.get(randomIndex);
            if (selectedQuestionsID.size() > 1) {
                selectedQuestionsID.remove(randomIndex);
            }
        }
    }

    private List<LocaledQuestion> getSevenQuestions(ArrayList<Integer> selectedQuestionsID, boolean isRussian) {
        int[] ids = new int[7];
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int randomIndex = rand.nextInt(selectedQuestionsID.size());
            ids[i] = selectedQuestionsID.get(randomIndex);
            if (selectedQuestionsID.size() > 1) {
                selectedQuestionsID.remove(randomIndex);
            }
        }
        List<Question> questions = questionRepository.findAllByIdIn(ids);
        ArrayList<LocaledQuestion> localedQuestions = new ArrayList<>();
        switchLocale(questions, isRussian, localedQuestions);
        return localedQuestions;
    }

    public static void switchLocale(List<Question> questions, boolean isRussian, ArrayList<LocaledQuestion> localedQuestions) {
        if (isRussian) {
            for (Question question : questions) {
                LocaledQuestion localedQuestion = new LocaledQuestion(question.getId(),
                        question.getQuestionRu(),
                        question.getAnswer1Ru(),
                        question.getAnswer2Ru(),
                        question.getAnswer3Ru(),
                        question.getAnswer4Ru(),
                        question.getRightAnswer(),
                        question.getLevel(),
                        question.getDifficulty(),
                        question.isInBook(),
                        question.isInSerial(),
                        question.getCategory());
                localedQuestions.add(localedQuestion);
            }
        } else {
            for (Question question : questions) {
                LocaledQuestion localedQuestion = new LocaledQuestion(question.getId(),
                        question.getQuestionEn(),
                        question.getAnswer1En(),
                        question.getAnswer2En(),
                        question.getAnswer3En(),
                        question.getAnswer4En(),
                        question.getRightAnswer(),
                        question.getLevel(),
                        question.getDifficulty(),
                        question.isInBook(),
                        question.isInSerial(),
                        question.getCategory());
                localedQuestions.add(localedQuestion);
            }
        }
    }
}
