package harelchuk.maxim.throneserver.Answer;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private Integer idAnswer;

    @Column(name = "id_question")
    private int idQuestion;

    @Column(name = "right_answer")
    private int rightAnswer;

    @Column(name = "answer_one")
    private int answerOne;

    @Column(name = "answer_two")
    private int answerTwo;

    @Column(name = "answer_three")
    private int answerThree;

    @Column(name = "answer_four")
    private int answerFour;

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getAnswerOne() {
        return answerOne;
    }

    public int getAnswerTwo() {
        return answerTwo;
    }

    public int getAnswerThree() {
        return answerThree;
    }

    public int getAnswerFour() {
        return answerFour;
    }

    void incrementOne() {
        ++this.answerOne;
    }

    void incrementTwo() {
        ++this.answerTwo;
    }

    void incrementThree() {
        ++this.answerThree;
    }

    void incrementFour() {
        ++this.answerFour;
    }
/*
    public void setAnswerOne(int answerOne) {
        this.answerOne = answerOne;
    }

    public void setAnswerTwo(int answerTwo) {
        this.answerTwo = answerTwo;
    }

    public void setAnswerThree(int answerThree) {
        this.answerThree = answerThree;
    }

    public void setAnswerFour(int answerFour) {
        this.answerFour = answerFour;
    }*/
}
