package harelchuk.maxim.throneserver.GameVariables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GameVariablesRepository extends JpaRepository<GameVariables, Integer> {

    ArrayList<GameVariables> findAllByMultiRatingCostNot(int zero);

    ArrayList<GameVariables> findAllByMultiRatingMinNot(int zero);

    ArrayList<GameVariables> findAllByIconsCostsNot(int zero);

    GameVariables findDistinctById(int firstRow);

}
