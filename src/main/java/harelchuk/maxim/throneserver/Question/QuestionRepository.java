package harelchuk.maxim.throneserver.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByIdIn(int[] ids);

    @Query("select id from Question where level = ?1 and inSerial = ?2")
    ArrayList<Integer> findByLevelSerial(int level, boolean in_serial);

    @Query("select id from Question where difficulty = ?1")
    ArrayList<Integer> findManyByDifficulty(int diff);


    @Query("select id from Question where difficulty = ?1 and inBook = ?2")
    ArrayList<Integer> findManyByDifficultyBook(int difficulty, boolean in_book);


    @Query("select id from Question where difficulty = ?1 and inSerial = ?2")
    ArrayList<Integer> findManyByDifficultySerial(int difficulty, boolean in_serial);

}
