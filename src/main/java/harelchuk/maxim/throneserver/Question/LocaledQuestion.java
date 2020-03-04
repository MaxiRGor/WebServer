package harelchuk.maxim.throneserver.Question;

public class LocaledQuestion {

    private int id;

    private String text;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private String answerFour;

    private int rightAnswer;

    private int level;

    private int difficulty;

    private boolean inBook;

    private boolean inSerial;

    private int category;

    LocaledQuestion(int id, String text, String answerOne, String answerTwo, String answerThree, String answerFour, int rightAnswer, int level, int difficulty, boolean inBook, boolean inSerial, int category) {
        this.id = id;
        this.text = text;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.rightAnswer = rightAnswer;
        this.level = level;
        this.difficulty = difficulty;
        this.inBook = inBook;
        this.inSerial = inSerial;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getLevel() {
        return level;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isInBook() {
        return inBook;
    }

    public boolean isInSerial() {
        return inSerial;
    }

    public int getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
