package harelchuk.maxim.throneserver.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("/{id_answer}")
    public @ResponseBody
    Answer getAnswers(@PathVariable int id_answer) {
        return answerRepository.findById(id_answer)
                .orElseThrow(() -> new AnswerNotFoundException(id_answer));
    }

    @GetMapping("/update/seven/{id_answers}/{user_answers}")
    public boolean updateAnswer(@PathVariable int[] id_answers,
                             @PathVariable int[] user_answers) {
        List<Answer> answers = answerRepository.findAnswersByIdAnswerIn(id_answers);
        for (int i = 0; i < id_answers.length; i++) {
            switch (user_answers[i]) {
                case 1:
                    answers.get(i).incrementOne();
                    break;
                case 2:
                    answers.get(i).incrementTwo();
                    break;
                case 3:
                    answers.get(i).incrementThree();
                    break;
                case 4:
                    answers.get(i).incrementFour();
                    break;
            }
        }
        answerRepository.saveAll(answers);
        return true;
    }
}
