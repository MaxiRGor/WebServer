package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {
    UserEmail findDistinctByEmail(String email);
}
