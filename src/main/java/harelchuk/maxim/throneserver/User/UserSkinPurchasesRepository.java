package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkinPurchasesRepository extends JpaRepository<UserSkinsPurchases, Integer> {
    UserSkinsPurchases findDistinctByUserId(int userId);
}
