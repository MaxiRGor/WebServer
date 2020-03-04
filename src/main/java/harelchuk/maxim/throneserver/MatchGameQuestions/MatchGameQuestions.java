package harelchuk.maxim.throneserver.MatchGameQuestions;

import javax.persistence.*;

@Entity
@Table(name = "match_game_questions")
public class MatchGameQuestions {
    public MatchGameQuestions() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_type")
    private Boolean gameType;

    @Column(name = "id_game")
    private int idGame;

    @Column(name = "id_question")
    private int idQuestion;

    public MatchGameQuestions(int idGame, boolean gameType, int idQuestion) {
        this.idGame = idGame;
        this.gameType = gameType;
        this.idQuestion = idQuestion;
    }

    public int getIdGame() {
        return idGame;
    }

    public Boolean getGameType() {
        return gameType;
    }

    public int getIdQuestion() {
        return idQuestion;
    }
}
