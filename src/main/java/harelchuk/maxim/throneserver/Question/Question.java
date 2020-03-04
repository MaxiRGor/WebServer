package harelchuk.maxim.throneserver.Question;

import javax.persistence.*;

@Entity
//@Table(name = "questions")
@Table(name = "new_questions")
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "question_ru")
    private String questionRu;

    @Column(name = "question_en")
    private String questionEn;

    @Column(name = "answer_1_ru")
    private String answer1Ru;

    @Column(name = "answer_1_en")
    private String answer1En;

    @Column(name = "answer_2_ru")
    private String answer2Ru;

    @Column(name = "answer_2_en")
    private String answer2En;

    @Column(name = "answer_3_ru")
    private String answer3Ru;

    @Column(name = "answer_3_en")
    private String answer3En;

    @Column(name = "answer_4_ru")
    private String answer4Ru;

    @Column(name = "answer_4_en")
    private String answer4En;

    @Column(name = "category")
    private int category;

    @Column(name = "level")
    private int level;

    @Column(name = "in_book")
    private boolean inBook;

    @Column(name = "in_serial")
    private boolean inSerial;

    @Column(name = "right_answer")
    private int rightAnswer;

    @Column(name = "difficulty")
    private int difficulty;

    public Integer getId() {
        return id;
    }

    public String getQuestionRu() {
        return questionRu;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public String getAnswer1Ru() {
        return answer1Ru;
    }

    public String getAnswer1En() {
        return answer1En;
    }

    public String getAnswer2Ru() {
        return answer2Ru;
    }

    public String getAnswer2En() {
        return answer2En;
    }

    public String getAnswer3Ru() {
        return answer3Ru;
    }

    public String getAnswer3En() {
        return answer3En;
    }

    public String getAnswer4Ru() {
        return answer4Ru;
    }

    public String getAnswer4En() {
        return answer4En;
    }

    public int getCategory() {
        return category;
    }

    public int getLevel() {
        return level;
    }

    public boolean isInBook() {
        return inBook;
    }

    public boolean isInSerial() {
        return inSerial;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
