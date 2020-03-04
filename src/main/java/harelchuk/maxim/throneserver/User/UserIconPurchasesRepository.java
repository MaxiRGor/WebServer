package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIconPurchasesRepository extends JpaRepository<UserIconsPurchases, Integer> {
    UserIconsPurchases findDistinctByUserId(int userID);
}
