package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsersMultiRatingRepository extends JpaRepository<UsersMultiRating, Integer> {
    UsersMultiRating findDistinctByIdUser(int id);

    ArrayList<UsersMultiRating> findAllByOrderByRatingDesc();

    ArrayList<UsersMultiRating> findAll();
}
