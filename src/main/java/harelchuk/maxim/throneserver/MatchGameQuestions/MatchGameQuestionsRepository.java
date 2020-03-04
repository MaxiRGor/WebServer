package harelchuk.maxim.throneserver.MatchGameQuestions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MatchGameQuestionsRepository extends JpaRepository<MatchGameQuestions, Integer> {
    ArrayList<MatchGameQuestions> findAllByIdGameAndGameType(int idGame, boolean gameType);
}
