package harelchuk.maxim.throneserver.Answer;

public class AnswerNotFoundException extends RuntimeException {
    AnswerNotFoundException(int id) {
        super("Could not find answer " + id);
    }
}
