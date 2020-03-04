package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSinglePointsRepository extends JpaRepository<UserSinglePoints, Integer> {
    UserSinglePoints getUserSinglePointsByUserId(int id);
}
